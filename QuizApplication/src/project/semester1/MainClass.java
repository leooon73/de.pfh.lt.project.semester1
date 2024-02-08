import java.util.Collections;
import java.util.List;
import javax.swing.*;



public class MainClass {
	
    public static void main(String[] args) {
        
        List<Question> questionList = CSVReader.importCSV("C:\\Users\\TAUCHMANN_L\\git\\pfh\\de.pfh.lt.project.semester1\\QuizApplication\\files\\fragen.CSV"); // Load questions from the CSV file

        int numberOfQuestions = askForNumberOfQuestions(questionList.size()); // Set the number of questions

        boolean studyMode = askForMode();
        
        Collections.shuffle(questionList); // Shuffle the question list

       
        List<Question> selectedQuestions = questionList.subList(0, numberOfQuestions); // Is used to Store the random questions the user will be asked

        GUI gui = new GUI(selectedQuestions, studyMode);
        gui.setVisible(true);
    }

    // Ask the user how many questions to review and handles incorrect inputs
    private static int askForNumberOfQuestions(int totalNumberOfQuestions) {
        int numberOfQuestions = 0;

        while (true) {
            String input = JOptionPane.showInputDialog("Wie viele Fragen möchtest du üben? (max " + totalNumberOfQuestions + "):");

            // Exit the program if the user presses Cancel/closes the window
            if (input == null) {
                System.exit(0);
            }

            try {
                numberOfQuestions = Integer.parseInt(input);

                if (numberOfQuestions > 0 && numberOfQuestions <= totalNumberOfQuestions) {
                    break; // Valid input, exit the loop
                } else {
                    JOptionPane.showMessageDialog(null, "Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und " + totalNumberOfQuestions + " ein.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein");
            }
        }

        return numberOfQuestions;
    }

    // Ask the user for the mode (Study or Exam)
    private static boolean askForMode() {
    	String[] options = {"Lernmodus (Lösung nach Beantwortung der Frage)", "Prüfungssimulation (Lösung erst am Ende)"};
    	int modeSelection = JOptionPane.showOptionDialog(null, "Wie möchtest du üben?", "Modusauswahl",
    	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


        return modeSelection == 0; // If 0 is selected, it's Study Mode (Lernmodus), otherwise, it's Exam Mode (Prüfungssimulation)
    }
}
