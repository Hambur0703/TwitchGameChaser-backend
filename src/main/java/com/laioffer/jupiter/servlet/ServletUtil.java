package com.laioffer.jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entity.Item;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ServletUtil {
    public static void writeItemMap(HttpServletResponse response,
                                    Map<String, List<Item>> itemMap) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(new ObjectMapper().writeValueAsString(itemMap));
    }

    // Help encrypt the user password before save to the database
    public static String encryptPassword(String userId, String password) throws IOException {
        // Only DigestUtils.md5Hex(password) is not enough because others can check sheet (rainbow dictionary)
        // to parse real password by hash value.
        // UserID is unique, md5Hex(userId + DigestUtils.md5Hex(password)) can avoid the possibility
        // to parse real password by hash value.
        return DigestUtils.md5Hex(userId + DigestUtils.md5Hex(password)).toLowerCase();
    }

}
