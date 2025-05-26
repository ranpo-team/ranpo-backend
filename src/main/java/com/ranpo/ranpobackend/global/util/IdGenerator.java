package com.ranpo.ranpobackend.global.util;

import java.util.UUID;

public class IdGenerator {

    public static String generateUuidUid() {
        return "u" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
