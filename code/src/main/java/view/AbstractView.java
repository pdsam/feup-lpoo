package view;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractView implements View {
    protected Queue<EventType> eventQueue;

    public AbstractView() {
        this.eventQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public abstract void render();

    @Override
    public EventType pollEvents() {
        return eventQueue.poll();
    }

}
