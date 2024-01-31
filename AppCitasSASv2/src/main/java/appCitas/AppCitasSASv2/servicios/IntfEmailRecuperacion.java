package appCitas.AppCitasSASv2.servicios;


public interface IntfEmailRecuperacion {

	
	public void enviarEmailRecuperacion(String emailDestino, String nombreUsuario, String token);
	
}
