package md.ceiti.dad.exemplu.lucruindividual3_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Sarcina1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 600, 350);


        TextField textFieldX = (TextField) root.lookup("#txtXfield");
        TextField textFieldA = (TextField) root.lookup("#txtAfield");
        TextField textFieldB = (TextField) root.lookup("#txtBfield");

        TextField resultTXT = (TextField) root.lookup("#resultTXT");

        Button solveButton = (Button) root.lookup("#solveButton");
        solveButton.setOnAction(e -> {
            double x = Double.parseDouble(textFieldX.getText());
            double a = Double.parseDouble(textFieldA.getText());
            double b = Double.parseDouble(textFieldB.getText());
            double result;

            if (x <= 7) {
                result = (x + 4) / (Math.pow(a, 2) + Math.pow(b, 2));
            } else {
                result = x * Math.pow((a + b), 2);
            }


            resultTXT.setText(String.valueOf(result));
        });

        Button clearButton = (Button) root.lookup("#clearButton");
        clearButton.setOnAction(e -> {
            textFieldX.setText("");
            textFieldA.setText("");
            textFieldB.setText("");
            resultTXT.setText("");
        });

        Button quitButton = (Button) root.lookup("#quitButton");
        quitButton.setOnAction(e -> {
            System.exit(0);
        });



        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}