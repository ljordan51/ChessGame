""" ChessGame
    Author: Lakhvinder "Lucky" Jordan
    Date: 5/17/2017
"""

import pygame
import os

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
    path = os.path.join("pieces", filename + '.png')
    img = pygame.image.load(path).convert_alpha()
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
        self.moved = False

    def move(self, new_x, new_y):
        self.moved = True
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
        super().__init__(x, y, col)

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
        super().__init__(x, y, col)

    def legal(self, new_x, new_y, taking=True):
        if new_x == self.x and new_y == self.y:
            return False
        xdif = abs(new_x-self.x)
        ydif = abs(new_y-self.y)
        if not (xdif == 0 or ydif == 0):
            return False
        return True


class pawn(piece):
    def __init__(self, x, y, col=1):
        self.type = 'P'
        super().__init__(x, y, col)

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
            if move.lower() == 'testing':
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
        if turn % 2 == 1:
            pieces = self.w_pcs
            opposing = self.b_pcs
        else:
            pieces = self.b_pcs
            opposing = self.w_pcs
        need_list = True
        attacker = self.check(turn, need_list)
        if attacker:
            attacker = opposing[attacker[0]]
            kx, ky = pieces[0].x, pieces[0].y
            ax, ay = attacker.x, attacker.y
            spaces = self.get_indices_between_spaces(kx, ky, ax, ay)
            pieces = [(index, piece) for index, piece in enumerate(pieces) if piece.active]
            moves = []
            for x, y in spaces:
                if x == ax and y == ay:
                    taking = True
                else:
                    taking = False
                options = self.any_piece_move_legal(turn, pieces, x, y, need_list=True, taking=taking)
                if options:
                    moves.append((options, x, y))
            no_moves = True
            for piece_indices, x, y in moves:
                if x == ax and y == ay:
                    taking = True
                else:
                    taking = False
                move = 'P' + self.get_space_from_pos(x, y)
                ans = self.check_after_move(piece_indices, move, turn, taking)
                no_moves = no_moves and ans[0]
            if no_moves:
                king_moves = []
                for i in range(3):
                    king_moves.append((kx-1, ky+(i-1)))
                    king_moves.append((kx, ky+(i-1)))
                    king_moves.append((kx+1, ky+(i-1)))
                king_moves_filt = []
                for move in king_moves:
                    if move != (kx, ky) and str(move[0]) in ROWS and str(move[1]) in ROWS:
                        king_moves_filt.append(move)
                no_moves = True
                for king_move in king_moves_filt:
                    move = 'K' + self.get_space_from_pos(king_move[0], king_move[1])
                    if self.check_occupation(move, turn) == 'empty':
                        ans = self.check_after_move([0], move, turn, taking=False)
                        no_moves = no_moves and ans[0]
                if no_moves:
                    return True
        return False

    def get_indices_between_spaces(self, x1, y1, x2, y2):
        spaces = []
        xdif = x1-x2
        ydif = y1-y2
        if xdif == 0:
            yfact = ydif/abs(ydif)
            for i in range(abs(ydif)):
                spaces.append((int(x2), int(y2+yfact*i)))
        elif ydif == 0:
            xfact = xdif/abs(xdif)
            for i in range(abs(xdif)):
                spaces.append((int(x2+xfact*i), int(y2)))
        elif abs(xdif) == abs(ydif):
            xfact = xdif/abs(xdif)
            yfact = ydif/abs(ydif)
            for i in range(abs(ydif)):
                spaces.append((int(x2+xfact*i), int(y2+yfact*i)))
        else:
            spaces.append((x2, y2))
        return spaces

    def check_occupation(self, move, turn):
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
                        result = [True, [6]]
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
                        result = [True, [7]]
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
            else:
                result = [True, indices]
        return result

    def multiple_legals(self, legals, indices):
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
        space = ''
        space = space + COLS[x-1]
        space = space + str(y)
        return space

    def check_path(self, piece_indices, move, turn):
        if turn % 2 == 1:
            pieces = self.w_pcs
            row = '1'
        else:
            pieces = self.b_pcs
            row = '8'
        if '0-0' in move:
            move = 'RE' + row
        pos = self.get_pos_from_move(move)
        options = []
        for piece_index in piece_indices:
            valid_option = True
            piece = pieces[piece_index]
            xdif = pos[0] - piece.x
            ydif = pos[1] - piece.y
            p_type = piece.type.lower()
            if p_type == 'k':
                options.append(piece_index)
            elif p_type == 'q':
                if xdif == 0:
                    for i in range(1, abs(ydif)):
                        if self.check_space(piece.x, piece.y + i*(ydif/abs(ydif)), 'Q', turn):
                            valid_option = False
                elif ydif == 0:
                    for i in range(1, abs(xdif)):
                        if self.check_space(piece.x + i*(xdif/abs(xdif)), piece.y, 'Q', turn):
                            valid_option = False
                else:
                    for i in range(1, abs(ydif)):
                        if self.check_space(piece.x + i*(xdif/abs(xdif)), piece.y + i*(ydif/abs(ydif)), 'Q', turn):
                            valid_option = False
                if valid_option:
                    options.append(piece_index)
            elif p_type == 'b':
                for i in range(1, abs(ydif)):
                    if self.check_space(piece.x + i*(xdif/abs(xdif)), piece.y + i*(ydif/abs(ydif)), 'B', turn):
                        valid_option = False
                if valid_option:
                    options.append(piece_index)
            elif p_type == 'n':
                options.append(piece_index)
            elif p_type == 'r':
                if xdif == 0:
                    for i in range(1, abs(ydif)):
                        if self.check_space(piece.x, piece.y + i*(ydif/abs(ydif)), 'R', turn):
                            valid_option = False
                else:
                    for i in range(1, abs(xdif)):
                        if self.check_space(piece.x + i*(xdif/abs(xdif)), piece.y, 'R', turn):
                            valid_option = False
                if valid_option:
                    options.append(piece_index)
            else:
                if (not piece.moved) and (move[2] == '4' or move[2] == '5'):
                    if self.check_space(piece.x, piece.y + ydif/2, 'P', turn):
                        valid_option = False
                if valid_option:
                    options.append(piece_index)
        if len(options) == 0:
            result = [False]
        else:
            result = [True, options]
        return result

    def check_space(self, x, y, piece_type, turn):
        """ Helper func for check_path to reduce copied and pasted code.
        """
        space = self.get_space_from_pos(int(x), int(y))
        move = piece_type + space
        status = self.check_occupation(move, turn)
        if status != 'empty':
            return True
        return False

    def check_after_move(self, piece_indices, move, turn, taking):
        indices = []
        pieces = []
        for piece_index in piece_indices:
            if turn % 2 == 1:
                piece = self.w_pcs[piece_index]
                opposing = self.b_pcs
            else:
                piece = self.b_pcs[piece_index]
                opposing = self.w_pcs
            if taking:
                pos = self.get_pos_from_move(move)
                for enemy_index, enemy in enumerate(opposing):
                    if enemy.x == pos[0] and enemy.y == pos[1]:
                        tx, ty, tactive, tmoved = enemy.x, enemy.y, enemy.active, enemy.moved
                        taken_index = enemy_index
            px, py, pmoved = piece.x, piece.y, piece.moved
            self.move_piece(piece_index, move, turn, taking)
            res = self.check(turn)
            if turn % 2 == 1:
                self.w_pcs[piece_index].x = px
                self.w_pcs[piece_index].y = py
                self.w_pcs[piece_index].moved = pmoved
                piece = self.w_pcs[piece_index]
                if taking:
                    self.b_pcs[taken_index].x = tx
                    self.b_pcs[taken_index].y = ty
                    self.b_pcs[taken_index].active = tactive
                    self.b_pcs[taken_index].moved = tmoved
            else:
                self.b_pcs[piece_index].x = px
                self.b_pcs[piece_index].y = py
                self.b_pcs[piece_index].moved = pmoved
                piece = self.b_pcs[piece_index]
                if taking:
                    self.w_pcs[taken_index].x = tx
                    self.w_pcs[taken_index].y = ty
                    self.w_pcs[taken_index].active = tactive
                    self.w_pcs[taken_index].moved = tmoved
            if '0-0' in move:
                if turn % 2 == 1:
                    self.w_pcs[0].x = E
                    self.w_pcs[0].y = 1
                    self.w_pcs[0].moved = False
                else:
                    self.b_pcs[0].x = E
                    self.b_pcs[0].y = 8
                    self.b_pcs[0].moved = False
            if not res:
                pieces.append(piece)
                indices.append(piece_index)
        if len(pieces) == 0:
            return [True]
        elif len(pieces) == 1:
            return [False, indices[0]]
        else:
            index = self.multiple_legals(pieces, indices)
            return [False, index]

    def check(self, turn, need_list=False):
        if turn % 2 == 1:
            x = self.w_pcs[0].x
            y = self.w_pcs[0].y
            pieces = [(index, piece) for index, piece in enumerate(self.b_pcs) if piece.active and piece.type.lower() not in ['k', 'p']]
        else:
            x = self.b_pcs[0].x
            y = self.b_pcs[0].y
            pieces = [(index, piece) for index, piece in enumerate(self.w_pcs) if piece.active and piece.type.lower() not in ['k', 'p']]
        turn += 1
        return self.any_piece_move_legal(turn, pieces, x, y, need_list)

    def any_piece_move_legal(self, turn, pieces, x, y, need_list=False, taking=False):
        options = []
        for piece_index, piece in pieces:
            if piece.legal(x, y, taking):
                move = 'P' + self.get_space_from_pos(x, y)
                if self.check_path([piece_index], move, turn)[0]:
                    if need_list:
                        options.append(piece_index)
                    else:
                        return True
        if need_list:
            return options
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
        x = COLS.index(move[1]) + 1
        y = int(move[2])
        return [x, y]

    def draw(self, screen):
        for piece in self.w_pcs:
            if piece.active:
                screen.blit(w_imgs[piece.type.lower()], (piece.x*BLOCK, (9-piece.y)*BLOCK))
        for piece in self.b_pcs:
            if piece.active:
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
            if turn % 2 == 1:
                print("Black WINS!")
            else:
                print("White WINS!")
            invalid = True
            while invalid:
                q = "Would you like to play again? Y or N?"
                reset = input(q)
                if reset.lower() in ['y', 'n']:
                    invalid = False
                else:
                    print('Please enter Y or N.')
            if reset.lower() == 'y':
                Game.reset()
                turn = 1
            else:
                running = False
            continue

        move = Game.get_input(turn)
        if move == 'quit':
            running = False
            continue
        if move == 'reset':
            Game.reset()
            turn = 1
            continue
        if move == 'testing':
            for piece in Game.w_pcs:
                if piece.type.lower() == 'p':
                    piece.taken()
            for piece in Game.b_pcs:
                if piece.type.lower() != 'k' and piece.type.lower() != 'q':
                    piece.taken()
            continue

        occupied = Game.check_occupation(move, turn)
        if occupied == 'blocked':
            print('That space is occupied by a piece of the same color. Try again.')
            continue
        elif occupied == 'take':
            taking = True
        else:
            taking = False

        legal = Game.check_legality(move, turn, taking)
        if not legal[0]:
            print('Your move is illegal. Try again.')
            continue

        piece_indices = legal[1]

        clearPath = Game.check_path(piece_indices, move, turn)
        if not clearPath[0]:
            print('There is at least one piece blocking your path. Try again.')
            continue

        piece_indices = clearPath[1]

        check = Game.check_after_move(piece_indices, move, turn, taking)
        if check[0]:
            print('That move leaves your king in check. Try again.')
            continue

        piece_index = check[1]
        if piece_index == 'quit':
            playing = False
            running = False
            continue
        if piece_index == 'reset':
            Game.reset()
            turn = 1
            continue

        Game.move_piece(piece_index, move, turn, taking)

        turn += 1

    pygame.quit()


if __name__ == '__main__':
    main()
