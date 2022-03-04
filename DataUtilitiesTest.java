package org.jfree.data;

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
    
    private Mockery mockingContext1 = new Mockery();
    private Values2D values1 = mockingContext1.mock(Values2D.class);
    
	 @Before
	 public void setUpForcalculateColumnTotal() throws Exception{
	     mockingContext1.checking(new Expectations() {
	         {
	             allowing(values1).getColumnCount();
	             will(returnValue(3));
	             allowing(values1).getRowCount();
	             will(returnValue(3));
	             allowing(values1).getValue(0, 0);
	             will(returnValue(7.5));
	             allowing(values1).getValue(1, 0);
	             will(returnValue(2.5));
	             allowing(values1).getValue(2, 0);
	             will(returnValue(5.0));
	             
	             allowing(values1).getValue(0, 1);
	             will(returnValue(3));
	             allowing(values1).getValue(1, 1);
	             will(returnValue(4));
	             allowing(values1).getValue(2, 1);
	             will(returnValue(null));
	             
	             allowing(values1).getValue(0, 2);
	             will(returnValue(8.8));
	             allowing(values1).getValue(1, 2);
	             will(returnValue(8.8));
	             allowing(values1).getValue(2, 2);
	             will(returnValue(8.8));
	             
	             allowing(values1).getValue(with(any(Integer.class)), with(-1));
	             will(throwException(new IndexOutOfBoundsException()));
	             allowing(values1).getValue(with(-1), with(any(Integer.class)));
	             will(throwException(new IndexOutOfBoundsException()));
	         }
	     });
	 }

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
 	 
	 //Test of null Value2D and regular array argument
	 @Test(expected = IllegalArgumentException.class)
	 public void testNullValue2DForcalculateRowTotalwithArray() {
		 int[] arr= {0,1,2};
	     DataUtilities.calculateRowTotal(null, 0, arr);
	 }
	 
	//Tests of positive, zero, and negative column number and regular array argument
	 @Test
	 public void testZeroValueForcalculateRowTotalwithRegularArray() {
		 int[] arr= {0,1,2};
	     double result = DataUtilities.calculateRowTotal(values1, 0, arr);
	     assertEquals(19.3, result, .000000001d);
	 }
	 @Test
	 public void testPositiveValueForcalculateRowTotalwithRegularArray() {
		 int[] arr= {0,1,2};
	     double result = DataUtilities.calculateRowTotal(values1, 2, arr);
	     assertEquals(13.8, result, .000000001d);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testNegativeValueForcalculateRowTotalwithRegularArray() {
		 int[] arr= {0,1,2};
	     DataUtilities.calculateRowTotal(values1, -1, arr);
	 }
	 
	//Tests of positive, zero, and negative column number and zero array argument
	 @Test
	 public void testZeroValueForcalculateRowTotalwithZeroArray() {
		 int[] arr= {0};
	     double result = DataUtilities.calculateRowTotal(values1, 0, arr);
	     assertEquals(7.5, result, .000000001d);
	 }
	 @Test
	 public void testPostiveValueForcalculateRowTotalwithZeroArray() {
		 int[] arr= {0};
	     double result = DataUtilities.calculateRowTotal(values1, 2, arr);
	     assertEquals(5.0, result, .000000001d);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testNegativeValueForcalculateRowTotalwithZeroArray() {
		 int[] arr= {0};
	     DataUtilities.calculateRowTotal(values1, -1, arr);
	 }
	 
	//Tests of positive, zero, and negative column number and negative array argument
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testPositiveValueForcalculateRowTotalwithNegativeArray() {
		 int[] arr= {-1};
	     DataUtilities.calculateRowTotal(values1, 5, arr);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testNegativeValueForcalculateRowTotalwithNegativeArray() {
		 int[] arr= {-1};
	     DataUtilities.calculateRowTotal(values1, -1, arr);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testZeroValueForcalculateRowTotalwithNegativeArray() {
		 int[] arr= {-1};
	     DataUtilities.calculateRowTotal(values1, 0, arr);
	 }
	 
	 @Test
	 public void testZeroValueForcalculateRowTotalwithOutBoundIndexArray() {
		 int[] arr= {3};
	     double result = DataUtilities.calculateRowTotal(values1, 0, arr);
	     assertEquals(0, result, .000000001d);
	 }
	 
	 //Test of null Value2D argument
	 @Test(expected = IllegalArgumentException.class)
	 public void testNullValue2DForcalculateColumnTotal() {
	     DataUtilities.calculateColumnTotal(null, 0);
	 }
	 
	//Tests of positive, zero, and negative column number
	 @Test
	 public void testZeroValueForcalculateColumnTotal() {
	     double result = DataUtilities.calculateColumnTotal(values1, 0);
	     assertEquals(15.0, result, .000000001d);
	 }
	 @Test
	 public void testPositiveValueForcalculateColumnTotal() {
	     double result = DataUtilities.calculateColumnTotal(values1, 1);
	     assertEquals(7.0, result, .000000001d);
	 }
	 @Test
	 public void testPositiveValueWithANullForcalculateColumnTotal() {
	     double result = DataUtilities.calculateColumnTotal(values1, 2);
	     assertEquals(26.4, result, .000000001d);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testNegativeValueForcalculateColumnTotal() {
	     DataUtilities.calculateColumnTotal(values1, -1);
	 }
	 
	 //Test of null Value2D and regular array argument
	 @Test(expected = IllegalArgumentException.class)
	 public void testNullValue2DForcalculateColumnTotalwithArray() {
		 int[] arr= {0,1,2};
	     DataUtilities.calculateColumnTotal(null, 0, arr);
	 }
	 
	//Tests of positive, zero, and negative column number and regular array argument
	 @Test
	 public void testZeroValueForcalculateColumnTotalwithRegularArray() {
		 int[] arr= {0,1,2};
	     double result = DataUtilities.calculateColumnTotal(values1, 0, arr);
	     assertEquals(15.0, result, .000000001d);
	 }
	 @Test
	 public void testPositiveValueForcalculateColumnTotalwithRegularArray() {
		 int[] arr= {0,1,2};
	     double result = DataUtilities.calculateColumnTotal(values1, 1, arr);
	     assertEquals(7.0, result, .000000001d);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testNegativeValueForcalculateColumnTotalwithRegularArray() {
		 int[] arr= {0,1,2};
	     DataUtilities.calculateColumnTotal(values1, -1, arr);
	 }
	 
	//Tests of positive, zero, and negative column number and zero array argument
	 @Test
	 public void testZeroValueForcalculateColumnTotalwithZeroArray() {
		 int[] arr= {0};
	     double result = DataUtilities.calculateColumnTotal(values1, 0, arr);
	     assertEquals(7.5, result, .000000001d);
	 }
	 @Test
	 public void testPostiveValueForcalculateColumnTotalwithZeroArray() {
		 int[] arr= {0};
	     double result = DataUtilities.calculateColumnTotal(values1, 2, arr);
	     assertEquals(8.8, result, .000000001d);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testNegativeValueForcalculateColumnTotalwithZeroArray() {
		 int[] arr= {0};
	     DataUtilities.calculateColumnTotal(values1, -1, arr);
	 }
	 
	//Tests of positive, zero, and negative column number and negative array argument
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testPositiveValueForcalculateColumnTotalwithNegativeArray() {
		 int[] arr= {-1};
	     DataUtilities.calculateColumnTotal(values1, 2, arr);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testNegativeValueForcalculateColumnTotalwithNegativeArray() {
		 int[] arr= {-1};
	     DataUtilities.calculateColumnTotal(values1, -1, arr);
	 }
	 @Test(expected = IndexOutOfBoundsException.class)
	 public void testZeroValueForcalculateColumnTotalwithNegativeArray() {
		 int[] arr= {-1};
	     DataUtilities.calculateColumnTotal(values1, 0, arr);
	 }
	 
	 @Test
	 public void testZeroValueForcalculateColumnTotalwithOutBoundIndexArray() {
		 int[] arr= {3};
	     double result = DataUtilities.calculateColumnTotal(values1, 0, arr);
	     assertEquals(0, result, .000000001d);
	 }
}
