/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author GHackAnonymous
 */
public class Taiko extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        Parent homePageParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene homePageScene = new Scene(homePageParent);
        
        stage.setScene(homePageScene);
        stage.getIcons().add(new Image("fotos/icono2.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
