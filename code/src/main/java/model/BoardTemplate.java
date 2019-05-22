package model;

public interface BoardTemplate {

    GridElement getGridElement(Position p);
    void setGridElements(Position p, GridElement element);
    void swap(Position p1, Position p2);


}
