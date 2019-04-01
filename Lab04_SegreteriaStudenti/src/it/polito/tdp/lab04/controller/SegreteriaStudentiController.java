package it.polito.tdp.lab04.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.SegreteriaStudentiModel;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	SegreteriaStudentiModel elenco;
	private SegreteriaStudentiModel model;
	private List<String> pregare = new LinkedList<>();
		
	
	public void setModel(SegreteriaStudentiModel model) {
		this.model = model;
	}
	

    @FXML
    private ComboBox<String> comboBoxCorsi;

    @FXML
    private Button btmCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btmCercaMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btmCercaCorsi;

    @FXML
    private Button btmIscrivi;

    @FXML
    private TextArea txtRisultati;

    @FXML
    private Button btmReset;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	String matricola = (txtMatricola.getText());
    	if(!matricola.matches("[0-9]*"))
    		txtRisultati.setText("Inserisci una matricola valida");
    	else {
	    	int m = Integer.parseInt(matricola);
	    	if(model.cercaStudente(m)==null) {
	    		txtRisultati.setText("Lo studente non è presente");
	    	}
	    	else {
		    		if(comboBoxCorsi.getValue()==null ||
		    				comboBoxCorsi.getValue().equals("")) {
			    	List<Corso> corsi1 = new LinkedList<>();
			    	corsi1.addAll(model.cercaCorsiPerStudente(m));
			    	if(corsi1==null)
			    		txtRisultati.setText("Lo studente non è iscritto ad alcun corso");
			    	else {
			    		String s = "";
			    		for(Corso c : corsi1) {
			    			s+=(c.toString()+"\n");
			    		}
			    		txtRisultati.setText(s);
			    	}
		    	}
		    	else {
		    		String corso = comboBoxCorsi.getValue();
		    		String corsi = corso.split(" ")[0];
		    		boolean iscritto = model.cercaCorsoPerStudente(m, corsi);
		    		if(iscritto==true)
		    			txtRisultati.setText("Lo studente è iscritto al corso");
		    		else 
		    			txtRisultati.setText("Lo studente non è iscritto al corso");
		    	}
	    	}
	    	
    	}

    }

    @FXML
    void cercaIscritti(ActionEvent event) {
    	String corso = comboBoxCorsi.getValue();
    	String corsi = "";
    	String risultato = "";
    	if(corso.equals(""))
    		txtRisultati.setText("Seleziona un corso!");
    	else {
    		corsi = corso.split(" ")[0];
	    	List<Studente> studenti = new LinkedList<>();
	    		studenti.addAll(model.cercaStudentePerCorso(corsi));
	    		if(studenti==null) {
	    			txtRisultati.setText("Nessuno studente è iscritto al corso");
	    		}
	    		else {
		    		for(Studente s : studenti) {
		    			risultato+=s.toString()+"\n";
		    		}
		    		txtRisultati.setText(risultato);
	    		}
    	}

    }

    @FXML
    void cercaMatricola(ActionEvent event) {
    	String matricola = (txtMatricola.getText());
    	if(!matricola.matches("[0-9]*"))
    		txtRisultati.setText("Inserisci una matricola valida");
    	else {
	    	int m = Integer.parseInt(matricola);
    		String stud = model.cercaStudente(m);
	    	String[] ris = stud.split(" ");
	    	txtNome.setText(ris[1]);
	    	txtCognome.setText(ris[0]);
    	}
    }

    @FXML
    void iscrivi(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	txtRisultati.clear();
    	List<String> s = new LinkedList<>();
    	s.addAll(this.model.getCorsi());
    	comboBoxCorsi.getItems().addAll(s);	
    }
    
   
    
    @FXML
	public void initialize() throws IOException{
    	comboBoxCorsi.getItems().add("");
	}
    
    
    
	
	

	
  

}
