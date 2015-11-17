package interfaces;

import java.util.ArrayList;

import modelo.Busqueda;

public interface DAOBusqueda {
	//public ArrayList<Busqueda> getNombres(String nombre)throws Exception;
	public ArrayList<Busqueda> getNombres(Busqueda b)throws Exception;
	//public int getCuantos(String nombre) throws Exception;
	public int getCuantos(Busqueda b) throws Exception;
	//public Busqueda getUnNombre(String nombre)throws Exception;
	public Busqueda getUnNombre(Busqueda b) throws Exception;
	//public Busqueda obtenerCampos(String nombre) throws Exception;
	public Busqueda obtenerCampos(Busqueda b) throws Exception;
	
}
