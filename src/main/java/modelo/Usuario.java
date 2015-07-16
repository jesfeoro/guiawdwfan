package modelo;

public class Usuario {
	private String usuario;
	private String password;
	private int codCliente;
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Usuario(String usuario, String password, int codCliente) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.codCliente = codCliente;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}