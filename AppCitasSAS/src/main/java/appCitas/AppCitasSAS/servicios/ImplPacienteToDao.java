package appCitas.AppCitasSAS.servicios;

import java.util.ArrayList;
import java.util.List;

import appCitas.AppCitasSAS.dao.Paciente;
import appCitas.AppCitasSAS.dto.PacienteDTO;

public class ImplPacienteToDao implements IntfPacienteToDao {

	@Override
	public Paciente pacienteToDao(PacienteDTO pacienteDTO) {
		
		Paciente pacienteDao = new Paciente();
		
		try {
			pacienteDao.setNombreCompletoPaciente(pacienteDTO.getNombreCompletoPaciente());
			pacienteDao.setDniPaciente(pacienteDTO.getDniPaciente());
			pacienteDao.setTlfPaciente(pacienteDTO.getTlfPaciente());
			pacienteDao.setEmailPaciente(pacienteDTO.getEmailPaciente());
			pacienteDao.setContrasenaPaciente(pacienteDTO.getContrasenaPaciente());
			pacienteDao.setFchNacimientoPaciente(pacienteDTO.getFchNacimientoPaciente());
			pacienteDao.setGeneroPaciente(pacienteDTO.getGeneroPaciente());
			pacienteDao.setDireccionPaciente(pacienteDTO.getDireccionPaciente());
			pacienteDao.setImgPaciente(pacienteDTO.getImgPaciente());
			
			return pacienteDao;
			
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR ImplPacienteToDao - toPacienteDao()] - Al convertir pacienteDTO a pacienteDAO (return null): "
							+ e);
			return null;
		}
	}
	
	@Override
	public List<Paciente> listPacienteToDao(List<PacienteDTO> listaPacienteDTO) {

		List<Paciente> listaPacienteDao = new ArrayList<>();

		try {
			for (PacienteDTO pacienteDTO : listaPacienteDTO) {
				listaPacienteDao.add(pacienteToDao(pacienteDTO));
			}

			return listaPacienteDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR ImplPacienteToDao - toListPacienteDao()] - Al convertir lista de pacienteDTO a lista de pacienteDAO (return null): "
							+ e);
		}
		return null;
	}
}
