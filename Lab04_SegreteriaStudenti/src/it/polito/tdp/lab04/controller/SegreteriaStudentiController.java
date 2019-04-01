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
	
	public void setModel(SegreteriaStudentiModel model) {
		this.model = model;
	}

    @FXML
    private ComboBox<Corso> comboBoxCorsi;

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
		    		if(comboBoxCorsi.getValue()==null) {
			    	List<Corso> corsi = new LinkedList<>();
			    	corsi.addAll(model.cercaCorsiPerStudente(m));
			    	if(corsi==null)
			    		txtRisultati.setText("Lo studente non è iscritto ad alcun corso");
			    	else {
			    		for(Corso c : corsi) {
			    			txtRisultati.setText(c.toString()+"\n");
			    		}
			    	}
		    	}
		    	else {
		    		Corso corso = comboBoxCorsi.getValue();
		    		boolean iscritto = model.cercaCorsoPerStudente(m, corso);
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
    	Corso corso = comboBoxCorsi.getValue();
    	List<Studente> studenti = new LinkedList<>();
    	if(corso==null)
    		txtRisultati.setText("Non hai selezionato alcun corso");
    	else {
    		studenti.addAll(model.cercaStudentePerCorso(corso));
    		if(studenti==null) {
    			txtRisultati.setText("Nessuno studente è iscritto al corso");
    		}
    		else {
	    		for(Studente s : studenti) {
	    			txtRisultati.setText(s.toString());
	    		}
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
    }
    
    @FXML
	public void initialize() throws IOException{
    	List<Corso> tuttiCorsi = new LinkedList<>();
    	tuttiCorsi.addAll(model.getCorsi());
		comboBoxCorsi.getItems().addAll(tuttiCorsi);
		comboBoxCorsi.getItems().add(null);
	}

}
