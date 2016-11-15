package com.codingkapoor.kaerujump;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import com.codingkapoor.kaerujump.model.Coordinate;
import com.codingkapoor.kaerujump.model.UserInput;
import com.codingkapoor.kaerujump.util.CoordinatesComparator;
import com.codingkapoor.kaerujump.util.UserInputFileReader;

public class KaeruJumpAlgo {

	private int iMax, jMax, numberOfNextPossibleMoves;

	private Coordinate previous;
	private Coordinate current;

	private ArrayList<Coordinate> available = new ArrayList<>();
	private ArrayList<Coordinate> solution = new ArrayList<>();

	private Stack<Coordinate> nextPossibleMoves = new Stack<>();
	
	private Stack<Coordinate> currentStack = new Stack<>();
	private Stack<Integer> numberOfNextPossibleMovesStack = new Stack<>();
	
	private Stack<ArrayList<Coordinate>> availableStack = new Stack<>();
	private Stack<ArrayList<Coordinate>> solutionStack = new Stack<>();

	private KaeruJumpAlgo(String filePath) throws Exception {
		
		UserInputFileReader reader = new UserInputFileReader();
		UserInput userInput = reader.getUserInput(filePath);
		
		available.addAll(userInput.getAllCoordinates());
		
		iMax = userInput.getiMax();
		jMax = userInput.getjMax();
		
		numberOfNextPossibleMoves = 0;
		
		previous = userInput.getPrevious();
		current = userInput.getCurrent();
	}

	private void findPath() {

		updateNextPossibleMoves();
		
		updateSolution(current);
		updateAvailable(current);

		if (available.isEmpty()) {
			display();
			return;
		}

		if (numberOfNextPossibleMoves == 0) {

			restoreAvailable();
			restoreSolution();
			
			previous = getLastCurrent();

		} else if (numberOfNextPossibleMoves > 1) {
			
			saveNumberOfNextPossibleMoves();
			
			saveAvailable();
			saveSolution();
			
			saveCurrent();
			
			previous = current;
			
		} else if (numberOfNextPossibleMoves == 1) {
			previous = current;
		}

		current = nextPossibleMoves.pop();	
		numberOfNextPossibleMoves = 0;

		findPath();
	}

	private void updateNextPossibleMoves() {

		int x = current.getX();
		int y = current.getY();

		switch (CoordinatesComparator.compare(current, previous)) {
			case LEFT_RIGHT_BELOW: {
				left(x, y);
				right(x, y);
				below(x, y);
				break;
			}
			case LEFT_RIGHT_ABOVE: {
				left(x, y);
				right(x, y);
				above(x, y);
				break;
			}
			case BELOW_ABOVE_LEFT: {
				left(x, y);
				above(x, y);
				below(x, y);
				break;
			}
			case BELOW_ABOVE_RIGHT: {
				right(x, y);
				above(x, y);
				below(x, y);
				break;
			}
		}
	}
	
	private void left(int i, int j) {

		while (j > 0) {
			j--;
			Coordinate coordinate = new Coordinate(i, j);

			if (isPresentInAvailable(coordinate)) {
				nextPossibleMoves.push(coordinate);
				numberOfNextPossibleMoves++;
				return;
			}
		}

	}

	private void right(int i, int j) {
		
		while (j < jMax) {
			j++;
			Coordinate coordinate = new Coordinate(i, j);

			if (isPresentInAvailable(coordinate)) {
				nextPossibleMoves.push(coordinate);
				numberOfNextPossibleMoves++;
				return;
			}
		}
	}

	private void above(int i, int j) {
		
		while (i > 0) {
			i--;
			Coordinate coordinate = new Coordinate(i, j);

			if (isPresentInAvailable(coordinate)) {
				nextPossibleMoves.push(coordinate);
				numberOfNextPossibleMoves++;
				return;
			}
		}
	}

	private void below(int i, int j) {
		
		while (i < iMax) {
			i++;
			Coordinate coordinate = new Coordinate(i, j);
			
			if (isPresentInAvailable(coordinate)) {
				nextPossibleMoves.push(coordinate);
				numberOfNextPossibleMoves++;
				return;
			}
		}
	}

	private boolean isPresentInAvailable(Coordinate coordinate) {
		if (available.contains(coordinate))
			return true;
		return false;
	}
	
	private Coordinate getLastCurrent() {
		if (numberOfNextPossibleMovesStack.peek() < 3) {
			numberOfNextPossibleMovesStack.pop();
			return currentStack.pop();
		} else {
			numberOfNextPossibleMovesStack.push(numberOfNextPossibleMovesStack.pop() - 1);
			return currentStack.peek();
		}
	}
	
	private void updateSolution(Coordinate coordinate) {
		solution.add(coordinate);
	}

	private void updateAvailable(Coordinate coordinate) {
		available.remove(coordinate);
	}
	
	private void saveCurrent() {
		currentStack.push(current);
	}

	private void saveAvailable() {
		
		ArrayList<Coordinate> availableClone = new ArrayList<Coordinate>();
		
		Iterator<Coordinate> iterator = available.iterator();
		while (iterator.hasNext()) {
			try {
				availableClone.add((Coordinate) ((Coordinate) iterator.next()).clone());
				
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		availableStack.push(availableClone);
	}

	private void saveSolution() {

		ArrayList<Coordinate> solutionClone = new ArrayList<Coordinate>();

		Iterator<Coordinate> iterator = solution.iterator();
		while (iterator.hasNext()) {
			try {
				solutionClone.add((Coordinate) ((Coordinate) iterator.next()).clone());
				
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		solutionStack.push(solutionClone);
	}
	
	private void saveNumberOfNextPossibleMoves() {
		numberOfNextPossibleMovesStack.push(numberOfNextPossibleMoves);
	}

	private void restoreAvailable() {

		available.clear();
		Iterator<Coordinate> iterator;

		if (numberOfNextPossibleMovesStack.peek() < 3) 
			iterator = ((ArrayList<Coordinate>) availableStack.pop()).iterator();
		else
			iterator = ((ArrayList<Coordinate>) availableStack.peek()).iterator();

		while (iterator.hasNext()) {
			available.add((Coordinate) iterator.next());
		}

	}

	private void restoreSolution() {

		solution.clear();
		Iterator<Coordinate> iterator;

		if (numberOfNextPossibleMovesStack.peek() < 3) 
			iterator = ((ArrayList<Coordinate>) solutionStack.pop()).iterator();
		else
			iterator = ((ArrayList<Coordinate>) solutionStack.peek()).iterator();

		while (iterator.hasNext()) {
			solution.add((Coordinate) iterator.next());
		}

	}

	private void display() {
		Iterator<Coordinate> iterator = solution.iterator();
		while (iterator.hasNext()) {
			Coordinate coordinate = iterator.next();
			System.out.print("(" + coordinate.getX() + "," + coordinate.getY() + ")" + " -> ");
		}
		System.out.print("x");
	}
	
	public static void main(String args[]) throws Exception {
		KaeruJumpAlgo kaeruJumpAlgo = new KaeruJumpAlgo((args.length > 0) ? args[0] : null);
		kaeruJumpAlgo.findPath();
	}

}
