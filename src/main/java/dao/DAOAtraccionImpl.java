package dao;

import interfaces.DAOAtraccion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import modelo.Atraccion;
import modelo.ImagenA;

public class DAOAtraccionImpl extends Conexion implements DAOAtraccion {

	@Override
	public Atraccion obtenerAtraccion(Atraccion at) throws Exception {
		// TODO Auto-generated method stub
		Atraccion atrac = new Atraccion();
		this.getConexion();
		try {
			PreparedStatement ps = this.conexion
					.prepareStatement("Select * from atraccion where Nombre=? limit 1");
			ps.setString(1, at.getNombre());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				atrac.setNombre(at.getNombre());
				atrac.setBDescripcion(rs.getString("BDescripcion"));
				atrac.setDescripcion(rs.getString("Descripcion"));
				atrac.setSonido(rs.getString("Sonido"));
				atrac.setImagenG(rs.getString("ImagenG"));

			} else {
				atrac.setNombre("");
				return atrac;
			}
			ps = this.conexion
					.prepareStatement("select * from imagenes "
							+ "join atraccion on imagenes.CodigoAtraccion=atraccion.CodigoAtraccion "
							+ "where atraccion.Nombre=?");
			ps.setString(1, at.getNombre());
			rs = ps.executeQuery();
			int i = 1;
			Map<Integer, ImagenA> Mimagen = new LinkedHashMap<Integer, ImagenA>();
			while (rs.next()) {
				Mimagen.put(i++, new ImagenA(rs.getString(3), rs.getString(4),
						rs.getString(5)));
			}
			atrac.setImagen(Mimagen);
			
			//obtenerCaracteristicas(at);

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error ->" + ex.getMessage());
		}
		return atrac;
	}

	@Override
	public Atraccion obtenerCaracteristicas(Atraccion at) throws Exception {
		// TODO Auto-generated method stub
		this.getConexion();
		String[] caracteristica;
		//Atraccion atra = new Atraccion();
		try {
			// La sentencia sql de abajo me selecciona el nombre de los campos
			// de una tabla.
			ResultSet rs = this.conexion
					.createStatement()
					.executeQuery(
							"SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'tiposcarac' AND table_schema = 'gwdwfan' ORDER BY ORDINAL_POSITION;");

			PreparedStatement ps2 = this.conexion
					.prepareStatement("Select atrac.* from tiposcarac as atrac "
							+ "join atraccion on  atrac.CodigoAtraccion=atraccion.CodigoAtraccion "
							+ "where nombre=? limit 1");
			ps2.setString(1, at.getNombre());
			ResultSet rs2 = ps2.executeQuery();
			String[][] todo = new String[19][2];
			int i = 0, e = 1;
			String reemplaza;
			// ponemos en una matriz el resultado de los dos resulset para tener
			// tipo y el valor
			if (rs.next() && rs2.next()) {
				// recogemos el primer registro
				reemplaza = rs.getString(1);
				reemplaza = reemplaza.replace("_", " ");
				todo[i][0] = reemplaza;
				todo[i][1] = rs2.getString(e);
				e++;
				i++;
				while (rs.next()) {
					// recogemos los siguientes moviendo el primero con el while
					// y el segundo incrementando el
					// contador( uno son filas y el otro columnas)
					reemplaza = rs.getString(1);
					reemplaza = reemplaza.replace("_", " ");
					todo[i][0] = reemplaza;
					todo[i][1] = rs2.getString(e);
					e++;
					i++;
				}
			}
			/*
			 * for (int j = 2; j < todo.length; j++) {
			 * System.out.println(todo[j][0]+" --- "+todo[j][1]); }
			 */
			// recogemos los diferentes tipos de caracteristicas en otro array
			// para poder fusionarlos con
			// el array bidimensional que acabo de crear
			PreparedStatement ps = this.conexion
					.prepareStatement("select NombreGCara from grupocarac "
							+ "join atrac_grupo on atrac_grupo.CodigoGrupoCarac=grupocarac.CodigoGrupoCarac "
							+ "join atraccion on atraccion.CodigoAtraccion=atrac_grupo.CodigoAtraccion "
							+ "where atraccion.Nombre =?");
			ps.setString(1, at.getNombre());
			rs = ps.executeQuery();
			caracteristica = new String[5];
			int t = 0;
			while (rs.next()) {
				caracteristica[t++] = rs.getString(1);

			}
			at.setTipos(caracteristica);

			/*
			 * for (int j = 0; j < caracteristica.length; j++) {
			 * System.out.println("tipo caracteristica -->"+caracteristica[j]);
			 * }
			 */

			// Obtentremos los datos que nos faltan
			ps = this.conexion
					.prepareStatement("select  parques.nombre as ParqueN, zonas.Nombre as ZonaN from zonas, parques,atraccion "
							+ "where atraccion.NOMBRE=? and "
							+ "atraccion.CodigoZona=zonas.CodigoZona and "
							+ "parques.CodigoParque=zonas.CodigoParque limit 1;");
			ps.setString(1, at.getNombre());
			rs = ps.executeQuery();
			while (rs.next()) {
				at.setNombreZ(rs.getString("ZonaN"));
				at.setNombreP(rs.getString("ParqueN"));
				// System.out.println(rs.getString("ZonaN")+" -- "+rs.getString("ParqueN"));
			}
			// Ahora pondremos todas las caracteristicas organizadas con sus
			// tipos
			Map<Integer, Atraccion> Micaracter = new LinkedHashMap<Integer, Atraccion>();
			int n = 0, a = 0;
			Micaracter.put(n++,
					new Atraccion(caracteristica[0], "Nombre", at.getNombre()));
			Micaracter
					.put(n++,
							new Atraccion(caracteristica[0], "Lugar", at
									.getNombreZ()));
			Micaracter.put(
					n++,
					new Atraccion(caracteristica[0], "Parque", at
							.getNombreP()));
			for (int j = 2; j < todo.length; j++) {
				if (a == 0) {
					for (int i0 = 2; i0 < 4; i0++) {// Datos principales
						Micaracter.put(n++, new Atraccion(caracteristica[a],
								todo[i0][0], todo[i0][1]));
					}
				} else if (a == 1) {
					for (int i0 = 4; i0 < 10; i0++) {// Restricciones
						Micaracter.put(n++, new Atraccion(caracteristica[a],
								todo[i0][0], todo[i0][1]));
					}
				} else if (a == 2) {
					for (int i0 = 10; i0 < 13; i0++) {// Datos tecnicos
						Micaracter.put(n++, new Atraccion(caracteristica[a],
								todo[i0][0], todo[i0][1]));
					}
				} else if (a == 3) {
					for (int i0 = 13; i0 < 14; i0++) {// Fotografia
						Micaracter.put(n++, new Atraccion(caracteristica[a],
								todo[i0][0], todo[i0][1]));
					}
				} else if (a == 4) {
					for (int i0 = 14; i0 < 18; i0++) {// Miscelanea
						Micaracter.put(n++, new Atraccion(caracteristica[a],
								todo[i0][0], todo[i0][1]));
					}
				} else if (a == 5) {// Otro
					Micaracter.put(n++, new Atraccion("Otro", todo[18][0],
							todo[18][1]));
				}
				a++;

			}
			at.setCaracter(Micaracter);

			/*
			 * for (int j = 0; j < caracteristica.length; j++) {
			 * System.out.println(caracteristica[j].toUpperCase()); Iterator it
			 * = Micaracter.keySet().iterator(); while(it.hasNext()){ Integer
			 * key = (Integer) it.next(); Atraccion m= Micaracter.get(key);
			 * if(caracteristica[j].equals(m.getCaracteristica())) {
			 * System.out.println(m.getTipoC()+" -- "+m.getValorC()); } }
			 * System.out.println(); } Atraccion m= Micaracter.get(18);
			 * System.out.println(m.getTipoC()+" -- "+m.getValorC());
			 */

			return at;
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error ->" + ex.getMessage());
		} finally {
			this.cerrar();
		}
		return at;
	}
}
