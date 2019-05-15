import java.util.Queue;

public abstract class AbstractView implements View {
    protected Queue<EventType> eventQueue;

    @Override
    public abstract void render();

    @Override
    public EventType pollEvents() {
        return eventQueue.poll();
    }

    @Override
    public abstract boolean shouldClose();
}
