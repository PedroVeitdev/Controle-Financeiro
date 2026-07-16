package controle_financeiro.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Formatador {
    private static final Locale PT_BR = new Locale("pt", "BR");

    public static String formatarMoeda(double valor) {
        return NumberFormat.getCurrencyInstance(PT_BR).format(valor);
    }
}