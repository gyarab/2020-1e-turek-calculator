/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class CalculatorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Calculator calculator = new Calculator();
    @FXML
    TextField text1;
    private  Screen screen = new Screen();

    @FXML
    public void clicNumber(ActionEvent a) {
        Button b = (Button) a.getSource();
        text1.setText(text1.getText() + b.getText());
    }
    @FXML
    public void clear()
    {
        text1.setText("");
    }

    @FXML
    public void clicEquel() {
        try{
            text1.setText(calculator.calculate(text1.getText()));}
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba!");
            alert.setHeaderText("Chybný vstup.");
            alert.setContentText("Zadejte platný matematický výraz.");            
            alert.showAndWait();
        }
        catch(IllegalArgumentException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba!");
            alert.setHeaderText("Chybný vstup.");
            alert.setContentText(e.getMessage());            
            alert.showAndWait();
        
        
        }
       
       
       
    }
     @FXML
    public void clicBackspace() {
       text1.setText(calculator.backspace(text1.getText()));
    }
     @FXML
    public void clicVolume() throws IOException {
            screen.changeScene("Volume.fxml", (Stage) text1.getScene().getWindow());
    }
     @FXML
    public void clicConventor() throws IOException {
            screen.changeScene("Conventor.fxml", (Stage) text1.getScene().getWindow());
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
