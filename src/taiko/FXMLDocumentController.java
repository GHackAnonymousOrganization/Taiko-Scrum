/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiko;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author GHackAnonymous
 */
public class FXMLDocumentController implements Initializable {

    Instruction instruction = new Instruction();
    
    @FXML
    private JFXButton buttonSettings;

    @FXML
    private JFXButton buttonInstructions;

    @FXML
    private Pane panelSettings;

    @FXML
    private Slider sliderSound;

    @FXML
    private Pane panelInstructions;

    @FXML
    private ComboBox<?> comboLanguage;

    @FXML
    private Pane panelMenuPrincipal;

    @FXML
    private CheckBox checkSoundMute;
    
    @FXML
    private Label textInstruction;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); 
        
    }
    
    @FXML
    void goSettings(ActionEvent event) {
        animation(panelMenuPrincipal, "leftCenter");
        animation(panelSettings, "left");
        panelSettings.setVisible(true);
    }

    @FXML
    void backSettings(ActionEvent event) {
        animation(panelSettings, "centeRight");
        animation(panelMenuPrincipal, "right");
        panelMenuPrincipal.setVisible(true);
    }
    
    @FXML
    void goExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void goPlay(ActionEvent event) {
        throw new UnsupportedOperationException("La ventana play no esta inplementada"); 
        /*animation(panelMenuPrincipal, "leftCenter");
        animation(panelPlay, "left");
        panelPlay.setVisible(true);*/
    }

    @FXML
    void goInstructions(ActionEvent event) {
        //panelInstructions.setVisible(true);
        //animation(panelInstructions, "top");
        
        animation(panelMenuPrincipal, "centeRight");
        animation(panelInstructions, "right");
        
        panelInstructions.setVisible(true);
        
    }

    @FXML
    void backInstructions(ActionEvent event) {
        //animation(panelInstructions, "up");
        
        animation(panelInstructions, "leftCenter");
        animation(panelMenuPrincipal, "left");
        
        panelMenuPrincipal.setVisible(true);
    }
    
    @FXML
    void selectCombo(ActionEvent event) {
        if(comboLanguage.getSelectionModel().isSelected(0)){
            textInstruction.setText(instruction.getEnglishInstruction());
        }else if(comboLanguage.getSelectionModel().isSelected(1)){
            textInstruction.setText(instruction.getSpanishInstruction());
        }else if(comboLanguage.getSelectionModel().isSelected(2)){
            textInstruction.setText(instruction.getBasqueInstruction());
        }
    }
    
    private void animation(Pane p, String mode) {
          if(mode.equals("top")){
           p.setLayoutX(0.0d);
           p.setLayoutY(-400.0d);
           Timeline time = new Timeline();
           time.getKeyFrames().addAll(
           new KeyFrame(Duration.ZERO, new KeyValue(p.layoutYProperty(), -400.0d)), new KeyFrame(Duration.millis(300.d), new KeyValue(p.layoutYProperty(), 0.0d)));
           time.play();
          }
          if(mode.equals("left")){
                p.setLayoutX(600.0d);
                p.setLayoutY(0.0d);
                Timeline time = new Timeline();
                time.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(p.layoutXProperty(), 600.0d)), new KeyFrame(Duration.millis(300.d), new KeyValue(p.layoutXProperty(), 0.0d)));
                time.play();
          }
            if(mode.equals("leftCenter")){
                p.setLayoutX(0.0d);
                p.setLayoutY(0.0d);
               Timeline time = new Timeline();
           time.getKeyFrames().addAll(
           new KeyFrame(Duration.ZERO, new KeyValue(p.layoutXProperty(), 0.0d)), new KeyFrame(Duration.millis(300.d), new KeyValue(p.layoutXProperty(), -600.0d)));
           time.play();
          }
          if(mode.equals("up")){
           p.setLayoutX(0.0d);
           p.setLayoutY(0.0d);
           Timeline time = new Timeline();
           time.getKeyFrames().addAll(
           new KeyFrame(Duration.ZERO, new KeyValue(p.layoutYProperty(), 0.0d)), new KeyFrame(Duration.millis(300.d), new KeyValue(p.layoutYProperty(), -400.0d)));
           time.play();
          }
          
          if(mode.equals("centeRight")){
           p.setLayoutX(0.0d);
           p.setLayoutY(0.0d);
           Timeline time = new Timeline();
           time.getKeyFrames().addAll(
           new KeyFrame(Duration.ZERO, new KeyValue(p.layoutXProperty(), 0.0d)), new KeyFrame(Duration.millis(300.d), new KeyValue(p.layoutXProperty(), 600.0d)));
           time.play();
          }
          
           if(mode.equals("right")){
           p.setLayoutX(-600.0d);
           p.setLayoutY(0.0d);
           Timeline time = new Timeline();
           time.getKeyFrames().addAll(
           new KeyFrame(Duration.ZERO, new KeyValue(p.layoutXProperty(), -600.0d)), new KeyFrame(Duration.millis(300.d), new KeyValue(p.layoutXProperty(), 0.0d)));
           time.play();
          }
          
    }

}
