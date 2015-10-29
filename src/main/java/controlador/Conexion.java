package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Conexion {
 
	private Connection conexion;
	private Statement coche;
	private PreparedStatement cochePreparado;
	
	/*public DatabaseConnection() {


		this("jdbc:mysql://127.0.0.1/plannerPropio","root","12345");		
	}*/
	public Conexion() {
		//this.ConexionLocal();
		this.ConexionRemota();	
	}
	public void ConexionLocal() {
		try {
			String url="jdbc:mysql://127.0.0.1/gwdwfan";
			String usuario="root";
			String contraseña="12345";
			//Cargamos el driver
			Class.forName("com.mysql.jdbc.Driver");
			//Establecemos la conexion
			conexion = DriverManager.getConnection(url,usuario,contraseña);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al cargar el Driver");
			e.printStackTrace(); // Indica el ambito de la excepción
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erron en la conexion a la BBDD gwdwfan(local)");
			e.printStackTrace(); 
		}		
	}
	public void ConexionRemota() {
		try {
			String USERNAME = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
			String PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
			String DB_NAME = System.getenv("OPENSHIFT_APP_NAME");
			String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
			   String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
			   
			String FORNAME_URL = "com.mysql.jdbc.Driver";
			String URL = "jdbc:mysql://" + host + ":" + port + "/" + DB_NAME;
			//Cargamos el driver
			Class.forName(FORNAME_URL);
			//Establecemos la conexion
			conexion = DriverManager.getConnection(URL , USERNAME , PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al cargar el Driver");
			e.printStackTrace(); // Indica el ambito de la excepci�n
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erron en la conexion a la BBDD de gwdwfan(remota)");
			e.printStackTrace();
		}
	}
	public Connection getConexion() {
		return conexion;
		
	}
	public PreparedStatement devuelvePS(String sql) {
		try {
			cochePreparado = conexion.prepareStatement(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al preparar el coche");
			e.printStackTrace();
		}
		return cochePreparado;
	}
	public ResultSet devuelveRS(String sql) throws SQLException{

		ResultSet rs = conexion.createStatement().executeQuery(sql);
		//conexion.close();
		return rs;
	}

               
 
}