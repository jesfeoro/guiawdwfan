package modelo;

public class ImagenA {
	private String ImagenP;
	private String ImagenG;
	private String Descripcion;
	public ImagenA(String imagenP, String imagenG, String descripcion) {
		super();
		ImagenP = imagenP;
		ImagenG = imagenG;
		Descripcion = descripcion;
	}
	public String getImagenP() {
		return ImagenP;
	}
	public void setImagenP(String imagenP) {
		ImagenP = imagenP;
	}
	public String getImagenG() {
		return ImagenG;
	}
	public void setImagenG(String imagenG) {
		ImagenG = imagenG;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
}
