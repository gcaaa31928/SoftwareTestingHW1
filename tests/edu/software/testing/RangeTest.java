package edu.software.testing;


import org.jfree.data.Range;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Red on 2016/3/20.
 */
public class RangeTest {

    @Test
    public void testNotIntersectsForMethodIntersects() {
        Range r = new Range(1, 2);
        assertEquals(false, r.intersects(0, 0.5));
        r = new Range(3, 4);
        assertEquals(false, r.intersects(1, 2));
        r = new Range(1, 1);
        assertEquals(false, r.intersects(0, 1));
    }

    @Test
    public void testIntersectsForMethodIntersects() {
        Range r = new Range(1, 3);
        assertEquals(true, r.intersects(0, 2));
        r = new Range(100, 101);
        assertEquals(true, r.intersects(100, 100.5));
    }

    @Test
    public void testUseConstrainForMethodConstrain() {
        Range r = new Range(1, 2);
        assertEquals(2, r.constrain(3), 0);
        r = new Range(3, 4);
        assertEquals(4, r.constrain(6), 0);
        r = new Range(1, 1);
        assertEquals(1, r.constrain(-1), 0);
    }

    @Test
    public void testNonUsedConstrainForMethodConstrain() {
        Range r = new Range(1, 2);
        assertEquals(1.5, r.constrain(1.5), 0);
        r = new Range(3, 4);
        assertEquals(3.1, r.constrain(3.1), 0);
        r = new Range(1, 1);
        assertEquals(1, r.constrain(1), 0);
    }

    @Test
    public void testWorkedCombineForMethodCombine() {
        Range r = new Range(1, 2);
        Range r1 = new Range(2, 3);
        Range r2 = new Range(3, 4);
        Range r3 = new Range(4, 5);
        Range actual = Range.combine(r, r1);
        assertEquals(actual.getLowerBound(), 1, 0);
        assertEquals(actual.getUpperBound(), 3, 0);
        actual = Range.combine(actual, r2);
        assertEquals(actual.getLowerBound(), 1, 0);
        assertEquals(actual.getUpperBound(), 4, 0);
        actual = Range.combine(actual, r3);
        assertEquals(actual.getLowerBound(), 1, 0);
        assertEquals(actual.getUpperBound(), 5, 0);

    }

    @Test
    public void testNoneWorkedCombineForMethodCombine() {
        Range r = new Range(1, 2);
        Range r1 = new Range(1, 1);
        Range actual = Range.combine(r, r1);
        assertEquals(actual.getLowerBound(), 1, 0);
        assertEquals(actual.getUpperBound(), 2, 0);
    }

    @Test
    public void testNegativeRangeForMethodExpand() {
        Range r = new Range(-3, -1);
        Range actual = Range.expand(r, 1, 1);
        assertEquals(-5.0, actual.getLowerBound(), 0);
        assertEquals(1, actual.getUpperBound(), 0);
        actual = Range.expand(r, -1, -1);
        assertEquals(-2.0, actual.getLowerBound(), 0);
        assertEquals(-2.0, actual.getUpperBound(), 0);
    }

    @Test
    public void testPositiveRangeForMethodExpand() {
        Range r = new Range(1, 3);
        Range actual = Range.expand(r, 1, 1);
        assertEquals(-1.0, actual.getLowerBound(), 0);
        assertEquals(5, actual.getUpperBound(), 0);
        actual = Range.expand(r, -1, -1);
        assertEquals(2.0, actual.getLowerBound(), 0);
        assertEquals(2.0, actual.getUpperBound(), 0);
    }


    @Test
    public void testSameRangeForMethodToString() {
        for (int i = 0; i < 100; i++) {
            Range r = new Range(i, i);
            assertEquals(r.toString(), "Range[" + Double.toString(i) + "," + Double.toString(i) + "]");
        }
    }

    @Test
    public void testCrossRangeForMethodExpand() {
        for (int i = 0; i < 100; i++) {
            for (int j = i; j < 100; j++) {
                Range r = new Range(i, j);
                assertEquals(r.toString(), "Range[" + Double.toString(i) + "," + Double.toString(j) + "]");
            }
        }
    }
}
