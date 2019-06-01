package view;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractView implements View {
    protected Queue<EventType> eventQueue;
    protected boolean closing;

    public AbstractView() {
        this.eventQueue = new ConcurrentLinkedQueue<>();
        this.closing = false;
    }

    @Override
    public abstract void render();

    @Override
    public EventType pollEvents() {
        return eventQueue.poll();
    }

    @Override
    public boolean shouldClose() {
        return closing;
    }
}
