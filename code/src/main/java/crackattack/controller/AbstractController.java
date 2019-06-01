package crackattack.controller;

import crackattack.controller.board.commands.Command;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractController implements Controller {
    private Queue<Command> commandsQueue;

    public AbstractController() {
        this.commandsQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public abstract void tick();

    public void executeCommands() {
        while (!commandsQueue.isEmpty()) {
            commandsQueue.poll().exec();
        }
    }

    @Override
    public void addCommand(Command command) {
        commandsQueue.add(command);
    }
}
