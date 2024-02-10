package appCitas.AppCitasSASv2.servicios.Interfaces;


public interface IntfEmailRecuperacion {

	
	public void enviarEmailRecuperacion(String emailDestino, String nombreUsuario, String token);
	
	
	void enviarEmailConfirmacion(String emailDestino, String nombrePaciente, String token);
}
