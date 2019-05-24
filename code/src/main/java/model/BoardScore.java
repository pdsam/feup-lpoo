package model;

public class BoardScore {
    int score;

    public BoardScore() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increase(int offset) {
        this.score += offset;
    }
}
