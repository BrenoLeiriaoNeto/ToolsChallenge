package com.tools.challenge.payment.core.application.utils;

public class CartaoMaskUtil {
    public static String mascarar(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.length() < 8) {
            return "****";
        }

        String primeiros4 = numeroCartao.substring(0, 4);
        String ultimos4 = numeroCartao.substring(numeroCartao.length() - 4);

        return primeiros4 + "********" + ultimos4;
    }
}
