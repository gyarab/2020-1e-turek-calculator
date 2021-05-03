package calculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class MainScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button calculatorBtn, volumeBtn, conventorBtn;
    Screen screen = new Screen();

    @FXML
    public void hitCalculatorBtn() throws IOException {
        screen.changeScene("Calculator.fxml", (Stage) calculatorBtn.getScene().getWindow());
    }

    public void hitConventorBtn() throws IOException {
        screen.changeScene("Conventor.fxml", (Stage) conventorBtn.getScene().getWindow());
    }

    public void hitVolumeBtn() throws IOException {
        screen.changeScene("Volume.fxml", (Stage) volumeBtn.getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
