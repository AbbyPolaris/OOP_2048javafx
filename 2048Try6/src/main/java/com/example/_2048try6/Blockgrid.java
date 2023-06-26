package com.example._2048try6;

import java.util.ArrayList;
import java.util.Random;

public class Blockgrid extends AbstractBlockgrid {
    Block[][] blocks = new Block[4][4];
    int Score = 0;
    boolean madeMove = false;
    final int winScore = 256;

    @Override
    boolean checkEnd() {
        boolean flag1 = addNewBlock();
        boolean flag2 = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ArrayList<GridPoint> Neibs = blocks[i][j].point.neighbors();
                for (GridPoint G :
                        Neibs) {
                    if (blocks[G.y][G.x].theNum == blocks[i][j].theNum && blocks[i][j].theNum != 0) {
                        flag2 = true;

                    }
                }

            }
        }
        //System.out.println(flag1);

        return !(flag1 || flag2);
    }

    @Override
    boolean addNewBlock() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j].theNum == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    void up() {
        boolean[] flags = new boolean[4];
        madeMove = false;
        for (int i = 0; i < 3; i++) {
            for (int row = 0; row < 3; row++)
                for (int column = 0; column < 4; column++) // the 'for' parameters might change for other directions
                    if (isBlockEmpty(row, column)) {
                        Block B = new Block();
                        B.point = new GridPoint(column, row);
                        B.theNum = blocks[row + 1][column].theNum;
                        blocks[row + 1][column].theNum = 0;
                        GridPoint G = new GridPoint(column, row);
                        setBlock(G, B);
                        madeMove = true;
                    } else if (!flags[column]) {
                        if (blocks[0][column].theNum == blocks[1][column].theNum && blocks[0][column].theNum != 0
                                && blocks[2][column].theNum == blocks[3][column].theNum && blocks[2][column].theNum != 0
                        ) {
                            blocks[0][column].theNum *= 2;
                            blocks[1][column].theNum *= 2;
                            blocks[2][column].theNum = 0;
                            blocks[3][column].theNum = 0;
                            Score+=blocks[1][column].theNum ;
                            Score+=blocks[1][column].theNum ;
                            flags[column] = true;
                            madeMove = true;
                        }
                        if (blocks[row][column].theNum == blocks[row + 1][column].theNum && !flags[column]) {
                            blocks[row][column].theNum *= 2;
                            Score+=blocks[row][column].theNum;
                            blocks[row + 1][column].theNum = 0;
                            flags[column] = true;
                            madeMove = true;
                        }
                    }
        }
    }

    @Override
    void down() {

        boolean[] flags = new boolean[4];
        for (int i = 0; i < 3; i++) {
            for (int row = 3; row > 0; row--)
                for (int column = 0; column < 4; column++) // the 'for' parameters might change for other directions
                    if (isBlockEmpty(row, column)) {
                        Block B = new Block();
                        B.point = new GridPoint(column, row);
                        B.theNum = blocks[row - 1][column].theNum;
                        blocks[row - 1][column].theNum = 0;
                        GridPoint G = new GridPoint(column, row);
                        setBlock(G, B);
                        madeMove = true;
                    } else if (!flags[column]) {
                        if (blocks[0][column].theNum == blocks[1][column].theNum && blocks[0][column].theNum != 0
                                && blocks[2][column].theNum == blocks[3][column].theNum && blocks[2][column].theNum != 0
                        ) {
                            blocks[0][column].theNum = 0;
                            blocks[1][column].theNum = 0;
                            blocks[2][column].theNum *= 2;
                            blocks[3][column].theNum *= 2;
                            Score+=blocks[2][column].theNum ;
                            Score+=blocks[3][column].theNum ;
                            flags[column] = true;
                            madeMove = true;
                        }
                        if (blocks[row][column].theNum == blocks[row - 1][column].theNum && !flags[column]) {
                            blocks[row][column].theNum *= 2;
                            Score+=blocks[row][column].theNum;
                            blocks[row - 1][column].theNum = 0;
                            flags[column] = true;
                            madeMove = true;
                        }
                    }
        }

    }

    @Override
    void right() {
        boolean[] flags = new boolean[4];
        for (int i = 0; i < 3; i++) {
            for (int column = 3; column > 0; column--)
                for (int row = 0; row < 4; row++) // the 'for' parameters might change for other directions
                    if (isBlockEmpty(row, column)) {
                        Block B = new Block();
                        B.point = new GridPoint(column, row);
                        B.theNum = blocks[row][column - 1].theNum;
                        blocks[row][column - 1].theNum = 0;
                        GridPoint G = new GridPoint(column, row);
                        setBlock(G, B);
                        madeMove = true;
                    } else if (!flags[row]) {
                        if (blocks[row][0].theNum == blocks[row][1].theNum && blocks[row][0].theNum != 0
                                && blocks[row][2].theNum == blocks[row][3].theNum && blocks[row][2].theNum != 0
                        ) {
                            blocks[row][0].theNum = 0;
                            blocks[row][1].theNum = 0;
                            blocks[row][2].theNum *= 2;
                            blocks[row][3].theNum *= 2;
                            Score+=blocks[row][2].theNum ;
                            Score+=blocks[row][3].theNum ;
                            madeMove = true;
                            flags[row] = true;
                        }
                        if (blocks[row][column].theNum == blocks[row][column - 1].theNum && !flags[row]) {
                            blocks[row][column].theNum *= 2;
                            blocks[row][column - 1].theNum = 0;
                            Score+= blocks[row][column].theNum;
                            flags[row] = true;
                            madeMove = true;
                        }
                    }
        }
    }

    @Override
    void left() {
        boolean[] flags = new boolean[4];
        for (int i = 0; i < 3; i++) {
            for (int column = 0; column < 3; column++)
                for (int row = 0; row < 4; row++) // the 'for' parameters might change for other directions
                    if (isBlockEmpty(row, column)) {
                        Block B = new Block();
                        B.point = new GridPoint(column, row);
                        B.theNum = blocks[row][column + 1].theNum;
                        blocks[row][column + 1].theNum = 0;
                        GridPoint G = new GridPoint(column, row);
                        setBlock(G, B);
                        madeMove = true;
                    } else if (!flags[row]) {
                        if (blocks[row][0].theNum == blocks[row][1].theNum && blocks[row][0].theNum != 0
                                && blocks[row][2].theNum == blocks[row][3].theNum && blocks[row][2].theNum != 0
                        ) {
                            blocks[row][0].theNum *= 2;
                            blocks[row][1].theNum *= 2;
                            blocks[row][2].theNum = 0;
                            blocks[row][3].theNum = 0;
                            Score+=blocks[row][0].theNum ;
                            Score+=blocks[row][1].theNum ;
                            flags[row] = true;
                            madeMove = true;
                        }
                        if (blocks[row][column].theNum == blocks[row][column + 1].theNum && !flags[row]) {
                            blocks[row][column].theNum *= 2;
                            Score+= blocks[row][column].theNum;
                            blocks[row][column + 1].theNum = 0;
                            flags[row] = true;
                            madeMove = true;
                        }
                    }
        }
    }

    @Override
    void setBlock(GridPoint point, AbstractBlock toBlock) {
        blocks[point.y][point.x] = (Block) toBlock;
    }

    void MakeNewBlock() {
        if (!checkEnd()) {
            GridPoint emptyOne = getRandomEmptyPoint();
            Random rand = new Random();
            double Drandom = rand.nextDouble();
            boolean Is2 = Drandom < .75;
            if (Is2) {
                //System.out.println(emptyOne.x + " "+ emptyOne.y);
                blocks[emptyOne.y][emptyOne.x].theNum = 2;
            } else {
                blocks[emptyOne.y][emptyOne.x].theNum = 4;
            }
        }
    }

    @Override
    GridPoint getRandomEmptyPoint() {
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j].theNum == 0)
                    flag = true;
            }
        }
        if (flag) {
            Random rand = new Random();
            int Irandom;
            int Jrandom;
            do {
                Irandom = rand.nextInt(4);
                Jrandom = rand.nextInt(4);
                if (blocks[Irandom][Jrandom].theNum == 0) {
                    return new GridPoint(Jrandom, Irandom);
                }
            }
            while (blocks[Irandom][Jrandom].theNum != 0);
        }
        return null;
    }


    @Override
    boolean isBlockEmpty(int i, int j) {
        if (blocks[i][j].theNum == 0) return true;
        return false;
    }

    @Override
    void debugMessage() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print((blocks[i][j].theNum == 0 ? "*" : blocks[i][j].theNum) + " ");
            }
            System.out.println();
        }
    }

    boolean isWon() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j].theNum >= winScore) {
                    return true;
                }
            }
        }
        return false;
    }

}
