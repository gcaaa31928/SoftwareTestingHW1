package edu.software.testing;


import org.jfree.data.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Red on 2016/3/20.
 */
public class DataUtilitiesTest {

    double[][] _array1 = new double[2][2];
    double[][] _array2 = new double[2][2];


    @Before
    public void before() {

    }

    @Test
    public void testEqualDataForMethodEqual() {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                _array1[row][col] = 1;
                _array2[row][col] = 1;
            }
        }
        assertEquals(true, DataUtilities.equal(_array1, _array2));
    }

    @Test
    public void testNotEqualDataForMethodEqual() {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                _array1[row][col] = 1;
                _array2[row][col] = 2;
            }
        }
        assertEquals(false, DataUtilities.equal(_array1, _array2));
        double[][] inputArray1 = new double[1][2];
        double[][] inputArray2 = new double[1][1];
        assertEquals(false, DataUtilities.equal(inputArray1, inputArray2));
    }

    @Test
    public void testPositiveTotalDataForMethodCalculateColumnTotal() {
        DefaultKeyedValues2D a = new DefaultKeyedValues2D();
        a.setValue(1, 0, 0);
        a.setValue(1, 1, 0);
        assertEquals(2.0, DataUtilities.calculateColumnTotal(a, 0), 0);
    }

    @Test
    public void testNegativeTotalDataForMethodCalculateColumnTotal() {
        DefaultKeyedValues2D a = new DefaultKeyedValues2D();
        a.setValue(1, 0, 0);
        a.setValue(-3, 1, 0);
        assertEquals(-2.0, DataUtilities.calculateColumnTotal(a, 0), 0);
    }

    @Test
    public void testPositiveTotalDataForMethodCalculateRowTotal() {
        DefaultKeyedValues2D a = new DefaultKeyedValues2D();
        a.setValue(1, 0, 0);
        a.setValue(1, 0, 1);
        assertEquals(2.0, DataUtilities.calculateRowTotal(a, 0), 0);
    }

    @Test
    public void testNegativeTotalDataForMethodCalculateRowTotal() {
        DefaultKeyedValues2D a = new DefaultKeyedValues2D();
        a.setValue(1, 0, 0);
        a.setValue(-3, 0, 1);
        assertEquals(-2.0, DataUtilities.calculateRowTotal(a, 0), 0);
    }

    @Test
    public void testEmptyDataForMethodCreateNumberArray() {
        double[] a = new double[0];
        Number[] res = DataUtilities.createNumberArray(a);
        assertEquals(res.length, 0);
    }

    @Test
    public void testNotEmptyDataForMethodCreateNumberArray() {
        double[] a = new double[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }
        Number[] res = DataUtilities.createNumberArray(a);
        for (int i = 0; i < 10; i++) {
            assertEquals(res[i], a[i]);
        }
    }

    @Test
    public void testNormalDataForMethodGetCumulativePercentages() {
        DefaultKeyedValues a = new DefaultKeyedValues();
        double[] expected = {0.0, 0.022222222222222223, 0.06666666666666667, 0.13333333333333333, 0.2222222222222222, 0.3333333333333333, 0.4666666666666667, 0.6222222222222222, 0.8, 1.0};

        for (int i = 0; i < 10; i++) {
            a.setValue(i, (Number) i);
        }
        KeyedValues actual = DataUtilities.getCumulativePercentages(a);
        for (int i = 0; i < 10; i++) {
            assertEquals(actual.getValue(i).doubleValue(), expected[i], 0.00000001);
        }
    }

    @Test
    public void testOneDataForMethodGetCumulativePercentages() {
        DefaultKeyedValues a = new DefaultKeyedValues();
        for (int i = 0; i < 10; i++) {
            if(i==0)
                a.setValue(i, (Number) 1);
            else
                a.setValue(i, (Number) 0);
        }
        KeyedValues actual = DataUtilities.getCumulativePercentages(a);
        for (int i = 0; i < 10; i++) {
            assertEquals(actual.getValue(i).doubleValue(), 1, 0.00000001);
        }
    }
}
