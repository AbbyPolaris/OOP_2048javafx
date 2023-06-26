package com.example._2048try6;

public abstract class AbstractBlockgrid {
    AbstractBlock[][] blocks = new AbstractBlock[4][4];

    abstract boolean checkEnd(); //use GridPoint.neighbors here
    abstract boolean addNewBlock(); // return false if is full

    abstract void up(); // example of how one might implement this function
    abstract void down();
    abstract void right();
    abstract void left();
    AbstractBlock getBlock(GridPoint point){
        return blocks[point.y][point.x];
    }

    abstract void setBlock(GridPoint point, AbstractBlock toBlock); // in the end you might want to cast to
                                                                    // the "Block" class you will implement
    abstract GridPoint getRandomEmptyPoint();
    abstract boolean isBlockEmpty(int i, int j);
    boolean isBlockEmpty(GridPoint point){
        return isBlockEmpty(point.x, point.y);
    }
    abstract void debugMessage();
}
