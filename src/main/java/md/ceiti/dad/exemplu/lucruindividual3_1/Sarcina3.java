package md.ceiti.dad.exemplu.lucruindividual3_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;

public class Sarcina3 extends Application {



    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Sarcina3.class.getResource("sarcina3.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);



        TextArea textArea = (TextArea) root.lookup("#text");



        Button upload = (Button) root.lookup("#upload");
        upload.setOnAction(e -> {
            textArea.clear();
            String fileName = "src/main/resources/fisier";
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    textArea.appendText(line + "\n");
                }
                bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button transform = (Button) root.lookup("#transform");
        transform.setOnAction(e -> {

            int[][] matrix = convertTextAreaToMatrix(textArea.getText());

            processMatrix(matrix);

            printMatrixToTextArea(matrix, textArea);

        });

        Button save = (Button) root.lookup("#save");
        save.setOnAction(e -> {
            int[][] matrix = convertTextAreaToMatrix(textArea.getText());
            String fileName = "src/main/resources/fisier";
            writeMatrixToFile(matrix, fileName);
        });

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean isTextAreaEmpty = newValue.trim().isEmpty();
            transform.setDisable(isTextAreaEmpty);
            save.setDisable(isTextAreaEmpty);
        });

        stage.setScene(scene);
        stage.show();
    }

    private int[][] convertTextAreaToMatrix(String text) {
        String[] lines = text.split("\n");
        int[][] matrix = new int[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            String[] numbers = lines[i].split("\\s+");
            matrix[i] = new int[numbers.length];
            for (int j = 0; j < numbers.length; j++) {
                matrix[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        return matrix;
    }

    private void printMatrixToTextArea(int[][] matrix, TextArea textArea) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int element : row) {
                sb.append(element).append(" ");
            }
            sb.append("\n");
        }
        textArea.setText(sb.toString());
    }

    private void processMatrix(int[][] matrix) {
        int countOnes = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > 1 && matrix[i][1] == 1) {
                countOnes++;
                if (countOnes == 2) {

                    int max = Integer.MIN_VALUE;
                    for (int k = 0; k < matrix[0].length; k++) {
                        max = Math.max(max, matrix[0][k]);
                    }
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (matrix[0][k] == max) {
                            matrix[0][k] /= 2;
                            break;
                        }
                    }

                    for (int o = 0; o < matrix.length; o++) {
                        for (int k = 0; k < matrix[o].length; k++) {
                            if (matrix[o][k] == 1) {
                                matrix[o][k] = 0;
                            }
                        }
                    }
                    break;
                }
            }
        }


    }

    private void writeMatrixToFile(int[][] matrix, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int[] row : matrix) {
                for (int element : row) {
                    writer.write(String.valueOf(element));
                    writer.write(" ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
