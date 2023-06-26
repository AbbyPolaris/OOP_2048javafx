package com.example._2048try6;

import java.util.ArrayList;
import java.util.List;

public class GridPoint implements Cloneable {
    int x;
    int y;

    GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridPoint up() {
        GridPoint res = new GridPoint(x, y);
        res.y = res.y - 1;
        return res;
    }

    public GridPoint down() {
        GridPoint res = new GridPoint(x, y);
        res.y = res.y + 1;
        return res;
    }

    public GridPoint right() {
        GridPoint res = new GridPoint(x, y);
        res.x = res.x + 1;
        return res;
    }

    public GridPoint left() {
        GridPoint res = new GridPoint(x, y);
        res.x = res.x - 1;
        return res;
    }

    @Override
    public String toString() {
        return null; // todo
    }

    @Override
    public boolean equals(Object o) {
        // do not forget to check class type first
        return true; // todo
    }

    public ArrayList<GridPoint> neighbors() { // this one is implemented for you!
        GridPoint[] potential = new GridPoint[4];
        //Get all potential candidates for the neighbors of point
        potential[0] = up();
        potential[1] = down();
        potential[2] = left();
        potential[3] = right();

        //Remove all disallowed/illegal points; such as points with negative coordinates or with coordinates more than 3
        ArrayList<GridPoint> neighbors = new ArrayList<>(List.of(potential));
        neighbors.removeIf(GridPoint::isIllegal);
        return neighbors;
    }

    private static boolean isIllegal(GridPoint p) {
        return p.x < 0 || p.x > 3 || p.y < 0 || p.y > 3;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GridPoint A = new GridPoint(x, y);
        return (Object) A;
    }

}
