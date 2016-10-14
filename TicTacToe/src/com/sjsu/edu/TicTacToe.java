package com.sjsu.edu;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interface containing methods of TicTacToe
 * @author group 22
 *
 */
public interface TicTacToe extends Remote {

	
	/**
	 * starts the game
	 * @return
	 * if true then users turn
	 * else computers turn
	 */
	public void start() throws RemoteException;  
	
	
	
	/**
	 * @param row
	 * row number
	 * @param col
	 * column number
	 * @return
	 * makes entry into matrix and return array
	 * which can be printed on client's side
	 */
	public boolean play(int row,int col) throws RemoteException;
	
	
	/**
	 * @return
	 * method to get matrix
	 */
	public char[][] getResult() throws RemoteException;
	
	/**
	 * @return
	 * return true if game is over
	 */
	public boolean isGameOver() throws RemoteException;
	
	/**
	 * @return
	 * return name of player who has won the game
	 */
	public String judge() throws RemoteException;
}
