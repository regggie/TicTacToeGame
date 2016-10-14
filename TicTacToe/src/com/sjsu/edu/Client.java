package com.sjsu.edu;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * client class which invokes methods on server using RMI
 * @author group22
 *
 */
public class Client {

	public static void main(String[] args) throws Exception {
		TicTacToe t = null ;
		try {
		t=(TicTacToe)Naming.lookup("rmi://10.189.56.49:1099/dac");
		}
		catch(final RemoteException ex) {
			System.out.println("Unable to connect to remote host");
			throw ex;
		}
		t.start();
		
		String winner = null;
		while (true) {
			// 1 start

			// 1a. if it is users turn
			char[][] result = t.getResult();
			printMatrix(result);
			List<Integer> rowCol = getInput();
			boolean res = t.play(rowCol.get(0), rowCol.get(1));
			// t.play(row, col);
			if (!res) {
				/*
				Outcomes :
				when result is ready
				when invalid access
				No result
				when invalid index or matrix is full*/
				
				// 1.check if matrix is full

				if (t.isGameOver()) {
				// 2.if yes evaluate result judge
					 winner = t.judge();
					if (winner != null) {
						
						System.out.println(winner);
						printMatrix(t.getResult());
						break;
					
					}

				} else {
					// 3.if no indexes are invalid or position is occupied
					// enter different indexes
					while (true) {
						// get another row column
						System.out
								.println("you are accessing location which is taken");

						rowCol = getInput();

						res = t.play(rowCol.get(0), rowCol.get(1));
						if (res) {
							
							break;
						}
						// call play again and break if u get success
					}

				}

			}
			if (res){
				winner = t.judge();
				if(winner != null) {
					System.out.println(winner);
					printMatrix(t.getResult());
					break;
				}
					
			}

		}

	}
	
	
	
	

	private static void printMatrix(final char[][] result) {

		System.out.println("---------------------");
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				System.out.print(result[i][j] + "\t|\t");
			}
			System.out.print("\n");
			System.out.println("---------------------");
		}
	}

	private static List<Integer> getInput() {
		final List<Integer> list = new ArrayList<Integer>();
		final Scanner sc = new Scanner(System.in);
		int row, col;

		do {
			System.out.println("Enter row ranging from 0-2");
			row = sc.nextInt();
			System.out.println("Enter col ranging from 0-2");
			col = sc.nextInt();
		} while (row > 2 || col > 2 || row < 0 || col < 0);

		list.add(row);
		list.add(col);
		return list;
	}

}
