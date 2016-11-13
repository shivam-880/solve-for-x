package com.codingkapoor.model;

public class Coordinate implements Cloneable {

	private int x, y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result + x;
		result = prime * result + y;
		
		return result;
	}

	@Override
	public boolean equals(Object coordinate) {
		
		if (this == coordinate)
			return true;
		
		if (coordinate == null)
			return false;
		
		if (getClass() != coordinate.getClass())
			return false;
		
		Coordinate other = (Coordinate) coordinate;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		
		return true;
	}

	public Object clone() throws CloneNotSupportedException {
		return this;
	}
	
}
