package appCitas.AppCitasSASv2.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

@Service
public class ImplDoctorToDto implements IntfDoctorToDto {
	
	@Override
	public DoctoresDTO doctoresToDto(Doctores u) {
		
		try {
			DoctoresDTO dto = new DoctoresDTO();
			
			dto.setIdDoctor(u.getIdDoctor());
			dto.setNombreCompletoDoctor(u.getNombreCompletoDoctor());
			dto.setEspecialidadDoctor(u.getEspecialidadDoctor());			
			dto.setConsultaTurno(u.getConsultaTurno());
			
			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR doctorToDtoImpl - doctorToDto()] - Error al convertir doctor DAO a doctorToDto (return null): "
							+ e);
			return null;
		}
	}
	
	@Override
	public List<DoctoresDTO> listDoctoresToDto(List<Doctores> listaDoctores){
		try {
				
			List<DoctoresDTO> listaDto = new ArrayList<>();
			for (Doctores u : listaDoctores) {
				listaDto.add(this.doctoresToDto(u));
			}
			return listaDto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR doctorToDtoImpl - listDoctoresToDto()] - Error al convertir lista de doctor DAO a lista de doctorDTO (return null): "
							+ e);
		}
		return null;
	}

}
