package com.msgpick.msgpick.global.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class TokenGenerator {
    private static final int TOKEN_LENGTH = 20;

    public static String randomCharacter(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomCharacterWithPrefix(String prefix) {
        return prefix + randomCharacter(TOKEN_LENGTH - prefix.length());
    }

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        String[] tokens = uuid.toString().split("-");
        return tokens[2] + tokens[1] + tokens[0] + tokens[3] + tokens[4];
    }

}
