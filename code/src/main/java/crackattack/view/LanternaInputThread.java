package crackattack.view;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import crackattack.events.EventType;

import java.io.IOException;
import java.util.Queue;

public class LanternaInputThread extends Thread {

    private Screen context;
    private Queue<EventType> eventQueue;
    private boolean running;

    private int ID;
    private static int currID = 1;

    public LanternaInputThread(Screen context, Queue<EventType> eventQueue) {
        this.context = context;
        this.eventQueue = eventQueue;
        this.running = false;
        this.ID = currID;
        currID++;
    }

    @Override
    public void run() {
        while(running) {
            KeyStroke kS;
            try {
                kS = context.pollInput();

                if (kS == null) {
                    continue;
                }

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
                    case Enter:
                        eventQueue.add(EventType.ENTER);
                        break;
                    case EOF:
                        running = false;
                        eventQueue.add(EventType.CLOSE);
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                return;
            }
        }
    }

    public synchronized void start() {
        running = true;
        super.start();
    }

    public void terminate() {
        running = false;
    }
}
