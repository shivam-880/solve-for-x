package com.codingkapoor.kaerujump.model;

import java.util.ArrayList;

public class UserInput {

	private int iMax, jMax;

	private ArrayList<Coordinate> allCoordinates = new ArrayList<>();

	private Coordinate previous;
	private Coordinate current;

	public int getiMax() {
		return iMax;
	}

	public void setiMax(int iMax) {
		this.iMax = iMax;
	}

	public int getjMax() {
		return jMax;
	}

	public void setjMax(int jMax) {
		this.jMax = jMax;
	}

	public ArrayList<Coordinate> getAllCoordinates() {
		return allCoordinates;
	}

	public void setAllCoordinates(ArrayList<Coordinate> allCoordinates) {
		this.allCoordinates = allCoordinates;
	}

	public Coordinate getPrevious() {
		return previous;
	}

	public void setPrevious(Coordinate previous) {
		this.previous = previous;
	}

	public Coordinate getCurrent() {
		return current;
	}

	public void setCurrent(Coordinate current) {
		this.current = current;
	}

}
