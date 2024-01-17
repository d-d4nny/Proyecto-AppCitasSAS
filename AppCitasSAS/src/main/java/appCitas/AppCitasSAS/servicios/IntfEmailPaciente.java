package appCitas.AppCitasSAS.servicios;


public interface IntfEmailPaciente {

	
	public void enviarEmailRecuperacion(String emailDestino, String nombreUsuario, String token);
	
}
