package com.example.loadtestdb.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RandomStringTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    void checkRandomStringLength(int length) {
        assertEquals(RandomString.random(length).length(), length);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -3, -5})
    void errorWhenRandomStringLengthBelowZero(int length) {
        assertThrows(IllegalStateException.class, () -> RandomString.random(length));
    }

}