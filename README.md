Chess Game
This program is intended to simulate a virtual chess game by initializing the spaces and pieces and keeping track of their
movements across the board. The challenge of this game is ensuring that all moves are legal. The important tasks it
must accomplish are listed below.

1. Initialize spaces and pieces. In this task it must create the piece objects and know their status (untaken at the beginning) as well as
their location and type. For the space objects, it must know there location and occupation status.

2. Request user input (white first).

3. Identify which piece is to be moved (if there are multiple of that type of piece on the board).

4. Check the legality of the player's move based on the movement rules for each chess piece type.

5. Check that the path of the piece is clear based on the movement rules for each chess piece type.

6. Check that the final destination is not obstructed by a piece of its own color.

7. Check if the final destination contains a piece of the opposite color, in which case it must identify the move as a taking move.

8. Check to make sure the move doesn't violate any laws of checking the king.

9. Move the piece. Change the piece's location, change the old space to unoccupied, and change the new space to occupied.
*In the case of a taking move, it must keep the new space as occupied and remove the taken piece and change its status to taken.

10. Check to see if check mate has been achieved and terminate the game if so.

The motivation behind this program is to eventually use it to control a physical chess board which controls the movement of pieces
for the players based on some kind of computer input (whether that is typing, talking or some other form).
