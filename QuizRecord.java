public class QuizRecord {
    private String difficulty;
    private int score;
    private long duration;
    private String badge;

    public QuizRecord(String difficulty, int score, long duration, String badge) {
        this.difficulty = difficulty;
        this.score = score;
        this.duration = duration;
        this.badge = badge;
    }

    @Override
    public String toString() {
        return "Difficulty: " + difficulty + ", Score: " + score + ", Time: " + duration + "s, Badge: " + badge;
    }
}
