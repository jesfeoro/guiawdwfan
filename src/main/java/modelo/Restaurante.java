package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import controlador.Conexion;

public class Restaurante {
private String Nombre;
private String TipoRes;
private String ImagenP;
private String Descrip;
private String BDescrip;
private String ImagenG;
private String []Tipos;
private Map<Integer,Restaurante>caracter;
private String Caracteristica;
private String TipoC;
private String ValorC;
private int Nulo1;
private int Nulo2;
private String[] Tmenus;

public Restaurante() {
	
}
public Restaurante(String nombre, String tipoRes, String imagenP) {
	super();
	Nombre = nombre;
	TipoRes = tipoRes;
	ImagenP = imagenP;
}

public Restaurante(String nombre, String bDescrip,
		String imagenG, String[]tmenus) {
	super();
	Nombre = nombre;
	BDescrip = bDescrip;
	ImagenG = imagenG;
	Tmenus = tmenus;
}

public Restaurante(String caracteristica, String tipoC, String valorC,
		int nulo1, int nulo2) {
	super();
	Caracteristica = caracteristica;
	TipoC = tipoC;
	ValorC = valorC;
	Nulo1 = nulo1;
	Nulo2 = nulo2;
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

public String getDescrip() {
	return Descrip;
}
public void setDescrip(String descrip) {
	Descrip = descrip;
}
public String getBDescrip() {
	return BDescrip;
}
public void setBDescrip(String bDescrip) {
	BDescrip = bDescrip;
}
public String getImagenG() {
	return ImagenG;
}
public void setImagenG(String imagenG) {
	ImagenG = imagenG;
}

public String[] getTipos() {
	return Tipos;
}
public void setTipos(String[] tipos) {
	Tipos = tipos;
}

public Map<Integer, Restaurante> getCaracter() {
	return caracter;
}
public void setCaracter(Map<Integer, Restaurante> caracter) {
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

public int getNulo1() {
	return Nulo1;
}
public void setNulo1(int nulo1) {
	Nulo1 = nulo1;
}
public int getNulo2() {
	return Nulo2;
}
public void setNulo2(int nulo2) {
	Nulo2 = nulo2;
}

public String[] getTmenus() {
	return Tmenus;
}
public void setTmenus(String[] tmenus) {
	Tmenus = tmenus;
}
public ArrayList<Restaurante> obtenerRest(String opcion, String parque){
	ArrayList<Restaurante> milista = new  ArrayList<Restaurante>();
	
	try {
		Conexion con = new Conexion();
		
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
public Restaurante getRestaurante(String resta) {
	try {
		Conexion con = new Conexion();
		PreparedStatement ps = con.devuelvePS("select NOMBRE, BDescripcion, ImagenG, Tmenus from restaurante where NOMBRE=? limit 1;");
		ps.setString(1, resta);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			
			this.setNombre(rs.getString("nombre"));
			this.setBDescrip(rs.getString("BDescripcion"));
			this.setImagenG(rs.getString("ImagenG")); 
			int tmenu = rs.getInt("Tmenus");
			this.setTmenus(obtenermenus(tmenu));
		}
		this.getCaracteristicasR(resta);
		
	}catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this;
}
public String[] obtenermenus(int tmenu) {
	String [] menus;
	switch (tmenu) {
	case 1:
		menus=new String[] {"Desayuno","Comida"};
		return menus;
	case 2:
		menus=new String[] {"Desayuno","Comida","Cena"};
		return menus;
	case 3:
		menus=new String[] {"Comida","Cena"};
		return menus;
	case 4:
		menus=new String[] {"Desayuno","Salon"};
		return menus;
	case 5:
		menus=new String[] {"Cena"};
		return menus;
	case 6:
		menus=new String[] {"Snacks"};
		return menus;
	case 7:
		menus=new String[] {"Pool-Bar"};
		return menus;
	default:
		break;
	}
	
	return null;	
}
public Restaurante getCaracteristicasR (String resta) {
	Conexion con = new Conexion();
	String[] caracteristica;
	try {
		ResultSet rs =con.devuelveRS("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'datosrestaurante' AND table_schema = 'gwdwfan' ORDER BY ORDINAL_POSITION;");
		
		PreparedStatement ps2 = con.devuelvePS("Select * from datosrestaurante " +
				"join restaurante on  restaurante.CodigoRestaurante=datosrestaurante.CodigoRestaurante " +
				"where NOMBRE =? limit 1;");	
		ps2.setString(1, resta);
		ResultSet rs2= ps2.executeQuery();
		String[][] todo= new String[20][2];
		int i=0, e=1;
		String reemplaza;
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
	/*		for (int j = 2; j < todo.length; j++) {
		System.out.println(todo[j][0]+" --- "+todo[j][1]);
	}*/
			PreparedStatement ps = con.devuelvePS("select nombregrupocarest from grupocarest "+
					"join rest_grupo on rest_grupo.Codigogrupocarest=grupocarest.Codigogrupocarest "+
					"join restaurante on restaurante.CodigoRestaurante=rest_grupo.CodigoRestaurante " +
					"where restaurante.NOMBRE =?");
		ps.setString(1, resta);
		 rs= ps.executeQuery();
		caracteristica = new String[4];
		int t=0;
		while (rs.next()) {
			caracteristica[t++]=rs.getString(1);
			
		}
		this.setTipos(caracteristica);
	/*		for (int j = 0; j < caracteristica.length; j++) {
		System.out.println("tipo caracteristica -->"+caracteristica[j]);
	}*/
	// Ahora pondremos todas las caracteristicas organizadas con sus tipos
				Map<Integer,Restaurante>Micaracter = new LinkedHashMap<Integer, Restaurante>();
				int n=0, a=0;
				//System.out.println("Esta aqui?");
				//System.out.println(caracteristica[a]+" -- "+ todo[2][0]+" -- "+todo[2][1]);
				for (int j = 2; j < todo.length; j++) {
					if(a==0) {
						for (int i0 = 2; i0 < 10; i0++) {//Datos principales
							Micaracter.put(n++,new Restaurante(caracteristica[a], todo[i0][0],todo[i0][1],0,0));
						}
							}else if(a==1) {
						for (int i0 = 10; i0 < 14; i0++) {//Comedor
							Micaracter.put(n++,new Restaurante(caracteristica[a], todo[i0][0],todo[i0][1],0,0));
						}
					}else if(a==2) {
						for (int i0 = 14; i0 < 17; i0++) {//Horarios de Comida
							Micaracter.put(n++,new Restaurante(caracteristica[a], todo[i0][0],todo[i0][1],0,0));
						}
					}else if(a==3) {
						for (int i0 = 17; i0 < 20; i0++) {//Plan de Comidas Disney
							Micaracter.put(n++,new Restaurante(caracteristica[a], todo[i0][0],todo[i0][1],0,0));
						}
					}
					a++;
					
				}
		this.setCaracter(Micaracter);
						
			/*				for (int j = 0; j < caracteristica.length; j++) {
								System.out.println(caracteristica[j].toUpperCase());
								Iterator it = Micaracter.keySet().iterator();
								while(it.hasNext()){
										  Integer key = (Integer) it.next();
										  Restaurante m= Micaracter.get(key);
										  if(caracteristica[j].equals(m.getCaracteristica())) {
										  System.out.println(m.getTipoC()+" -- "+m.getValorC());
										  }
								}
								System.out.println();
							}*/
									
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return this;
	/* 	 <%if (m.getTipoC().equals("Precio")){ 
        int cant = Integer.parseInt(m.getValorC());
        for(int i=1;i<=cant;i++){%>	                       
     	<span class="glyphicon glyphicon-usd"></span>   		
       <%}%></td>
      <% }else */
}
}
