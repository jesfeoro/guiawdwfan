package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import controlador.Conexion;

public class Busqueda {
	private String nombre;
	private String tipos;
	private String direccion;
	private Map<Integer,Busqueda>pagina;
	public Busqueda() {
		super();
	}
	public Busqueda(String nombre, String tipos) {
		super();
		this.nombre = nombre;
		this.tipos = tipos;
	}
	
	public Busqueda(String nombre, String tipos, String direccion) {
		super();
		this.nombre = nombre;
		this.tipos = tipos;
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipos() {
		return tipos;
	}
	public void setTipos(String tipos) {
		this.tipos = tipos;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Map<Integer, Busqueda> getPagina() {
		return pagina;
	}
	public void setPagina(Map<Integer, Busqueda> pagina) {
		this.pagina = pagina;
	}
	public ArrayList<String> getNombres(String nombre)throws Exception{
        ArrayList<String> list = new ArrayList<String>();
        String data;
        try{   
        		  Conexion conn = new Conexion();
                  PreparedStatement ps1 = conn.devuelvePS("SELECT * FROM busqueda  WHERE  nombre LIKE ?");
                  String nombreB = (nombre + "%");
                  ps1.setString(1, nombreB);
                  ResultSet rs = ps1.executeQuery();

                       while (rs.next()) {
                                data = rs.getString("NOMBRE");                              
                                list.add(data);
                       }
               } catch (SQLException e) {
                  System.out.println(e.getMessage()); 
               }
        return list;
        }
	public int getCuantos(String nombre) {
		int count=0;
		try{   
  		  Conexion conn = new Conexion();
  		PreparedStatement ps = conn.devuelvePS("SELECT count(*) FROM busqueda  WHERE  nombre like ?");
  		String nombreB = (nombre + "%");
        ps.setString(1, nombreB);
        ResultSet rs = ps.executeQuery();
        rs.next();
        count = rs.getInt(1) ;
        System.out.println(count+" elementos encontrados");
        return count;
        /*    PreparedStatement ps1 = conn.devuelvePS("SELECT tipo FROM busqueda  WHERE  nombre= ?");
            ps1.setString(1, nombre);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
            	tipo = rs1.getString(1);
            }*/

         } catch (SQLException e) {
            System.out.println(e.getMessage()); 
         }
		return count;
	}
	public Busqueda getUnNombre(String nombre) {
		try{   
	  		  Conexion conn = new Conexion();
	  		
	            PreparedStatement ps1 = conn.devuelvePS("select * from busqueda where nombre like  ?");
	            String nombreB = (nombre + "%");
	            ps1.setString(1, nombreB);
	            ResultSet rs1 = ps1.executeQuery();
	            if (rs1.next()) {
	            	this.setNombre( rs1.getString("Nombre"));
	            	this.setTipos(rs1.getString("Tipo"));
	            }

	         } catch (SQLException e) {
	            System.out.println(e.getMessage()); 
	         }
		return this;
	}
	public Busqueda obtenerCampos(String nombre) {
		try{   
	  		  Conexion conn = new Conexion();
	  		
	            PreparedStatement ps1 = conn.devuelvePS("select * from busqueda where nombre like  ?");
	            String nombreB = (nombre + "%");
	            ps1.setString(1, nombreB);
	            ResultSet rs1 = ps1.executeQuery();
	            Map<Integer,Busqueda>Mipagina = new LinkedHashMap<Integer, Busqueda>();
				int n=0, a=0;
	            
	            while (rs1.next()) {
	            	String direccion="";
	            	if(rs1.getString("Tipo").equals("atraccion")){
	            		direccion="Atrac?atraccion=";
	            	}else if(rs1.getString("Tipo").equals("parques")){
	            		direccion="Parques?parque=";
	            	}
	            	Mipagina.put(n++,new Busqueda(rs1.getString("Nombre"), rs1.getString("Tipo"),direccion));
	            	
	            }
	            this.pagina=Mipagina;
	            return this;

	         } catch (SQLException e) {
	            System.out.println(e.getMessage()); 
	         }
		return this;
	}
}
