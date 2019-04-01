package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;


public class CorsoDAO {
	
	
	public List<String> getCorsi(){
	Connection conn = ConnectDB.getConnection();
	List<String> tuttiCorsi = new LinkedList<>();
		try {
			String sql = "SELECT * FROM corso";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),
						rs.getInt("crediti"),
						rs.getString("nome"),
						rs.getInt("pd"));
				tuttiCorsi.add(c.getCodins()+" "+c.getNome());
			}
		
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tuttiCorsi;
		
		
	
	}

	public List<Corso> getCorsiPerStudente(int matricola) {
		Connection conn = ConnectDB.getConnection();
		List<Corso> corsi = new LinkedList<>();
		String sql = "SELECT c.codins, crediti, nome, pd FROM corso AS c, iscrizione AS i WHERE c.codins=i.codins AND i.matricola=?";
			try {
				
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, matricola);
				ResultSet rs = st.executeQuery();
				
				
				while(rs.next()) {
					Corso c = new Corso(rs.getString("codins"),
							rs.getInt("crediti"),
							rs.getString("nome"),
							rs.getInt("pd"));
					corsi.add(c);
				}
			
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return corsi;
	}

	public boolean getCorsiPerStudente(int matricola, String corso) {
		Connection conn = ConnectDB.getConnection();
		String codice = null;
		boolean iscritto = false;
		String sql = "SELECT c.codins, crediti, nome, pd FROM corso AS c, iscrizione AS i WHERE c.codins=i.codins AND i.matricola=? AND i.codins=?";
			try {
				
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, matricola);
				st.setString(2, corso);
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					codice=rs.getString("codins");
				}
				if(codice!=null && !codice.equals(""))
					iscritto=true;
				
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return iscritto;
	}
	
	
}
