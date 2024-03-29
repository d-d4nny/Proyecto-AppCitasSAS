package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteToDto;

@Service
public class ImplPacienteToDto implements IntfPacienteToDto {
	
    /**
     * Convierte un objeto Paciente a un objeto PacienteDTO.
     *
     * @param u Objeto Paciente a convertir.
     * @return Objeto PacienteDTO convertido.
     */
	@Override
	public PacienteDTO pacienteToDto(Paciente u) {
		try {
			ImplPacienteServicio iPac = new ImplPacienteServicio();
			PacienteDTO dto = new PacienteDTO();
			
			dto.setIdPaciente(u.getIdPaciente());
			dto.setNombreCompletoPaciente(u.getNombreCompletoPaciente());
			dto.setDniPaciente(u.getDniPaciente());
			dto.setTlfPaciente(u.getTlfPaciente());
			dto.setEmailPaciente(u.getEmailPaciente());
			dto.setDireccionPaciente(u.getDireccionPaciente());
			dto.setRolPaciente(u.getRolPaciente());
			dto.setGeneroPaciente(u.getGeneroPaciente());
			dto.setContrasenaPaciente(u.getContrasenaPaciente());
			dto.setToken(u.getToken());
			dto.setExpiracionToken(u.getExpiracionToken());
			dto.setProfilePicture(iPac.convertToBase64(u.getProfilePicture()));
			dto.setCuentaConfirmada(u.isCuentaConfirmada());
			
			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR PacienteToDtoImpl - pacienteToDto()] - Error al convertir paciente DAO a pacienteDTO (return null): "
							+ e);
			return null;
		}
	}
	
    /**
     * Convierte una lista de objetos Paciente a una lista de objetos PacienteDTO.
     *
     * @param listaPaciente Lista de objetos Paciente a convertir.
     * @return Lista de objetos PacienteDTO convertidos.
     */
	@Override
	public List<PacienteDTO> listPacienteToDto(List<Paciente> listaPaciente){
		try {
				
			List<PacienteDTO> listaDto = new ArrayList<>();
			for (Paciente u : listaPaciente) {
				listaDto.add(this.pacienteToDto(u));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR PacienteToDtoImpl - listPacienteToDto()] - Error al convertir lista de paciente DAO a lista de pacienteDTO (return null): "
							+ e);
		}
		return null;
	}
}
