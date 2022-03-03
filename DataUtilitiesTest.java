package org.jfree.data.test;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

public class DataUtilitiesTest{
    Mockery mockClass=new Mockery();
    final Values2D table=mockClass.mock(Values2D.class);

    // equal
	@Test
	public void checkTwoArraysEqual() {
		double array[][] = new double[2][2];
		array[0][0] = 1d;
		array[0][1] = 2d;
		array[1][0] = 3d;
		array[1][1] = 4d;
		double doubleArray[][] = {
				{1d,2d},
				{3d,4d}
		};
		assertTrue(DataUtilities.equal(array, doubleArray));
	}
	 
	@Test
	public void checkTwoArraysEqualNull() {
		double array[][] = null;
		assertTrue(DataUtilities.equal(null, array));
	}
	
	@Test
	public void checkTwoArraysEqualNaN() {
		double array[][] = {
				{Math.log(-1), Math.log(-1)}
		};
		double array2[][] = {
				{Math.sqrt(-1), Math.sqrt(-1)}
		};
		assertTrue(DataUtilities.equal(array2, array));
	}
	
	@Test
	public void checkTwoArraysEqualEmpty() {
		double array[][] = {	 
		};
		double array2[][] = {
		};
		assertTrue(DataUtilities.equal(array2, array));
	}
	
	@Test
	public void checkTwoArraysNotEqual() {
		double array[][] = {	
				{1,2,3,4,5}
		};
		double array2[][] = {
				{1,2,3,4},
				{5}
		};
		assertFalse(DataUtilities.equal(array2, array));
	}
    
	@Test
    public void checkTwoArraysEqualBothNull() {
        double array[][] = null;
        double array2[][] = null;
        assertTrue(DataUtilities.equal(array, array2));
    }
	
    @Test
    public void checkTwoArraysEqualOneArrayNull() {
        double array[][] = null;
        double array2[][] = {
                {1,2,3,4},
                {5}
        };
        assertFalse(DataUtilities.equal(array, array2));
    }
    
    @Test
    public void checkTwoArrayEqualSecondArrayNull() {
        double array[][] = {{1,2,3,4}};
        double array2[][] = null;
        assertFalse(DataUtilities.equal(array, array2));
    }
    
    @Test
    public void checkTwoArraysNotEqual2() {
        double array[][] = {    
                {1,2,3,4,5}
        };
        double array2[][] = {
                {4,2,3,4}
        };
        assertFalse(DataUtilities.equal(array2, array));
    }
	
}