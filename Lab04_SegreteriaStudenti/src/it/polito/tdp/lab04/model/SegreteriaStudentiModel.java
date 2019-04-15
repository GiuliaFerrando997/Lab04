package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class SegreteriaStudentiModel {
	
	private CorsoDAO dao;
	private StudenteDAO ddao = new StudenteDAO();
	
	public SegreteriaStudentiModel() {
		CorsoDAO dao = new CorsoDAO();
		StudenteDAO ddao = new StudenteDAO();
	}
	
	public List<String> getCorsi(){
		return dao.getCorsi();
	}

	public String cercaStudente(int matricola) {
		return ddao.getStudente(matricola);
	}

	public List<Studente> cercaStudentePerCorso(String corso) {
		return ddao.getStudentiPerCorso(corso);
		
	}

	public List<Corso> cercaCorsiPerStudente(int matricola) {
		return dao.getCorsiPerStudente(matricola);
	}

	public boolean cercaCorsoPerStudente(int matricola, String corso) {
		return dao.getCorsiPerStudente(matricola, corso);
	}

	public boolean aggiungiStudente(int m, String corso) {
		return ddao.aggiungiStudente(m, corso);
	}

	

	

}
