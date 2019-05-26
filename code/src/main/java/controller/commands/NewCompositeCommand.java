package controller.commands;

import model.Board;

public class NewCompositeCommand implements Command{
    private Board board;

    public NewCompositeCommand(Board board) {
        this.board = board;
    }



    @Override
    public void exec() {

    }
}
