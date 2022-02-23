/**
 * @author Emily Young, Chad Morrow
 * 
 * Emily wrote the methods PlayGame(), placeMarker() and winner()
 * Chad wrote checkStatus(), printBoard and checkVal()
 * Both made slight changes to each other's work collaboratively
 * 
 * Idea of grid layout
 * +-+-+-+
 * |1|2|3|
 * +-+-+-+
 * |4|5|6|
 * +-+-+-+
 * |7|8|9|
 * +-+-+-+
 *  
 */

package tictactoe;

import java.util.*;

import static java.lang.System.exit;

public class game {
	public static String play1 = "";
	public static String play2 = "";
	public static ArrayList<Integer> play1Marker = new ArrayList<>();
	public static ArrayList<Integer> play2Marker = new ArrayList<>();
	public static int markerP1 = 0;
	public static int markerP2 = 0;
	private static boolean endGame = true;
	
	public static void main(String[] args) {
		PlayGame();
	}

	public static void PlayGame() {
		printBoard();

		System.out.println("Welcome to Tic-TacToe!");
		System.out.println("Are you an 'X' or an 'O'?");
		Scanner scan = new Scanner(System.in); 
		play1 = scan.nextLine();
		if(play1.equals("X")|| play1.equals("x")) {
			play1 = "X";
			play2 = "O";
			System.out.println("Player 1: " + play1 + " Player 2: " + play2);
		} else if(play1.equals("O") || play1.equals("o")) {
			play1 = "O";
			play2 = "X";
			System.out.println("Player 1: " + play1 + " Player 2: " + play2);
		} else {
			System.out.println("That is not a valid input. Please try again.");
		}
		
		while(endGame) {
			placeMarker();
		}
		scan.close();
	}

	public static void placeMarker() {
		System.out.println("Player 1, where would you like to place your marker? (1-9)");
		Scanner scan = new Scanner(System.in);
		markerP1 = scan.nextInt();

		while(play1Marker.contains(markerP1) || play2Marker.contains(markerP1)){
			System.out.println("Try again!");
			markerP1 = scan.nextInt();
		}

		play1Marker.add(markerP1);

		printBoard();
		if(checkStatus()){
			scan.close();
			exit(0);
		}

		System.out.println("Player 2, where would you like to place your marker? (1-9)");
		Scanner scan2 = new Scanner(System.in);
		markerP2 = scan2.nextInt();

		while(play2Marker.contains(markerP2) || play1Marker.contains(markerP2)){
			System.out.println("Try again!");
			markerP2 = scan2.nextInt();
		}
		play2Marker.add(markerP2);


		printBoard();
		if(checkStatus()){
			scan2.close();
			exit(0);
		}
	}

	private static boolean checkStatus(){
		String finalMessage = winner();
		System.out.println(finalMessage);
		if(finalMessage.contains("wins") || finalMessage.contains("tie")){
			endGame = false;
			return true;
		}
		return false;
	}

	public static String winner() {
		List row1 = Arrays.asList(1, 2, 3);
		List row2 = Arrays.asList(4, 5, 6);
		List row3 = Arrays.asList(7, 8, 9);
		List col1 = Arrays.asList(1, 4, 7);
		List col2 = Arrays.asList(2, 5, 8);
		List col3 = Arrays.asList(3, 6, 9);
		List diag1 = Arrays.asList(1, 5, 9);
		List diag2 = Arrays.asList(3, 5, 7);
		
		List<List> allList = new ArrayList<>();
		allList.add(row1);
		allList.add(row2);
		allList.add(row3);
		allList.add(col1);
		allList.add(col2);
		allList.add(col3);
		allList.add(diag1);
		allList.add(diag2);
		
		for(List n: allList) {
			if(play1Marker.containsAll(n)) {
				return "Player 1 wins!";
			} else if(play2Marker.containsAll(n)) {
				return "Player 2 wins!";
			} else if(play1Marker.size() + play2Marker.size() == 9 && !play2Marker.containsAll(n) && !play1Marker.containsAll(n)) {
				return "It's a tie!";
			}
		}
		return "";
	}

	/*
 		 * +-+-+-+
		 * |1|2|3|
		 * +-+-+-+
		 * |4|5|6|
		 * +-+-+-+
		 * |7|8|9|
		 * +-+-+-+
	 */
	private static void printBoard(){
		for(int i = 0; i < 7; i++){
			if(i % 2 == 0){
				System.out.println("+-+-+-+");
			} else {
				for(int j = 0; j < 7; j++){
					if(j % 2 == 0){
						System.out.print("|");
					} else {
						if(i == 1){
							if(j == 1){
								checkVal(1);
							} else if(j == 3){
								checkVal(2);
							} else if(j == 5){
								checkVal(3);
							}
						} else if(i == 3){
							if(j == 1){
								checkVal(4);
							} else if(j == 3){
								checkVal(5);
							} else if(j == 5){
								checkVal(6);
							}
						} else if(i == 5){
							if(j == 1){
								checkVal(7);
							} else if(j == 3){
								checkVal(8);
							} else if(j == 5){
								checkVal(9);
							}
						}
					}
				}
				System.out.println();
			}
		}
	}

	private static void checkVal(int loc){
		if(play1Marker.contains(loc)){
			System.out.print(play1);
		} else if(play2Marker.contains(loc)){
			System.out.print(play2);
		} else {
			System.out.print(loc);
		}
	}
}
