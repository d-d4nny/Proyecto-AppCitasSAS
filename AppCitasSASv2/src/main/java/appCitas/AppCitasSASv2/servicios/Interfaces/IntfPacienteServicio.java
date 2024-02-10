package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

public interface IntfPacienteServicio {

	
	public PacienteDTO registrar(PacienteDTO pacienteDTO);
	
	
	public PacienteDTO buscarPorId(long id);
	
	
	public Paciente buscarPorEmail(String emailPaciente);
	
	
	public boolean buscarPorDni(String dni);
	
	
	public List<PacienteDTO> buscarTodos();
	

	public void actualizarPaciente(PacienteDTO pacienteModificado);

	
	public PacienteDTO obtenerUsuarioPorToken(String token);
	
	
	public boolean iniciarResetPassConEmail(String emailPaciente);
	
	
	public boolean modificarContrasenaConToken(PacienteDTO paciente);

	
	public Paciente eliminar(long id);

	
	public String convertToBase64(byte[] data);
	
	
	public byte[] convertToByteArray(String base64String);
	
	
	boolean confirmarCuenta(String emailPaciente); 
	

	public boolean estaLaCuentaConfirmada(String email);
}
