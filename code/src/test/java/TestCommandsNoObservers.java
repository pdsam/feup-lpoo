import crackattack.controller.board.commands.*;
import crackattack.model.board.*;
import crackattack.model.board.generators.BoardContentGenerator;
import crackattack.model.board.generators.TestBoardGenerator;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCommandsNoObservers {
    private Board board;
    @Before
    public void initBoard() {
        this.board = new Board(new TestBoardGenerator());

    }

    //TODO test if commands notify observers;

    @Test
    public void MoveDownTest(){
       Command test =  new MoveDownCommand(this.board);
        board.getSelector().setPos(new Position(0,0));
        Position temp = this.board.getSelector().getPos();
        Position before = new Position(temp.getX(),temp.getY());
        test.exec();
        assertEquals(new Position(before.getX(),before.getY()+1),board.getSelector().getPos());

        board.getSelector().setPos(new Position(0,board.getMaxY()-1));
        test.exec();
        assertEquals(new Position(0,board.getMaxY()-1),board.getSelector().getPos());
    }

    @Test
    public void MoveUpTest(){
    Command toTest = new MoveUpCommand(this.board);
    Selector temp = this.board.getSelector();
    temp.setPos(new Position(5,5));
    Position before = new Position(temp.getPos().getX(),temp.getPos().getY());
    toTest.exec();
    assertEquals(new Position(before.getX(),before.getY() -1),temp.getPos());

    temp.setPos(new Position(0,0));
    toTest.exec();
    assertEquals(new Position(0,0),temp.getPos());

    }

    @Test
    public void moveLeftTest(){

        Command toTest = new MoveLeftCommand(this.board);
        Selector selector = this.board.getSelector();
        selector.setPos(new Position(5,5));
        Position before = new Position(5,5);
        toTest.exec();
        assertEquals(new Position(before.getX()-1,before.getY()),selector.getPos());

        selector.setPos(new Position(0,1));
        toTest.exec();
        assertEquals(new Position(0,1),selector.getPos());

    }

    @Test
    public void MoveRightTest(){
        Command toTest = new MoveRightCommand(this.board);
        Selector selector = this.board.getSelector();
        selector.setPos(new Position(3,5));
        Position before = new Position(3,5);
        toTest.exec();
        assertEquals(new Position(before.getX()+1,before.getY()),selector.getPos());

        selector.setPos(new Position(board.getMaxX()-2,1));
        toTest.exec();
        assertEquals(new Position(board.getMaxX()-2,1),selector.getPos());

    }

    @Test
    public void swapTest(){
        Command toTest = new SwapCommand(this.board);
        board.getSelector().setPos(new Position(1,2));
        GridElement p1 = board.getGridElement(board.getSelector().getPos());
        GridElement p2 = board.getGridElement(new Position(board.getSelector().getPos().getX()+1,board.getSelector().getPos().getY()));
        toTest.exec();

        assertEquals(p1,board.getGridElement(new Position(board.getSelector().getPos().getX()+1,board.getSelector().getPos().getY())));
        assertEquals(p2,board.getGridElement(board.getSelector().getPos()));
    }

    private GridElement[][] copyBoard(Board board) {
        GridElement[][] copy = new GridElement[board.getMaxY()][board.getMaxX()];
        for (int i = 0; i < board.getMaxY(); i++) {
            for (int j = 0; j < board.getMaxX(); j++) {
                copy[i][j] = board.getGridElement(new Position(j,i));
            }
        }

        return copy;
    }

    @Test
    public void NewLineTest(){
        Command toTest = new NewLineCommand(this.board);
        GridElement[][] copy = copyBoard(this.board);
        toTest.exec();

        for (int i = 0; i < board.getMaxY()-1; i++) {
            for (int j = 0; j < board.getMaxX(); j++) {
                assertEquals(copy[i+1][j], board.getGridElement(new Position(j,i)));
            }
        }
    }

    @Test
    public void TestImpossibleNewLine() {
        BoardContentGenerator generator = board -> {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = new Block();
                }
            }
        };

        Board board = new Board(generator);
        NewLineCommand toTest = new NewLineCommand(board);
        GridElement[][] copy = copyBoard(board);
        toTest.exec();

        for (int i = 0; i < board.getMaxY(); i++) {
            for (int j = 0; j < board.getMaxX(); j++) {
                assertEquals(copy[i][j], board.getGridElement(new Position(j,i)));
            }
        }

        assertTrue(toTest.lost());

    }


}
