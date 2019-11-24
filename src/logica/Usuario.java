package logica;

public class Usuario {

	private String nombreUsuario;
	private byte []  password;
	private String correoElectronico;
	
	
	public Usuario () {
		this.nombreUsuario = null;
		//this.password = 0;
		this.correoElectronico = null;
	}
	
	public Usuario(String nombreUsuario, byte [] password, String correoElectronico) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.correoElectronico = correoElectronico;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public byte [] getPassword() {
		return password;
	}
	
	public void setPassword(byte [] password) {
		this.password = password;
	}
}
