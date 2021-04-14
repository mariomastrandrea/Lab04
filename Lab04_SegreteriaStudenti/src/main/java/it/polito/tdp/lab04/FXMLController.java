/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController 
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> CorsiComboBox;

    @FXML
    private TextField MatricolaTextField;

    @FXML
    private TextField NomeTextField;

    @FXML
    private TextField CognomeTextField;

    @FXML
    private TextArea ResultTextArea;

    @FXML
    void handleAutocompletamento(ActionEvent event) 
    {

    }

    @FXML
    void handleCercaCorsi(ActionEvent event) 
    {

    }

    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) 
    {

    }

    @FXML
    void handleCorsiComboBox(ActionEvent event) 
    {

    }

    @FXML
    void handleIscriviStudente(ActionEvent event) 
    {

    }

    @FXML
    void handleMatricolaTextField(ActionEvent event) 
    {

    }

    @FXML
    void handleReset(ActionEvent event) 
    {

    }

    @FXML
    void initialize() 
    {
        assert CorsiComboBox != null : "fx:id=\"CorsiComboBox\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert MatricolaTextField != null : "fx:id=\"MatricolaTextField\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert NomeTextField != null : "fx:id=\"NomeTextField\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert CognomeTextField != null : "fx:id=\"CognomeTextField\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert ResultTextArea != null : "fx:id=\"ResultTextArea\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
    }
    
}
