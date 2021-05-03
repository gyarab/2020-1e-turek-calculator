/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jakub
 */
public class Screen {
    
    public void changeScene(String scene,Stage stage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource(scene));
        stage.setScene(new Scene(root));
    
    }
    
}
