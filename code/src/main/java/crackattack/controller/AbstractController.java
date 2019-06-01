package crackattack.controller;

import crackattack.Application;
import crackattack.controller.board.commands.Command;
import crackattack.events.EventDispatcher;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractController implements Controller {
    protected EventDispatcher dispatcher;
    private Queue<Command> commandsQueue;

    public AbstractController(EventDispatcher dispatcher) {
        this.commandsQueue = new ConcurrentLinkedQueue<>();
        this.dispatcher = dispatcher;
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
