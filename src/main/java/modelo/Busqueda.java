package modelo;

import java.util.Map;

public class Busqueda {
	private String nombre;
	private String tipos;
	private String direccion;
	private Map<Integer,Busqueda>pagina;
	private String imagen;
	
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
