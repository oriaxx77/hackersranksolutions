/**
 * 
 */
package com.oriaxx77.hackersranksolutions.algorithms.backtracing;

import java.util.Arrays;
import java.util.stream.Collectors;




/**
 * Backtracing solution for the KnightsTour problem.
 */
public class KnightsTour { 
	
	private Solution solution;

	public KnightsTour(int boardDimension) {
		this.solution = new Solution(boardDimension);
	}
	 	
		
	public Solution solve() { 
		Position startPosition = new Position(0,0);
		solution.add(startPosition, 0);
		solve( startPosition, 1); 
		return solution; 
	} 
	
	
	boolean solve( Position actPos, int moveCount ) { 		
		if ( solution.done(moveCount) ) 
			return true; 
		
		for (Move move: possibleKnightMoves()) {
			// Try next move
			Position nextPos = actPos.move(move);
			// If valid move
			if ( solution.isPartOf(nextPos)) {
				// Check the valid move
				solution.add(nextPos, moveCount);
				// 
				if (solve(nextPos, moveCount+1)) 
					return true; // It is a solution 
				else
					solution.reset(nextPos); // backtracing 
			} 
		} 

		return false; 
	} 

	
	
	
	
	private Move[] possibleKnightMoves() {
		return new Move[]{new Move(2,1), new Move(1,2), new Move(-1,2), new Move(-2,1), new Move(-2,-1), new Move(-1,-2), new Move(1,-2), new Move(2,-1)}; 
	}
	
	
	
	public static void main(String args[]) { 
		new KnightsTour(8).solve().print(); 
	} 
} 

class Solution {
	private int[][] matrix;
	private int n;
	
	public Solution(int n) {
		this.n = n;
		this.matrix = createEmptyMatrix();
	}
	
	private int[][] createEmptyMatrix() {
		int sol[][] = new int[n][n]; 
		for (int x = 0; x < this.n; x++) { 
			for (int y = 0; y < this.n; y++) { 
				sol[x][y] = -1;
			}
		}
		return sol;
	}
	
	public void reset(Position pos) {
		add(pos, -1);
	}
	
	public void add(Position pos, int moveCount) {
		matrix[pos.x()][pos.y()] = moveCount;
	}
	
	public boolean done(int moveCount) {
		return  moveCount == n*n;
	}
	
	public boolean isPartOf(Position pos) { 
		return (pos.x() >= 0 && pos.x() < this.n && pos.y() >= 0 && 
				pos.y() < this.n && this.matrix[pos.x()][pos.y()] == -1); 
	}
	
	
	public void print() {
		for (int x = 0; x < this.n; x++) { 
			for (int y = 0; y < this.n; y++) {
				System.out.print(matrix[x][y] + " ");
			}
			System.out.println(); 
		} 
	} 

}

/**
 * Represents a delta x and delta y move on a chessboard.
 */
class Move {
	
	private int x;
	private int y;
	
	public Move(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int x() { return this.x; }
	
	public int y() { return this.y; }
}

/**
 * An x,y position on a chess board
 */
class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int x() { return this.x; }
	
	public int y() { return this.y; }
	
	public Position move(Move move) {
		return new Position(x+move.x(), y+move.y());
	}
	
	public Position[] moves( Move[] moves) {
		return Arrays.stream( moves ).map( this::move ).collect( Collectors.toList() ).toArray( new Position[0] );
	}
	
}



