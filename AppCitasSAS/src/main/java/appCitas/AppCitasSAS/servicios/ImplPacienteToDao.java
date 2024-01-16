package appCitas.AppCitasSAS.servicios;

import appCitas.AppCitasSAS.dao.Paciente;
import appCitas.AppCitasSAS.dto.PacienteDTO;

public class ImplPacienteToDao implements IntfPacienteToDao {

	@Override
	public Paciente pacienteToDao(PacienteDTO pacienteDTO) {
		
		Paciente pacienteDao = new Paciente();
	}
}
