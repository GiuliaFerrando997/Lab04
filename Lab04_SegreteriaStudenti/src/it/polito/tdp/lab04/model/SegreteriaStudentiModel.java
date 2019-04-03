package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class SegreteriaStudentiModel {
	
	public List<String> getCorsi(){
		CorsoDAO dao = new CorsoDAO();
		return dao.getCorsi();
	}

	public String cercaStudente(int matricola) {
		StudenteDAO dao = new StudenteDAO();
		return dao.getStudente(matricola);
	}

	public List<Studente> cercaStudentePerCorso(String corso) {
		StudenteDAO dao = new StudenteDAO();
		return dao.getStudentiPerCorso(corso);
		
	}

	public List<Corso> cercaCorsiPerStudente(int matricola) {
		CorsoDAO dao = new CorsoDAO();
		return dao.getCorsiPerStudente(matricola);
	}

	public boolean cercaCorsoPerStudente(int matricola, String corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.getCorsiPerStudente(matricola, corso);
	}

	public void aggiungiStudente(int m, String corso) {
		StudenteDAO dao = new StudenteDAO();
		dao.aggiungiStudente(m, corso);
	}

	

	

}
