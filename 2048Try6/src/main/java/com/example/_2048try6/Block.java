package com.example._2048try6;

public class Block extends AbstractBlock {
    GridPoint point;
    String Color;
    int theNum = 0;
    @Override
    void changePoint(GridPoint point) {
        this.point = point;
    }
    @Override
    GridPoint move(GridPoint from, Direction direction) {
        if (direction.equals(Direction.UP)) {
            return from.up();
        } else if (direction.equals(Direction.RIGHT)) {
            return from.right();
        } else if (direction.equals(Direction.DOWN)) {
            return from.down();
        } else if (direction.equals(Direction.LEFT)) {
            return from.left();
        } else {
            return null;
        }
    }
}
