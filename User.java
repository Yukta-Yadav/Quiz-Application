import java.util.*;

public class User {
    private String username;
    private String password;
    private List<QuizRecord> quizHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.quizHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addQuizRecord(QuizRecord record) {
        quizHistory.add(record);
    }

    public void displayQuizHistory() {
        if (quizHistory.isEmpty()) {
            System.out.println("No quiz history available.");
            return;
        }

        System.out.println("\n=== Quiz History ===");
        for (QuizRecord record : quizHistory) {
            System.out.println(record);
        }
    }
}