import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Queue;

public class LanternaInputThread implements Runnable {

    private Screen context;
    private Queue<EventType> eventQueue;
    private boolean running;

    public LanternaInputThread(Screen context, Queue<EventType> eventQueue) {
        this.context = context;
        this.eventQueue = eventQueue;
        this.running = false;
    }

    @Override
    public void run() {
        while(running) {
            KeyStroke kS;
            try {
                kS = context.readInput();

                switch (kS.getKeyType()) {
                    case ArrowDown:
                        eventQueue.add(EventType.DOWN_ARROW);
                        break;
                    case ArrowLeft:
                        eventQueue.add(EventType.LEFT_ARROW);
                        break;
                    case ArrowUp:
                        eventQueue.add(EventType.UP_ARROW);
                        break;
                    case ArrowRight:
                        eventQueue.add(EventType.RIGHT_ARROW);
                        break;
                    case Character:
                        if (kS.getCharacter() == ' ') {
                            eventQueue.add(EventType.SPACE);
                        } else if (kS.getCharacter() == 'q') {
                            running = false;
                            eventQueue.add(EventType.CLOSE);
                        }
                        break;
                    case EOF:
                        running = false;
                        eventQueue.add(EventType.CLOSE);
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        running = true;
        new Thread(this, "Lanterna Input Listener").start();
    }

    public void stop() {
        running = false;
    }
}
