import pygame

BLOCK = 100
WINDOW = BLOCK*9
A = 1
B = 2
C = 3
D = 4
E = 5
F = 6
G = 7
H = 8
ROWS = '12345678'
COLS = 'ABCDEFGH'

pygame.init()
screen = pygame.display.set_mode((WINDOW, WINDOW))  # initialize and display window

BOARD = pygame.image.load('chess_board.png').convert_alpha()
w_imgs = {}
b_imgs = {}
im_files = ['wk', 'wq', 'wb', 'wn', 'wr', 'wp', 'bk', 'bq', 'bb', 'bn', 'br', 'bp']
for filename in im_files:
    img = pygame.image.load(filename + '.png').convert_alpha()
    if filename[0] == 'w':
        w_imgs[filename[1]] = img
    else:
        b_imgs[filename[1]] = img


class piece(object):
    def __init__(self, x, y, col=1):
        self.x = x
        self.y = y
        self.col = col
        self.active = True

    def move(self, new_x, new_y):
        self.x = new_x
        self.y = new_y

    def taken(self):
        self.x = 0
        self.y = 0
        self.active = False

    def legal(self, new_x, new_y, taking=True):
        return True


class king(piece):
    def __init__(self, x, y, col=1):
        self.type = 'K'
        self.moved = False
        super().__init__(x, y, col)

    def move(self, new_x, new_y):
        self.moved = True
        super().move(new_x, new_y)

    def legal(self, new_x, new_y, taking=True):
        if new_x == self.x and new_y == self.y:
            return False
        xdif = abs(new_x-self.x)
        ydif = abs(new_y-self.y)
        if not (xdif <= 1 and ydif <= 1):
            return False
        return True


class queen(piece):
    def __init__(self, x, y, col=1):
        self.type = 'Q'
        super().__init__(x, y, col)

    def legal(self, new_x, new_y, taking=True):
        if new_x == self.x and new_y == self.y:
            return False
        xdif = abs(new_x-self.x)
        ydif = abs(new_y-self.y)
        if not (xdif == ydif or xdif == 0 or ydif == 0):
            return False
        return True


class bishop(piece):
    def __init__(self, x, y, col=1):
        self.type = 'B'
        super().__init__(x, y, col)

    def legal(self, new_x, new_y, taking=True):
        if new_x == self.x and new_y == self.y:
            return False
        xdif = abs(new_x-self.x)
        ydif = abs(new_y-self.y)
        if not xdif == ydif:
            return False
        return True


class knight(piece):
    def __init__(self, x, y, col=1):
        self.type = 'N'
        super().__init__(x, y, col)

    def legal(self, new_x, new_y, taking=True):
        xdif = abs(new_x-self.x)
        ydif = abs(new_y-self.y)
        if not ((xdif == 2 and ydif == 1) or (xdif == 1 and ydif == 2)):
            return False
        return True


class rook(piece):
    def __init__(self, x, y, col=1):
        self.type = 'R'
        self.moved = False
        super().__init__(x, y, col)

    def move(self, new_x, new_y):
        self.moved = True
        super().move(new_x, new_y)

    def legal(self, new_x, new_y, taking=True):
        if new_x == self.x and new_y == self.y:
            return False
        xdif = abs(new_x-self.x)
        ydif = abs(new_y-self.y)
        if not (xdif == 0 or ydif == 0):
            return False
        return True


class pawn(piece):
    """
        >>> pawnw = pawn(3, 2)
        >>> pawnw.legal(3, 3)
        True
        >>> pawnw.legal(4, 3)
        False
        >>> pawnw.legal(4, 3, True)
        True
        >>> pawnw.legal(3, 4)
        True
        >>> pawnw.legal(3, 4, True)
        False
        >>> pawnw.move(3, 3)
        >>> pawnw.legal(3, 5)
        False
        >>> pawnb = pawn(3, 7, 1, 2)
        >>> pawnb.legal(3, 6)
        True
        >>> pawnb.legal(4, 6)
        False
        >>> pawnb.legal(4, 6, True)
        True
        >>> pawnb.legal(3, 5)
        True
        >>> pawnb.legal(3, 5, True)
        False
        >>> pawnb.move(3, 6)
        >>> pawnb.legal(3, 4)
        False
    """
    def __init__(self, x, y, col=1):
        self.type = 'P'
        self.moved = False
        super().__init__(x, y, col)

    def move(self, new_x, new_y):
        self.moved = True
        super().move(new_x, new_y)

    def legal(self, new_x, new_y, taking=True):
        fact = -2*self.col+3
        xdif = new_x-self.x
        ydif = new_y-self.y
        if (not taking) and (not self.moved) and xdif == 0 and ydif == 2*fact:
            return True
        if taking:
            if abs(xdif) == 1 and ydif == 1*fact:
                return True
            else:
                return False
        if not (xdif == 0 and ydif == 1*fact):
            return False
        return True


class game(object):
    def __init__(self):
        wk = king(E, 1)
        wq = queen(D, 1)
        wb1 = bishop(C, 1)
        wb2 = bishop(F, 1)
        wh1 = knight(B, 1)
        wh2 = knight(G, 1)
        wr1 = rook(A, 1)
        wr2 = rook(H, 1)
        wp1 = pawn(A, 2)
        wp2 = pawn(B, 2)
        wp3 = pawn(C, 2)
        wp4 = pawn(D, 2)
        wp5 = pawn(E, 2)
        wp6 = pawn(F, 2)
        wp7 = pawn(G, 2)
        wp8 = pawn(H, 2)
        bk = king(E, 8, 2)
        bq = queen(D, 8, 2)
        bb1 = bishop(C, 8, 2)
        bb2 = bishop(F, 8, 2)
        bh1 = knight(B, 8, 2)
        bh2 = knight(G, 8, 2)
        br1 = rook(A, 8, 2)
        br2 = rook(H, 8, 2)
        bp1 = pawn(A, 7, 2)
        bp2 = pawn(B, 7, 2)
        bp3 = pawn(C, 7, 2)
        bp4 = pawn(D, 7, 2)
        bp5 = pawn(E, 7, 2)
        bp6 = pawn(F, 7, 2)
        bp7 = pawn(G, 7, 2)
        bp8 = pawn(H, 7, 2)
        self.w_pcs = [wk, wq, wb1, wb2, wh1, wh2, wr1, wr2, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8]
        self.b_pcs = [bk, bq, bb1, bb2, bh1, bh2, br1, br2, bp1, bp2, bp3, bp4, bp5, bp6, bp7, bp8]

    def get_input(self, turn):
        if turn % 2 == 1:
            q = "White, what is your move?"
        else:
            q = "Black, what is your move?"
        wrong = True
        while wrong:
            move = input(q)
            if move.lower() == 'quit':
                return move.lower()
            if move.lower() == 'reset':
                return move.lower()
            if move.lower() == 'help':
                self.print_opts()
                continue
            wrong = not self.check_input(move)
        return move

    def print_opts(self):
        print('Input examples:' + '\n' + 'PE4 = pawn to E4' + '\n' + 'RE4 = rook to E4' + '\n' + 'NE4 = knight to E4'
              + '\n' + 'BE4 = bishop to E4' + '\n' + 'QE4 = queen to E4' + '\n' + 'KE4 = king to E4' + '\n' +
              '0-0-0 = queenside castle' + '\n' + '0-0 = kingside castle')

    def check_input(self, move):
        """ Checks string input 'move' to see if it is an appropriate input.

            move: 3 or 5 letter string with user input for move ex: 'PE7'

            >>> Game = game()
            >>> Game.check_input('PE7')
            True
            >>> Game.check_input('0-0')
            True
            >>> Game.check_input('0-0-0')
            True
            >>> Game.check_input('PE9')
            False
            >>> Game.check_input('PJ7')
            False
            >>> Game.check_input('LE7')
            False
            >>> Game.check_input('P-0')
            False
            >>> Game.check_input('0-E')
            False
            >>> Game.check_input('PE7X')
            False
        """
        ones = 'PRNBQK'
        if move == '0-0' or move == '0-0-0':
            return True
        elif len(move) == 3:
            one = move[0]
            two = move[1]
            three = move[2]
            if one in ones and two in COLS and three in ROWS:
                return True
        print('Invalid input. Try again.')
        return False

    def checkmate(self, turn):
        return False

    def check_occupation(self, move, turn):
        """
            >>> Game = game()
            >>> Game.check_occupation('PA1', 1)
            'blocked'
            >>> Game.check_occupation('KC5', 2)
            'empty'
            >>> Game.check_occupation('BH8', 1)
            'take'
        """
        if '0-0' in move:
            return 'empty'
        pos = self.get_pos_from_move(move)
        if turn % 2 == 1:
            for piece in self.b_pcs:
                if piece.x == pos[0] and piece.y == pos[1]:
                    return "take"
            for piece in self.w_pcs:
                if piece.x == pos[0] and piece.y == pos[1]:
                    return "blocked"
        else:
            for piece in self.b_pcs:
                if piece.x == pos[0] and piece.y == pos[1]:
                    return "blocked"
            for piece in self.w_pcs:
                if piece.x == pos[0] and piece.y == pos[1]:
                    return "take"
        return "empty"

    def check_legality(self, move, turn, taking):
        if turn % 2 == 1:
            pieces = self.w_pcs
        else:
            pieces = self.b_pcs
        if move == '0-0-0':
            king = pieces[0]
            rook = pieces[6]
            if king.moved or rook.moved:
                result = [False]
            else:
                if self.check(turn):
                    result = [False]
                else:
                    pieces[0].x = D
                    if self.check(turn):
                        result = [False]
                    else:
                        result = [True, 6]
                    pieces[0].x = E
        elif move == '0-0':
            king = pieces[0]
            rook = pieces[7]
            if king.moved or rook.moved:
                result = [False]
            else:
                if self.check(turn):
                    result = [False]
                else:
                    pieces[0].x = F
                    if self.check(turn):
                        result = [False]
                    else:
                        result = [True, 7]
                    pieces[0].x = E
        else:
            legals = []
            indices = []
            pos = self.get_pos_from_move(move)
            l = move[0].lower()
            potentials = [(index, piece) for index, piece in enumerate(pieces) if piece.type.lower() == l and piece.active]
            for index, piece in potentials:
                if piece.legal(pos[0], pos[1], taking):
                    legals.append(piece)
                    indices.append(index)
            if len(legals) == 0:
                result = [False]
            elif len(legals) == 1:
                result = [True, indices[0]]
            else:
                index = self.multiple_legals(legals, indices)
                result = [True, index]
        return result

    def multiple_legals(self, legals, indices):
        """
            The below doctest doesn't work with the pawn object. Change the output of this function to some attributes of the piece
            to determine if it is working. Also remove the !'s because this doctest shouldn't run normally otherwise
            it will always fail unless the program is altered to not work.

            !>>> Game = game()
            !>>> Game.multiple_legals(Game.w_pcs[8:11])
            Multiple pieces can make that move. Please choose the space that the piece you want to move is on from the choices below.
            A2
            B2
            C2
            [1, 2]
        """
        spaces = []
        print('Multiple pieces can make that move. Please choose the space that the piece you want to move is on from the choices below.')
        for piece in legals:
            space = self.get_space_from_pos(piece.x, piece.y)
            spaces.append(space)
            print(space)
        not_choice = True
        while not_choice:
            space = input()
            if space.lower() == 'quit':
                return space.lower()
            if space.lower() == 'reset':
                return space.lower()
            if space in spaces:
                not_choice = False
                index = indices[spaces.index(space)]
            else:
                print("That wasn't one of the options. Try again.")
        return index

    def get_space_from_pos(self, x, y):
        """
            >>> Game = game()
            >>> Game.get_space_from_pos(1, 1)
            'A1'
            >>> Game.get_space_from_pos(3, 5)
            'C5'
            >>> Game.get_space_from_pos(8, 8)
            'H8'
        """
        space = ''
        space = space + COLS[x-1]
        space = space + str(y)
        return space

    def check_path(self, piece_index, move, turn):
        if turn % 2 == 1:
            pieces = self.w_pcs
            row = '1'
        else:
            pieces = self.b_pcs
            row = '8'
        if '0-0' in move:
            move = 'RE' + row
        pos = self.get_pos_from_move(move)
        piece = pieces[piece_index]
        xdif = pos[0] - piece.x
        ydif = pos[1] - piece.y
        p_type = piece.type.lower()
        if p_type == 'k':
            return True
        elif p_type == 'q':
            if xdif == 0:
                for i in range(1, abs(ydif)):
                        if self.check_space(piece.x, piece.y + i*(ydif/abs(ydif)), 'Q', turn):
                            return False
            elif ydif == 0:
                for i in range(1, abs(xdif)):
                        if self.check_space(piece.x + i*(xdif/abs(xdif)), piece.y, 'Q', turn):
                            return False
            else:
                for i in range(1, abs(ydif)):
                    if self.check_space(piece.x + i*(xdif/abs(xdif)), piece.y + i*(ydif/abs(ydif)), 'Q', turn):
                        return False
            return True
        elif p_type == 'b':
            for i in range(1, abs(ydif)):
                if self.check_space(piece.x + i*(xdif/abs(xdif)), piece.y + i*(ydif/abs(ydif)), 'B', turn):
                    return False
            return True
        elif p_type == 'n':
            return True
        elif p_type == 'r':
            if xdif == 0:
                for i in range(1, abs(ydif)):
                    if self.check_space(piece.x, piece.y + i*(ydif/abs(ydif)), 'R', turn):
                        return False
            else:
                for i in range(1, abs(xdif)):
                    if self.check_space(piece.x + i*(xdif/abs(xdif)), piece.y, 'R', turn):
                        return False
            return True
        else:
            if (not piece.moved) and (move[2] == '4' or move[2] == '5'):
                if self.check_space(piece.x, piece.y + ydif/2, 'P', turn):
                    return False
            return True

    def check_space(self, x, y, piece_type, turn):
        """ Helper func for check_path to reduce copied and pasted code.
        """
        space = self.get_space_from_pos(int(x), int(y))
        move = piece_type + space
        status = self.check_occupation(move, turn)
        if status != 'empty':
            return True
        return False

    def check_after_move(self, piece_index, move, turn):
        return False

    def check(self, turn):
        if turn % 2 == 1:
            x = self.w_pcs[0].x
            y = self.w_pcs[0].y
            pieces = self.b_pcs
        else:
            x = self.b_pcs[0].x
            y = self.b_pcs[0].y
            pieces = self.w_pcs
        turn += 1
        for piece in pieces:
            if piece.legal(x, y):
                piece_index = pieces.index(piece)
                move = 'P' + self.get_space_from_pos(x, y)
                if self.check_path(piece_index, move, turn):
                    return True
        return False

    def move_piece(self, piece_index, move, turn, taking):
        if '0-0' in move:
            self.castling(piece_index, move, turn)
        else:
            pos = self.get_pos_from_move(move)
            if taking:
                if turn % 2 == 1:
                    for piece in self.b_pcs:
                        if piece.x == pos[0] and piece.y == pos[1]:
                            piece.taken()
                else:
                    for piece in self.w_pcs:
                        if piece.x == pos[0] and piece.y == pos[1]:
                            piece.taken()
            if turn % 2 == 1:
                self.w_pcs[piece_index].move(pos[0], pos[1])
                if self.w_pcs[piece_index].type.lower() == 'p' and pos[1] == 8:
                    self.w_pcs.append(queen(pos[0], pos[1]))
                    self.w_pcs[piece_index].taken()
            else:
                self.b_pcs[piece_index].move(pos[0], pos[1])
                if self.b_pcs[piece_index].type.lower() == 'p' and pos[1] == 1:
                    self.b_pcs.append(queen(pos[0], pos[1]))
                    self.b_pcs[piece_index].taken()

    def castling(self, piece_index, move, turn):
        if turn % 2 == 1:
            row = 1
            if move == '0-0-0':
                self.w_pcs[piece_index].move(D, row)
                self.w_pcs[0].move(C, row)
            elif move == '0-0':
                self.w_pcs[piece_index].move(F, row)
                self.w_pcs[0].move(G, row)
        else:
            row = 8
            if move == '0-0-0':
                self.b_pcs[piece_index].move(D, row)
                self.b_pcs[0].move(C, row)
            elif move == '0-0':
                self.b_pcs[piece_index].move(F, row)
                self.b_pcs[0].move(G, row)

    def get_pos_from_move(self, move):
        """
            >>> Game = game()
            >>> Game.get_pos_from_move('PA1')
            [1, 1]
            >>> Game.get_pos_from_move('KC5')
            [3, 5]
            >>> Game.get_pos_from_move('BH8')
            [8, 8]
        """
        x = COLS.index(move[1]) + 1
        y = int(move[2])
        return [x, y]

    def draw(self, screen):
        for piece in self.w_pcs:
            screen.blit(w_imgs[piece.type.lower()], (piece.x*BLOCK, (9-piece.y)*BLOCK))
        for piece in self.b_pcs:
            screen.blit(b_imgs[piece.type.lower()], (piece.x*BLOCK, (9-piece.y)*BLOCK))

    def reset(self):
        self.__init__()


def main():
    print('Welcome to CHESS! by Lucky Jordan.\nType help for examples of commands.\nType quit to quit.\nType reset to reset the board.')
    Game = game()
    turn = 1

    running = True
    playing = True
    while running:
        screen.blit(BOARD, (0, 0))
        Game.draw(screen)
        pygame.display.update()

        checkmate = Game.checkmate(turn)
        if checkmate:
            print('CHECKMATE!')
            running = False
            continue

        move = Game.get_input(turn)
        if move == 'quit':
            playing = False
            running = False
            continue
        if move == 'reset':
            Game.reset()
            turn = 1
            continue

        occupied = Game.check_occupation(move, turn)
        if occupied == 'blocked':
            print('That space is occupied by a piece of the same color. Try again.')
            continue
        elif occupied == 'take':
            taking = True
        else:
            taking = False

        legality_and_piece = Game.check_legality(move, turn, taking)
        if not legality_and_piece[0]:
            print('Your move is illegal. Try again.')
            continue

        piece_index = legality_and_piece[1]
        if piece_index == 'quit':
            playing = False
            running = False
            continue
        if piece_index == 'reset':
            Game.reset()
            turn = 1
            continue

        clearPath = Game.check_path(piece_index, move, turn)
        if not clearPath:
            print('There is at least one piece blocking your path. Try again.')
            continue

        check = Game.check_after_move(piece_index, move, turn)
        if check:
            print('That move leaves your king in check. Try again.')
            continue

        Game.move_piece(piece_index, move, turn, taking)
        """ need to handle queening of pawns
            need to handle checkmate
            need to add good commenting
            need to add good funcs for troubleshooting code
            need to make functions more modular so as to not edit good code too much (check path)
            need to fix multiple_legals because doesn't account for clearPath
        """

        turn += 1

    if playing:
        if turn % 2 == 1:
            print("Black WINS!")
        else:
            print("White WINS!")

    pygame.quit()


if __name__ == '__main__':
    main()
    # import doctest
    # doctest.testmod(verbose=True)
