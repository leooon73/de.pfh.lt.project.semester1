import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SaveResult {
    private List<Question> questionList;
    private List<Boolean> answers;
    private int correctlyAnswered;
    private String passed;
    private String filePath;
    private String response = "";
    
    // Constructor with input of the questions asked and which question was answered right
    public SaveResult(List<Question> questionList, List<Boolean> answers) {
        this.questionList = questionList;
        this.answers = answers;
    }

    // Generate evaluation
    public void createEvaluation(String filePath, double percentageThreshold) {
        this.filePath = filePath;
        // Count the number of correct answers
        for (Boolean answer : answers) {
            if (answer) {
                correctlyAnswered++;
            }
        }
        // Determine if the percentage threshold is met
        if ((double) correctlyAnswered / answers.size() >= percentageThreshold) { // Translation: "percentageThreshold" = "prozentualer Schwellenwert"
            passed = "Bestanden.";
        } else {
            passed = "Nicht Bestanden.";
        }
        createFile();
    }

    // Create the evaluation file
    public void createFile() {
        String timestamp = generateTimestamp();
        String fileName = filePath + "TestEvaluation_" + timestamp + ".txt";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write("Testauswertung vom Test am: " + timestamp + "\n\n");
            fw.write("Du hast " + correctlyAnswered + " von " + answers.size() + " Fragen richtig beantwortet.\n\n");
            fw.write("Damit hast du " + passed + "\n\n" + "Hier findest du eine Übersicht über die Fragen:" +"\n\n");
            Question currentQuestion;
            int temp = 0;
            for(int i = 0; i < answers.size(); i++) {
                currentQuestion = questionList.get(i);
                temp = i+1;
                fw.write("Fragee " + temp + " von " + answers.size() + " (" + currentQuestion.getNumber() + ") du hast ");
                if(answers.get(i)) {
                    response = "Richtig";
                } else {
                    response = "Falsch";
                }
                fw.write(response + " beantwortet! \nDie Frage lautet: " + currentQuestion.getText()  + "\nDie Antwort lautet: " + currentQuestion.getAnswer() + "\n \n");    
            }
            fw.flush();
            System.out.println("Document has been created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to generate a timestamp
    public String generateTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return now.format(formatter);
    }
}
