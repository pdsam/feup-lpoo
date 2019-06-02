package crackattack.model.board;

public abstract class CompositeBlock {
    int numberUntillBreak;
    int sizeX;
    int sizeY;

    public abstract void toBlocks();
}
