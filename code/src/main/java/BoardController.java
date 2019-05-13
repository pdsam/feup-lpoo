import java.util.EnumMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private Board board;
    private View boardView;

    private Map<EventType, Callback> callbackMap;

    public BoardController(View view, Board board) {
        this.boardView = view;
        this.board = board;
        this.callbackMap = new EnumMap<>(EventType.class);

        callbackMap.put(EventType.UP_ARROW, this::moveUp);
        callbackMap.put(EventType.DOWN_ARROW, this::moveDown);
        callbackMap.put(EventType.LEFT_ARROW, this::moveLeft);
        callbackMap.put(EventType.RIGHT_ARROW, this::moveRight);
        callbackMap.put(EventType.SPACE, this::swap);
        callbackMap.put(EventType.CLOSE, this::close);
    }

    public void run() {
        boardView.render();
        while (!boardView.shouldClose()) {
            EventType currentEvent;
            while ((currentEvent = boardView.pollEvents()) != null) {
                callbackMap.get(currentEvent).run();
            }
            boardView.render();
        }
    }

    public void moveUp() {
        new MoveUpCommand(board).exec();
    }

    public void moveDown() {
        new MoveDownCommand(board).exec();
    }

    public void moveLeft() {
        new MoveLeftCommand(board).exec();
    }

    public void moveRight() {
        new MoveRightCommand(board).exec();
    }

    public void swap() {
        new SwapCommand(board).exec();
    }

    public void close() {
        boardView.close();
    }


    public void blockGravityUpdate(Position p){
        /*for(int i = 1;i < board.getMaxY() -1 - p.getY();i++){
            if(board.getGridElement(new Position(p.getX(),p.getY() +1) ) != null)
                board.setGridElements(new Position(p.getX(),p.getY() +i -1), board.getGridElement(p));
            board.setGridElements(p, null);
                return;
        }

        */
    }


    public void comboChecker(Position p){

    }

}
