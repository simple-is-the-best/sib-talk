package com.sib.utils;

public class ObjectValidator {

    public static boolean isNil(Object o) {
        return o == null || o.toString().isEmpty();
    }
}
