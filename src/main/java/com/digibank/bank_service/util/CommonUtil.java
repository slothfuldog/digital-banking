package com.digibank.bank_service.util;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class CommonUtil {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    public static String generateShortId() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        String timestamp = formatter.format(now);

        byte[] randomBytes = new byte[6];
        secureRandom.nextBytes(randomBytes);

        String randomPart = base64Encoder.encodeToString(randomBytes).substring(0, 2);

        return timestamp + randomPart;
    }
}
