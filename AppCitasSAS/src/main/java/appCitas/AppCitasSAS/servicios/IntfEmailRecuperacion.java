package appCitas.AppCitasSAS.servicios;


public interface IntfEmailRecuperacion {

	
	public void enviarEmailRecuperacion(String emailDestino, String nombreUsuario, String token);
	
}
