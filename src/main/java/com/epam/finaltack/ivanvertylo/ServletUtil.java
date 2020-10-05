package com.epam.finaltack.ivanvertylo;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ServletUtil {

    public static String getUTF8(HttpServletRequest request, String parameter) {
        String item = request.getParameter(parameter);
        byte[] bytes = item.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
