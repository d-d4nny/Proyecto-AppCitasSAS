package appCitas.AppCitasSAS.servicios;

import java.util.List;

import appCitas.AppCitasSAS.dao.Paciente;
import appCitas.AppCitasSAS.dto.PacienteDTO;

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
