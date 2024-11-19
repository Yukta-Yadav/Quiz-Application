import java.util.*;

public class QuizApplication {
    private List<Question> questions;
    private List<User> users;
    private Scanner scanner;

    public QuizApplication() {
        questions = new ArrayList<>();
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
        seedQuestions();
    }

    public void seedQuestions() {
        // Easy level questions
        questions.add(new Question("Capital of India?", new String[]{"Jaipur", "New Delhi", "Surat", "Mumbai"}, 2, "Easy"));
        questions.add(new Question("5 + 3 = ?", new String[]{"5", "8", "7", "10"}, 2, "Easy"));

        // Medium level questions
        questions.add(new Question("Largest planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 3, "Medium"));
        questions.add(new Question("Square root of 16?", new String[]{"3", "4", "5", "6"}, 2, "Medium"));

        // Hard level questions
        questions.add(new Question("Who developed the Theory of Relativity?", new String[]{"Newton", "Einstein", "Tesla", "Galileo"}, 2, "Hard"));
        questions.add(new Question("Atomic number of Oxygen?", new String[]{"8", "10", "16", "12"}, 1, "Hard"));
    }

    public void start() {
        System.out.println("=== Welcome to Brainiacs our Quiz Application ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        User user = findUser(username);
        if (user == null) {
            System.out.print("New user! Enter a password to register: ");
            String password = scanner.nextLine();
            user = new User(username, password);
            users.add(user);
        } else {
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            if (!user.getPassword().equals(password)) {
                System.out.println("Invalid password. Try again.");
                return;
            }
        }

        System.out.println("Login successful!");
        showQuizMenu(user);
    }

    public void showQuizMenu(User user) {
        while (true) {
            System.out.println("\n>>>>>>>>> Quiz Menu <<<<<<<<");
            System.out.println("1. Take Quiz");
            System.out.println("2. View Your Quiz History");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> startQuiz(user);
                case 2 -> user.displayQuizHistory();
                case 3 -> {
                    System.out.println("Thank you for using the Quiz Application!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void startQuiz(User user) {
        System.out.println("\nChoose difficulty level: Easy / Medium / Hard");
        String difficulty = scanner.nextLine();
        List<Question> selectedQuestions = getQuestionsByDifficulty(difficulty);

        if (selectedQuestions.isEmpty()) {
            System.out.println("No questions available for this difficulty.");
            return;
        }

        int score = 0;
        long startTime = System.currentTimeMillis();

        for (Question question : selectedQuestions) {
            System.out.println(question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.print("Your answer: ");
            int answer = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (answer == question.getCorrectOption()) {
                score++;
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000; // in seconds

        System.out.println("\nQuiz completed in " + duration + " seconds.");
        System.out.println("Your score: " + score);

        String badge = awardBadge(score, selectedQuestions.size());
        System.out.println("You earned: " + badge);

        user.addQuizRecord(new QuizRecord(difficulty, score, duration, badge));
    }

    public List<Question> getQuestionsByDifficulty(String difficulty) {
        List<Question> filteredQuestions = new ArrayList<>();
        for (Question question : questions) {
            if (question.getDifficulty().equalsIgnoreCase(difficulty)) {
                filteredQuestions.add(question);
            }
        }
        return filteredQuestions;
    }

    public String awardBadge(int score, int totalQuestions) {
        double percentage = (double) score / totalQuestions * 100;
        if (percentage == 100) {
            return "🏆 Gold Badge - Perfect Score!";
        } else if (percentage >= 70) {
            return "🥈 Silver Badge - Great Job!";
        } else if (percentage >= 50) {
            return "🥉 Bronze Badge - Keep Trying!";
        } else {
            return "🙃 Participation Badge - Better luck next time!";
        }
    }

    private User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}