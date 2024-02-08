Quiz Application

This Java application is designed to import quiz questions from a CSV file, allow users to answer them interactively, and provide evaluation feedback. Below are instructions for modifying certain aspects of the application.

Contents:
1. Overview
2. Instructions
   - Changing the CSV Memory Path
   - Changing the Evaluation Storage Path

Overview:

The application consists of several Java classes:
- CSVReader.java: This class is responsible for importing quiz questions from a CSV file.
- Question.java: Defines the structure of a quiz question.
- GUI.java: Implements the graphical user interface for the quiz application.
- MainClass.java: Contains the main method to run the application.
- SaveResult.java: Manages the process of saving the evaluation results.

Instructions:

Changing the CSV Memory Path
To modify the memory path where the CSV file is saved, follow these steps:
1. Open the file "MainClass.java".
2. Locate line 11 in the code.
3. Modify the file path specified in this line to point to the desired location where your CSV file is stored.
   Example: 
   List<Question> questionList = CSVReader.importCSV("C:\\Users\\TAUCHMANN_L\\Documents\\Eclipse\\de.pfh.lt\\files\\fragen.csv");

Changing the Evaluation Storage Path
To change the storage path for saving the evaluation results, follow these steps:
1. Open the file "GUI.java".
2. Navigate to line 15 in the code.
3. Adjust the file path specified in this line to point to the desired location where you want to save the evaluation results.
   Example:
   SaveResult evaluation = new SaveResult(selectedQuestions, answersList);
   String filePath = "C:\\Users\\TAUCHMANN_L\\Documents\\Eclipse\\de.pfh.lt\\files\\";
   evaluation.createEvaluationFile(filePath, 0.7);
