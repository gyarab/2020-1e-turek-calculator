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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jakub
 */
public class VolumeAndContentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ComboBox oComb,uComb; 
    @FXML
    TextField vTex,bTex,aTex,cTex;
    @FXML
    Label showResult,showResult1, result1,result, vLab,bLab,aLab,cLab;
   
   private Volume volume = new Volume();
   private Surface surface = new Surface();
   private Perimeter perimeter = new Perimeter();
   private Screen screen = new Screen();

    public void clicNumber(ActionEvent a) {
        Button b = (Button) a.getSource();
        if (aTex.isFocused()) {
            aTex.setText(aTex.getText() + b.getText());
        } else if (vTex.isFocused()) {
            vTex.setText(vTex.getText() + b.getText());
        } else if (bTex.isFocused()) {
            bTex.setText(bTex.getText() + b.getText());
        }
         else if (cTex.isFocused()) {
            cTex.setText(cTex.getText() + b.getText());
        }


    }

    @FXML
    public void hitCombo() {
        String object = String.valueOf(oComb.getSelectionModel().getSelectedItem());
        switch (object) {
            case "Kruh":
                setValue("r:", null,null, null, "O:", "S:");
                break;
            case "Obdelník":
                setValue("a:", "b:",null, null, "O:", "S:");
                break;
            case "Čtverec":
                setValue("a:", null,null, null, "O:", "S:");
                break;
            case "Trojúhelník":
                setValue("a:", "b:","v:","c:", "O:", "S:");
                break;
            case "Válec":
                setValue("r:", "v:",null, null, "V:", "S:");
                break;
            case "Kvádr":
                setValue("a:", "b:",null, "c:", "V:", "S:");
                break;
            case "Krychle":
                setValue("a:", null,null, null, "V:", "S:");
                break;
            case "Jehlan":
                setValue("a:","v:",null, null, "V:", "S:");
                break;
            case "Koule":
                setValue("r:", null, null,null, "V:", "S:");
                break;

        }
    }

    @FXML
    public void hitEnter() {
        String object = String.valueOf(oComb.getSelectionModel().getSelectedItem());

        double a = 0;
        double b = 0;
        double v = 0;
        double c =0;
        try {
            if (!"".equals(aTex.getText())) {
                a = Double.valueOf(aTex.getText());
            }
            if (!"".equals(bTex.getText())) {
                b = Double.valueOf(bTex.getText());
            }
            if (!"".equals(vTex.getText())) {
                v = Double.valueOf(vTex.getText());
            }
             if (!"".equals(cTex.getText())) {
                c = Double.valueOf(cTex.getText());
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Chyba!");
            alert.setHeaderText("Chybný vstup.");
            alert.setContentText("Zadávejte pouze čísla.");
            
            alert.showAndWait();
        }

        switch (object) {
            case "Kruh":
                setResult(surface.roundSurface(a), perimeter.roundPerimeter(a));
                break;
            case "Obdelník":
                setResult(surface.rectangleSurface(a, b), perimeter.rectangelPerimetr(a, b));
                break;
            case "Čtverec":
                setResult(surface.squerContent(a), perimeter.squerPerimeter(a));
                break;
            case "Trojúhelník":
                setResult(surface.triangleSurface(a, c),perimeter.triangelPerimeter(a, b, v));
                break;
            case "Válec":
                setResult(volume.cylinderVolume(a,b), surface.cylinderSurface(a,b));
                break;
            case "Kvádr":
                setResult(surface.blockSurface(a, b, v),volume.blockVolume(a, b, v));
                break;
            case "Krychle":
                setResult(surface.cubeSurface(a),volume.cubeVolume(a));
                break;
            case "Jehlan":
                setResult(volume.pyramidVolume(a,b), volume.pyramidVolume(a,b));
                break;
            case "Koule":
                 setResult(volume.sphereVolume(a), surface.sphereSurface(a));
                break;
            default:
                showResult.setText("Je nutné vybrat těleso");

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oComb.getItems().addAll("Čtverec", "Obdelník", "Kruh", "Trojúhelník",
                "Válec", "Kvádr", "Jehlan", "Krychle", "Koule");
        uComb.getItems().addAll("cm", "dm", "m", "in", "ft", "yd");
        uComb.getSelectionModel().select("-Vyberte jednotky-");
        oComb.getSelectionModel().select("-Vyberte obrazec-");

    }

    private void resetState() {
        vTex.setVisible(false);
        bTex.setVisible(false);
        aTex.setVisible(false);
        cTex.setVisible(false);
        vTex.setText("");
        bTex.setText("");
        aTex.setText("");
        cTex.setText("");
    }

    private void setValue(String a, String b, String c, String v, String res, String res1) {
        resetState();
        aLab.setText(a);
        bLab.setText(b);
        cLab.setText(c);
        vLab.setText(v);
        result.setText(res);
        result1.setText(res1);
        if (a != null) {
            aTex.setVisible(true);
        }
        if (b != null) {
            bTex.setVisible(true);
        }
        if (v != null) {
            vTex.setVisible(true);
        }
         if (c != null) {
            cTex.setVisible(true);
        }

    }

    private void setResult(double result, double result1) {
        String units = String.valueOf(uComb.getSelectionModel().getSelectedItem());
       if(!units.equals("-Vyberte jednotky-"))
       {
        String exp ="" ;
        String res = Output.removeDecimal(String.valueOf(result));
        String res1 = Output.removeDecimal(String.valueOf(result1));
        if(this.result.getText().equals("V:"))
            exp = "³";
        showResult.setText(res1 + " " + units + exp);
        showResult1.setText(res+ " " + units +"²");
       }
       else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Upozornění");
            alert.setHeaderText("Nezadané jednoty");
            alert.setContentText("Je nutné zadat jednotky.");
             alert.showAndWait();      
       }

    }
      @FXML
    public void clicConventor() throws IOException {
            screen.changeScene("Conventor.fxml", (Stage) showResult.getScene().getWindow());
    }
      @FXML
    public void clicCalculator() throws IOException {
            screen.changeScene("Calculator.fxml", (Stage) showResult.getScene().getWindow());
    }


}
