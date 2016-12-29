package com.codingkapoor.problemsolving;

import org.junit.Assert;
import org.junit.Test;

import com.codingkapoor.problemsolving.PointInTriangle.Point;

public class PointInTriangleTest {

	PointInTriangle obj = new PointInTriangle();
	
	@Test
	public void testIfPointIsInsideTriangle() throws Exception {
		
		Point a = obj.new Point(0, 0);
		Point b = obj.new Point(5, 0);
		Point c = obj.new Point(0, 4);

		Point[] triangle = new Point[] { a, b, c };
		Point point = obj.new Point(1, 1);
		
		Assert.assertEquals(obj.find(triangle, point), true);
	}

	@Test
	public void testIfPointIsOutsideTriangle() throws Exception {
		Point a = obj.new Point(0, 0);
		Point b = obj.new Point(5, 0);
		Point c = obj.new Point(0, 4);

		Point[] triangle = new Point[] { a, b, c };
		Point point = obj.new Point(5, 10);
		
		Assert.assertEquals(obj.find(triangle, point), false);
	}

	@Test(expected = Exception.class)
	public void testIfPointsProvidedAreCollinearAndDoNotFormATriangle() throws Exception {
		Point a = obj.new Point(0, 0);
		Point b = obj.new Point(0, 2);
		Point c = obj.new Point(0, 4);

		Point[] triangle = new Point[] { a, b, c };
		obj.isValidTriangle(triangle);
		
	}
}
