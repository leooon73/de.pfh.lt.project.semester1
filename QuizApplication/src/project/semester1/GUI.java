import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
	
   	private static final long serialVersionUID = 1L;
	private JTextArea taRuleQuestion;
    private JButton yesButton;
    private JButton noButton;
    private boolean studyMode;
    private String filePath = "C:\\Users\\TAUCHMANN_L\\git\\pfh\\de.pfh.lt.project.semester1\\QuizApplication\\files\\";

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
        setLayout(new GridLayout(3, 1));

        taRuleQuestion = new JTextArea();
        taRuleQuestion.setEditable(false);
        taRuleQuestion.setLineWrap(true);
        taRuleQuestion.setWrapStyleWord(true);
        yesButton = new JButton("Ja");
        noButton = new JButton("Nein");

        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer(true);
                showNextQuestion();
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer(false);
                showNextQuestion();
            }
        });

        add(taRuleQuestion);
        add(yesButton);
        add(noButton);

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
