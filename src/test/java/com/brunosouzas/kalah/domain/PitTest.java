package com.brunosouzas.kalah.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PitTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addStone() {
        Pit pit = new Pit(1, 0);
        pit.addStone(2);
        assertEquals("Stones number should be 2", 2, pit.getStones().intValue());
    }

    @Test
    public void capture() {
        Pit pit = new Pit(1, 10);
        int stones = pit.capture();
        assertEquals("Stones captured should be 10", 10, stones);
        assertEquals("Stones remaining should be 10", 0, pit.getStones().intValue());
    }
}