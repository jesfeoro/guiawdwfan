package modelo;

import java.util.Map;
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


}
