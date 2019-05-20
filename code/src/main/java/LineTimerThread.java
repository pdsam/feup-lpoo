public class LineTimerThread extends Thread {
    private boolean running;
    private Board board;

    public LineTimerThread(String name, Board board) {
        super(name);
        this.running = false;
        this.board = board;
    }

    @Override
    public synchronized void start() {
        running = true;
        super.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new NewLineCommand(board).exec();
        }
    }

    public void termminate() {
        running = false;
    }
}
