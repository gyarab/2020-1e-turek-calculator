package calculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class CoventorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ComboBox fromUnit, toUnit;
    @FXML
    TextField fromTex;
    @FXML
    Label toLabel;
    @FXML
    MenuBar menu;
    private Conventor conventor = new Conventor();
    private Screen screen = new Screen();

    @FXML
    public void clicNumPad(ActionEvent a) {
        Button b = (Button) a.getSource();
        fromTex.setText(fromTex.getText() + b.getText());
    }

    @FXML
    public void clicConvert() {
        String from = String.valueOf(fromUnit.getSelectionModel().getSelectedItem());
        String to = String.valueOf(toUnit.getSelectionModel().getSelectedItem());
        try{
        toLabel.setText(conventor.convert(from, to, fromTex.getText()));}
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba!");
            alert.setHeaderText("Chybný vstup.");
            alert.setContentText("Zadejte pouze čísla.");            
            alert.showAndWait();
        }

    }

    @FXML
    public void clicFlat() {

        resetCombo();
        String[] units = new String[]{"mm²", "cm²", "dm²", "m²", "km²", "in²",
            "ft²", "yd²", "mi²"};
        fromUnit.getItems().addAll(units);
        toUnit.getItems().addAll(units);

    }

    private void resetCombo() {
        fromUnit.getItems().remove(0, fromUnit.getItems().size());
        toUnit.getItems().remove(0, toUnit.getItems().size());

    }

    @FXML
    public void clicLenght() {
        resetCombo();
        String[] units = new String[]{"mm", "cm", "dm", "m", "km", "in", "ft",
            "yd", "mi"};
        fromUnit.getItems().addAll(units);
        toUnit.getItems().addAll(units);

    }

    @FXML
    public void clicVolumeCombo() {
        resetCombo();
        String[] units = new String[]{"ml", "cl", "dl", "l", "hl",
            "čajové lžičky", "polévkové lžíce", "šálky", "pinty", "galony"};
        fromUnit.getItems().addAll(units);
        toUnit.getItems().addAll(units);

    }

    @FXML
    public void clicCalculator() throws IOException {
            screen.changeScene("Calculator.fxml", (Stage) fromTex.getScene().getWindow());
    }
    @FXML
    public void clicVolume() throws IOException {
            screen.changeScene("Volume.fxml", (Stage) fromTex.getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fromUnit.getItems().addAll("mm", "cm", "dm", "m", "km", "in", "ft", "yd", "mi");
        toUnit.getItems().addAll("mm", "cm", "dm", "m", "km", "in", "ft", "yd", "mi");
        fromUnit.getSelectionModel().select("--Vyberte jednotky--");
        toUnit.getSelectionModel().select("--Vyberte jednotky--");
    }

}
