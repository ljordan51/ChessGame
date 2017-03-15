import java.util.Scanner;
import java.lang.*;

class Space {
	String name;
	int xpos;
	int ypos;
	boolean occupied;
	Piece pieceOnSpace;
}

class Result {
	boolean boole;
	String piece;
}

//ChessGame class creates piece and space objects and get user input to play game
class ChessGame {
	
	//instance variables of game
	
	//space objects (8x8 board 64 total)
	Space A1;Space A2;Space A3;Space A4;Space A5;Space A6;Space A7;Space A8;
	Space B1;Space B2;Space B3;Space B4;Space B5;Space B6;Space B7;Space B8;
	Space C1;Space C2;Space C3;Space C4;Space C5;Space C6;Space C7;Space C8;
	Space D1;Space D2;Space D3;Space D4;Space D5;Space D6;Space D7;Space D8;
	Space E1;Space E2;Space E3;Space E4;Space E5;Space E6;Space E7;Space E8;
	Space F1;Space F2;Space F3;Space F4;Space F5;Space F6;Space F7;Space F8;
	Space G1;Space G2;Space G3;Space G4;Space G5;Space G6;Space G7;Space G8;
	Space H1;Space H2;Space H3;Space H4;Space H5;Space H6;Space H7;Space H8;

	//piece objects
	King wk0; //white king
	Queen wq0; //white queen
	Bishop wb0;Bishop wb1; //white bishops
	Horse wh0;Horse wh1; //white horses (knights)
	Rook wr0;Rook wr1; //white rooks (castles)
	Pawn wp0;Pawn wp1;Pawn wp2;Pawn wp3;Pawn wp4;Pawn wp5;Pawn wp6;Pawn wp7; //white pawns (8)
	King bk0; //black king
	Queen bq0; //black queen
	Bishop bb0;Bishop bb1; //black bishops
	Horse bh0;Horse bh1; //black horses (knights)
	Rook br0;Rook br1; //black rooks (castles)
	Pawn bp0;Pawn bp1;Pawn bp2;Pawn bp3;Pawn bp4;Pawn bp5;Pawn bp6;Pawn bp7; //black pawns (8)
	
	//integer for handling turns
	int x = 0;
	String turn;

	//instance variables for handling inputs
	boolean acceptableInputs = false;
	boolean legal = false;
	boolean clearPath = false;
	boolean notSameColorOccupation = false;
	String piece;
	boolean acceptablePieceInput = false;
	String newPosition;
	boolean acceptableNewPositionInput = false;
	
	public void start() {

		//setting up pieces
		//white
		wk0 = new King();wk0.name = "White King";wk0.color = "white";wk0.type = "king";wk0.xpos = 3;wk0.ypos = 0;
		wq0 = new Queen();wq0.name = "White Queen";wq0.color = "white";wq0.type = "queen";wq0.xpos = 4;wq0.ypos = 0;
		wb0 = new Bishop();wb0.name = "White Bishop";wb0.color = "white";wb0.type = "bishop";wb0.xpos = 2;wb0.ypos = 0;
		wb1 = new Bishop();wb1.name = "White Bishop";wb1.color = "white";wb1.type = "bishop";wb1.xpos = 5;wb1.ypos = 0;wb1.number = 1;
		wh0 = new Horse();wh0.name = "White Horse";wh0.color = "white";wh0.type = "horse";wh0.xpos = 1;wh0.ypos = 0;
		wh1 = new Horse();wh1.name = "White Horse";wh1.color = "white";wh1.type = "horse";wh1.xpos = 6;wh1.ypos = 0;wh1.number = 1;
		wr0 = new Rook();wr0.name = "White Rook";wr0.color = "white";wr0.type = "rook";wr0.xpos = 0;wr0.ypos = 0;
		wr1 = new Rook();wr1.name = "White Rook";wr1.color = "white";wr1.type = "rook";wr1.xpos = 7;wr1.ypos = 0;wr1.number = 1;
		wp0 = new Pawn();wp0.name = "White Pawn";wp0.color = "white";wp0.type = "pawn";wp0.xpos = 0;wp0.ypos = 1;
		wp1 = new Pawn();wp1.name = "White Pawn";wp1.color = "white";wp1.type = "pawn";wp1.xpos = 1;wp1.ypos = 1;wp1.number = 1;
		wp2 = new Pawn();wp2.name = "White Pawn";wp2.color = "white";wp2.type = "pawn";wp2.xpos = 2;wp2.ypos = 1;wp2.number = 2;
		wp3 = new Pawn();wp3.name = "White Pawn";wp3.color = "white";wp3.type = "pawn";wp3.xpos = 3;wp3.ypos = 1;wp3.number = 3;
		wp4 = new Pawn();wp4.name = "White Pawn";wp4.color = "white";wp4.type = "pawn";wp4.xpos = 4;wp4.ypos = 1;wp4.number = 4;
		wp5 = new Pawn();wp5.name = "White Pawn";wp5.color = "white";wp5.type = "pawn";wp5.xpos = 5;wp5.ypos = 1;wp5.number = 5;
		wp6 = new Pawn();wp6.name = "White Pawn";wp6.color = "white";wp6.type = "pawn";wp6.xpos = 6;wp6.ypos = 1;wp6.number = 6;
		wp7 = new Pawn();wp7.name = "White Pawn";wp7.color = "white";wp7.type = "pawn";wp7.xpos = 7;wp7.ypos = 1;wp7.number = 7;
		//black
		bk0 = new King();bk0.name = "Black King";bk0.color = "black";bk0.type = "king";bk0.xpos = 3;bk0.ypos = 7;
		bq0 = new Queen();bq0.name = "Black Queen";bq0.color = "black";bq0.type = "queen";bq0.xpos = 4;bq0.ypos = 7;
		bb0 = new Bishop();bb0.name = "Black Bishop";bb0.color = "black";bb0.type = "bishop";bb0.xpos = 2;bb0.ypos = 7;
		bb1 = new Bishop();bb1.name = "Black Bishop";bb1.color = "black";bb1.type = "bishop";bb1.xpos = 5;bb1.ypos = 7;bb1.number = 1;
		bh0 = new Horse();bh0.name = "Black Horse";bh0.color = "black";bh0.type = "horse";bh0.xpos = 1;bh0.ypos = 7;
		bh1 = new Horse();bh1.name = "Black Horse";bh1.color = "black";bh1.type = "horse";bh1.xpos = 6;bh1.ypos = 7;bh1.number = 1;
		br0 = new Rook();br0.name = "Black Rook";br0.color = "black";br0.type = "rook";br0.xpos = 0;br0.ypos = 7;
		br1 = new Rook();br1.name = "Black Rook";br1.color = "black";br1.type = "rook";br1.xpos = 7;br1.ypos = 7;br1.number = 1;
		bp0 = new Pawn();bp0.name = "Black Pawn";bp0.color = "black";bp0.type = "pawn";bp0.xpos = 0;bp0.ypos = 6;
		bp1 = new Pawn();bp1.name = "Black Pawn";bp1.color = "black";bp1.type = "pawn";bp1.xpos = 1;bp1.ypos = 6;bp1.number = 1;
		bp2 = new Pawn();bp2.name = "Black Pawn";bp2.color = "black";bp2.type = "pawn";bp2.xpos = 2;bp2.ypos = 6;bp2.number = 2;
		bp3 = new Pawn();bp3.name = "Black Pawn";bp3.color = "black";bp3.type = "pawn";bp3.xpos = 3;bp3.ypos = 6;bp3.number = 3;
		bp4 = new Pawn();bp4.name = "Black Pawn";bp4.color = "black";bp4.type = "pawn";bp4.xpos = 4;bp4.ypos = 6;bp4.number = 4;
		bp5 = new Pawn();bp5.name = "Black Pawn";bp5.color = "black";bp5.type = "pawn";bp5.xpos = 5;bp5.ypos = 6;bp5.number = 5;
		bp6 = new Pawn();bp6.name = "Black Pawn";bp6.color = "black";bp6.type = "pawn";bp6.xpos = 6;bp6.ypos = 6;bp6.number = 6;
		bp7 = new Pawn();bp7.name = "Black Pawn";bp7.color = "black";bp7.type = "pawn";bp7.xpos = 7;bp7.ypos = 6;bp7.number = 7;

		//setting up spaces, letters on x axis, numbers on y axis, indexed at 0, first two and last two rows occupied
		A1 = new Space();A1.name = "A1";A1.xpos = 0;A1.ypos = 0;A1.occupied = true;A1.pieceOnSpace = wr0;
		B1 = new Space();B1.name = "B1";B1.xpos = 1;B1.ypos = 0;B1.occupied = true;B1.pieceOnSpace = wh0;
		C1 = new Space();C1.name = "C1";C1.xpos = 2;C1.ypos = 0;C1.occupied = true;C1.pieceOnSpace = wb0;
		D1 = new Space();D1.name = "D1";D1.xpos = 3;D1.ypos = 0;D1.occupied = true;D1.pieceOnSpace = wk0;
		E1 = new Space();E1.name = "E1";E1.xpos = 4;E1.ypos = 0;E1.occupied = true;E1.pieceOnSpace = wq0;
		F1 = new Space();F1.name = "F1";F1.xpos = 5;F1.ypos = 0;F1.occupied = true;F1.pieceOnSpace = wb1;
		G1 = new Space();G1.name = "G1";G1.xpos = 6;G1.ypos = 0;G1.occupied = true;G1.pieceOnSpace = wh1;
		H1 = new Space();H1.name = "H1";H1.xpos = 7;H1.ypos = 0;H1.occupied = true;H1.pieceOnSpace = wr1;
		A2 = new Space();A2.name = "A2";A2.xpos = 0;A2.ypos = 1;A2.occupied = true;A2.pieceOnSpace = wp0;
		B2 = new Space();B2.name = "B2";B2.xpos = 1;B2.ypos = 1;B2.occupied = true;B2.pieceOnSpace = wp1;
		C2 = new Space();C2.name = "C2";C2.xpos = 2;C2.ypos = 1;C2.occupied = true;C2.pieceOnSpace = wp2;
		D2 = new Space();D2.name = "D2";D2.xpos = 3;D2.ypos = 1;D2.occupied = true;D2.pieceOnSpace = wp3;
		E2 = new Space();E2.name = "E2";E2.xpos = 4;E2.ypos = 1;E2.occupied = true;E2.pieceOnSpace = wp4;
		F2 = new Space();F2.name = "F2";F2.xpos = 5;F2.ypos = 1;F2.occupied = true;F2.pieceOnSpace = wp5;
		G2 = new Space();G2.name = "G2";G2.xpos = 6;G2.ypos = 1;G2.occupied = true;G2.pieceOnSpace = wp6;
		H2 = new Space();H2.name = "H2";H2.xpos = 7;H2.ypos = 1;H2.occupied = true;H2.pieceOnSpace = wp7;
		A3 = new Space();A3.name = "A3";A3.xpos = 0;A3.ypos = 2;A3.occupied = false;
		B3 = new Space();B3.name = "B3";B3.xpos = 1;B3.ypos = 2;B3.occupied = false;
		C3 = new Space();C3.name = "C3";C3.xpos = 2;C3.ypos = 2;C3.occupied = false;
		D3 = new Space();D3.name = "D3";D3.xpos = 3;D3.ypos = 2;D3.occupied = false;
		E3 = new Space();E3.name = "E3";E3.xpos = 4;E3.ypos = 2;E3.occupied = false;
		F3 = new Space();F3.name = "F3";F3.xpos = 5;F3.ypos = 2;F3.occupied = false;
		G3 = new Space();G3.name = "G3";G3.xpos = 6;G3.ypos = 2;G3.occupied = false;
		H3 = new Space();H3.name = "H3";H3.xpos = 7;H3.ypos = 2;H3.occupied = false;
		A4 = new Space();A4.name = "A4";A4.xpos = 0;A4.ypos = 3;A4.occupied = false;
		B4 = new Space();B4.name = "B4";B4.xpos = 1;B4.ypos = 3;B4.occupied = false;
		C4 = new Space();C4.name = "C4";C4.xpos = 2;C4.ypos = 3;C4.occupied = false;
		D4 = new Space();D4.name = "D4";D4.xpos = 3;D4.ypos = 3;D4.occupied = false;
		E4 = new Space();E4.name = "E4";E4.xpos = 4;E4.ypos = 3;E4.occupied = false;
		F4 = new Space();F4.name = "F4";F4.xpos = 5;F4.ypos = 3;F4.occupied = false;
		G4 = new Space();G4.name = "G4";G4.xpos = 6;G4.ypos = 3;G4.occupied = false;
		H4 = new Space();H4.name = "H4";H4.xpos = 7;H4.ypos = 3;H4.occupied = false;
		A5 = new Space();A5.name = "A5";A5.xpos = 0;A5.ypos = 4;A5.occupied = false;
		B5 = new Space();B5.name = "B5";B5.xpos = 1;B5.ypos = 4;B5.occupied = false;
		C5 = new Space();C5.name = "C5";C5.xpos = 2;C5.ypos = 4;C5.occupied = false;
		D5 = new Space();D5.name = "D5";D5.xpos = 3;D5.ypos = 4;D5.occupied = false;
		E5 = new Space();E5.name = "E5";E5.xpos = 4;E5.ypos = 4;E5.occupied = false;
		F5 = new Space();F5.name = "F5";F5.xpos = 5;F5.ypos = 4;F5.occupied = false;
		G5 = new Space();G5.name = "G5";G5.xpos = 6;G5.ypos = 4;G5.occupied = false;
		H5 = new Space();H5.name = "H5";H5.xpos = 7;H5.ypos = 4;H5.occupied = false;
		A6 = new Space();A6.name = "A6";A6.xpos = 0;A6.ypos = 5;A6.occupied = false;
		B6 = new Space();B6.name = "B6";B6.xpos = 1;B6.ypos = 5;B6.occupied = false;
		C6 = new Space();C6.name = "C6";C6.xpos = 2;C6.ypos = 5;C6.occupied = false;
		D6 = new Space();D6.name = "D6";D6.xpos = 3;D6.ypos = 5;D6.occupied = false;
		E6 = new Space();E6.name = "E6";E6.xpos = 4;E6.ypos = 5;E6.occupied = false;
		F6 = new Space();F6.name = "F6";F6.xpos = 5;F6.ypos = 5;F6.occupied = false;
		G6 = new Space();G6.name = "G6";G6.xpos = 6;G6.ypos = 5;G6.occupied = false;
		H6 = new Space();H6.name = "H6";H6.xpos = 7;H6.ypos = 5;H6.occupied = false;
		A7 = new Space();A7.name = "A7";A7.xpos = 0;A7.ypos = 6;A7.occupied = true;A7.pieceOnSpace = bp0;
		B7 = new Space();B7.name = "B7";B7.xpos = 1;B7.ypos = 6;B7.occupied = true;B7.pieceOnSpace = bp1;
		C7 = new Space();C7.name = "C7";C7.xpos = 2;C7.ypos = 6;C7.occupied = true;C7.pieceOnSpace = bp2;
		D7 = new Space();D7.name = "D7";D7.xpos = 3;D7.ypos = 6;D7.occupied = true;D7.pieceOnSpace = bp3;
		E7 = new Space();E7.name = "E7";E7.xpos = 4;E7.ypos = 6;E7.occupied = true;E7.pieceOnSpace = bp4;
		F7 = new Space();F7.name = "F7";F7.xpos = 5;F7.ypos = 6;F7.occupied = true;F7.pieceOnSpace = bp5;
		G7 = new Space();G7.name = "G7";G7.xpos = 6;G7.ypos = 6;G7.occupied = true;G7.pieceOnSpace = bp6;
		H7 = new Space();H7.name = "H7";H7.xpos = 7;H7.ypos = 6;H7.occupied = true;H7.pieceOnSpace = bp7;
		A8 = new Space();A8.name = "A8";A8.xpos = 0;A8.ypos = 7;A8.occupied = true;A8.pieceOnSpace = br0;
		B8 = new Space();B8.name = "B8";B8.xpos = 1;B8.ypos = 7;B8.occupied = true;B8.pieceOnSpace = bh0;
		C8 = new Space();C8.name = "C8";C8.xpos = 2;C8.ypos = 7;C8.occupied = true;C8.pieceOnSpace = bb0;
		D8 = new Space();D8.name = "D8";D8.xpos = 3;D8.ypos = 7;D8.occupied = true;D8.pieceOnSpace = bk0;
		E8 = new Space();E8.name = "E8";E8.xpos = 4;E8.ypos = 7;E8.occupied = true;E8.pieceOnSpace = bq0;
		F8 = new Space();F8.name = "F8";F8.xpos = 5;F8.ypos = 7;F8.occupied = true;F8.pieceOnSpace = bb1;
		G8 = new Space();G8.name = "G8";G8.xpos = 6;G8.ypos = 7;G8.occupied = true;G8.pieceOnSpace = bh1;
		H8 = new Space();H8.name = "H8";H8.xpos = 7;H8.ypos = 7;H8.occupied = true;H8.pieceOnSpace = br1;

		while(!(wk0.taken || wb0.taken)) {

			if ((x % 2) == 0) {
				turn = "White";
			} else {
				turn = "Black";
			}
			x++;

			manageInputs:
				while (!(acceptableInputs && legal && clearPath && notSameColorOccupation)) {
	
					piece = getPieceInput(turn);
					acceptablePieceInput = checkPieceInput(piece); //returns true if acceptable
	
					if (!(acceptablePieceInput)) {
						System.out.println("Your piece does not match an acceptable choice. Please try again.");
						continue manageInputs;
					}
			
					newPosition = getNewPositionInput(turn);
					acceptableNewPositionInput = checkNewPositionInput(newPosition); //returns true if acceptable

					if (!(acceptableNewPositionInput)) {
						System.out.println("Your new position does not match an acceptable choice. Please try again.");
						continue manageInputs;
					}

					acceptableInputs = true;

					notSameColorOccupation = checkOccupation(newPosition, turn);
					if (!(notSameColorOccupation)) {
						System.out.println("Your movement is obstructed by another piece. Please try again.");
						continue manageInputs;
					}

					Result resultLegal = new Result();
					resultLegal = checkMoveLegality(piece, newPosition, turn);
					piece = resultLegal.piece;
					legal = resultLegal.boole;
					if (!(legal)) {
						System.out.println("Your move is illegal. Please try again.");
						continue manageInputs;
					}

					clearPath = checkPath(piece, newPosition);
					if (!(clearPath)) {
						System.out.println("Your movement is obstructed by another piece. Please try again.");
						continue manageInputs;
					}

				} //close manage inputs while loop

			acceptableInputs = false;
			legal = false;
			clearPath = false;
			notSameColorOccupation = false;

		} //close main game while loop
	} //close start method

	public String getPieceInput(String turn) {
		Scanner user_input = new Scanner(System.in);
		System.out.println(turn + ", enter the piece you would like to move (king, queen, bishop, knight, rook, or pawn): ");
		piece = user_input.next();
		return piece;
	}

	public boolean checkPieceInput(String piece) {
		switch (piece) {
			case "king":
			case "queen":
			case "bishop":
			case "knight":
			case "rook":
			case "pawn":
				acceptablePieceInput = true;
				break;
			case "quit":
				System.exit(0);
			default: 
				acceptablePieceInput = false;
				break;	
		}
		return acceptablePieceInput;
	}

	public String getNewPositionInput(String turn) {
		Scanner user_input = new Scanner(System.in);
		System.out.println(turn + ", enter its new position (example A1): ");
		newPosition = user_input.next();
		return newPosition;
	}

	public boolean checkNewPositionInput(String newPosition) {
		switch (newPosition) {
			case "A1": case "A2": case "A3": case "A4": case "A5": case "A6": case "A7": case "A8":
			case "B1": case "B2": case "B3": case "B4": case "B5": case "B6": case "B7": case "B8":
			case "C1": case "C2": case "C3": case "C4": case "C5": case "C6": case "C7": case "C8":
			case "D1": case "D2": case "D3": case "D4": case "D5": case "D6": case "D7": case "D8":
			case "E1": case "E2": case "E3": case "E4": case "E5": case "E6": case "E7": case "E8":
			case "F1": case "F2": case "F3": case "F4": case "F5": case "F6": case "F7": case "F8":
			case "G1": case "G2": case "G3": case "G4": case "G5": case "G6": case "G7": case "G8":
			case "H1": case "H2": case "H3": case "H4": case "H5": case "H6": case "H7": case "H8":
				acceptableNewPositionInput = true;
				break;
			case "quit":
				System.exit(0);
			default: 
				acceptableNewPositionInput = false;
				break;	
		}
		return acceptableNewPositionInput;
	}

	public Result checkMoveLegality(String piece, String newPosition, String turn) {
		boolean legal0 = false; boolean legal1 = false; boolean legal2 = false;
		boolean legal3 = false; boolean legal4 = false; boolean legal5 = false;
		boolean legal6 = false; boolean legal7 = false; boolean legal = false;
		Space varSpace = getSpaceFromSpaceString(newPosition);
		int[] newIndices = {varSpace.xpos, varSpace.ypos};
		if (turn == "White") {

			switch (piece) {
				case "king":
					legal = wk0.checkMove(newIndices);
					break;
				case "queen":
					legal = wq0.checkMove(newIndices);
					break;
				case "bishop":
					legal0 = wb0.checkMove(newIndices);
					if (legal0) {
						piece = piece + "0";
					}
					legal1 = wb1.checkMove(newIndices); //it is impossible for both to be legal
					if (legal1) {
						piece = piece + "1";
					}
					if (legal0 && legal1) {
						System.out.println("Error. Game terminated.");
						System.exit(0);
					}
					legal = (legal0 || legal1);
					break;
				case "knight":
					legal0 = wh0.checkMove(newIndices);
					legal1 = wh1.checkMove(newIndices); //it is possible for both to be legal
					if (legal0 && legal1) {
						System.out.println("Both knights can make that move.");
						boolean positionInvalid = true;
						while (positionInvalid) {
							String piecePosition = getPiecePosition(turn, piece);
							Space theSpace = getSpaceFromSpaceString(piecePosition);
							int[] piecePositionIndices = {theSpace.xpos, theSpace.ypos};
							if (wh0.xpos == piecePositionIndices[0] && wh0.ypos == piecePositionIndices[1]) {
								piece = piece + "0";
								positionInvalid = false;
							} else if (wh1.xpos == piecePositionIndices[0] && wh1.ypos == piecePositionIndices[1]) {
								piece = piece + "1";
								positionInvalid = false;
							} else {
								System.out.println("That space does not match either Knight. Try again.");
							}
						}
					} else if (legal0 || legal1) {
						if (legal0) {
							piece = piece + "0";
						}
						if (legal1) {
							piece = piece + "1";
						}
					}
					legal = (legal0 || legal1);
					break;
				case "rook":
					legal0 = wr0.checkMove(newIndices);
					legal1 = wr1.checkMove(newIndices); //it is possible for both to be legal
					if (legal0 && legal1) {
						System.out.println("Both rooks can make that move.");
						boolean positionInvalid = true;
						while (positionInvalid) {
							String piecePosition = getPiecePosition(turn, piece);
							Space theSpace = getSpaceFromSpaceString(piecePosition);
							int[] piecePositionIndices = {theSpace.xpos, theSpace.ypos};
							if (wr0.xpos == piecePositionIndices[0] && wr0.ypos == piecePositionIndices[1]) {
								piece = piece + "0";
								positionInvalid = false;
							} else if (wr1.xpos == piecePositionIndices[0] && wr1.ypos == piecePositionIndices[1]) {
								piece = piece + "1";
								positionInvalid = false;
							} else {
								System.out.println("That space does not match either Rook. Try again.");
							}
						}
					} else if (legal0 || legal1) {
						if (legal0) {
							piece = piece + "0";
						}
						if (legal1) {
							piece = piece + "1";
						}
					}
					legal = (legal0 || legal1);
					break;
				case "pawn":
					if (varSpace.occupied) {
						String pieceGeneric = piece;
						int count = 0;
						legal0 = wp0.checkMoveTake(newIndices);
						if (legal0) {piece = piece + "0";count++;}
						legal1 = wp1.checkMoveTake(newIndices);
						if (legal1) {piece = piece + "1";count++;}
						legal2 = wp2.checkMoveTake(newIndices);
						if (legal2) {piece = piece + "2";count++;}
						legal3 = wp3.checkMoveTake(newIndices);
						if (legal3) {piece = piece + "3";count++;}
						legal4 = wp4.checkMoveTake(newIndices);
						if (legal4) {piece = piece + "4";count++;}
						legal5 = wp5.checkMoveTake(newIndices);
						if (legal5) {piece = piece + "5";count++;}
						legal6 = wp6.checkMoveTake(newIndices);
						if (legal6) {piece = piece + "6";count++;}
						legal7 = wp7.checkMoveTake(newIndices);
						if (legal7) {piece = piece + "7";count++;}

						if (count > 1) {
							System.out.println("More than one pawn can make that move.");
							boolean positionInvalid = true;
							while (positionInvalid) {
								String piecePosition = getPiecePosition(turn, pieceGeneric);
								Space theSpace = getSpaceFromSpaceString(piecePosition);
								int[] piecePositionIndices = {theSpace.xpos, theSpace.ypos};
								if (wp0.xpos == piecePositionIndices[0] && wp0.ypos == piecePositionIndices[1]) {
									piece = piece + "0";
									positionInvalid = false;
								} else if (wp1.xpos == piecePositionIndices[0] && wp1.ypos == piecePositionIndices[1]) {
									piece = piece + "1";
									positionInvalid = false;
								} else if (wp2.xpos == piecePositionIndices[0] && wp2.ypos == piecePositionIndices[1]) {
									piece = piece + "2";
									positionInvalid = false;
								} else if (wp3.xpos == piecePositionIndices[0] && wp3.ypos == piecePositionIndices[1]) {
									piece = piece + "3";
									positionInvalid = false;
								} else if (wp4.xpos == piecePositionIndices[0] && wp4.ypos == piecePositionIndices[1]) {
									piece = piece + "4";
									positionInvalid = false;
								} else if (wp5.xpos == piecePositionIndices[0] && wp5.ypos == piecePositionIndices[1]) {
									piece = piece + "5";
									positionInvalid = false;
								} else if (wp6.xpos == piecePositionIndices[0] && wp6.ypos == piecePositionIndices[1]) {
									piece = piece + "6";
									positionInvalid = false;
								} else if (wp7.xpos == piecePositionIndices[0] && wp7.ypos == piecePositionIndices[1]) {
									piece = piece + "7";
									positionInvalid = false;
								} else {
									System.out.println("That space does not match any pawn. Try again.");
								}
							}
						}

					} else {
						legal0 = wp0.checkMove(newIndices);
						if (legal0) {piece = piece + "0";}
						legal1 = wp1.checkMove(newIndices);
						if (legal1) {piece = piece + "1";}
						legal2 = wp2.checkMove(newIndices);
						if (legal2) {piece = piece + "2";}
						legal3 = wp3.checkMove(newIndices);
						if (legal3) {piece = piece + "3";}
						legal4 = wp4.checkMove(newIndices);
						if (legal4) {piece = piece + "4";}
						legal5 = wp5.checkMove(newIndices);
						if (legal5) {piece = piece + "5";}
						legal6 = wp6.checkMove(newIndices);
						if (legal6) {piece = piece + "6";}
						legal7 = wp7.checkMove(newIndices);
						if (legal7) {piece = piece + "7";}
					}
					legal = !(!(legal0) && !(legal1) && !(legal2) && !(legal3) && !(legal4) && !(legal5) && !(legal6) && !(legal7));
					break;
				default:
					legal = false;
					break;	
			}

		} else {

			switch (piece) {
				case "king":
					legal = bk0.checkMove(newIndices);
					break;
				case "queen":
					legal = bq0.checkMove(newIndices);
					break;
				case "bishop":
					legal0 = bb0.checkMove(newIndices);
					if (legal0) {
						piece = piece + "0";
					}
					legal1 = bb1.checkMove(newIndices); //it is impossible for both to be legal
					if (legal1) {
						piece = piece + "1";
					}
					if (legal0 && legal1) {
						System.out.println("Error. Game terminated.");
						System.exit(0);
					}
					legal = (legal0 || legal1);
					break;
				case "knight":
					legal0 = bh0.checkMove(newIndices);
					legal1 = bh1.checkMove(newIndices); //it is possible for both to be legal
					if (legal0 && legal1) {
						System.out.println("Both knights can make that move.");
						boolean positionInvalid = true;
						while (positionInvalid) {
							String piecePosition = getPiecePosition(turn, piece);
							Space theSpace = getSpaceFromSpaceString(piecePosition);
							int[] piecePositionIndices = {theSpace.xpos, theSpace.ypos};
							if (bh0.xpos == piecePositionIndices[0] && bh0.ypos == piecePositionIndices[1]) {
								piece = piece + "0";
								positionInvalid = false;
							} else if (bh1.xpos == piecePositionIndices[0] && bh1.ypos == piecePositionIndices[1]) {
								piece = piece + "1";
								positionInvalid = false;
							} else {
								System.out.println("That space does not match either Knight. Try again.");
							}
						}
					} else if (legal0 || legal1) {
						if (legal0) {
							piece = piece + "0";
						}
						if (legal1) {
							piece = piece + "1";
						}
					}
					legal = (legal0 || legal1);
					break;
				case "rook":
					legal0 = br0.checkMove(newIndices);
					legal1 = br1.checkMove(newIndices); //it is possible for both to be legal
					if (legal0 && legal1) {
						System.out.println("Both rooks can make that move.");
						boolean positionInvalid = true;
						while (positionInvalid) {
							String piecePosition = getPiecePosition(turn, piece);
							Space theSpace = getSpaceFromSpaceString(piecePosition);
							int[] piecePositionIndices = {theSpace.xpos, theSpace.ypos};
							if (br0.xpos == piecePositionIndices[0] && br0.ypos == piecePositionIndices[1]) {
								piece = piece + "0";
								positionInvalid = false;
							} else if (br1.xpos == piecePositionIndices[0] && br1.ypos == piecePositionIndices[1]) {
								piece = piece + "1";
								positionInvalid = false;
							} else {
								System.out.println("That space does not match either Rook. Try again.");
							}
						}
					} else if (legal0 || legal1) {
						if (legal0) {
							piece = piece + "0";
						}
						if (legal1) {
							piece = piece + "1";
						}
					}
					legal = (legal0 || legal1);
					break;
				case "pawn":
					if (varSpace.occupied) {
						String pieceGeneric = piece;
						int count = 0;
						legal0 = bp0.checkMoveTake(newIndices);
						if (legal0) {piece = piece + "0";count++;}
						legal1 = bp1.checkMoveTake(newIndices);
						if (legal1) {piece = piece + "1";count++;}
						legal2 = bp2.checkMoveTake(newIndices);
						if (legal2) {piece = piece + "2";count++;}
						legal3 = bp3.checkMoveTake(newIndices);
						if (legal3) {piece = piece + "3";count++;}
						legal4 = bp4.checkMoveTake(newIndices);
						if (legal4) {piece = piece + "4";count++;}
						legal5 = bp5.checkMoveTake(newIndices);
						if (legal5) {piece = piece + "5";count++;}
						legal6 = bp6.checkMoveTake(newIndices);
						if (legal6) {piece = piece + "6";count++;}
						legal7 = bp7.checkMoveTake(newIndices);
						if (legal7) {piece = piece + "7";count++;}

						if (count > 1) {
							System.out.println("More than one pawn can make that move.");
							boolean positionInvalid = true;
							while (positionInvalid) {
								String piecePosition = getPiecePosition(turn, pieceGeneric);
								Space theSpace = getSpaceFromSpaceString(piecePosition);
								int[] piecePositionIndices = {theSpace.xpos, theSpace.ypos};
								if (bp0.xpos == piecePositionIndices[0] && bp0.ypos == piecePositionIndices[1]) {
									piece = piece + "0";
									positionInvalid = false;
								} else if (bp1.xpos == piecePositionIndices[0] && bp1.ypos == piecePositionIndices[1]) {
									piece = piece + "1";
									positionInvalid = false;
								} else if (bp2.xpos == piecePositionIndices[0] && bp2.ypos == piecePositionIndices[1]) {
									piece = piece + "2";
									positionInvalid = false;
								} else if (bp3.xpos == piecePositionIndices[0] && bp3.ypos == piecePositionIndices[1]) {
									piece = piece + "3";
									positionInvalid = false;
								} else if (bp4.xpos == piecePositionIndices[0] && bp4.ypos == piecePositionIndices[1]) {
									piece = piece + "4";
									positionInvalid = false;
								} else if (bp5.xpos == piecePositionIndices[0] && bp5.ypos == piecePositionIndices[1]) {
									piece = piece + "5";
									positionInvalid = false;
								} else if (bp6.xpos == piecePositionIndices[0] && bp6.ypos == piecePositionIndices[1]) {
									piece = piece + "6";
									positionInvalid = false;
								} else if (bp7.xpos == piecePositionIndices[0] && bp7.ypos == piecePositionIndices[1]) {
									piece = piece + "7";
									positionInvalid = false;
								} else {
									System.out.println("That space does not match any pawn. Try again.");
								}
							}
						}

					} else {
						legal0 = bp0.checkMove(newIndices);
						if (legal0) {piece = piece + "0";}
						legal1 = bp1.checkMove(newIndices);
						if (legal1) {piece = piece + "1";}
						legal2 = bp2.checkMove(newIndices);
						if (legal2) {piece = piece + "2";}
						legal3 = bp3.checkMove(newIndices);
						if (legal3) {piece = piece + "3";}
						legal4 = bp4.checkMove(newIndices);
						if (legal4) {piece = piece + "4";}
						legal5 = bp5.checkMove(newIndices);
						if (legal5) {piece = piece + "5";}
						legal6 = bp6.checkMove(newIndices);
						if (legal6) {piece = piece + "6";}
						legal7 = bp7.checkMove(newIndices);
						if (legal7) {piece = piece + "7";}
					}
					legal = !(!(legal0) && !(legal1) && !(legal2) && !(legal3) && !(legal4) && !(legal5) && !(legal6) && !(legal7));
					break;
				default:
					legal = false;
					break;	
			}
			
		}
		Result result = new Result();
		result.boole = legal;
		result.piece = piece;
		return result;
	}

	public boolean checkPath(String piece, String newPosition) {
		return true;
	}

	public boolean checkOccupation(String newPosition, String turn) {
		boolean notSameColorOccupation = false;
		Space varSpace = getSpaceFromSpaceString(newPosition);
		Piece thisPiece;
		if (!(varSpace.occupied)) {
			notSameColorOccupation = true;
		} else {
			thisPiece = varSpace.pieceOnSpace;
			if (turn.equalsIgnoreCase(thisPiece.color)) {
				notSameColorOccupation = false;
			} else {
				notSameColorOccupation = true;
			}
		}
		return notSameColorOccupation;
	}

	public Space getSpaceFromSpaceString(String position) {
		char column = position.charAt(0);
		char row = position.charAt(1);
		Space varSpace = null;
		switch (column) {
			case 'A':
				switch (row) {
					case '1': varSpace = A1; break;
					case '2': varSpace = A2; break;
					case '3': varSpace = A3; break;
					case '4': varSpace = A4; break;
					case '5': varSpace = A5; break;
					case '6': varSpace = A6; break;
					case '7': varSpace = A7; break;
					case '8': varSpace = A8; break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				} //close switch case
				break;
			case 'B':
				switch (row) {
					case '1': varSpace = B1; break;
					case '2': varSpace = B2; break;
					case '3': varSpace = B3; break;
					case '4': varSpace = B4; break;
					case '5': varSpace = B5; break;
					case '6': varSpace = B6; break;
					case '7': varSpace = B7; break;
					case '8': varSpace = B8; break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				} //close switch case
				break;
			case 'C':
				switch (row) {
					case '1': varSpace = C1; break;
					case '2': varSpace = C2; break;
					case '3': varSpace = C3; break;
					case '4': varSpace = C4; break;
					case '5': varSpace = C5; break;
					case '6': varSpace = C6; break;
					case '7': varSpace = C7; break;
					case '8': varSpace = C8; break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				} //close switch case
				break;
			case 'D':
				switch (row) {
					case '1': varSpace = D1; break;
					case '2': varSpace = D2; break;
					case '3': varSpace = D3; break;
					case '4': varSpace = D4; break;
					case '5': varSpace = D5; break;
					case '6': varSpace = D6; break;
					case '7': varSpace = D7; break;
					case '8': varSpace = D8; break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				} //close switch case
				break;
			case 'E':
				switch (row) {
					case '1': varSpace = E1; break;
					case '2': varSpace = E2; break;
					case '3': varSpace = E3; break;
					case '4': varSpace = E4; break;
					case '5': varSpace = E5; break;
					case '6': varSpace = E6; break;
					case '7': varSpace = E7; break;
					case '8': varSpace = E8; break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				} //close switch case
				break;
			case 'F':
				switch (row) {
					case '1': varSpace = F1; break;
					case '2': varSpace = F2; break;
					case '3': varSpace = F3; break;
					case '4': varSpace = F4; break;
					case '5': varSpace = F5; break;
					case '6': varSpace = F6; break;
					case '7': varSpace = F7; break;
					case '8': varSpace = F8; break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				} //close switch case
				break;
			case 'G':
				switch (row) {
					case '1': varSpace = G1; break;
					case '2': varSpace = G2; break;
					case '3': varSpace = G3; break;
					case '4': varSpace = G4; break;
					case '5': varSpace = G5; break;
					case '6': varSpace = G6; break;
					case '7': varSpace = G7; break;
					case '8': varSpace = G8; break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				} //close switch case
				break;
			case 'H':
				switch (row) {
					case '1': varSpace = H1; break;
					case '2': varSpace = H2; break;
					case '3': varSpace = H3; break;
					case '4': varSpace = H4; break;
					case '5': varSpace = H5; break;
					case '6': varSpace = H6; break;
					case '7': varSpace = H7; break;
					case '8': varSpace = H8; break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				} //close switch case
				break;
			default:
				System.out.println("Error. Game terminated.");
				System.exit(0);
				break;
		} //close switch case
		return varSpace;
	}

	public String getPiecePosition(String turn, String piece) {
		Scanner user_input = new Scanner(System.in);
		System.out.println(turn + ", enter the position of the " + piece + " you want to move (example A1): ");
		String piecePosition = user_input.next();
		return piecePosition;
	}
	
} //close ChessGame class

class Piece {
	String name;
	String color;
	String type;
	int xpos;
	int ypos;
	int number = 0;
	boolean taken = false;
	
	public boolean checkMove(int[] newIndices) {
		return true;
	}

}

class Pawn extends Piece {

	public boolean checkMove(int[] newIndices) {
		int newxpos = newIndices[0];
		int newypos = newIndices[1];

		int xdif = newxpos - xpos;
		int ydif = newypos - ypos;
		boolean legal = false;

		if (color == "white") {
			if (xdif == 0 && ydif == 1) {
				legal = true;
			}
		} else {
			if (xdif == 0 && ydif == -1) {
				legal = true;
			}
		}
		return legal;
	}

	public boolean checkMoveTake(int[] newIndices) {
		int newxpos = newIndices[0];
		int newypos = newIndices[1];

		int xdif = newxpos - xpos;
		int ydif = newypos - ypos;
		boolean legal = false;

		if (color == "white") {
			if (xdif == -1 && ydif == 1) {
				legal = true;
			} else if (xdif == 1 && ydif == 1) {
				legal = true;
			}
		} else {
			if (xdif == -1 && ydif == -1) {
				legal = true;
			} else if (xdif == 1 && ydif == -1) {
				legal = true;
			}
		}
		return legal;
	}

}

class Rook extends Piece {

	public boolean checkMove(int[] newIndices) {
		int newxpos = newIndices[0];
		int newypos = newIndices[1];

		int xdif = Math.abs(newxpos - xpos);
		int ydif = Math.abs(newypos - ypos);
		boolean legal = false;
		if (xdif > 0 && ydif == 0) {
			legal = true;
		} else if (xdif == 0 && ydif > 0) {
			legal = true;
		}
		return legal;
	}

}

class Horse extends Piece {

	public boolean checkMove(int[] newIndices) {
		int newxpos = newIndices[0];
		int newypos = newIndices[1];

		int xdif = Math.abs(newxpos - xpos);
		int ydif = Math.abs(newypos - ypos);
		boolean legal = false;
		if (xdif == 2 && ydif == 1) {
			legal = true;
		} else if (xdif == 1 && ydif == 2) {
			legal = true;
		}
		return legal;
	}
}

class Bishop extends Piece {

	public boolean checkMove(int[] newIndices) {
		int newxpos = newIndices[0];
		int newypos = newIndices[1];

		int xdif = Math.abs(newxpos - xpos);
		int ydif = Math.abs(newypos - ypos);
		boolean legal = false;
		if (xdif/ydif == 1) {
			legal = true;
		}
		return legal;
	}

}

class King extends Piece {

	public boolean checkMove(int[] newIndices) {
		int newxpos = newIndices[0];
		int newypos = newIndices[1];

		int xdif = Math.abs(newxpos - xpos);
		int ydif = Math.abs(newypos - ypos);
		boolean legal = false;
		if (xdif == 1 && ydif == 1) {
			legal = true;
		} else if (xdif == 1 && ydif == 0) {
			legal = true;
		} else if (xdif == 0 && ydif == 1) {
			legal = true;
		}
		return legal;
	}

}

class Queen extends Piece {

	public boolean checkMove(int[] newIndices) {
		int newxpos = newIndices[0];
		int newypos = newIndices[1];

		int xdif = Math.abs(newxpos - xpos);
		int ydif = Math.abs(newypos - ypos);
		boolean legal = false;
		if (xdif > 0 && ydif == 0) {
			legal = true;
		} else if (xdif == 0 && ydif > 0) {
			legal = true;
		} else if (xdif/ydif == 1) {
			legal = true;
		}
		return legal;
	}

}

//ChessLauncher class to create and start the game
public class ChessLauncherV2 {
	
	//main method to start chess game by creating new object and running the start method
	public static void main (String[] args) {
		ChessGame game = new ChessGame(); //create new ChessGame object called game
		game.start(); //run start method
	}
}