package crackattack.view;

import crackattack.events.EventType;

public interface View {
    void render();
    EventType pollEvents();
    void notifyClosing();
    void close();
    boolean shouldClose();
}
