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
		String sql = "SELECT cognome, nome FROM studente WHERE matricola=? ";
		String nome = null;
		String cognome = null;
		Connection conn = ConnectDB.getConnection();
		List<Corso> tuttiCorsi = new LinkedList<>();
			try {
				
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				st.setInt(1, matr);
				
				cognome=rs.getString("cognome");
				nome=rs.getString("nome");
				
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return cognome + " "+nome;
	}

	public List<Studente> getStudentiPerCorso(Corso corso) {;
		String sql = "SELECT s.matricola, cognome, nome, CDS FROM studente as s, iscrizione as i "
				+ "WHERE s.matricola=i.matricola  AND i.codins=?";
		List<Studente> stud = new LinkedList<>();
		Connection conn = ConnectDB.getConnection();
		
		try {
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			st.setString(1, corso.getCodins());
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"),
						rs.getString("cognome"),
						rs.getString("nome"),
						rs.getString("CDS"));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stud;
	}

}
