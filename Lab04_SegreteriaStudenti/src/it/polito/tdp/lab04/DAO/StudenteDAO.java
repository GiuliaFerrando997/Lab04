package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public String getStudente(int matr) {
		
		String nome = "";
		String cognome = "";
		Connection conn = ConnectDB.getConnection();
		List<Corso> tuttiCorsi = new LinkedList<>();
			try {
				String sql = "SELECT cognome, nome FROM studente WHERE matricola=? ";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, matr);
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
				cognome=rs.getString("cognome");
				nome=rs.getString("nome");
				}
				
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(nome.equals("") && cognome.equals(""))
				return null;
			else 
				return cognome + " "+nome;
	}

	public List<Studente> getStudentiPerCorso(String corso) {;
		
		List<Studente> stud = new LinkedList<>();
		Connection conn = ConnectDB.getConnection();
		
		try {
			String sql = "SELECT s.matricola, cognome, nome, CDS FROM studente as s, iscrizione as i WHERE s.matricola=i.matricola  AND i.codins=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);
			ResultSet rs = st.executeQuery();
			
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"),
						rs.getString("cognome"),
						rs.getString("nome"),
						rs.getString("CDS"));
				stud.add(s);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stud;
	}

	public void aggiungiStudente(int m, String corso) {
		
		Connection conn = ConnectDB.getConnection();
		 
		try {
			String sql = "INSERT INTO iscrizione (matricola, codins) VALUES (?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,m);
			st.setString(2,corso);
			
			ResultSet rs = st.executeQuery();
		
		conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	

}
