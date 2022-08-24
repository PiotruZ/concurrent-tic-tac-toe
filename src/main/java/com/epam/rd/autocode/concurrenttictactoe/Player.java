package com.epam.rd.autocode.concurrenttictactoe;

public interface Player extends Runnable{

    static boolean wonBoard(char[][] table, char mark){
        for(int i=0; i<=2;i++){
            if(table[0][i]==mark&&table[1][i]==mark&&table[2][i]==mark)
                return true;
            if(table[i][0]==mark&&table[i][1]==mark&&table[i][2]==mark)
                return true;
        }
        return table[0][0] == mark && table[1][1] == mark && table[2][2] == mark
                || table[0][2] == mark && table[1][1] == mark && table[2][0] == mark;
    }

    static Player createPlayer(final TicTacToe ticTacToe, final char mark, PlayerStrategy strategy) {

        Object foo = new Object();

        return () -> {
            synchronized (foo) {
                try {
                    do {
                        while (mark == ticTacToe.lastMark()) { // if the last placed mark is the same as this player's mark wait for the other thread
                            foo.wait();
                        }
                        ticTacToe.setMark(strategy.computeMove(mark, ticTacToe).row, strategy.computeMove(mark, ticTacToe).column, mark);
                    } while (!wonBoard(ticTacToe.table(), mark)); //check if the board has 3 consecutive marks on any line
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
