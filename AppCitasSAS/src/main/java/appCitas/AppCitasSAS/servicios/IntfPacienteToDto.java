package appCitas.AppCitasSAS.servicios;

import java.util.List;

import appCitas.AppCitasSAS.dao.Paciente;
import appCitas.AppCitasSAS.dto.PacienteDTO;

public interface IntfPacienteToDto {

	
	public PacienteDTO pacienteToDto(Paciente u);

	
	public List<PacienteDTO> listPacienteToDto(List<Paciente> listaPaciente);
	
}
