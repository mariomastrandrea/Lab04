/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.segreteriastudenti;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import it.polito.tdp.segreteriastudenti.model.Corso;
import it.polito.tdp.segreteriastudenti.model.SegreteriaStudentiModel;
import it.polito.tdp.segreteriastudenti.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FXMLController 
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> corsiComboBox;

    @FXML
    private TextField matricolaTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cognomeTextField;

    @FXML
    private TextArea resultTextArea;
    
    @FXML
    private Button cercaIscrittiCorsoButton;
    
    @FXML
    private Button autocompletamentoButton;
    
    @FXML
    private Button cercaCorsiButton;

    @FXML
    private Button iscriviStudenteButton;

    @FXML
    private Button resetButton;
    
    private SegreteriaStudentiModel model;
    

    @FXML
    void handleCorsiComboBox(ActionEvent event) 
    {
    	Corso selectedCorso = this.corsiComboBox.getValue();
    	
    	if(selectedCorso == null || selectedCorso.getCodiceInsegnamento() == null)
    	{
    		this.cercaIscrittiCorsoButton.setDisable(true);
    		this.iscriviStudenteButton.setDisable(true);
    		
    		String resultText = this.resultTextArea.getText();
    		String matricolaInput = this.matricolaTextField.getText();
    		
    		if(resultText == null || resultText.isBlank())
    			if(matricolaInput == null || matricolaInput.isBlank())
    				this.resetButton.setDisable(true);
    	}
    	else 
    	{
    		this.cercaIscrittiCorsoButton.setDisable(false);
    		
    		String matricolaInput = this.matricolaTextField.getText();
    		if(matricolaInput != null && !matricolaInput.isBlank())
    			this.iscriviStudenteButton.setDisable(false);
    		
    		this.resetButton.setDisable(false);
    	}    	
    }
    
    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) 
    {
    	Corso selectedCorso = this.checkSelectedCorso();
    	if(selectedCorso == null)
    		return;
    	
    	Collection<Studente> iscrittiCorso = this.model.getIscrittiA(selectedCorso);
    	
    	if(iscrittiCorso.isEmpty())
    	{
    		this.resultTextArea.setText(String.format("Il corso \"%s\" non ha alcun iscritto!", selectedCorso));
    		return;
    	}
    	
    	//print iscrittiCorso
    	StringBuilder sb = new StringBuilder();
    	
    	for(Studente iscritto : iscrittiCorso)
    	{
    		if(sb.length() > 0)
    			sb.append("\n");
    		
    		sb.append(iscritto.print());
    	}
    	
    	this.resultTextArea.setText(String.format("%-10s %-23s %-23s %-15s\n%s", 
    								"MATRICOLA", "COGNOME", "NOME", "CORSO DI STUDI", sb.toString()));
    }
    
    private Corso checkSelectedCorso()
    {
    	Corso selectedCorso = this.corsiComboBox.getValue();
    	
    	if(selectedCorso == null || selectedCorso.getCodiceInsegnamento() == null)
    	{
    		this.resultTextArea.setText("Errore: selezionare un corso dal menù a tendina");
    		return null;
    	}
    	
    	return selectedCorso;
    }

    @FXML
    void handleMatricolaTextField(KeyEvent event) 
    {    	
    	String matricolaInput = this.matricolaTextField.getText();
    	
    	if(matricolaInput == null || matricolaInput.isBlank())
    	{
    		this.cercaCorsiButton.setDisable(true);
    		this.autocompletamentoButton.setDisable(true);
    		
    		this.nomeTextField.clear();
    		this.cognomeTextField.clear();
    		
    		this.iscriviStudenteButton.setDisable(true);
    		
    		String resultText = this.resultTextArea.getText();
    		Corso selectedCorso = this.corsiComboBox.getValue();
    		
    		if(resultText == null || resultText.isBlank())
    			if(selectedCorso == null || selectedCorso.getCodiceInsegnamento() == null)
    				this.resetButton.setDisable(true);
    	}
    	else 
    	{
    		this.cercaCorsiButton.setDisable(false);
    		this.autocompletamentoButton.setDisable(false);
    		
    		Corso seletedCorso = this.corsiComboBox.getValue();
    		if(seletedCorso != null && seletedCorso.getCodiceInsegnamento() != null)
    			this.iscriviStudenteButton.setDisable(false);
    		
    		this.resetButton.setDisable(false);
		}
    }
    
    @FXML
    void handleAutocompletamento(ActionEvent event) 
    {
    	Studente searchedStudente = this.checkMatricolaInput();
    	
    	if(searchedStudente == null)
    		return;
    	
    	//all right here
    	this.nomeTextField.setText(searchedStudente.getNome());
    	this.cognomeTextField.setText(searchedStudente.getCognome());
    }
    
    /*
     * it checks if exists a student with the inserted 'matricola' and returns it; if not, return 'null'
     */
    private Studente checkMatricolaInput()
    {
    	String matricolaInput = this.matricolaTextField.getText().trim();
    	
    	if(matricolaInput == null || matricolaInput.isBlank())
    	{
    		this.resultTextArea.setText("Errore: devi inserire una matricola!");
    		return null;
    	}
    	    	
    	if(matricolaInput.length() > 6 || !matricolaInput.matches("[0-9]+") )
    	{
    		this.resultTextArea.setText("Errore: inserisci una matricola valida (numero intero di 6 cifre)");
    		return null;
    	}
    	
    	int matricola;
    	try
		{
			matricola = Integer.parseInt(matricolaInput);
		}
		catch(NumberFormatException nfe)
		{
			nfe.printStackTrace();
			this.resultTextArea.setText("Errore formato matricola: inserisci un numero intero di 6 cifre");
			return null;
		}
    	
    	if(!this.model.existsStudente(matricola))
    	{
    		this.resultTextArea.setText(
    				String.format("Errore: NON esiste alcuno studente con matricola \"%d\"!", matricola));
    		return null;
    	}
    	
    	//all right here
    	return this.model.getStudente(matricola);
    }

    @FXML
    void handleCercaCorsi(ActionEvent event) 
    {
    	Studente searchedStudente = this.checkMatricolaInput();
    	
    	if(searchedStudente == null)
    		return;
    	
    	//all right here
    	Collection<Corso> corsi = this.model.getCorsiStudente(searchedStudente);
    	
    	if(corsi.isEmpty())
    	{
    		this.resultTextArea.setText(String.format(
    				"Lo studente %d non e' iscritto ad alcun corso!", searchedStudente.getMatricola()));
    		return;
    	}
    	
    	//print 'corsi'
    	StringBuilder sb = new StringBuilder();
    	
    	for(Corso c : corsi)
    	{
    		if(sb.length() > 0)
    			sb.append("\n");
    		
    		sb.append(c.print());
    	}
    	
    	this.resultTextArea.setText(String.format("%-8s %-8s %-50s %-7s\n%s", 
    								"CODICE", "CREDITI", "NOME", "PERIODO", sb.toString()));
    }
    
    @FXML
    void handleIscriviStudente(ActionEvent event) 
    {
    	Studente selectedStudente = this.checkMatricolaInput();
    	
    	if(selectedStudente == null)
    		return;
    	
    	Corso selectedCorso = this.checkSelectedCorso();
    	
    	if(selectedCorso == null)
    		return;
    	
    	//register new 'iscrizione'
    	
    	boolean outcome = this.model.newIscrizione(selectedStudente, selectedCorso);
    	
    	if(outcome == false)
    	{
    		this.resultTextArea.setText(String.format("Errore: lo studente %d e' gia' iscritto al corso \"%s\"!",
    											selectedStudente.getMatricola(), selectedCorso.getNome()));
    		return;
    	}
    	
    	this.resultTextArea.setText(String.format("Lo studente %d e' stato iscritto correttamente al corso \"%s\" :)",
				selectedStudente.getMatricola(), selectedCorso.getNome()));
    }

    @FXML
    void handleReset(ActionEvent event) 
    {
    	this.corsiComboBox.setValue(this.model.getNoCorso());
    	this.matricolaTextField.clear();
    	this.nomeTextField.clear();
    	this.cognomeTextField.clear();
    	this.resultTextArea.clear();
    	
    	this.cercaIscrittiCorsoButton.setDisable(true);
    	this.autocompletamentoButton.setDisable(true);
    	this.cercaCorsiButton.setDisable(true);
    	this.iscriviStudenteButton.setDisable(true);
    	this.resetButton.setDisable(true);
    }

    @FXML
    void initialize() 
    {
        assert corsiComboBox != null : "fx:id=\"CorsiComboBox\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert cercaIscrittiCorsoButton != null : "fx:id=\"cercaIscrittiCorsoButton\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert matricolaTextField != null : "fx:id=\"MatricolaTextField\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert autocompletamentoButton != null : "fx:id=\"autocompletamentoButton\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert nomeTextField != null : "fx:id=\"NomeTextField\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert cognomeTextField != null : "fx:id=\"CognomeTextField\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert cercaCorsiButton != null : "fx:id=\"cercaCorsiButton\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert iscriviStudenteButton != null : "fx:id=\"iscriviStudenteButton\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert resultTextArea != null : "fx:id=\"ResultTextArea\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
        assert resetButton != null : "fx:id=\"resetButton\" was not injected: check your FXML file 'Scene_lab04.fxml'.";
    }
    
    void setModel(SegreteriaStudentiModel newModel)
    {
    	this.model = newModel;
    	
    	Collection<Corso> corsi = this.model.getAllCorsi();
    	this.corsiComboBox.getItems().addAll(corsi);
    	
    	Corso noCorso = this.model.getNoCorso();
    	this.corsiComboBox.getItems().add(noCorso);
    	
    	this.resultTextArea.setStyle("-fx-font-family: monospaced");
    }
    
}
