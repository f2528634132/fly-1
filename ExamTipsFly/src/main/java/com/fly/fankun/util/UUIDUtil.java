package com.fly.fankun.util;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("第" + i + "个=" + UUIDUtil.getUUID());
        }
    }
}
