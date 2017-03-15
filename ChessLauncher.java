import java.util.Scanner;

class Space {
	String name;
	int xpos;
	int ypos;
	boolean occupied;

	public void toggleOccupation() {
		if (occupied) {
			occupied = false;
			System.out.println(name + " is now empty.");
		} else {
			occupied = true;
			System.out.println(name + " is now occupied.");
		}
	}
}

class Pawn extends Piece {

}

class Rook extends Piece {

}

class Horse extends Piece {

}

class Bishop extends Piece {

}

class King extends Piece {

}

class Queen extends Piece {

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
	
	public void start() {

		//setting up spaces, letters on x axis, numbers on y axis, indexed at 0, first two and last two rows occupied
		A1 = new Space();A1.name = "A1";A1.xpos = 0;A1.ypos = 0;A1.occupied = true;
		B1 = new Space();B1.name = "B1";B1.xpos = 1;B1.ypos = 0;B1.occupied = true;
		C1 = new Space();C1.name = "C1";C1.xpos = 2;C1.ypos = 0;C1.occupied = true;
		D1 = new Space();D1.name = "D1";D1.xpos = 3;D1.ypos = 0;D1.occupied = true;
		E1 = new Space();E1.name = "E1";E1.xpos = 4;E1.ypos = 0;E1.occupied = true;
		F1 = new Space();F1.name = "F1";F1.xpos = 5;F1.ypos = 0;F1.occupied = true;
		G1 = new Space();G1.name = "G1";G1.xpos = 6;G1.ypos = 0;G1.occupied = true;
		H1 = new Space();H1.name = "H1";H1.xpos = 7;H1.ypos = 0;H1.occupied = true;
		A2 = new Space();A2.name = "A2";A2.xpos = 0;A2.ypos = 1;A2.occupied = true;
		B2 = new Space();B2.name = "B2";B2.xpos = 1;B2.ypos = 1;B2.occupied = true;
		C2 = new Space();C2.name = "C2";C2.xpos = 2;C2.ypos = 1;C2.occupied = true;
		D2 = new Space();D2.name = "D2";D2.xpos = 3;D2.ypos = 1;D2.occupied = true;
		E2 = new Space();E2.name = "E2";E2.xpos = 4;E2.ypos = 1;E2.occupied = true;
		F2 = new Space();F2.name = "F2";F2.xpos = 5;F2.ypos = 1;F2.occupied = true;
		G2 = new Space();G2.name = "G2";G2.xpos = 6;G2.ypos = 1;G2.occupied = true;
		H2 = new Space();H2.name = "H2";H2.xpos = 7;H2.ypos = 1;H2.occupied = true;
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
		A7 = new Space();A7.name = "A7";A7.xpos = 0;A7.ypos = 6;A7.occupied = true;
		B7 = new Space();B7.name = "B7";B7.xpos = 1;B7.ypos = 6;B7.occupied = true;
		C7 = new Space();C7.name = "C7";C7.xpos = 2;C7.ypos = 6;C7.occupied = true;
		D7 = new Space();D7.name = "D7";D7.xpos = 3;D7.ypos = 6;D7.occupied = true;
		E7 = new Space();E7.name = "E7";E7.xpos = 4;E7.ypos = 6;E7.occupied = true;
		F7 = new Space();F7.name = "F7";F7.xpos = 5;F7.ypos = 6;F7.occupied = true;
		G7 = new Space();G7.name = "G7";G7.xpos = 6;G7.ypos = 6;G7.occupied = true;
		H7 = new Space();H7.name = "H7";H7.xpos = 7;H7.ypos = 6;H7.occupied = true;
		A8 = new Space();A8.name = "A8";A8.xpos = 0;A8.ypos = 7;A8.occupied = true;
		B8 = new Space();B8.name = "B8";B8.xpos = 1;B8.ypos = 7;B8.occupied = true;
		C8 = new Space();C8.name = "C8";C8.xpos = 2;C8.ypos = 7;C8.occupied = true;
		D8 = new Space();D8.name = "D8";D8.xpos = 3;D8.ypos = 7;D8.occupied = true;
		E8 = new Space();E8.name = "E8";E8.xpos = 4;E8.ypos = 7;E8.occupied = true;
		F8 = new Space();F8.name = "F8";F8.xpos = 5;F8.ypos = 7;F8.occupied = true;
		G8 = new Space();G8.name = "G8";G8.xpos = 6;G8.ypos = 7;G8.occupied = true;
		H8 = new Space();H8.name = "H8";H8.xpos = 7;H8.ypos = 7;H8.occupied = true;

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

		while(!(wk0.taken || wb0.taken)) {

			if ((x % 2) == 0) {
				turn = "White";
			} else {
				turn = "Black";
			}
			x++;
			
			Scanner user_input = new Scanner(System.in);
			String piece;
			String position;
			System.out.println(turn + ", enter the piece you would like to move: ");
			piece = user_input.next();

			switch (piece) {
				case "king":
				case "queen":
				case "bishop":
				case "knight":
				case "rook":
				case "pawn":
					break;
				case "quit":
					System.exit(0);
				default: 
					boolean i = true;
					while (i) {
						System.out.println(turn + ", please enter a valid piece (king, queen, bishop, knight, rook, pawn): ");
						piece = user_input.next();
						switch (piece) {
							case "king":
							case "queen":
							case "bishop":
							case "knight":
							case "rook":
							case "pawn":
								i = false;
								break;
							case "quit":
								System.exit(0);
							default:
								i = true;
								break;
						} //close switch case 2
					} //close while loop
			} //close switch case 1

			System.out.println(turn + ", enter its new position: ");
			position = user_input.next();

			switch (position) {
				case "A1": case "A2": case "A3": case "A4": case "A5": case "A6": case "A7": case "A8":
				case "B1": case "B2": case "B3": case "B4": case "B5": case "B6": case "B7": case "B8":
				case "C1": case "C2": case "C3": case "C4": case "C5": case "C6": case "C7": case "C8":
				case "D1": case "D2": case "D3": case "D4": case "D5": case "D6": case "D7": case "D8":
				case "E1": case "E2": case "E3": case "E4": case "E5": case "E6": case "E7": case "E8":
				case "F1": case "F2": case "F3": case "F4": case "F5": case "F6": case "F7": case "F8":
				case "G1": case "G2": case "G3": case "G4": case "G5": case "G6": case "G7": case "G8":
				case "H1": case "H2": case "H3": case "H4": case "H5": case "H6": case "H7": case "H8":
					break;
				case "quit":
					System.exit(0);
				default: 
					boolean i = true;
					while (i) {
						System.out.println(turn + ", please enter a valid position (example: A1): ");
						position = user_input.next();
						switch (position) {
							case "A1": case "A2": case "A3": case "A4": case "A5": case "A6": case "A7": case "A8":
							case "B1": case "B2": case "B3": case "B4": case "B5": case "B6": case "B7": case "B8":
							case "C1": case "C2": case "C3": case "C4": case "C5": case "C6": case "C7": case "C8":
							case "D1": case "D2": case "D3": case "D4": case "D5": case "D6": case "D7": case "D8":
							case "E1": case "E2": case "E3": case "E4": case "E5": case "E6": case "E7": case "E8":
							case "F1": case "F2": case "F3": case "F4": case "F5": case "F6": case "F7": case "F8":
							case "G1": case "G2": case "G3": case "G4": case "G5": case "G6": case "G7": case "G8":
							case "H1": case "H2": case "H3": case "H4": case "H5": case "H6": case "H7": case "H8":
								i = false;
								break;
							case "quit":
								System.exit(0);
							default:
								i = true;
								break;
						} //close switch case 2
					} //close while loop
			} //close switch case 1

			switch (piece) {
				case "king":
					if (turn == "White") {
						movePiece(wk0, position);
					} else {
						movePiece(bk0, position);
					}
					break;
				case "queen":
					if (turn == "White") {
						movePiece(wq0, position);
					} else {
						movePiece(bq0, position);
					}
					break;
				case "bishop":
					break;
				case "knight":
					break;
				case "rook":
					break;
				case "pawn":
					break;
				default:
					System.out.println("Error. Game terminated.");
					System.exit(0);
			} //close switch case
		} //close main game while loop
	} //close start method

	public void movePiece(Piece movingPiece, String position) {
		toggleSpaceOccupation(movingPiece.xpos, movingPiece.ypos);
		movingPiece.move(position);
		toggleSpaceOccupation(movingPiece.xpos, movingPiece.ypos);
	}

	public void toggleSpaceOccupation(int x, int y) {
		switch (x) {
			case 0:
				switch (y) {
					case 0: A1.toggleOccupation(); break;
					case 1: A2.toggleOccupation(); break;
					case 2: A3.toggleOccupation(); break;
					case 3: A4.toggleOccupation(); break;
					case 4: A5.toggleOccupation(); break;
					case 5: A6.toggleOccupation(); break;
					case 6: A7.toggleOccupation(); break;
					case 7: A8.toggleOccupation(); break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				}
				break;
			case 1:
				switch (y) {
					case 0: B1.toggleOccupation(); break;
					case 1: B2.toggleOccupation(); break;
					case 2: B3.toggleOccupation(); break;
					case 3: B4.toggleOccupation(); break;
					case 4: B5.toggleOccupation(); break;
					case 5: B6.toggleOccupation(); break;
					case 6: B7.toggleOccupation(); break;
					case 7: B8.toggleOccupation(); break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				}
				break;
			case 2:
				switch (y) {
					case 0: C1.toggleOccupation(); break;
					case 1: C2.toggleOccupation(); break;
					case 2: C3.toggleOccupation(); break;
					case 3: C4.toggleOccupation(); break;
					case 4: C5.toggleOccupation(); break;
					case 5: C6.toggleOccupation(); break;
					case 6: C7.toggleOccupation(); break;
					case 7: C8.toggleOccupation(); break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				}
				break;
			case 3:
				switch (y) {
					case 0: D1.toggleOccupation(); break;
					case 1: D2.toggleOccupation(); break;
					case 2: D3.toggleOccupation(); break;
					case 3: D4.toggleOccupation(); break;
					case 4: D5.toggleOccupation(); break;
					case 5: D6.toggleOccupation(); break;
					case 6: D7.toggleOccupation(); break;
					case 7: D8.toggleOccupation(); break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				}
				break;
			case 4:
				switch (y) {
					case 0: E1.toggleOccupation(); break;
					case 1: E2.toggleOccupation(); break;
					case 2: E3.toggleOccupation(); break;
					case 3: E4.toggleOccupation(); break;
					case 4: E5.toggleOccupation(); break;
					case 5: E6.toggleOccupation(); break;
					case 6: E7.toggleOccupation(); break;
					case 7: E8.toggleOccupation(); break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				}
				break;
			case 5:
				switch (y) {
					case 0: F1.toggleOccupation(); break;
					case 1: F2.toggleOccupation(); break;
					case 2: F3.toggleOccupation(); break;
					case 3: F4.toggleOccupation(); break;
					case 4: F5.toggleOccupation(); break;
					case 5: F6.toggleOccupation(); break;
					case 6: F7.toggleOccupation(); break;
					case 7: F8.toggleOccupation(); break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				}
				break;
			case 6:
				switch (y) {
					case 0: G1.toggleOccupation(); break;
					case 1: G2.toggleOccupation(); break;
					case 2: G3.toggleOccupation(); break;
					case 3: G4.toggleOccupation(); break;
					case 4: G5.toggleOccupation(); break;
					case 5: G6.toggleOccupation(); break;
					case 6: G7.toggleOccupation(); break;
					case 7: G8.toggleOccupation(); break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				}
				break;
			case 7:
				switch (y) {
					case 0: H1.toggleOccupation(); break;
					case 1: H2.toggleOccupation(); break;
					case 2: H3.toggleOccupation(); break;
					case 3: H4.toggleOccupation(); break;
					case 4: H5.toggleOccupation(); break;
					case 5: H6.toggleOccupation(); break;
					case 6: H7.toggleOccupation(); break;
					case 7: H8.toggleOccupation(); break;
					default:
						System.out.println("Error. Game terminated.");
						System.exit(0);
						break;
				}
				break;
			default:
				System.out.println("Error. Game terminated.");
				System.exit(0);
				break;
		} //end switch case
	} //close toggleSpaceOccupation method
} //close ChessGame class

class Piece {
	String name;
	String color;
	String type;
	int xpos;
	int ypos;
	int number = 0;
	boolean taken = false;
	
	public void move(String newPosition) {

		System.out.println(name + " was at (" + xpos + ", " + ypos + ").");
		
		//has position and run with right piece, need to accomplish tasks here and call method on space, etc see word doc
		char column = newPosition.charAt(0);
		char row = newPosition.charAt(1);
		switch (column) {
			case 'A': xpos = 0; break;
			case 'B': xpos = 1; break;
			case 'C': xpos = 2; break;
			case 'D': xpos = 3; break;
			case 'E': xpos = 4; break;
			case 'F': xpos = 5; break;
			case 'G': xpos = 6; break;
			case 'H': xpos = 7; break;
			default:
				System.out.println("Error. Game terminated.");
				System.exit(0);
				break;
		} //close switch case
		switch (row) {
			case '1': ypos = 0; break;
			case '2': ypos = 1; break;
			case '3': ypos = 2; break;
			case '4': ypos = 3; break;
			case '5': ypos = 4; break;
			case '6': ypos = 5; break;
			case '7': ypos = 6; break;
			case '8': ypos = 7; break;
			default:
				System.out.println("Error. Game terminated.");
				System.exit(0);
				break;
		} //close switch case
		System.out.println(name + " is now at (" + xpos + ", " + ypos + ").");		
	}
}

//ChessLauncher class to create and start the game
public class ChessLauncher {
	
	//main method to start chess game by creating new object and running the start method
	public static void main (String[] args) {
		ChessGame game = new ChessGame(); //create new ChessGame object called game
		game.start(); //run start method
	}
}