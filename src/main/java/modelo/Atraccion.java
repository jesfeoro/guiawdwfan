package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import controlador.Conexion;

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
	public Atraccion obtenerAtraccion(String atrac) {
		Conexion con = new Conexion();
		try {
			PreparedStatement ps = con.devuelvePS("Select * from atraccion where Nombre=? limit 1");
			ps.setString(1, atrac);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.setNombre(atrac);
				this.setBDescripcion(rs.getString("BDescripcion"));
				this.setDescripcion(rs.getString("Descripcion"));
				this.setSonido(rs.getString("Sonido"));
				this.setImagenG(rs.getString("ImagenG"));
				
			}else {
				this.setNombre("");
				return this;
			}
			 ps = con.devuelvePS("select * from imagenes "+
					 			"join atraccion on imagenes.CodigoAtraccion=atraccion.CodigoAtraccion "+
					 			"where atraccion.Nombre=?");
			ps.setString(1, atrac);
			rs=ps.executeQuery();
			int i=1;
			 Map<Integer,ImagenA>Mimagen= new LinkedHashMap<Integer, ImagenA>();
			while(rs.next()) {
				Mimagen.put(i++,new ImagenA(rs.getString(3), rs.getString(4),rs.getString(5)));
			}
			this.setImagen(Mimagen);			
			this.obtenerCaracteristicas(atrac);
		}catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error ->" + ex.getMessage());
		}finally {
			try {
				con.getConexion().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		return this;
	}
	public Atraccion obtenerCaracteristicas(String atrac) {
		Conexion con = new Conexion();
		String[] caracteristica;
		//String atrac="Star Tours: The Adventures Continue";
		try {
			// La sentencia sql de abajo me selecciona el nombre de los campos de una tabla.
			ResultSet rs =con.devuelveRS("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'tiposcarac' AND table_schema = 'gwdwfan' ORDER BY ORDINAL_POSITION;"); 

			PreparedStatement ps2 = con.devuelvePS("Select atrac.* from tiposcarac as atrac " +
										"join atraccion on  atrac.CodigoAtraccion=atraccion.CodigoAtraccion " +
										"where nombre=? limit 1");	
			ps2.setString(1, atrac);
			ResultSet rs2= ps2.executeQuery();
			String[][] todo= new String[19][2];
			int i=0, e=1;
			String reemplaza;
			// ponemos en una matriz el resultado de los dos resulset para tener tipo y el valor 
			if (rs.next()&& rs2.next()) {
				//recogemos el primer registro 
				reemplaza =rs.getString(1);
				reemplaza = reemplaza.replace("_", " ");
				todo[i][0]= reemplaza;
				todo[i][1]= rs2.getString(e);e++;i++;
				while(rs.next()) {
					//recogemos los siguientes moviendo el primero con el while y el segundo incrementando el 
					//contador( uno son filas y el otro columnas)
					reemplaza =rs.getString(1);
					reemplaza = reemplaza.replace("_", " ");
					todo[i][0]= reemplaza;
					todo[i][1]= rs2.getString(e);e++;i++;
				}
			}
		/*	for (int j = 2; j < todo.length; j++) {
				System.out.println(todo[j][0]+" --- "+todo[j][1]);
			}*/
			//recogemos los diferentes tipos de caracteristicas en otro array para poder fusionarlos con 
			//el array bidimensional que acabo de crear
			PreparedStatement ps = con.devuelvePS("select NombreGCara from grupocarac "+
						"join atrac_grupo on atrac_grupo.CodigoGrupoCarac=grupocarac.CodigoGrupoCarac "+
						"join atraccion on atraccion.CodigoAtraccion=atrac_grupo.CodigoAtraccion " +
						"where atraccion.Nombre =?");
			ps.setString(1, atrac);
			 rs= ps.executeQuery();
			caracteristica = new String[5];
			int t=0;
			while (rs.next()) {
				caracteristica[t++]=rs.getString(1);
				
			}
			this.setTipos(caracteristica);
			
		/*	for (int j = 0; j < caracteristica.length; j++) {
				System.out.println("tipo caracteristica -->"+caracteristica[j]);
			}*/

			// Obtentremos los datos que nos faltan
			ps = con.devuelvePS("select  parques.nombre as ParqueN, zonas.Nombre as ZonaN from zonas, parques,atraccion "+
					"where atraccion.NOMBRE=? and "+
					"atraccion.CodigoZona=zonas.CodigoZona and "+
					"parques.CodigoParque=zonas.CodigoParque limit 1;");
			ps.setString(1, atrac);
			rs= ps.executeQuery();
			while (rs.next()) {
				this.setNombreZ(rs.getString("ZonaN"));
				this.setNombreP(rs.getString("ParqueN"));
			//	System.out.println(rs.getString("ZonaN")+" -- "+rs.getString("ParqueN"));
			}
			// Ahora pondremos todas las caracteristicas organizadas con sus tipos
			Map<Integer,Atraccion>Micaracter = new LinkedHashMap<Integer, Atraccion>();
			int n=0, a=0;
			Micaracter.put(n++,new Atraccion(caracteristica[0], "Nombre",atrac));
			Micaracter.put(n++,new Atraccion(caracteristica[0], "Lugar",this.getNombreZ()));
			Micaracter.put(n++,new Atraccion(caracteristica[0], "Parque",this.getNombreP()));
			for (int j = 2; j < todo.length; j++) {
				if(a==0) {
					for (int i0 = 2; i0 < 4; i0++) {//Datos principales
						Micaracter.put(n++,new Atraccion(caracteristica[a], todo[i0][0],todo[i0][1]));
					}
				}else if(a==1) {
					for (int i0 = 4; i0 < 10; i0++) {//Restricciones
						Micaracter.put(n++,new Atraccion(caracteristica[a], todo[i0][0],todo[i0][1]));
					}
				}else if(a==2) {
					for (int i0 = 10; i0 < 13; i0++) {//Datos tecnicos
						Micaracter.put(n++,new Atraccion(caracteristica[a], todo[i0][0],todo[i0][1]));
					}
				}else if(a==3) {
					for (int i0 = 13; i0 < 14; i0++) {//Fotografia
						Micaracter.put(n++,new Atraccion(caracteristica[a], todo[i0][0],todo[i0][1]));
					}
				}else if(a==4) {
					for (int i0 = 14; i0 < 18; i0++) {//Miscelanea
						Micaracter.put(n++,new Atraccion(caracteristica[a], todo[i0][0],todo[i0][1]));
					}
				}else if(a==5) {// Otro 
						Micaracter.put(n++,new Atraccion("Otro", todo[18][0],todo[18][1]));					
				}
				a++;
				
			}
			this.setCaracter(Micaracter);
			
		/*	for (int j = 0; j < caracteristica.length; j++) {
				System.out.println(caracteristica[j].toUpperCase());
				Iterator it = Micaracter.keySet().iterator();
				while(it.hasNext()){
						  Integer key = (Integer) it.next();
						  Atraccion m= Micaracter.get(key);
						  if(caracteristica[j].equals(m.getCaracteristica())) {
						  System.out.println(m.getTipoC()+" -- "+m.getValorC());
						  }
				}
				System.out.println();
			}
			Atraccion m= Micaracter.get(18);
			System.out.println(m.getTipoC()+" -- "+m.getValorC());*/
			
			return this;
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error ->" + ex.getMessage());
		}finally {
			try {
				con.getConexion().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return this;
	}
}

