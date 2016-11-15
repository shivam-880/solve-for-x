package com.codingkapoor.kaerujump.util;

import com.codingkapoor.kaerujump.PossibleDirections;
import com.codingkapoor.kaerujump.model.Coordinate;

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
