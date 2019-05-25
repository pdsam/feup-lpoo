package view;

import view.EventType;

public interface View {
    void render();
    EventType pollEvents();
    void notifyClosing();
    void close();
}
