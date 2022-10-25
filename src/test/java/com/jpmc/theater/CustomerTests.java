package com.jpmc.theater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTests {

    private Customer john1;
    private Customer john2;
    private Customer jim;

    @BeforeEach
    void setUp() {
        john1 = new Customer("John", "id-12345");
        john2 = new Customer("John", "id-12345");
        jim = new Customer("Jim", "id-678910");
    }

    @Test
    public void equalsTest() {
        assertEquals(john1, john2);
        assertEquals(john1.hashCode(), john2.hashCode());
        assertTrue(john1.equals(john2));
    }

    @Test
    public void notEqualsTest() {
        assertNotEquals(john1, jim);
        assertNotEquals(john1.hashCode(), jim.hashCode());
        assertFalse(john1.equals(jim));
        assertFalse(john1.equals(null));
    }

    @Test
    public void toStringTest() {
        assertEquals("name: John", john1.toString());
    }
}