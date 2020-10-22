package com.epam.finaltack.ivanvertylo;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

public class ServletUtil {

    public static String getUTF8(HttpServletRequest request, String parameter) {
        String item = request.getParameter(parameter);
        byte[] bytes = item.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String[] getUTF8Array(HttpServletRequest request, String parameter) {
        String[] items = request.getParameterValues(parameter);
        for (int i = 0; i < items.length; i++) {
            byte[] bytes = items[i].getBytes(StandardCharsets.ISO_8859_1);
            items[i] = new String(bytes, StandardCharsets.UTF_8);
        }
        return items;
    }
}
