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
	private double[][] values; //for clone() method
	
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
    
    //clone
  //Test of null double[][] argument
  	 @Test(expected = IllegalArgumentException.class)
  	 public void testNullDoubleArray() {
  	     DataUtilities.clone(values);
  	 }
  	 
  	//Test of double[][] with null value argument
      @Test
  	public void testDoubleArrayWithNullValue() {
      	values=new double[2][5];
      	this.values[0]=null;
      	this.values[1][0]=3;
      	this.values[1][1]=4;
      	this.values[1][2]=5;
  		double[][] clone=DataUtilities.clone(values);
  		assertEquals(values[0],clone[0]);
  		assertEquals(values[1][0], clone[1][0], .000000001d);
  		assertEquals(values[1][1], clone[1][1], .000000001d);
  		assertEquals(values[1][2], clone[1][2], .000000001d);
  	}
      
    //Test of double[][] with no null value argument
      @Test
  	public void testDoubleArrayWithNoneNullValue() {
      	values=new double[2][5];
      	this.values[0][1]=1;
      	this.values[0][2]=2;
      	this.values[1][0]=3;
      	this.values[1][1]=4;
      	this.values[1][2]=5;
  		double[][] clone=DataUtilities.clone(values);
  		assertEquals(values[0][1],clone[0][1], .000000001d);
  		assertEquals(values[0][2],clone[0][2], .000000001d);
  		assertEquals(values[0][4],clone[0][4], .000000001d);
  		assertEquals(values[1][0], clone[1][0], .000000001d);
  		assertEquals(values[1][1], clone[1][1], .000000001d);
  		assertEquals(values[1][2], clone[1][2], .000000001d);
  	}
	
      
    //caculateRowTotal(Values2D,int)
      @Test
 	 public void calculateRowTotalForZeroValues() {
 	     // setup
 	     Mockery mockingContext = new Mockery();
 	     final Values2D values = mockingContext.mock(Values2D.class);
 	     mockingContext.checking(new Expectations() {
 	         {
 	        	 one(values).getColumnCount();
 	             will(returnValue(2));
 	             one(values).getValue(0, 0);
 	             will(returnValue(0));
 	             one(values).getValue(0, 1);
 	             will(returnValue(0));
 	             
 	         }
 	     });
 		
 	     double result = DataUtilities.calculateRowTotal(values, 0);
 	     assertEquals(0, result, .000000001d);
 	 }
 	 
 	 @Test
 	 public void calculateRowTotalForTwoPositiveValues() {
 	     // setup
 	     Mockery mockingContext = new Mockery();
 	     final Values2D values = mockingContext.mock(Values2D.class);
 	     mockingContext.checking(new Expectations() {
 	         {
 	        	 one(values).getColumnCount();
 	             will(returnValue(2));
 	          
 	             one(values).getValue(0, 0);
 	             will(returnValue(2.5));
 	             one(values).getValue(0, 1);
 	             will(returnValue(7.5));
 	         }
 	     });
 	   	
 	     double result = DataUtilities.calculateRowTotal(values, 0);
 	     assertEquals(10.0, result, .000000001d);
 	 }
 	 
 	 @Test
 	 public void calculateRowTotalForTwoNegativeValues() {
 	     // setup
 	     Mockery mockingContext = new Mockery();
 	     final Values2D values = mockingContext.mock(Values2D.class);
 	     mockingContext.checking(new Expectations() {
 	         {
 	        	 one(values).getColumnCount();
 	             will(returnValue(2));
 	          
 	             one(values).getValue(0, 0);
 	             will(returnValue(-2.5));
 	             one(values).getValue(0, 1);
 	             will(returnValue(-7.5));
 	         }
 	     });
 	
 	     double result = DataUtilities.calculateRowTotal(values, 0);
 	     assertEquals(-10.0, result, .000000001d);
 	 }
 	 
 	 @Test
 	 public void calculateRowTotalForFourValues() {
 	     // setup
 	     Mockery mockingContext = new Mockery();
 	     final Values2D values = mockingContext.mock(Values2D.class);
 	     mockingContext.checking(new Expectations() {
 	         {
 	        	 one(values).getColumnCount();
 	             will(returnValue(4));
 	          
 	             one(values).getValue(0, 0);
 	             will(returnValue(-2.5));
 	             one(values).getValue(0, 1);
 	             will(returnValue(-7.5));
 	             one(values).getValue(0, 2);
 	             will(returnValue(7.5));
 	             one(values).getValue(0, 3);
 	             will(returnValue(0));
 	         }
 	     });
 	  	
 	     double result = DataUtilities.calculateRowTotal(values, 0);
 	     assertEquals(-2.5, result, .000000001d);
 	 }
 	 
 	 @Test
 	 public void caculateRowTotalWithNullValues() {
 		 Mockery mockingContext = new Mockery();
 	     final Values2D values = mockingContext.mock(Values2D.class);
 	     
 	     mockingContext.checking(new Expectations() {
 	         {
 	        	 one(values).getColumnCount();
 	             will(returnValue(3));
 	          
 	             one(values).getValue(0, 0);
 	             one(values).getValue(0, 1);
 	             will(returnValue(3.0));
 	             one(values).getValue(0, 2);
 	             will(returnValue(7.5));
 	         }
 	     }); 
 	     
 	     double result = DataUtilities.calculateRowTotal(values, 0);
 	     assertEquals(10.5, result, .000000001d);
 	 }
}
