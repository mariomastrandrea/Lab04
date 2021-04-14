package it.polito.tdp.lab04;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application 
{
    @Override
    public void start(Stage stage) throws Exception 
    {    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene_lab04.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
             
    	FXMLController controller = loader.getController();
    	
        /*
		 * Create and set the model here!
		 * 
		 *  Model model = new Model();
		 *  controller.setModel(model);
		 */
          
        stage.setTitle("Lab04 - Segreteria Studenti");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
