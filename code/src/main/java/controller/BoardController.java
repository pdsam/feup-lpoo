package controller;

import model.Block;

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

        this.board.attachObserver(new PhysicsBoardObserver(board,this));
        this.board.attachObserver(new ComboBoardObserver(board, this));

        this.callbackMap = new EnumMap<>(EventType.class);

        callbackMap.put(EventType.UP_ARROW, this::moveUp);
        callbackMap.put(EventType.DOWN_ARROW, this::moveDown);
        callbackMap.put(EventType.LEFT_ARROW, this::moveLeft);
        callbackMap.put(EventType.RIGHT_ARROW, this::moveRight);
        callbackMap.put(EventType.SPACE, this::swap);
        callbackMap.put(EventType.CLOSE, this::close);
    }

    public void run() {

        LineTimerThread timer = new LineTimerThread("LineTimerThread", board);
        timer.start();

        boardView.render();
        while (!boardView.shouldClose()) {
            EventType currentEvent;
            while ((currentEvent = boardView.pollEvents()) != null) {
                callbackMap.get(currentEvent).run();
            }
            boardView.render();
        }

        timer.termminate();
        try {
            timer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
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


    private boolean inBoundaries(Position p){
        if(p.getY() <0 || p.getY() >= board.getMaxY()  || p.getX() < 0 || p.getX()>=board.getMaxX())
            return false;

        return true;
    }




    public void blockGravityUpdate(Position p){
/*
        for(int i = board.getMaxY()-1; i >= 0; i--){
            model.Position newest = new model.Position(p.getX(),i);
            if(board.getGridElement(newest)== null){
                for(int j = newest.getY()-1; j >= 0 ; j--){
                    model.Position toFall = new model.Position(p.getX(),j);
                    if(board.getGridElement(toFall) != null){
                        board.swap(newest,toFall);
                        break;
                    }
                }

            }
*/

        Position lastNull = null;
        int y = board.getMaxY()-1;
        for (; y >= 0; y--) {
            Position temp = new Position(p.getX(), y);
            if (board.getGridElement(temp) == null) {
                lastNull = temp;
                break;
            }
        }

        y--;
        for (; y >= 0; y--) {
            Position temp = new Position(p.getX(), y);
            if (board.getGridElement(temp) != null) {
                board.swap(temp,lastNull);
                lastNull.decrementY();
            }
        }




/*        if(!inBoundaries(p))
            return;

        if(board.getGridElement(p) != null) {
            if(p.getY() == board.getMaxY() -1)
                return;

            if(board.getGridElement(new model.Position(p.getX(),p.getY()-1))!= null)
                return;
            model.Position bottom = new model.Position(p.getX(),board.getMaxY()-1);

            while(board.getGridElement(bottom )!=null&& bottom.getY()> p.getY() ){

                bottom.decrementY();


            }

            board.swap(p,bottom);
            blockGravityUpdate(new model.Position(p.getX(),p.getY()-1));

        }
        else{
            if(p.getY() != 0){
                model.Position aux = new model.Position(p.getX(),p.getY()-1);
                if(board.getGridElement(aux)!= null)
                    board.swap(p, aux);
                blockGravityUpdate(aux);

            }

        }
*/
    }


    public void comboChecker(Position p){//first approach
        if(board.getGridElement(p)== null)
            return;
        Block origin = (Block) board.getGridElement(p);
        List<Position> positions = new ArrayList<>();
        //vertical
        int offset = 1;
        positions.add(p);
        while(true) {
            Position temp = new Position(p.getX(), p.getY() + offset);
            if (inBoundaries(temp) && board.getGridElement(temp)!= null) {

                if (origin.equals(board.getGridElement(temp))) {
                    positions.add(temp);
                    offset++;
                } else
                    break;
            }

            else break;
        }
        offset = 1;
        while(true) {
            Position temp = new Position(p.getX(), p.getY() - offset);
            if (inBoundaries(temp) && board.getGridElement(temp)!= null) {
                if (origin.equals(board.getGridElement(temp))) {
                    positions.add(temp);
                    offset++;
                } else
                    break;
            }
            else break;
        }
        offset = 1;
    //    System.out.println("vertical: "+positions.size());
        if(positions.size()< 3){
            positions.clear();
            positions.add(p);
        }

        //horizontal
        while(true) {
            Position temp = new Position(p.getX() + offset, p.getY());
            if (inBoundaries(temp) && board.getGridElement(temp)!= null) {
                if (origin.equals(board.getGridElement(temp))) {
                    positions.add(temp);
                    offset++;
                } else
                    break;
            }
            else break;
        }
        offset = 1;
        while(true) {
            Position temp = new Position(p.getX() - offset, p.getY());
            if (inBoundaries(temp)&& board.getGridElement(temp)!= null) {
                if (origin.equals(board.getGridElement(temp))) {
                    positions.add(temp);
                    offset++;
                } else
                    break;
            }
            else break;
        }


        if(positions.size() >= 3)
            breaker(positions);

    }


    public void breaker(List<Position> p){
        for(Position tmp : p){
            board.setGridElements(tmp, null);
        }

            board.notifyObserver(p);

    }
}
