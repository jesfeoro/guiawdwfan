package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import controlador.Conexion;

public class Usuario {
	private int IdUsuario;
	private String usuario;
	private String password;
	private boolean registrado;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Usuario(String usuario, int IdUsuario) {
		super();
		this.usuario = usuario;
		this.IdUsuario = IdUsuario;
	}

	public Usuario(String usuario, int IdUsuario, boolean registrado) {
		super();
		this.usuario = usuario;
		this.IdUsuario = IdUsuario;
		this.registrado = registrado;
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
	public boolean isRegistrado() {
		return registrado;
	}
	public void setRegistrado(boolean registrado) {
		this.registrado = registrado;
	}
	
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}

	public Usuario busquedaUser(String ema, String pass){
		Usuario user =new Usuario();
		Conexion con = new Conexion();
		try {
			String passcodif= DigestUtils.sha256Hex(pass);			
			PreparedStatement ps = con.devuelvePS("Select IdUsuario, usuario from usuarios where email= ? and password= ?");		
			ps.setString(1, ema);
			ps.setString(2, passcodif);
			ResultSet maleta= ps.executeQuery();
			maleta.first();
			if(maleta.getRow()==0) {
				user.setRegistrado(false);
				con.getConexion().close();
			}else {
				user.setUsuario(maleta.getString("usuario"));
				user.setIdUsuario(maleta.getInt("IdUsuario"));
				user.setRegistrado(true);
				con.getConexion().close();
			}
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return user;
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
	public Boolean emaCorrecto(String email, String busqueda ) {
		try {
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("Select IdUsuario from usuarios where email= ?");		
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			if(busqueda.equals("registro")) {
				if (rs.next()) {
					con.getConexion().close();
					return false;
				}else {
					con.getConexion().close();
					return true;
				}
			}else if (busqueda.equals("lost")) {
				if (rs.next()) {
					con.getConexion().close();
					return true;
				}else {
					con.getConexion().close();
					return false;
				}	
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
	public  int ObtenerIDUsuario(String email) {
		int IdUser;
		try {
			
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("Select IdUsuario from usuarios where email= ?");		
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			//rs.first();
			if (rs.next()) {
				IdUser=rs.getInt("IdUsuario");
				con.getConexion().close();
				return IdUser;
				
			}else {
				IdUser=0;
				con.getConexion().close();
				return IdUser;
				
			}
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return 0;
		
	}
	public void InsertarLostPass(int id, String email, String clave, String token) {
		try {
			Conexion con = new Conexion();
			// Codificamos la password  para no tenerla en texto plano en la base de datos
			String query= "insert into lostpassuser (IdUsuario, email, pass, token, fecha) values (?,?,?,?, DEFAULT)";
			PreparedStatement ps = con.devuelvePS(query);		
			ps.setInt(1, id);
			ps.setString(2, email);
			ps.setString(3, clave);
			ps.setString(4, token);
			ps.execute();
			con.getConexion().close();
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public boolean BusquedaClave(String clave, String email) {
		
		try {
			
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("Select * from lostpassuser where email= ? and pass =?");		
			ps.setString(1, email);
			ps.setString(2, clave);
			ResultSet rs= ps.executeQuery();
			if (rs.next()) {				
				con.getConexion().close();
				return true;	
			}else {				
				con.getConexion().close();
				return false;	
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
	public void CambiarPass(String pass,String email, String token) {
	try {
			String passcodif=DigestUtils.sha256Hex(pass);
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("UPDATE usuarios set password= ? where  email= ?");		
			ps.setString(1, passcodif);
			ps.setString(2, email);
			ps.executeUpdate();
			ps= con.devuelvePS("DELETE FROM lostpassuser where email=? and token =?");
			ps.setString(1, email);
			ps.setString(2, token);
			ps.executeUpdate();
			con.getConexion().close();
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public boolean buscaemalost(String email) {
		try {
			
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("Select * from lostpassuser where email= ?");		
			ps.setString(1, email);
			ResultSet rs= ps.executeQuery();
			if (rs.next()) {				
				con.getConexion().close();
				return true;	
			}else {				
				con.getConexion().close();
				return false;	
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
	public boolean buscaematoken(String email, String token) {
		try {
			
			Conexion con = new Conexion();
			PreparedStatement ps = con.devuelvePS("Select * from lostpassuser where email= ? and token = ?");		
			ps.setString(1, email);
			ps.setString(2, token);
			ResultSet rs= ps.executeQuery();
			if (rs.next()) {				
				con.getConexion().close();
				return true;	
			}else {				
				con.getConexion().close();
				return false;	
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
}