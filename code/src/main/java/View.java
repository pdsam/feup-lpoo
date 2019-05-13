public interface View {
    void render();
    EventType pollEvents();
    boolean shouldClose();
    void close();
}
