package modelo;

import java.util.Map;

public class Atraccion {
	private String Nombre;
	private String ImagenP;
	private int CodigoZona;
	private String BDescripcion;
	private String Descripcion;
	private String ImagenG;
	private String Sonido;
	private Map<Integer,ImagenA>imagen;
	private Map<Integer,Atraccion>caracter;
	private String Caracteristica;
	private String TipoC;
	private String ValorC;
	private String NombreZ;
	private String NombreP;
	private String []Tipos;
	public Atraccion() {
		
	}
	public Atraccion(int codigoZona,String nombre, String imagenP) {
		super();
		CodigoZona = codigoZona;
		Nombre = nombre;
		ImagenP = imagenP;
	}
	
	public Atraccion(String caracteristica, String tipoC, String valorC) {
		super();
		Caracteristica = caracteristica;
		TipoC = tipoC;
		ValorC = valorC;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getImagenP() {
		return ImagenP;
	}
	public void setImagenP(String imagenP) {
		ImagenP = imagenP;
	}
	public int getCodigoZona() {
		return CodigoZona;
	}
	public void setCodigoZona(int codigoZona) {
		CodigoZona = codigoZona;
	}
	
	public Map<Integer, Atraccion> getCaracter() {
		return caracter;
	}
	public void setCaracter(Map<Integer, Atraccion> caracter) {
		this.caracter = caracter;
	}
	
	public String getCaracteristica() {
		return Caracteristica;
	}
	public void setCaracteristica(String caracteristica) {
		Caracteristica = caracteristica;
	}
	public String getTipoC() {
		return TipoC;
	}
	public void setTipoC(String tipoC) {
		TipoC = tipoC;
	}
	public String getValorC() {
		return ValorC;
	}
	public void setValorC(String valorC) {
		ValorC = valorC;
	}
	
	public String getNombreZ() {
		return NombreZ;
	}
	public void setNombreZ(String nombreZ) {
		NombreZ = nombreZ;
	}
	public String getNombreP() {
		return NombreP;
	}
	public void setNombreP(String nombreP) {
		NombreP = nombreP;
	}
	
	public String[] getTipos() {
		return Tipos;
	}
	public void setTipos(String[] tipos) {
		Tipos = tipos;
	}
	
	public String getBDescripcion() {
		return BDescripcion;
	}
	public void setBDescripcion(String bDescripcion) {
		BDescripcion = bDescripcion;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getImagenG() {
		return ImagenG;
	}
	public void setImagenG(String imagenG) {
		ImagenG = imagenG;
	}
	public String getSonido() {
		return Sonido;
	}
	public void setSonido(String sonido) {
		Sonido = sonido;
	}
	
	public Map<Integer, ImagenA> getImagen() {
		return imagen;
	}
	public void setImagen(Map<Integer, ImagenA> imagen) {
		this.imagen = imagen;
	}

}

