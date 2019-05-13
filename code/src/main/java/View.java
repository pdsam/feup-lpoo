public interface View {
    void render();
    void processInput();
    boolean shouldClose();

    //void subscribeEvent(EventType event, EventHandler handler);
}
