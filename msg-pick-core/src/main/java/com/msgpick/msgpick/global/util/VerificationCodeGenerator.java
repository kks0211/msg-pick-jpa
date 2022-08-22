package com.msgpick.msgpick.global.util;

import org.apache.commons.lang3.RandomUtils;

public class VerificationCodeGenerator {

    // 기본 4자리의 난수 생성
    public static int randomNumber() {
        int max = 9999;
        int min = 1000;

        return RandomUtils.nextInt(min, max);
    }

    public static int randomNumber(int size) {
        int max = 9999;
        int min = 1000;

        if (size == 5) {
            max = 99999;
            min = 10000;
        } else if (size == 6) {
            max = 999999;
            min = 100000;
        }

        return RandomUtils.nextInt(min, max);
    }

}
