package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.Conexion;
import modelo.*;

public class DenunciaDAO {
	public ArrayList<Denuncia> listarTodos(){
		ArrayList<Denuncia> lista = new ArrayList<Denuncia>();
		Connection cn = null;
		CallableStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = Conexion.getConexion();
			pstm = cn.prepareCall("{call sp_listarTodosDenuncias()}");
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Denuncia obj = new Denuncia();
				obj.setId_dep(rs.getInt("id_dep"));
				obj.setNom_dep(rs.getString("nom_dep"));
				obj.setId_denuncia(rs.getInt("id_denuncia"));
				obj.setNom_dep(rs.getString("nom_dep"));
				obj.setDni(rs.getString("dni"));
				obj.setCorreo(rs.getString("correo"));
				obj.setTelefono(rs.getString("telefono"));
				obj.setCentro_laboral(rs.getString("centro_laboral"));
				obj.setDireccion(rs.getString("direccion"));
				obj.setDenuncia(rs.getString("denuncia"));
				obj.setNom_ape(rs.getString("nom_ape"));
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
	
	
	public int Registrar(Denuncia obj) {
		int resultado = 0;
		Connection cn = null;
		CallableStatement psmt = null;
		
		try {
			cn =  Conexion.getConexion();
			psmt = cn.prepareCall("{call sp_registrarDenuncias(?,?,?,?,?,?,?,?)}");
			
			psmt.setInt(1, obj.getId_dep());
			psmt.setString(2, obj.getNom_ape());
			psmt.setString(3, obj.getDni());
			psmt.setString(4, obj.getCorreo());
			psmt.setString(5, obj.getTelefono());
			psmt.setString(6, obj.getCentro_laboral());
			psmt.setString(7, obj.getDireccion());
			psmt.setString(8, obj.getDenuncia());
			resultado = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (psmt != null) psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	public int Editar(Denuncia obj) {
		int resultado = 0;
		Connection cn = null;
		CallableStatement psmt = null;
		
		try {
			cn =  Conexion.getConexion();
			psmt = cn.prepareCall("{call sp_editarDenuncias(?,?,?,?,?,?,?,?,?)}");
			
			psmt.setInt(1, obj.getId_dep());
			psmt.setString(2, obj.getNom_ape());
			psmt.setString(3, obj.getDni());
			psmt.setString(4, obj.getCorreo());
			psmt.setString(5, obj.getTelefono());
			psmt.setString(6, obj.getCentro_laboral());
			psmt.setString(7, obj.getDireccion());
			psmt.setString(8, obj.getDenuncia());
			psmt.setInt(9, obj.getId_denuncia());
			resultado = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (psmt != null) psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	public int Eliminar(int id) {
		int resultado = 0;
		Connection cn = null;
		CallableStatement psmt = null;
		
		try {
			cn =  Conexion.getConexion();
			psmt = cn.prepareCall("{call sp_eliminarDenuncias(?)}");
			psmt.setInt(1, id);
			resultado = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) cn.close();
				if (psmt != null) psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	public ArrayList<Denuncia> ListarReporteIncidenciasPorReg(){
		ArrayList<Denuncia> lista = new ArrayList<Denuncia>();
		Connection cn = null;
		CallableStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = Conexion.getConexion();
			pstm = cn.prepareCall("{call sp_reporteIncidencias()}");
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Denuncia obj = new Denuncia();
				obj.setNom_dep(rs.getString(1));
				obj.setNumero(rs.getString(2));
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
