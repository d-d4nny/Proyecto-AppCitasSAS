package appCitas.AppCitasSAS.servicios;

import java.util.List;

import appCitas.AppCitasSAS.dao.Paciente;
import appCitas.AppCitasSAS.dto.PacienteDTO;

public interface IntfPacienteToDao {
	
	
	public Paciente pacienteToDao(PacienteDTO pacienteDTO);
	
	
	public List<Paciente> listPacienteToDao(List<PacienteDTO>listaPacienteDTO);

}
