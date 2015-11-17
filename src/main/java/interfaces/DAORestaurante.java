package interfaces;

import java.util.ArrayList;

import modelo.Restaurante;

public interface DAORestaurante {
	public ArrayList<Restaurante> obtenerRest(String opcion, String parque) throws Exception;
	public Restaurante getRestaurante(Restaurante  res)throws Exception;
	public String[] obtenermenus(int tmenu);
	public Restaurante getCaracteristicasR (Restaurante res)throws Exception;
	
}
