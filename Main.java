// Ebaad Awan
// ICS3U
// 2022-06-10
// CPT - Connect 4

import java.util.*; 
class Main {
  public static void main(String[] args) {
	// variable declaration 
    int round=0; 
	int random; 
	String CPU = "Computer";
	String response; 
	String player1; 
	String player2; 
	boolean winner = false; 

	// creating 2D array for the board
	String [][] board = new String [5][5];

	// Filling the board with one space in each box
	for(int row = 0; row < board.length; row++) {
		for (int col = 0; col < board[0].length; col++) {
			board[row][col] = " ";
		}
	}

	// printing out rules
	rules();
	System.out.println(); 

	// Scanner open to collect data from player 1
	Scanner input = new Scanner(System.in); 
	System.out.print("Player 1, enter your name: "); 
	player1 = input.next(); 
	System.out.println(); 
	System.out.println(player1+", choose A or B to decide who you want to play with:"); 
	System.out.println("A. Computer"); 
	System.out.println("B. Another Player");
	System.out.print("Choose A or B: "); 
	response = input.next(); 
	// Player 1 has option to play against computer or another player. They either choose "A" or "B"
	// Player 1 choosing another letter than "A" or "B" will be asked again to enter either "A" or "B"
	// Uppercase or lowercase does not matter due to the string method
	while(!response.equalsIgnoreCase("A") && !response.equalsIgnoreCase("B")) {
		System.out.print("Invalid response. Choose A or B to determine who you would like to play with: ");
		response = input.next();
	}

	// If player 1 chooses "A", they play against computer
	if(response.equalsIgnoreCase("A")){
		System.out.println();
		System.out.println(player1+", you will play against the computer.");
		System.out.println();
		// Random number is assigned to player 1 and computer
		// Player 1 is number 1 while computer is number 2
		// If 1 is genereated first, player 1 goes first and if 2 is generated first, computer goes first
		// This is just a fair way to decide who goes first
		// Player 1 goes first
		random =  (int) (Math.random() * (2-1 +1) +1);
		if(random == 1){
			System.out.println(player1+" will play the first move.");
			System.out.println();
			// Round variable refers to available slots in the game
			// There are 25 slots available in the game
			// Game goes on while there are no winners and when all 25 slots are not used
			while(round<=25 && winner == false){
				// board is first displayed
				displayBoard(board);
				// winner variable is used to see if there are any winners
				winner = win(board, CPU);
				// If winner is true, computer wins
				if(winner==true){
					System.out.println("👑👑👑 Computer has won the game! 👑👑👑");
					break;
				}
				// If there are no winners, player 1 chooses where to drop their red token
				redTokens(board,player1);
				round++;
				displayBoard(board);
				// When round number has exceeded 25 and there are no winners, then game is resulted into a draw
				if(round>=25){
					System.out.println("THE GAME HAS RESULTED INTO A DRAW");
					break;
				}
				winner = win(board, player1);
				// if winner is true, player 1 wins
				if(winner==true){
					System.out.println("🏆🏆🏆 " +player1+" has won the game! 🏆🏆🏆");
					break;
				}
				// If there are no winners, computer chooses its slot to drop the yellow tokens
				yellowTokens(board,CPU);
				round++;
				}
			// placement of the code looks wierd but this is how I ended up writing it in order for the game to run properly
			}

		// Computer goes first
		// The comments before are the same comments in this block of code as well
		else if (random == 2){
			System.out.println("Computer will play the first move."); 
			System.out.println();
			while(round<=25 && winner == false){
				displayBoard(board);
				winner = win(board, player1);
				if(winner==true){
					System.out.println("🏆🏆🏆 " +player1+" has won the game! 🏆🏆🏆");
					break;
				}
				yellowTokens(board,CPU);
				round++;
				displayBoard(board);
				if(round>=25){
					System.out.println("THE GAME HAS RESULTED INTO A DRAW");
					break;
				}
				winner = win(board, CPU);
				if(winner==true){
					System.out.println("👑👑👑 Computer has won the game! 👑👑👑");
					break;
				}
				redTokens(board,player1);
				round++;

			}
		}
	}

	// If player 1 chooses "B", they play against another player 
	if(response.equalsIgnoreCase("B")){
		System.out.println();
		System.out.print("Player 2, enter your name: ");
		player2 = input.next(); 
		System.out.println();
		System.out.println(player1+", you will play against " +player2+ ".");
		System.out.println();
		random =  (int) (Math.random() * (2-1 +1) +1);
		// Random number is assigned to player 1 and player 2
		// Player 1 is number 1 whileplayer 2's number is 2
		// If 1 is genereated first, player 1 goes first and if 2 is generated first, player 2 goes first
		// This is just a fair way to decide who goes first
		// Player 1 goes first
		if(random == 1){
			System.out.println(player1+" will play the first move.");
			System.out.println();
			// Same concept of comments can be applied here
			// Instead of playing against a computer, player 1 is playing against another player
			// Not much of a difference in terms of code compared to the previous codes
			while(round<=25 && winner == false){
				displayBoard(board);
				winner = win(board, player2);
				if(winner==true){
					System.out.println("👑👑👑 "+player2+" has won the game! 👑👑👑");
					break;
				}
				redTokens(board,player1);
				round++;
				displayBoard(board);
				if(round>=25){
					System.out.println("THE GAME HAS RESULTED INTO A DRAW");
					break;
				}
				winner = win(board, player1);
				if(winner==true){
					System.out.println("🏆🏆🏆 " +player1+" has won the game! 🏆🏆🏆");
					break;
				}
				yellowTokens(board,player2);
				round++;
			  }
			}
		else if (random == 2){
			System.out.println(player2+" will play the first move."); 
			System.out.println();
			while(round<=25 && winner == false){
				displayBoard(board);
				winner = win(board, player1);
				if(winner==true){
					System.out.println("🏆🏆🏆 " +player1+" has won the game! 🏆🏆🏆");
					break;
				}
				yellowTokens(board,player2);
				round++;
				displayBoard(board);
				if(round>=25){
					System.out.println("THE GAME HAS RESULTED INTO A DRAW");
					break;
				}
				winner = win(board, player2);
				if(winner==true){
					System.out.println("👑👑👑 "+player2+" has won the game! 👑👑👑");
					break;
				}
				redTokens(board,player1);
				round++;
			  }
			}
		}
	  // This is the end of the public static void main
  }

  /**
	Purpose: Prints the rules of the game 
	Pre: no parameters 
	Post: N/A
  **/
  public static void rules() {
	  System.out.println("Welcome to Connect 4!"); 
	  System.out.println("Tokens will be assigned in the next lines");
	  System.out.println("REMEMBER: PLAYER 1 WILL BE PLAYING WITH A RED TOKEN");
	  System.out.println("REMEMBER: PLAYER 2/COMPUTER WILL BE PLAYING WITH YELLOW TOKENS");
	  System.out.println("The objective of the game is to have 4 of your tokens lined up either horizontally, vertically or in diagonal.");
	  System.out.println("You will be playing on a 5x5 board which means there will be 25 slots available.");
	  System.out.println("If all slots are taken and no 4 tokens of the same colour are lined up, the game will result in a draw.");
	  System.out.println("You have the option to play against another player or against the computer."); 
	  System.out.println("You will be asked to enter a column number to place your token on the board.");
	  System.out.println("Think wisely and have fun!");
  	}

  /**
	Purpose: Creates a random number generated between 0 to 4. This is used for the computer’s moves. 
	Pre: no parameters
	Post: N/A
  **/
  public static int randomNumberGenerator(int y) {
	  y =  (int) (Math.random() * (4-0 +1) +0);
	  return y;
	}

  /**
	Purpose: Responsible for displaying the board 
	Pre: one String board (2D Array) parameter 
	Post: N/A
  **/
  public static void displayBoard(String [][] boards) {
	  System.out.println("  0   1   2   3   4");

	  for(int row = 0; row < boards.length; row++) {
		for (int col = 0; col < boards[0].length; col++) {
			System.out.format("["+"%3s",boards[row][col]+"]");
				}
			System.out.println();
		}	 
		System.out.println("  0   1   2   3   4");
	} 

  /**
	Purpose:  To see if a slot is empty so that player’s can put their assigned token in that available slot
	Pre: one int column parameter, one String board (2D Array) parameter 
	Post: returns boolean “true or false” parameter 
  **/
  public static boolean availableSlot(int column, String [][] board) {
	  if(column < 0 || column > board[0].length) {
		  return false; 
		  // If false, players are asked again to enter a number between 0 to 4.
	  	}
	  if(board[0][column] != " "){
		  return false; 
		  // If false, player will be asked again to enter a number between 0 to 4.
		  // Player needs to make sure that they choose an empty slot
	  	}
	  return true;
    }	

  /**
	Purpose: Responsible for dropping red tokens for player 1
	Pre: one String player parameter, one String board (2D Array) parameter 
	Post: N/A
  **/
  public static void redTokens (String [][] boards, String player) {
	  Scanner in = new Scanner(System.in); 
	  int column;
	  boolean valids; 
	  do{
		System.out.print(player+" choose a column number between 0 to 4: ");
		column= in.nextInt(); 
		System.out.println();
		valids = availableSlot(column, boards);
		}while (valids == false);

		for(int row = boards.length-1;row >= 0; row--){
			if(boards[row][column] == " "){
				boards[row][column] = "🔴";
				break; 
			}
		}
    }

  /**
	Purpose: Responsible for dropping yellow tokens for computer or player 2
	Pre: one String player parameter, one String board (2D Array) parameter 
	Post: N/A
  **/
  public static void yellowTokens (String [][] boards, String player) {
	  Scanner inp = new Scanner(System.in); 
	  int column = 0; 
	  boolean valids; 
	  // Following code is responsible for dropping yellow tokens if the player is "Computer"
	  if(player.equalsIgnoreCase("Computer")) {
		  do{
			System.out.println("Computer will play its move."); 
			System.out.println();
			column = randomNumberGenerator(column); 
			valids = availableSlot(column, boards);
			}while (valids == false);

			for(int row = boards.length-1;row >=0; row--){
				if(boards[row][column] == " "){
					boards[row][column] = "🟡";
					break; 
					}
				}
	  		}
	  else{
		  // Following code is responsible for dropping yellow toke for player 2
		  do{
		System.out.print(player+" choose a column number between 0 to 4: ");
		column= inp.nextInt(); 
		System.out.println();
		valids = availableSlot(column, boards);
		}while (valids == false);

		for(int row = boards.length-1;row >= 0; row--){
			if(boards[row][column] == " "){
				boards[row][column] = "🟡";
				break; 
			}
		}
	  }
  	}

  /**
	Purpose: Determine if player has any 4 matching tokens 
	Pre: one String parameter of the player’s name, one String grid (2D Array) parameter
	Post: returns boolean “true or false” parameter 
  **/
  public static boolean win(String [][] boards, String player) {
	  String red = "🔴";
	  String yellow = "🟡";
	  // checking if 4 red tokens are lined up horizontally 
	  for(int row = 0; row<boards.length; row++){
		  for(int col=0; col<boards[0].length-3; col++){
			  if(boards[row][col]==red && boards[row][col+1]==red && boards[row][col+2]==red && boards[row][col+3]==red){
				  return true;
			  }
		  }
	  }
	  
	  // checking if 4 yellow tokens are lined up horizontally 
	  for(int row = 0; row<boards.length; row++){
		  for(int col=0; col<boards[0].length-3; col++){
			  if(boards[row][col]==yellow && boards[row][col+1]==yellow && boards[row][col+2]==yellow && boards[row][col+3]==yellow){
				  return true;
			  }
		  }
	  }

	  // checking if 4 red tokens are lined up vertically
	  for(int row = 0; row<boards.length-3; row++){
		  for(int col=0; col<boards[0].length; col++){
			  if(boards[row][col]==red && boards[row+1][col]==red && boards[row+2][col]==red && boards[row+3][col]==red){
				  return true;
			  }
		  }
	  }

	  // checking if 4 yellow tokens are lined up vertically
	  for(int row = 0; row<boards.length-3; row++){
		  for(int col=0; col<boards[0].length; col++){
			  if(boards[row][col]==yellow && boards[row+1][col]==yellow && boards[row+2][col]==yellow && boards[row+3][col]==yellow){
				  return true;
			  }
		  }
	  }

	  // checking if 4 red tokens are lined up diagonally (upward)
	  for(int row = 3; row<boards.length; row++){
		  for(int col=0; col<boards[0].length-3; col++){
			  if(boards[row][col]==red && boards[row-1][col+1]==red && boards[row-2][col+2]==red && boards[row-3][col+3]==red){
				  return true;
			  }
		  }
	  }

	  // checking if 4 yellow tokens are lined up diagonally (upward)
	  for(int row = 3; row<boards.length; row++){
		  for(int col=0; col<boards[0].length-3; col++){
			  if(boards[row][col]==yellow && boards[row-1][col+1]==yellow && boards[row-2][col+2]==yellow && boards[row-3][col+3]==yellow){
				  return true;
			  }
		  }
	  }

	  // checking if 4 red tokens are lined up diagonally (downward)
	  for(int row = 0; row<boards.length-3; row++){
		  for(int col=0; col<boards[0].length-3; col++){
			  if(boards[row][col]==red && boards[row+1][col+1]==red && boards[row+2][col+2]==red && boards[row+3][col+3]==red){
				  return true;
			  }
		  }
	  }
	  
	  // checking if 4 yellow tokens are lined up diagonally (upward)
	  for(int row = 0; row<boards.length-3; row++){
		  for(int col=0; col<boards[0].length-3; col++){
			  if(boards[row][col]==yellow && boards[row+1][col+1]==yellow && boards[row+2][col+2]==yellow && boards[row+3][col+3]==yellow){
				  return true;
			  }
		  }
	  }
	  return false;
  }
	// end to the whole program
}