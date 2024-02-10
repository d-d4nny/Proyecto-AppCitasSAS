package appCitas.AppCitasSASv2.servicios.Interfaces;


public interface IntfEmailRecuperacion {

	
	public void enviarEmailRecuperacion(String emailDestino, String nombreUsuario, String token);
	
}
