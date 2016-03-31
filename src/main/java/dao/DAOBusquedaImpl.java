package dao;

import interfaces.DAOBusqueda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import modelo.Busqueda;

public class DAOBusquedaImpl extends Conexion implements DAOBusqueda {

	@Override
	public ArrayList<Busqueda> getNombres(Busqueda b) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Busqueda> list = new ArrayList<Busqueda>();
		String nombre = b.getNombre();
		try {
			this.getConexion();
			PreparedStatement ps1 = this.conexion
					.prepareStatement("SELECT * FROM busqueda  WHERE  nombre LIKE ?");
			String nombreB = (nombre + "%");
			ps1.setString(1, nombreB);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				Busqueda bu = new Busqueda(rs.getString(2), rs.getString(3),
						rs.getString(4));
				list.add(bu);
			}
			rs.close();
			ps1.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			this.cerrar();
		}
		return list;

	}

	@Override
	public int getCuantos(Busqueda b) throws Exception {
		// TODO Auto-generated method stub
		String nombre = b.getNombre();
		int count = 0;
		try {
			this.getConexion();
			PreparedStatement ps = this.conexion
					.prepareStatement("SELECT count(*) FROM busqueda  WHERE  nombre like ?");
			String nombreB = (nombre + "%");
			ps.setString(1, nombreB);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			System.out.println(count + " elementos encontrados");
			// return count;
			/*
			 * PreparedStatement ps1 =
			 * conn.devuelvePS("SELECT tipo FROM busqueda  WHERE  nombre= ?");
			 * ps1.setString(1, nombre); ResultSet rs1 = ps1.executeQuery(); if
			 * (rs1.next()) { tipo = rs1.getString(1); }
			 */
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return count;
	}

	@Override
	public Busqueda getUnNombre(Busqueda b) throws Exception {
		// TODO Auto-generated method stub
		Busqueda bu = new Busqueda();
		String nombre = b.getNombre();
		try {
			this.getConexion();

			PreparedStatement ps1 = this.conexion
					.prepareStatement("select * from busqueda where nombre like  ?");
			String nombreB = (nombre + "%");
			ps1.setString(1, nombreB);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				bu.setNombre(rs1.getString("Nombre"));
				bu.setTipos(rs1.getString("Tipo"));
			}
			ps1.close();
			rs1.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return bu;
	}

	@Override
	public Busqueda obtenerCampos(Busqueda b) throws Exception {
		// TODO Auto-generated method stub
		Busqueda bu = new Busqueda();
		String nombre = b.getNombre();
		try {
			this.getConexion();
			PreparedStatement ps1 = this.conexion
					.prepareStatement("select * from busqueda where nombre like  ?");
			String nombreB = (nombre + "%");
			ps1.setString(1, nombreB);
			ResultSet rs1 = ps1.executeQuery();
			Map<Integer, Busqueda> Mipagina = new LinkedHashMap<Integer, Busqueda>();
			int n = 0;

			while (rs1.next()) {
				String direccion = "";
				if (rs1.getString("Tipo").equals("atraccion")) {
					direccion = "Atrac?atraccion=";
				} else if (rs1.getString("Tipo").equals("parques")) {
					direccion = "Parques?parque=";
				} else if (rs1.getString("Tipo").equals("restaurante")) {
					direccion = "Resta?restaurante=";
				}
				Mipagina.put(
						n++,
						new Busqueda(rs1.getString("Nombre"), rs1
								.getString("Tipo"), direccion));

			}
			bu.setPagina(Mipagina);
			ps1.close();
			rs1.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			this.cerrar();
		}
		return bu;
	}

}
