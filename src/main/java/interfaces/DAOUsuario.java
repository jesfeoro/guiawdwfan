package interfaces;

import modelo.Usuario;

public interface DAOUsuario {
	public Usuario busquedaUser(Usuario usu) throws Exception;
	public Boolean usuCorrecto(Usuario usu) throws Exception;
	public Boolean emaCorrecto(Usuario usu, String busqueda) throws Exception;
	public Boolean NuevoUsu(Usuario usu) throws Exception;
	public int ObtenerIDUsuario(Usuario usu) throws Exception;
	public void InsertarLostPass(Usuario usu)throws Exception;
	public boolean BusquedaClave(Usuario usu) throws Exception;
	public void CambiarPass(Usuario usu) throws Exception;
	public boolean buscaemalost(Usuario usu) throws Exception;
	public boolean buscaematoken(Usuario usu) throws Exception;
	public void enviomail(Usuario usu, String URL) throws Exception;
	
	
}
