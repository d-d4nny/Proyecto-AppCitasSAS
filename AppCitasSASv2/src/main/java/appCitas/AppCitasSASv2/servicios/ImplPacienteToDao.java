package appCitas.AppCitasSASv2.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

@Service
public class ImplPacienteToDao implements IntfPacienteToDao {

	@Override
	public Paciente pacienteToDao(PacienteDTO pacienteDTO) {
		
		try {
			ImplPacienteServicio iPac = new ImplPacienteServicio();
			Paciente pacienteDao = new Paciente();
			
			pacienteDao.setNombreCompletoPaciente(pacienteDTO.getNombreCompletoPaciente());
			pacienteDao.setDniPaciente(pacienteDTO.getDniPaciente());
			pacienteDao.setTlfPaciente(pacienteDTO.getTlfPaciente());
			pacienteDao.setEmailPaciente(pacienteDTO.getEmailPaciente());
			pacienteDao.setContrasenaPaciente(pacienteDTO.getContrasenaPaciente());
			pacienteDao.setGeneroPaciente(pacienteDTO.getGeneroPaciente());
			pacienteDao.setDireccionPaciente(pacienteDTO.getDireccionPaciente());
			pacienteDao.setProfilePicture(iPac.convertToByteArray(pacienteDTO.getProfilePicture()));
			
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
