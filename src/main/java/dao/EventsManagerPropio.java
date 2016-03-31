package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.EventoMod;
import modelo.Usuario;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.dhtmlx.planner.data.DHXCollection;
import com.mysql.jdbc.Statement;

public class EventsManagerPropio extends DHXEventsManager {	
	public Usuario usu;
	public EventsManagerPropio(HttpServletRequest request) {
		super(request);
		HttpSession sesion = request.getSession();
      					//Recojo el atributo sesion
      					 usu =(Usuario)sesion.getAttribute("Usuario");
		// TODO Auto-generated constructor stub
	}
	
   	public Iterable getEvents() {
      	DHXEventsManager.date_format = "yyyy-MM-dd HH:mm:ss";
      	List evs = new ArrayList();
      	try {
      					Conexion con = new Conexion();
      					
      					
						String query = "SELECT event_id, event_name, start_date, end_date, subject, readonly FROM events_mio";
								//	" WHERE usuario='admin' OR usuario='"+usu.getUsuario()+"'";

						ResultSet resultset =con.devuelveRS(query);
					
						while (resultset.next()) {
					  	EventoMod e = new EventoMod();
					  	e.setId(Integer.parseInt(resultset.getString("event_id")));
					      e.setText(resultset.getString("event_name"));
					      e.setStart_date(resultset.getString("start_date"));
					      e.setEnd_date(resultset.getString("end_date"));
					      e.setSubject(resultset.getString("subject"));
					      e.setReadonly(resultset.getString("readonly"));
					  	evs.add(e);
				
						}
						con.getConexion().close();
					      	} catch (SQLException e1) {
					                    	e1.printStackTrace();
					      	}
					      	DHXEventsManager.date_format = "MM/dd/yyyy HH:mm";
					
					      	return evs;
}
   	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
   		Connection conn = new Conexion().getConexion();
   		java.sql.PreparedStatement ps = null;
   		java.sql.ResultSet result = null;
   		String usuario = "prueba";
   		EventoMod e = (EventoMod) event;
   		
   		//Tendre que hacer 2 metodos para el admin y para el usuario
   		// Ya que el readonly esta en Int y me detectara q esta escrito y me lo reconoce como true
   		try {
   		String query = null;
   		String start_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(event.getStart_date());
   		String end_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(event.getEnd_date());
   		if (status == DHXStatus.UPDATE) {
   		query = "UPDATE events_mio SET event_name=?, start_date=?, end_date=?, subject=?, usuario=? WHERE event_id=?";
   		ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
   		ps.setString(1, event.getText());
   		ps.setString(2, start_date);
   		ps.setString(3, end_date);
   		ps.setString(4, e.getSubject());
   		
   		ps.setString(5, usu.getUsuario());
   		
   		ps.setInt(6, event.getId());

   		} else if (status == DHXStatus.INSERT) {
   		query = "INSERT INTO events_mio (event_id, event_name, start_date, end_date, subject, usuario) VALUES (null, ?, ?, ?, ?, ?)";
   		ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
   		ps.setString(1, event.getText());
   		ps.setString(2, start_date);
   		ps.setString(3, end_date);
   		ps.setString(4, e.getSubject());
   		ps.setString(5, usuario);
   		
   		} else if (status == DHXStatus.DELETE) {
   		query = "DELETE FROM events_mio WHERE event_id=? LIMIT 1";
   		ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
   		ps.setInt(1, event.getId());
   		}

   		if (ps!=null) {
   		ps.executeUpdate();
   		result = ps.getGeneratedKeys();
   		if (result.next()) {
   		event.setId(result.getInt(1));
   		}
   		}

   		} catch (SQLException e1) {

   		e1.printStackTrace();
   		} finally {
   		if (result != null) try { result.close(); } catch (SQLException e1) {}
   		if (ps != null) try { ps.close(); } catch (SQLException e1) {}
   		if (conn != null) try { conn.close(); } catch (SQLException e1) {}
   		}

   		return status;
   		}

  	@Override
   	public DHXEv createEvent(String id, DHXStatus status) {
                  	return new EventoMod();
   	}
  	 @Override
     public HashMap<String, Iterable<DHXCollection>> getCollections() {

     //    ArrayList<DHXCollection> users_list = new ArrayList<DHXCollection>();

      

         HashMap<String, Iterable<DHXCollection>> c = new HashMap<String, Iterable<DHXCollection>>();
 

         ArrayList<DHXCollection> subject = new ArrayList<DHXCollection>();
         subject.add(new DHXCollection("appoiment", "Appoiment"));
         subject.add(new DHXCollection("english", "English"));
         subject.add(new DHXCollection("math", "Math"));
         subject.add(new DHXCollection("science", "Science"));
         c.put("subject", subject);

         ArrayList<DHXCollection> urgency = new ArrayList<DHXCollection>();
         urgency.add(new DHXCollection("low", "Low"));
         urgency.add(new DHXCollection("medium", "Medium"));
         urgency.add(new DHXCollection("high", "High"));
         c.put("urgency", urgency);

         return c;
     }

}
