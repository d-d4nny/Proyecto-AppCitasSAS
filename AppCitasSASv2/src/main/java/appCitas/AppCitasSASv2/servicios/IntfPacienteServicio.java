package appCitas.AppCitasSASv2.servicios;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

	
	boolean updateProfilePicture(String emailPaciente, MultipartFile file) throws IOException;
}
