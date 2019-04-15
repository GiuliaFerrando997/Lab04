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
		this.setComboItems();
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
    	try {
    	String matricola = (txtMatricola.getText());
    	if(!matricola.matches("[0-9]*") || matricola==null || matricola.equals(""))
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
			    		StringBuilder sb = new StringBuilder();
			    		for(Corso c : corsi1) {
			    			sb.append(String.format("%-10s", c.getCodins()));
			    			sb.append(String.format("%-8s", c.getCrediti()));
			    			sb.append(String.format("%-40s", c.getNome()));
			    			sb.append(String.format("%-10s", c.getPd()));
			    			sb.append("\n");
			    			
			    		}
			    		txtRisultati.appendText(sb.toString());
			    	}
		    	}
		    	else {
		    		this.eIscritto(m);
		    		
		    	}
	    	}
	    	
    	}
    	} catch (NumberFormatException e) {
    		txtRisultati.setText("Inserire una matricola nel formato corretto");
    	} catch (RuntimeException e) {
    		txtRisultati.setText("Errore di connessione al database!");
    	}

    }
    
    public boolean eIscritto(int m) {
    	String corso = comboBoxCorsi.getValue();
		String corsi = corso.split(" ")[0];
		boolean iscritto = model.cercaCorsoPerStudente(m, corsi);
		if(iscritto==true)
			txtRisultati.setText("Lo studente è già iscritto al corso");
		else 
			txtRisultati.setText("Lo studente non è iscritto al corso");
		return iscritto;
		
	
    }

    @FXML
    void cercaIscritti(ActionEvent event) {
    	try {
    	String corso = comboBoxCorsi.getValue();
    	String corsi = "";
    	corsi = corso.split(" ")[0];
    	String risultato = "Gli iscritti al corso " +corso+" sono:\n";
    	StringBuilder sb = new StringBuilder();
    	List<Studente> studenti = new LinkedList<>();
    	if(corso.equals(""))
    		txtRisultati.setText("Seleziona un corso!");
    	else {
	    		studenti.addAll(model.cercaStudentePerCorso(corsi));
	    		if(studenti.size()==0) {
	    			txtRisultati.setText("Nessuno studente è iscritto al corso");
	    		}
	    		else {
		    		for(Studente s : studenti) {
		    			sb.append(String.format("%-10s ", s.getMatricola()));
		    			sb.append(String.format("%-20s ", s.getCognome()));
		    			sb.append(String.format("%-20s ", s.getNome()));
		    			sb.append(String.format("%-10s ", s.getCDS()));
		    			sb.append("\n");
		    		}
		    		txtRisultati.setText(risultato+sb.toString());
	    		}
    	}
    	}catch(RuntimeException e) {
        	txtRisultati.setText("Errore di connessione al Database");
    	}

    }

    @FXML
    void cercaMatricola(ActionEvent event) {
    	try {
    	String matricola = (txtMatricola.getText());
    	if(!matricola.matches("[0-9]*")|| matricola==null || matricola.equals(""))
    		txtRisultati.setText("Inserisci una matricola valida");
    	else {
	    	int m = Integer.parseInt(matricola);
    		String stud = model.cercaStudente(m);
    		if(stud==null)
    			txtRisultati.setText("Matricola non presente");
    		else {
	    	String[] ris = stud.split(" ");
	    	txtNome.setText(ris[1]);
	    	txtCognome.setText(ris[0]);
    		}
    	}
    } catch(RuntimeException e) {
    	txtRisultati.setText("Errore di connessione al Database");
    }
    }

    @FXML
    void iscrivi(ActionEvent event) {
    	try {
    	String matricola = (txtMatricola.getText());
    	String corso = comboBoxCorsi.getValue();
    	int m = -1;
    	boolean presente = false;
    	if(corso.equals(" ")) {
    		txtRisultati.setText("Selezionare il corso");
    	}
    	else {
    		corso=corso.split(" ")[0];
    		if(!matricola.matches("[0-9]*") || matricola==null || matricola.equals("")) {
        		txtRisultati.setText("Inserisci una matricola valida");
    		}
    		else {
    			m=Integer.parseInt(matricola);
    			presente = this.eIscritto(m);
    			if(presente == false) {
    				if(model.aggiungiStudente(m, corso)==true)
    					txtRisultati.setText("Lo studente è stato iscritto");
    				else
    					txtRisultati.setText("Non è stato possibile aggiungere lo studente");
    			}
    		}
    		
    	}
    	}catch(RuntimeException e) {
        	txtRisultati.setText("Errore di connessione al Database");
    }
    }

    @FXML
    void reset(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	txtRisultati.clear();	
    }
    
    private void setComboItems() {
    	List<String> s = new LinkedList<>();
     	s.addAll(this.model.getCorsi());
     	comboBoxCorsi.getItems().add("");
     	comboBoxCorsi.getItems().addAll(s);
  	}
    
    
    @FXML
    void initialize() {
        assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btmCercaIscritti != null : "fx:id=\"btmCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btmCercaMatricola != null : "fx:id=\"btmCercaMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btmCercaCorsi != null : "fx:id=\"btmCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btmIscrivi != null : "fx:id=\"btmIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtRisultati != null : "fx:id=\"txtRisultati\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btmReset != null : "fx:id=\"btmReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
       txtRisultati.setStyle("-fx-font-family: monospace");
    }
    
	
	

	
  

}
