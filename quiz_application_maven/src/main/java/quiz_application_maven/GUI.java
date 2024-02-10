package quiz_application_maven;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    private JTextArea taRuleQuestion;
    private JButton yesButton;
    private JButton noButton;
    private boolean studyMode;
    private String filePath = "C:\\Users\\TAUCHMANN_L\\Documents\\Eclipse\\quiz_application_maven\\src\\test\\resources\\";

    private List<Question> questionList;
    private List<Boolean> answerList = new ArrayList<>();
    private int currentQuestionCounter = 0;
    private int correctlyAnswered = 0;
    private int incorrectlyAnswered = 0;

    // Constructor for GUI
    public GUI(List<Question> questionList, boolean studyMode) {
        this.questionList = questionList;
        this.studyMode = studyMode;

        setTitle("Fragerunde");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());

        taRuleQuestion = new JTextArea();
        taRuleQuestion.setEditable(false);
        taRuleQuestion.setLineWrap(true);
        taRuleQuestion.setWrapStyleWord(true);
        taRuleQuestion.setPreferredSize(new Dimension(50, 100)); // Set the preferred size
        textPanel.add(taRuleQuestion, BorderLayout.CENTER);
        mainPanel.add(textPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        yesButton = new JButton("Ja");
        yesButton.setPreferredSize(new Dimension(100, 50));
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer(true);
                showNextQuestion();
            }
        });
        buttonPanel.add(yesButton);

        noButton = new JButton("Nein");
        noButton.setPreferredSize(new Dimension(100, 50));
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer(false);
                showNextQuestion();
            }
        });
        buttonPanel.add(noButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        showNextQuestion();
    }

    // Display the next question
    private void showNextQuestion() {
        if (currentQuestionCounter < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionCounter);
            taRuleQuestion.setText(currentQuestion.getNumber() + ": " + currentQuestion.getText());

            currentQuestionCounter++;
        } else {
            showStatistics();
        }
    }

    // Check if the answer is right or wrong
    private void checkAnswer(boolean answer) {
        if (currentQuestionCounter <= questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionCounter - 1);
            if ((answer && currentQuestion.isSolution()) || (!answer && !currentQuestion.isSolution())) {
                correctlyAnswered++;
                answerList.add(true);
                if (studyMode) {
                    JOptionPane.showMessageDialog(null, "Richtig! " + currentQuestion.getAnswer());
                }
            } else {
                incorrectlyAnswered++;
                answerList.add(false);
                if (studyMode) {
                    JOptionPane.showMessageDialog(null, "Falsch! " + currentQuestion.getAnswer());
                }
            }
        }
    }

    // Display the statistics at the end
    private void showStatistics() {
        JOptionPane.showMessageDialog(null, "Fertig!\nRichtig beantwortet: " + correctlyAnswered + "\nFalsch beantwortet: " + incorrectlyAnswered);
        if (!studyMode) { // Only saves an Evaluation when the Exam-Mode is turned on
            SaveResult evaluation = new SaveResult(questionList, answerList);
            evaluation.createEvaluation(filePath, 0.7);
        }
        System.exit(0);
    }
}
