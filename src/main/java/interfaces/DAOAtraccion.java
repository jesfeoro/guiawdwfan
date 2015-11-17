package interfaces;

import modelo.Atraccion;

public interface DAOAtraccion {
	public Atraccion obtenerAtraccion(Atraccion at) throws Exception;
	public Atraccion obtenerCaracteristicas(Atraccion at) throws Exception;
	
}
