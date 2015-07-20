package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import controlador.Conexion;

public class Usuario {
	private String usuario;
	private String password;
	private int codUsuario;
	
	private int getCodUsuario() {
		return codUsuario;
	}
	private void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Usuario(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String busquedaUser(String ema, String pass) {
		String nombreUser="";
		try {
			String passcodif= DigestUtils.sha256Hex(pass);
			System.out.println("pass codificada = "+passcodif );
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("Select IdUsuario, usuario from usuarios where email= ? and password= ?");		
			ps.setString(1, ema);
			ps.setString(2, passcodif);
			ResultSet maleta= ps.executeQuery();
			maleta.first();
			if(maleta.getRow()==0) {
				con.getConexion().close();
			}else {
				nombreUser = maleta.getString("usuario");
				con.getConexion().close();

			}
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return nombreUser;
	}
	public Boolean usuCorrecto(String usuario) {
		try {
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("Select IdUsuario from usuarios where usuario= ?");		
			ps.setString(1, usuario);
			ResultSet rs= ps.executeQuery();
			//rs.first();
			if (rs.next()) {
				con.getConexion().close();
				return false;
			}else {
				con.getConexion().close();
				return true;
			}
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	public Boolean emaCorrecto(String email) {
		try {
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("Select IdUsuario from usuarios where email= ?");		
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			//rs.first();
			if (rs.next()) {
				con.getConexion().close();
				return false;
			}else {
				con.getConexion().close();
				return true;
			}
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	public Boolean NuevoUsu(String usuario, String email, String pass) {
		try {
			Conexion con = new Conexion();
			// Codificamos la password  para no tenerla en texto plano en la base de datos
			String passcodif= DigestUtils.sha256Hex(pass);
			String query= "insert into usuarios (IdUsuario, usuario, password, email) values (Null, ?,?,?)";
			PreparedStatement ps = con.devuelvePS(query);		
			ps.setString(1, usuario);
			ps.setString(2, passcodif);
			ps.setString(3, email);
			ps.execute();
			con.getConexion().close();
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
}