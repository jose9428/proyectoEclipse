package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.Conexion;
import modelo.Departamento;

public class DepartamentoDAO {
	
	public ArrayList<Departamento> listarTodos(){
		ArrayList<Departamento> lista = new ArrayList<Departamento>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = Conexion.getConexion();
			String mysql = "SELECT * FROM Departamento";
			pstm = cn.prepareStatement(mysql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Departamento obj = new Departamento();
				obj.setId_dep(rs.getInt("id_dep"));
				obj.setNom_dep(rs.getString("nom_dep"));
				lista.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (pstm != null) pstm.close();
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return lista;
	}
}
