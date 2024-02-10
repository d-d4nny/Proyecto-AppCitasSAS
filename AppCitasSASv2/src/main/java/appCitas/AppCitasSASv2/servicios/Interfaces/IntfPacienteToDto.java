package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

public interface IntfPacienteToDto {

	
	public PacienteDTO pacienteToDto(Paciente u);

	
	public List<PacienteDTO> listPacienteToDto(List<Paciente> listaPaciente);
	
}
