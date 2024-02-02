package appCitas.AppCitasSASv2.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

@Service
public class ImplPacienteToDto implements IntfPacienteToDto {
	
	@Override
	public PacienteDTO pacienteToDto(Paciente u) {
		
		try {
			PacienteDTO dto = new PacienteDTO();
			
			dto.setIdPaciente(u.getIdPaciente());
			dto.setNombreCompletoPaciente(u.getNombreCompletoPaciente());
			dto.setDniPaciente(u.getDniPaciente());
			dto.setTlfPaciente(u.getTlfPaciente());
			dto.setEmailPaciente(u.getEmailPaciente());
			dto.setDireccionPaciente(u.getDireccionPaciente());
			dto.setGeneroPaciente(u.getGeneroPaciente());
			dto.setImgPaciente(u.getImgPaciente());
			dto.setContrasenaPaciente(u.getContrasenaPaciente());
			dto.setToken(u.getToken());
			dto.setExpiracionToken(u.getExpiracionToken());
			
			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR PacienteToDtoImpl - pacienteToDto()] - Error al convertir paciente DAO a pacienteDTO (return null): "
							+ e);
			return null;
		}
	}
	
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
					"\n[ERROR PacienteToDtoImpl - listaPacienteToDto()] - Error al convertir lista de paciente DAO a lista de pacienteDTO (return null): "
							+ e);
		}
		return null;
	}
	

}
