package crackattack.controller;

import crackattack.controller.board.commands.Command;

public interface Controller {
    void tick();
    void executeCommands();
    void addCommand(Command command);
}
