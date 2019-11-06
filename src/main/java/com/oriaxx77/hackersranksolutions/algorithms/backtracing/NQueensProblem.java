package com.oriaxx77.hackersranksolutions.algorithms.backtracing;

import java.util.Arrays;

/**
 * The Eight Queens problem solved with backtracing.
 */
public class NQueensProblem {
	
	private int n;
	
	
	
	private int[] queensByRows; // poz = row, value=col 
	
	public NQueensProblem( int n ){
		if ( n <=0 )
			throw new IllegalArgumentException( "n must be > 0" );
		
		queensByRows = new int[n];
		this.n = n;
		
		placeQueen( 0, n );
	}
	
	
	private void placeQueen( int row, int n ){
		
		for ( int col = 0; col < n; col++ ){
			if ( queenCanBePlaced( row, col ) ){
				queensByRows[row] = col;
				if ( row == n-1 )
					printQueens();
				else
					placeQueen( row+1, n);
			}
		}
	}
	
	private boolean queenCanBePlaced( int row, int col ){
		
		for ( int rowi = 0 ; rowi < row; rowi++ ){
			if (isAttackingQueens( rowi, queensByRows[rowi], row, col) )
				return false;
		}
		return true;
	}
	
	private boolean isAttackingQueens( int row1, int col1, int row2, int col2 ) {
		return ( row1 == row2 ) || // same row
			   ( col1 == col2 ) || // same column
			   ( Math.abs( row1 - row2 ) == Math.abs( col1 - col2 ) ); // diagonal
	}
	
	private void printQueens(){
		for ( int row = 0; row < n; row++ ){
			for ( int col = 0; col < n; col++ ){
				if ( queensByRows[row] == col )
					System.out.print( "Q" );
				else
					System.out.print( "." );
				
				if ( col == n-1 )
					System.out.println( "\n" );
			}
		}
		System.out.println( "\n" );
	}
	
	
	public static void main(String[] args) {
		new NQueensProblem(4);
	}
	
}

