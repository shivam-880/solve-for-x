package com.codingkapoor.util;

import com.codingkapoor.PossibleDirections;
import com.codingkapoor.model.Coordinate;

public class CoordinatesComparator {

	public static PossibleDirections compare(Coordinate current, Coordinate previous) {
		
		if (current.getX() == previous.getX()) {
			if (current.getY() < previous.getY())
				return PossibleDirections.BELOW_ABOVE_LEFT;
			
			else if (current.getY() > previous.getY())
				return PossibleDirections.BELOW_ABOVE_RIGHT;
			
		} else if (current.getY() == previous.getY()) {
			if (current.getX() < previous.getX())
				return PossibleDirections.LEFT_RIGHT_ABOVE;
			
			else if (current.getX() > previous.getX())
				return PossibleDirections.LEFT_RIGHT_BELOW;
		}
		
		return null;
	}
	
}
