public class Question {
    private String questionText;
    private String[] options;
    private int correctOption;
    private String difficulty;

    public Question(String questionText, String[] options, int correctOption, String difficulty) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String getDifficulty() {
        return difficulty;
    }
}