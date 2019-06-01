package crackattack.events;

import crackattack.Application;
import crackattack.view.Callback;

import java.util.EnumMap;
import java.util.Map;

public abstract class EventHandler {

    protected Map<EventType, Callback> callbackMap;

    protected Application app;

    public EventHandler(Application app) {
        this.app = app;
        this.callbackMap = new EnumMap<>(EventType.class);
        callbackMap.put(EventType.CLOSE, ()->app.getView().notifyClosing());
        initiateCallbacks();
    }

    protected abstract void initiateCallbacks();

    public void handleEvent(EventType eventType) {
        Callback callback = callbackMap.get(eventType);
        if (callback != null) {
            callback.run();
        }
    }
}
