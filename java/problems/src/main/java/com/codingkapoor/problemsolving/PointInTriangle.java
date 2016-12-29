package com.codingkapoor.problemsolving;

public class PointInTriangle {

	public class Point {

		private int x;
		private int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

	}

	public void isValidTriangle(Point[] triangle) throws Exception {

		if (triangle.length > 3 || triangle.length < 3) {
			throw new Exception("Invalid triangle.");
		}

		// Formula to check if three points are Collinear:
		// (y2-y1) (y3-y2)
		// ------- = -------
		// (x2-x1) (x3-x2)

		double slope1 = (double) (triangle[1].getY() - triangle[0].getY()) / (triangle[1].getX() - triangle[0].getX());
		double slope2 = (double) (triangle[2].getY() - triangle[1].getY()) / (triangle[2].getX() - triangle[1].getX());

		if (slope1 == slope2) {
			throw new Exception("Invalid triangle.");
		}

	}

	private double area(Point[] triangle) {

		// Area=|(x1(y2−y3) + x2(y3−y1) + x3(y1−y2))/2|

		double a = triangle[0].getX() * (triangle[1].getY() - triangle[2].getY())
				+ triangle[1].getX() * (triangle[2].getY() - triangle[0].getY())
				+ triangle[2].getX() * (triangle[0].getY() - triangle[1].getY());

		return Math.abs(a / 2);
	}

	public boolean find(Point[] triangle, Point point) throws Exception {

		isValidTriangle(triangle);

		Point[] subTriangle1 = new Point[] { triangle[0], triangle[1], point };
		Point[] subTriangle2 = new Point[] { triangle[0], triangle[2], point };
		Point[] subTriangle3 = new Point[] { triangle[1], triangle[2], point };

		if (area(subTriangle1) + area(subTriangle2) + area(subTriangle3) == area(triangle))
			return true;

		return false;
	}

	public static void main(String[] args) throws Exception {

		PointInTriangle obj = new PointInTriangle();

		Point a = obj.new Point(0, 0);
		Point b = obj.new Point(5, 0);
		Point c = obj.new Point(0, 4);

		Point[] triangle = new Point[] { a, b, c };
		Point point = obj.new Point(1, 1);

		System.out.println(obj.find(triangle, point));
	}
}
