package com.sjsu.edu;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 * @author group22
 *
 */
public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToe {

	
	private static final long serialVersionUID = 1L;
	private char[][] matrix;
    
    
    public TicTacToeImpl () throws RemoteException {
    	super();
        matrix = new char [3][3];
        System.out.println("Server has started");
        
    }

    // Reset/Initialise the game
    
    private void clearGame() {
        
        for (int i=0; i<3; i++) {
            
            for (int j=0; j<3; j++) {
                
                matrix[i][j] = '-';
            }
       }
    }
 
	@Override
	public void start() throws RemoteException {
		
		clearGame();
		
		final Random tempRandomInt = new Random();
        int turn = tempRandomInt.nextInt(2);
        
        if (turn == 0) { // Computer's turn
            
            //Auto select location
            int rowLocation = tempRandomInt.nextInt(3);
            int columnLocation = tempRandomInt.nextInt(3);
            //this.play(rowLocation, columnLocation);
            
            TickTacToeUtil.placeSymbol(0, rowLocation, columnLocation, matrix);
            
            
        } 
		//client program should enter details if true
		
	}

	@Override
	public boolean play(int row, int col) throws RemoteException {
		
		boolean result = false;
		final String res = this.judge();
		if(res == null) {
		 result = TickTacToeUtil.placeSymbol(1, row, col, matrix);
			if(result) //if result true 
			{
				
				this.playComputer();	
			}
	    }
		
		// if result is false then either coordinates are invaid or matrix is full	
		return result;
	}

	private void playComputer() throws RemoteException{
		
		final Random tempRandomInt = new Random();
		int rowLocation = tempRandomInt.nextInt(3);
        int columnLocation = tempRandomInt.nextInt(3);
        
        final boolean result = TickTacToeUtil.placeSymbol(0, rowLocation, columnLocation, matrix);
        if(!result) {
        	//1.check if matrix is full
        	 final String res = this.judge();
        	 if(res == null) {
        		 playComputer();
        	 }
        	
        }
       
    }
	
	@Override
	public String judge() throws RemoteException{
		
		String result = null;
		
		
			//1 evaluate result for computer
			 if( TickTacToeUtil.determineResult(0, matrix)) {
				result = "Winner is Computer"; 
			 }
			//2 evaluate result for user
			 else if(TickTacToeUtil.determineResult(1, matrix)){
				 result = "Winner is User";
			 }
			 
			 else if(TickTacToeUtil.isMatrixFull(matrix)) {
				 result = "Match Drawn, No result";
			 }
		
		
		return result;
	}
	
	
	@Override
	public char[][] getResult() throws RemoteException{
		return this.matrix;
	}

	@Override
	public boolean isGameOver() throws RemoteException{
		
		return TickTacToeUtil.isMatrixFull(matrix);
	}
	


}
