package crackattack.model.board;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass())return false;
        Position p = (Position) o;
        return this.x ==p.getX() && this.y == p.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void decrementY(){
        this.y--;
    }
/*
    public void incrementY(){
        this.y = y + 1;
    }

    public void decrementX(){
        this.x = x - 1;
    }

    public void incrementX(){
        this.x = x + 1;
    }
*/

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
