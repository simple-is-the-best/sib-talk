package com.sib.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectValidatorTest {

    @Test
    void objectValidatorNullTest() {
        Object sut = null;

        boolean actual = ObjectValidator.isNil(sut);

        assertTrue(actual);
    }

    @Test
    void objectValidatorEmptyTest() {
        String sut = "";

        boolean actual = ObjectValidator.isNil(sut);

        assertTrue(actual);
    }

    @Test
    void objectValidatorNotNullTest() {
        Object sut = new Object();

        boolean actual = ObjectValidator.isNil(sut);

        assertFalse(actual);
    }

    @Test
    void objectValidatorNotEmptyTest() {
        String sut = "any";

        boolean actual = ObjectValidator.isNil(sut);

        assertFalse(actual);
    }
}