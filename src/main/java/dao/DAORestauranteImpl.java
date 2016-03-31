package dao;

import interfaces.DAORestaurante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import modelo.Restaurante;

public class DAORestauranteImpl extends Conexion implements DAORestaurante{

	@Override
	public ArrayList<Restaurante> obtenerRest(String opcion, String parque)
			throws Exception {
		ArrayList<Restaurante> milista = new  ArrayList<Restaurante>();
		
		try {
			this.getConexion();
			
			if(opcion.equals("todos")) {			
				PreparedStatement ps = this.conexion.prepareStatement("select restaurante.*, tipos_restaurante.TipoNombre from restaurante "+
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
				PreparedStatement ps = this.conexion.prepareStatement("select restaurante.*, tipos_restaurante.TipoNombre from restaurante "+
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
			this.cerrar();
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

	@Override
	public Restaurante getRestaurante(Restaurante res) throws Exception {
		Restaurante resta = new Restaurante();
		try {
			this.getConexion();
			PreparedStatement ps = this.conexion.prepareStatement("select CodigoRestaurante, NOMBRE, BDescripcion, ImagenG, Tmenus from restaurante where NOMBRE=? limit 1;");
			ps.setString(1, res.getNombre());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resta.setCodRestaurante(rs.getInt("CodigoRestaurante"));
				resta.setNombre(rs.getString("nombre"));
				resta.setBDescrip(rs.getString("BDescripcion"));
				resta.setImagenG(rs.getString("ImagenG"));				
				resta.setNmenu(rs.getInt("Tmenus"));
				//esto va fuera
				//resta.setTmenus(obtenermenus(tmenu));
			}
			//Esto va fuera
		//	this.getCaracteristicasR(resta);
			
		}catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resta;
	}

	@Override
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

	@Override
	public Restaurante getCaracteristicasR(Restaurante res) throws Exception {
		this.getConexion();
		String[] caracteristica;
//		Restaurante restau = new Restaurante();
		try {
			ResultSet rs =this.conexion.createStatement().executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'datosrestaurante' AND table_schema = 'gwdwfan' ORDER BY ORDINAL_POSITION;");
			
			PreparedStatement ps2 = this.conexion.prepareStatement("Select * from datosrestaurante " +
					"join restaurante on  restaurante.CodigoRestaurante=datosrestaurante.CodigoRestaurante " +
					"where NOMBRE =? limit 1;");	
			ps2.setString(1, res.getNombre());
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
				PreparedStatement ps = this.conexion.prepareStatement("select nombregrupocarest from grupocarest "+
						"join rest_grupo on rest_grupo.Codigogrupocarest=grupocarest.Codigogrupocarest "+
						"join restaurante on restaurante.CodigoRestaurante=rest_grupo.CodigoRestaurante " +
						"where restaurante.NOMBRE =?");
			ps.setString(1, res.getNombre());
			 rs= ps.executeQuery();
			caracteristica = new String[4];
			int t=0;
			while (rs.next()) {
				caracteristica[t++]=rs.getString(1);
				
			}
			res.setTipos(caracteristica);
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
			res.setCaracter(Micaracter);
							
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
		return res;
	}

}
