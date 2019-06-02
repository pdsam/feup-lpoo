package crackattack.view.loseScreen.lanterna;

import com.googlecode.lanterna.TextColor;
import crackattack.view.AbstractLanternaView;

import java.io.IOException;

public class LanternaLoseScreenView extends AbstractLanternaView {

    private final String youLostMessage = "You lost. Score: ";
    private final String tryGainMessage = "[Space] - Try again";
    private final String quitMessage = "[Q] - Quit";

    private int finalScore;

    public LanternaLoseScreenView(int finalScore) throws IOException {
        super();

        this.finalScore = finalScore;
    }

    @Override
    public void render() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(WIDTH/2 - youLostMessage.length()/2, HEIGHT/2 - 1, youLostMessage + finalScore);
        graphics.putString(WIDTH/2 - tryGainMessage.length()/2, HEIGHT/2, tryGainMessage);
        graphics.putString(WIDTH/2 - quitMessage.length()/2, HEIGHT/2 + 1, quitMessage);

        try {
            context.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
