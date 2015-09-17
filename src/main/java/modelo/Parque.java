package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.Conexion;
public class Parque {
	private int codigoParque;
	private String nombre;
	private String descripcion;
	private String imagen;
	private Map<Integer,String>zonas;

	private Map<Integer,Atraccion>atraccion;	
	
	public Parque() {
		
	}
	public int getCodigoParque() {
		return codigoParque;
	}
	public void setCodigoParque(int codigoParque) {
		this.codigoParque = codigoParque;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Map<Integer, String> getZonas() {
		return zonas;
	}
	public void setZonas(Map<Integer, String> zonas) {
		this.zonas = zonas;
	}	
	public Map<Integer, Atraccion> getAtraccion() {
		return atraccion;
	}
	public void setAtraccion(Map<Integer, Atraccion> atraccion) {
		this.atraccion = atraccion;
	}
	public Parque obtenerParque(String parque) {
		
		Conexion con = new Conexion();
		try {
					
			PreparedStatement ps = con.devuelvePS("Select * from parques where NOMBRE=?");		
			ps.setString(1, parque);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				this.setNombre(rs.getString("NOMBRE").toUpperCase());
				this.setDescripcion(rs.getString("Descripcion"));
				this.setImagen(rs.getString("Imagen"));
			}
			 ps = con.devuelvePS("select CodigoZona, zonas.Nombre from zonas "
					 				+"join parques on parques.CodigoParque=zonas.CodigoParque "
					 				+"where parques.NOMBRE=?;");
			 ps.setString(1, parque);
			 rs= ps.executeQuery();
			  Map<Integer, String> map = new HashMap<Integer, String>();
			 while(rs.next()) {
				map.put(rs.getInt("CodigoZona"), rs.getString("zonas.Nombre"));								 
			 }
			 this.setZonas(map);
			  
			 ps = con.devuelvePS("select  atraccion.Nombre, atraccion.codigoZona, atraccion.ImagenP from atraccion "
									+"join zonas on zonas.CodigoZona=atraccion.CodigoZona "
									+"join parques on parques.CodigoParque=zonas.CodigoParque "
									+"where parques.NOMBRE=?;");
			 ps.setString(1, parque);
			 rs= ps.executeQuery();
			 Map<Integer, Atraccion> map2 = new HashMap<Integer, Atraccion>();
			 int i =0;
			 while(rs.next()) {
		 	 	map2.put(i,new Atraccion(rs.getInt("atraccion.codigoZona"), rs.getString("atraccion.Nombre"),
		 	 											rs.getString("atraccion.ImagenP")));
		 	 	i++;
			 }
			 con.getConexion().close();
			 this.setAtraccion(map2);
	 
			 return this;
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return this;
		
	}

}
