package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

public interface IntfPacienteServicio {

	
	public PacienteDTO registrar(PacienteDTO pacienteDTO);
	
	
	public Paciente buscarPorId(long id);
	
	
	public Paciente buscarPorEmail(String emailPaciente);
	
	
	public boolean buscarPorDni(String dni);
	
	
	public List<PacienteDTO> buscarTodos();
	

	public PacienteDTO obtenerUsuarioPorToken(String token);
	
	
	public boolean iniciarResetPassConEmail(String emailPaciente);
	
	
	public boolean modificarContrasenaConToken(PacienteDTO paciente);

	
	public Paciente eliminar(long id);
}
