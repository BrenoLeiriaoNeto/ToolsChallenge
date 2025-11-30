# 🐾 Payment Challenge API

API de pagamentos com **solicitação**, **consulta** e **estorno** de transações, seguindo **Clean Architecture** + **CQRS** (Command Query Responsibility Segregation).

[![Java](https://img.shields.io/badge/Java-25-ED8B00?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-6DB33F?logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-SQL_Server-2496ED?logo=docker&logoColor=white)](https://hub.docker.com/_/microsoft-mssql-server)

---

## 🚀 Tecnologias

- **Linguagem:** Java 25  
- **Framework:** Spring Boot 4.0.0  
- **Persistência:** Spring Data JPA + Hibernate  
- **Banco de dados:** SQL Server (container Docker)  
- **Testes de API:** Insomnia / Postman  
- **Arquitetura:** Clean Architecture + CQRS  

---

## 🗄️ Banco de Dados – SQL Server via Docker

```bash
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Toolschallenge@2025" \
  -p 1433:1433 --name sqlserver-payment \
  -d mcr.microsoft.com/mssql/server:2022-latest
```

### Dados de conexão:
- **Host:** localhost
- **Porta:** 1433
- **Usuário:** sa
- **Senha:** Toolschallenge@2025
- **Database:** payment (criado automaticamente pelo Hibernate, mas pode ser necessário criar antes para não haver problemas)

---

## ⚙️ Configurações principais
### application.properties
```
spring.application.name=payment
spring.messages.basename=messages
spring.messages.encoding=UTF-8

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=payment;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=Toolschallenge@2025

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

### messages.properties
```
jakarta.validation.constraints.NotBlank.message=Não pode estar em branco
jakarta.validation.constraints.NotNull.message=Não pode ser nulo
jakarta.validation.constraints.Positive.message=Deve ser maior que zero
jakarta.validation.constraints.Pattern.message=Formato inválido
```

## 📂 Estrutura do Projeto
- **core/domain ->** entidades, enums, exceptions, value objects
- **core/application ->** contratos, mapeadores, utils, serviços
- **infrastructre/persistence ->** Repositórios Jpa (Command e Query)
- **presentation ->** Controllers e Middlewares

### Padroes aplicados
- **Clean Architecture (Arquitetura Limpa)**
- **CQRS (segregação total de comandos e consultas)**

---

## 📌 Regras de Negocio
- **Idempotência:** chave única ```id``` enviada pelo cliente
- **Status possíveis:**
	- **AUTORIZADA:** transação aceita
	- **NEGADA:** saldo insuficiente
	- **CANCELADA:** estorno realizado

- **Formas de pagamento:**
	- **AVISTA**
	- **PARCELADO_EMISSOR**
	- **PARCELADO_LOJA**

- Pagamentos à vista entram como pagos imediatamente (vencimento +30 dias)
- **Regra para status NEGADA:** valores acima de **R$ 200,00** são automaticamente **NEGADOS**

---

## 🔗 Endpoints
Método   | Rota | Descrição
------- | ------ | -----
POST | /api/pagamentos | Solicita um novo pagamento
GET | /api/pagamentos | Busca todos os pagamentos
GET | /api/pagamentos/{id} | Busca um pagamento especifico
GET | /api/estorno/{id} | Consulta um pagamento estornado
PUT | /api/estorno/{id} | Estorna um pagamento

---

## 📄 Exemplos de JSON de solicitação de pagamento
**Pagamento AUTORIZADO (valor < R$ 200)**
```json
{
  "transacao": {
    "cartao": "4444555555551234",
    "id": "0000100023568900001",
    "descricao": {
      "valor": "120.00",
      "dataHora": "27/11/2025 19:30:00",
      "estabelecimento": "PetShop Mundo Cão"
    },
    "formaPagamento": {
      "tipo": "AVISTA",
      "parcelas": "1"
    }
  }
}
```

**Pagamento NEGADO (valor > R$ 200)**
```json
{
  "transacao": {
    "cartao": "4444555555551234",
    "id": "0000100023568900001",
    "descricao": {
      "valor": "520.00",
      "dataHora": "27/11/2025 19:30:00",
      "estabelecimento": "PetShop Mundo Cão"
    },
    "formaPagamento": {
      "tipo": "AVISTA",
      "parcelas": "1"
    }
  }
}
```

**Pagamento Parcelado**
```json
{
  "transacao": {
    "cartao": "4444555555551234",
		"id": "0000100023568900051",
    "descricao": {
      "valor": "180.00",
      "dataHora": "27/11/2025 19:30:00",
      "estabelecimento": "PetShop Mundo Cão"
    },
    "formaPagamento": {
      "tipo": "PARCELADO LOJA",
      "parcelas": "3"
    }
  }
}
```

---

## ✅ Testes realizados (Insomnia)
- **Pagamento autorizado**
- **Solicitação de pagamento com mesmo ID -> erro esperado**
- **Solicitação de pagamento AVISTA com número de parcelas diferente de 1 -> erro esperado**
- **Pagamento negado (valor > R$ 200)**
- **Pagamento parcelado (loja/emissor)**
- **Estorno de pagamento autorizado**
- **Estorno duplicado -> erro esperado**
- **Estorno de pagamento já negado -> erro esperado**
- **Busca de todos os pagamentos -> Retorna mensagem de aviso caso não houver pagamentos**
- **Consulta de pagamento -> Retorna erro caso não exista o pagamento**
- **Consulta de pagamento estornado -> Retorna erro caso não exista o pagamento**
- **Datas retornadas no formato dd/MM/yyyy HH:mm:ss**
- **Máscara de cartão para não expor informação sensível**

---

## ⚠️ Observações Importantes
- Schema gerado automaticamente pelo Hibernate (ddl-auto=update)
- Dialeto: org.hibernate.dialect.SQLServerDialect
- Senha exposta apenas para o desafio (em produção, use variáveis de ambiente ou secret manager)
- Portabilidade pronta para outros SGBD SQL
