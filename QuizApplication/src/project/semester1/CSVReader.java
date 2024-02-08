import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<Question> importCSV(String filePath) {
        List<Question> questionList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.ISO_8859_1))) { // ISO_8859_1 --> Formatting of the CSV with German special characters
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.startsWith("R-")) {
                    continue;
                }

                String[] columns = line.split(";");
                if (columns[1].trim().isEmpty()) {
                    continue;
                }
                String number = columns[0];
                String questionText = columns[1];
                boolean solution = "x".equalsIgnoreCase(columns[2]); // equalsIgnoreCase --> capitalisation independent (x || X)
                String answer = columns[4];
                String article = "Nicht vorhanden."; // Prevents the error if no article is given
                if (columns.length == 6) {
                    article = columns[5];
                }

                Question question = new Question(number, questionText, solution, answer, article);
                questionList.add(question);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return questionList;
    }
}
