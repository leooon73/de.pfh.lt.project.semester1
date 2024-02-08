public class Question {
    private String number;
    private String text;
    private boolean solution;
    private String answer;
    private String article;

    // Constructor for Question
    public Question(String number, String text, boolean solution, String answer, String article) {
        this.number = number;
        this.text = text;
        this.solution = solution;
        this.answer = answer;
        this.article = article;
    }

    // Getter-methods for Question attributes
    public String getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public boolean isSolution() {
        return solution;
    }

    public String getAnswer() {
        return answer;
    }

    public String getArticle() {
        return article;
    }
}
