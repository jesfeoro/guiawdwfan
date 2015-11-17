package dao;

import interfaces.DAOParque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import modelo.Atraccion;
import modelo.Parque;

public class DAOParqueImpl extends Conexion implements DAOParque {

	@Override
	public Parque obtenerParque(Parque pa) throws Exception {
		// TODO Auto-generated method stub
		String parque = pa.getNombre();
		System.out.println("el nombre es "+parque);
		Parque p = new Parque();
		try {
			this.getConexion();
			PreparedStatement ps = this.conexion
					.prepareStatement("Select * from parques where NOMBRE=?");
			ps.setString(1, parque);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p.setNombre(rs.getString("NOMBRE").toUpperCase());
				p.setDescripcion(rs.getString("Descripcion"));
				p.setImagen(rs.getString("Imagen"));
			}
			ps = this.conexion
					.prepareStatement("select CodigoZona, zonas.Nombre from zonas "
							+ "join parques on parques.CodigoParque=zonas.CodigoParque "
							+ "where parques.NOMBRE=?;");
			ps.setString(1, parque);
			rs = ps.executeQuery();
			Map<Integer, String> map = new HashMap<Integer, String>();
			while (rs.next()) {
				map.put(rs.getInt("CodigoZona"), rs.getString("zonas.Nombre"));
			}
			p.setZonas(map);

			ps = this.conexion
					.prepareStatement("select  atraccion.Nombre, atraccion.codigoZona, atraccion.ImagenP from atraccion "
							+ "join zonas on zonas.CodigoZona=atraccion.CodigoZona "
							+ "join parques on parques.CodigoParque=zonas.CodigoParque "
							+ "where parques.NOMBRE=?;");
			ps.setString(1, parque);
			rs = ps.executeQuery();
			Map<Integer, Atraccion> map2 = new HashMap<Integer, Atraccion>();
			int i = 0;
			while (rs.next()) {
				map2.put(
						i,
						new Atraccion(rs.getInt("atraccion.codigoZona"), rs
								.getString("atraccion.Nombre"), rs
								.getString("atraccion.ImagenP")));
				i++;
			}

			p.setAtraccion(map2);
			return p;

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}

}
