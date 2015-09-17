package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.Conexion;

public class Restaurante {
private String Nombre;
private String TipoRes;
private String ImagenP;

public Restaurante() {
	
}
public Restaurante(String nombre, String tipoRes, String imagenP) {
	super();
	Nombre = nombre;
	TipoRes = tipoRes;
	ImagenP = imagenP;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getTipoRes() {
	return TipoRes;
}
public void setTipoRes(String tipoRes) {
	TipoRes = tipoRes;
}
public String getImagenP() {
	return ImagenP;
}
public void setImagenP(String imagenP) {
	ImagenP = imagenP;
}
public ArrayList<Restaurante> obtenerRest(String opcion, String parque){
	ArrayList<Restaurante> milista = new  ArrayList<Restaurante>();
	
	try {
		Conexion con = new Conexion();
		System.out.println("La opcion dentro de restaurante ->"+opcion);
		if(opcion.equals("todos")) {			
			PreparedStatement ps = con.devuelvePS("select restaurante.*, tipos_restaurante.TipoNombre from restaurante "+
							"join tipos_restaurante on tipos_restaurante.CodigoTipoRestaurante=restaurante.CodTiporest "+
							"join parques on parques.CodigoParque=tipos_restaurante.codigoParque "+
							"where parques.NOMBRE=?;");		
			ps.setString(1, parque);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Restaurante res= new Restaurante(rs.getString("nombre"), rs.getString("TipoNombre"), rs.getString("ImagenP"));
				milista.add(res);
			}
		}else {
			opcion=opcion.replace("_", " ");
			PreparedStatement ps = con.devuelvePS("select restaurante.*, tipos_restaurante.TipoNombre from restaurante "+
					"join tipos_restaurante on tipos_restaurante.CodigoTipoRestaurante=restaurante.CodTiporest "+
					"join parques on parques.CodigoParque=tipos_restaurante.codigoParque "+
					"where parques.NOMBRE=? and tipos_restaurante.TipoNombre=?;");		
			ps.setString(1, parque);
			ps.setString(2, opcion);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				Restaurante res= new Restaurante(rs.getString("nombre"), rs.getString("TipoNombre"), rs.getString("ImagenP"));
				milista.add(res);
			}
						
		}
		con.getConexion().close();
		return milista;
		
	}catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return milista;
}
}
