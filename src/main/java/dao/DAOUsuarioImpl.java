package dao;

import interfaces.DAOUsuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import modelo.Usuario;

import org.apache.commons.codec.digest.DigestUtils;

public class DAOUsuarioImpl extends Conexion implements DAOUsuario {

	@Override
	public Usuario busquedaUser(Usuario usu) throws Exception {
		// TODO Auto-generated method stub
		
		this.getConexion();
		try {
			String passcodif = DigestUtils.sha256Hex(usu.getPassword());
			PreparedStatement ps = this.conexion.prepareStatement
					("Select IdUsuario, usuario from usuarios where email= ? and password= ?");
			ps.setString(1, usu.getEmail());
			ps.setString(2, passcodif);
			ResultSet rs = ps.executeQuery();
			rs.first();
			if (rs.getRow() == 0) {
				usu.setRegistrado(false);
				
			} else {
				usu.setUsuario(rs.getString("usuario"));
				usu.setIdUsuario(rs.getInt("IdUsuario"));
				usu.setRegistrado(true);
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.cerrar();
		}
		return usu;
	}

	@Override
	public Boolean usuCorrecto(Usuario usu) throws Exception {
		Boolean resul = false;
		try {
			this.getConexion();
			PreparedStatement ps = this.conexion.prepareStatement
					("Select IdUsuario from usuarios where usuario= ?");
			ps.setString(1, usu.getUsuario());
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {		
				resul= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.cerrar();
		}
		return resul;

	}

	@Override
	public Boolean emaCorrecto(Usuario usu, String busqueda) throws Exception {
		Boolean res = false;
		try {
			this.getConexion();
			PreparedStatement ps = this.conexion.prepareStatement
					("Select IdUsuario from usuarios where email= ?");
			ps.setString(1, usu.getEmail());
			ResultSet rs = ps.executeQuery();
			if (busqueda.equals("registro")) {
				if (!rs.next()) {					
					res= true;
				}
			} else if (busqueda.equals("lost")) {
				if (rs.next()) {					
					res= true;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.cerrar();
		
		}
		return res;
	}

	@Override
	public Boolean NuevoUsu(Usuario usu) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getConexion();
			// Codificamos la password para no tenerla en texto plano en la base
			// de datos
			String passcodif = DigestUtils.sha256Hex(usu.getPassword());
			String query = "insert into usuarios (IdUsuario, usuario, password, email) values (Null, ?,?,?)";
			PreparedStatement ps = this.conexion.prepareStatement(query);
			ps.setString(1, usu.getUsuario());
			ps.setString(2, passcodif);
			ps.setString(3, usu.getEmail());
			ps.execute();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.cerrar();
		}
		return true;
	}
	

	@Override
	public int ObtenerIDUsuario(Usuario usu) throws Exception {
		// TODO Auto-generated method stub
		int IdUser=0;
		try {

			this.getConexion();
			PreparedStatement ps = this.conexion.prepareStatement
					("Select IdUsuario from usuarios where email= ?");
			ps.setString(1, usu.getEmail());
			ResultSet rs = ps.executeQuery();
			// rs.first();
			if (rs.next()) {
				IdUser = rs.getInt("IdUsuario");
			} else {
				IdUser = 0;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IdUser;
	}

	@Override
	public void InsertarLostPass(Usuario usu) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getConexion();
			// Codificamos la password para no tenerla en texto plano en la base
			// de datos
			String query = "insert into lostpassuser (IdUsuario, email, pass, token, fecha) values (?,?,?,?, DEFAULT)";
			PreparedStatement ps = this.conexion.prepareStatement(query);
			ps.setInt(1, usu.getIdUsuario());
			ps.setString(2, usu.getEmail());
			ps.setString(3, usu.getPassword());
			ps.setString(4, usu.getToken());
			ps.execute();

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.cerrar();
		}
		
	}

	@Override
	public boolean BusquedaClave(Usuario usu) throws Exception {
		// TODO Auto-generated method stub
		Boolean resul = true;
		try {

			this.getConexion();
			PreparedStatement ps = this.conexion.prepareStatement("Select * from lostpassuser where email= ? and pass =?");
			ps.setString(1, usu.getEmail());
			ps.setString(2, usu.getPassword());
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				resul = false;
			} 
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public void CambiarPass(Usuario usu) throws Exception {
		// TODO Auto-generated method stub
		try {
			String passcodif = DigestUtils.sha256Hex(usu.getPassword());
			this.getConexion();
			PreparedStatement ps = this.conexion.prepareStatement("UPDATE usuarios set password= ? where  email= ?");
			ps.setString(1, passcodif);
			ps.setString(2, usu.getEmail());
			ps.executeUpdate();
			ps = this.conexion.prepareStatement("DELETE FROM lostpassuser where email=? and token =?");
			ps.setString(1, usu.getEmail());
			ps.setString(2, usu.getToken());
			ps.executeUpdate();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.cerrar();
		}
		
	}

	@Override
	public boolean buscaemalost(Usuario usu) throws Exception {
		Boolean resul = true;
		try {

			this.getConexion();
			PreparedStatement ps = this.conexion.prepareStatement("Select * from lostpassuser where email= ?");
			ps.setString(1, usu.getEmail());
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				resul = false;
			} 
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resul;
	}

	@Override
	public boolean buscaematoken(Usuario usu) throws Exception {
		Boolean resul = false;
		try {

			Conexion con = new Conexion();
			PreparedStatement ps = this.conexion.prepareStatement
					("Select * from lostpassuser where email= ? and token = ?");
			ps.setString(1, usu.getEmail());
			ps.setString(2, usu.getToken());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {				
				resul = true;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.cerrar();
		}
		return resul;
	}

	@Override
	public void enviomail(Usuario usu, String URL) throws Exception {
		try {
			// Propiedades de la conexión
			// esto ya no funciona habrá que hacerlo con autorizacion OAuth
			// ... Investigando...
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.user", "infogwdwfan@gmail.com");
			props.setProperty("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.EnableSSL.enable", "true");
			props.setProperty("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.fallbac k", "false");
			props.setProperty("mail.smtp.socketFactory.port", "465");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"infogwdwfan@gmail.com", "QHQBFyuW");
						}
					});

			// Construimos el mensaje
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usu.getEmail()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					usu.getEmail()));
			message.setSubject(" Recuperacion de contraseña ");
			message.setText(
					"<img src='https://drive.google.com/uc?export=download&id=0B1P7AQivZakEM2Z6VkplRjNXXzg' alt=''>"
							+ "<p><b>Recuperación de contraseña</b></p>"
							+ "<p>Para poder cambiar tu contraseña de entrada tendrás que seguir dos simples pasos</p>"
							+ "1.- Compia la clave que te proporcionamos que aparece a continuación</p>"
							+ "La clave =<b>"
							+ usu.getPassword()
							+ "</b><br>"
							+ "<p>2.- Accede a la siguiente pagina</p>"
							+ "La URL = <a href='" + URL + "'> " + URL,
					"ISO-8859-1", "html");

			// Lo enviamos.
			Transport t = session.getTransport("smtp");
			t.connect("infogwdwfan@gmail.com", "QHQBFyuW");
			t.sendMessage(message, message.getAllRecipients());

			// Cierre.
			t.close();
			System.out.println("mensaje enviado");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}


