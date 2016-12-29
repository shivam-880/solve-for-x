package com.codingkapoor.problemsolving;

import org.junit.Assert;
import org.junit.Test;

public class SpiralMatrixTest {

	SpiralMatrix s = new SpiralMatrix();

	// c < r
	// { 1, 5, 6, 7 }
	// { 2, 11, 12, 10 }
	// { 3, 13, 14, 15 }
	// { 4, 20, 19, 18 }
	// { 8, 3, 9, 11 }

	@Test
	public void testWhenColumnsCountIsLessThanRowsCount() {
		int arr[][] = { { 1, 5, 6, 7 }, { 2, 11, 12, 10 }, { 3, 13, 14, 15 }, { 4, 20, 19, 18 }, { 8, 3, 9, 11 } };
		int[] res = s.compute(arr);

		Assert.assertArrayEquals(res, new int[] { 1, 5, 6, 7, 10, 15, 18, 11, 9, 3, 8, 4, 3, 2, 11, 12, 14, 19, 20, 13 });
	}

	// c > r
	// { 1, 5, 6, 7, 8 }
	// { 2, 11, 12, 10, 9 }
	// { 3, 13, 14, 15, 7 }
	// { 4, 20, 19, 18, 16 }

	@Test
	public void testWhenColumnsCountIsMoreThanRowsCount() {
		int arr[][] = { { 1, 5, 6, 7, 8 }, { 2, 11, 12, 10, 9 }, { 3, 13, 14, 15, 7 }, { 4, 20, 19, 18, 16 }, { 8, 3, 9, 11, 15 } };
		int[] res = s.compute(arr);

		Assert.assertArrayEquals(res, new int[] { 1, 5, 6, 7, 8, 9, 7, 16, 15, 11, 9, 3, 8, 4, 3, 2, 11, 12, 10, 15, 18, 19, 20, 13, 14 });
	}

	// c = r
	// { 1, 5, 6, 7, 8 }
	// { 2, 11, 12, 10, 9 }
	// { 3, 13, 14, 15, 7 }
	// { 4, 20, 19, 18, 16 }
	// { 8, 3, 9, 11, 15 }

	@Test
	public void testWhenColumnsCountIsEqualToRowsCount() {
		int arr[][] = { { 1, 5, 6, 7, 8 }, { 2, 11, 12, 10, 9 }, { 3, 13, 14, 15, 7 }, { 4, 20, 19, 18, 16 } };
		int[] res = s.compute(arr);

		Assert.assertArrayEquals(res, new int[] { 1, 5, 6, 7, 8, 9, 7, 16, 18, 19, 20, 4, 3, 2, 11, 12, 10, 15, 14, 13 });
	}

	@Test(expected = Exception.class)
	public void testIfExceptionThrownForInvalidMatrix() throws Exception {
		int arr[][] = { { 1, 5, 6, 7, 8 }, { 2, 11, 10, 9 }, { 3 }, { 4, 20, 19, 18, 16 } };
		s.isValidMatrix(arr);
	}
}
