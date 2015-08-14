package modelo;

public class Atraccion {
	private String Nombre;
	private String ImagenP;
	private int CodigoZona;
	public Atraccion() {
		
	}
	public Atraccion(int codigoZona,String nombre, String imagenP) {
		super();
		CodigoZona = codigoZona;
		Nombre = nombre;
		ImagenP = imagenP;
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

}
