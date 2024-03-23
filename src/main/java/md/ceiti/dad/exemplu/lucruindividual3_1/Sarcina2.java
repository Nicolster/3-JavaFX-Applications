package md.ceiti.dad.exemplu.lucruindividual3_1;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sarcina2 extends Application {

    @FXML
    private TableView<String> tableView;

    @FXML
    private TableColumn<String, String> column;

    @FXML
    private void initialize() {
        column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setEditable(true);
    }

    private List<String> cellValues = new ArrayList<>();

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Sarcina2.class.getResource("sarcina2.fxml")));
        Scene scene = new Scene(root, 600, 400);



        Node node = root.lookup("#tabela");
        if (node instanceof TableView) {
            tableView = (TableView<String>) node;
        } else {
            System.out.println("Elementul cu id-ul 'tabela' nu este un TableView.");
        }


        TableColumn<String, String> column = (TableColumn<String, String>) tableView.getColumns().get(0);
        column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setEditable(true);

        tableView.getSelectionModel().selectFirst();
        tableView.setEditable(true);


        TextArea textArea = (TextArea) root.lookup("#text");
        textArea.setOnKeyPressed(e -> {
            String text = textArea.getText();
        });

        Button corectButton = (Button) root.lookup("#corectButton");
        corectButton.setOnAction(e -> {
            String text = textArea.getText();
            String textActualizat = text;


            String cuvantSelectat = tableView.getSelectionModel().getSelectedItem();

            if (cuvantSelectat != null) {

                String[] cuvinte = textActualizat.split("\\s+");


                for (int i = 0; i < cuvinte.length; i++) {
                    if (cuvinte[i].contains("$")) {

                        cuvinte[i] = cuvantSelectat;
                    }
                }


                textActualizat = String.join(" ", cuvinte);


                textArea.setText(textActualizat);
            }
        });








        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll("buna", "salut", "salutare");
        tableView.setItems(data);

        Button addButton = (Button) root.lookup("#addButton");
        addButton.setOnAction(e -> {
            data.add("");
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
