package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static Connection getConexion() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			String url = "jdbc:mysql://localhost:3306/BDFiscalia?useSSL=false&useTimezone=true&serverTimezone=UTC";
			String usr = "root";
			String psw = "";
			con = DriverManager.getConnection(url, usr, psw);
			System.out.println("Conexion OK");
		} catch (ClassNotFoundException e) {
			System.out.println("Error >> Driver no Instalado!!" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error >> de conexión con la BD" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error >> general : " + e.getMessage());
		} 
		return con;
	}
	
}
