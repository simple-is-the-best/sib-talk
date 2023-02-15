package com.sib.utils.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    @Test
    void idGeneratorTest() {
        long before = IdGenerator.nextId();
        long after = IdGenerator.nextId();

        boolean actual = after > before;

        assertTrue(actual);
        assertNotEquals(after, before);
    }
}