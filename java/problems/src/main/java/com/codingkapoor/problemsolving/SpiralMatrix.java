package com.codingkapoor.problemsolving;

public class SpiralMatrix {

	public int[] compute(int arr[][]) {

		int rows = arr.length;
		int columns = (rows > 0) ? arr[0].length : 0;

		// level = UpperBound(Rows/2)
		int level = (int) Math.ceil((double) rows / 2), r = 0;

		int[] result = new int[(rows * columns)];

		int i = 0, j = 0, count = 0;

		while (r < level) {

			// traverse right: [R, C + 1]
			for (int t = j + r; t < columns - r; t++) {
				result[count] = arr[i][t];
				count++;
				j = t;
			}

			// traverse down: [R + 1, C]
			for (int t = i + 1; t < rows - r; t++) {
				result[count] = arr[t][j];
				count++;
				i = t;
			}

			// traverse left: [R, C - 1]
			for (int t = j - 1; t >= 0 + r; t--) {
				result[count] = arr[i][t];
				count++;
				j = t;
			}

			// traverse up: [R - 1, C]
			for (int t = i - 1; t > 0 + r; t--) {
				result[count] = arr[t][j];
				count++;
				i = t;
			}

			r++;
		}

		if (rows == columns) {
			result[count] = arr[rows / 2][columns / 2];
		}

		return result;
	}

	public void isValidMatrix(int arr[][]) throws Exception {
		int rows = arr.length;
		int columns = arr[0].length;
		for (int i = 1; i < rows; i++) {
			if (arr[i].length != columns) {
				throw new Exception("Invalid matrix!");
			}
		}
	}

	private void display(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void main(String[] args) throws Exception {

		// (0,0) (0,1) (0,2) (0,3) (0,4)
		// (1,0) (1,1) (1,2) (1,3) (1,4)
		// (2,0) (2,1) (2,2) (2,3) (2,4)
		// (3,0) (3,1) (3,2) (3,3) (3,4)

		int arr[][] = { { 1, 2, 3, 4, 5 }, { 14, 15, 16, 17, 6 }, { 13, 20, 19, 18, 7 }, { 12, 11, 10, 9, 8 } };

		SpiralMatrix s = new SpiralMatrix();

		s.isValidMatrix(arr);
		s.display(s.compute(arr));
	}
}
