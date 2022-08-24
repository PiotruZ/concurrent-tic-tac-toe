package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImpl implements TicTacToe {

    final static char xM='X';
    final static char oM='O';
    static char prevMark =oM;

    static char[][] board;

    public TicTacToeImpl() {
        newBoard();
    }

    @Override
    public void setMark(int x, int y, char mark) {
        if(x>2||y>2||(mark!=xM&&mark!=oM))
            throw new IllegalArgumentException();
        if (board[x][y]==' ') {
            board[x][y] = mark;
            prevMark = mark;
        }
        else
            throw new IllegalArgumentException();
    }

    @Override
    public char[][] table() {
        return Arrays.stream(board).map(char[]::clone).toArray(char[][]::new); //clone the board to a new char table
    }

    @Override
    public char lastMark() {
        return prevMark;
    }

    static private void newBoard() {
        board= new char[][]{{' ', ' ', ' '},
                            {' ', ' ', ' '},
                            {' ', ' ', ' '},
        };
    }
}
