package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

public interface IntfPacienteToDao {
	
	
	public Paciente pacienteToDao(PacienteDTO pacienteDTO);
	
	
	public List<Paciente> listPacienteToDao(List<PacienteDTO>listaPacienteDTO);

}
