Chess Game
An exercise in Python OOP
DEPENDENCIES: pygame

This program runs a virtual chess game by initializing the pieces and keeping track of their
movements across the board. The challenge of this game is ensuring that all moves are legal.
The important tasks it must accomplish are listed below. They form a basic summary of the main
while loop that controls the program.

1. Initialize a game containing an object for each piece on the board.

2. Draw the board and pieces.

3. Confirm that the king is not in checkmate or otherwise end the game.

4. Get user input and confirm the input is valid or otherwise get new input.

5. Check target space. If blocked, get new input. If empty or guarded, store that information.

6. Find all pieces which match the move input and can legally carry out the move. If none are found, get new input.

7. Of the legal pieces which match the input, find all pieces whose paths are clear based on the movement rules for each chess piece type. If none are found, get new input.

8. Of the legal pieces which match the input with a clear path, find all pieces whose moves do not leave the king in check. If none are found, get new input.

9. If multiple pieces match the input, can legally make the move, have a clear path, and do not leave the king in check, ask the user to specify which of these pieces they'd like to move.

10. Move the piece. Remove the taken piece if applicable.

11. Repeat steps 2-10 until checkmate is achieved.

The motivation behind this program is to eventually use it to control a physical chess board which controls the movement of pieces
for the players based on some kind of computer input (whether that is typing, talking or some other form). Hopefully, this could
eventually become a set of two remote chess boards, such that players could play physical chess games with anybody, anywhere.

Additional features:
undo - undoes last move
quit - quits the game and closes the pygame window
reset - resets the game
testing - removes all white pawns and all black pieces other than the king and queen in order to test certain functions more easily
help - prints examples of valid user input

Artwork obtained from https://commons.wikimedia.org/wiki/File:AAA_SVG_Chessboard_and_chess_pieces_06.svg but edited/formatted
in adobe illustrator by myself in order to work properly for this game and improve overall aesthetics.

Project was originally started in Java but after taking an introduction to software design course in Python, I switched over.
