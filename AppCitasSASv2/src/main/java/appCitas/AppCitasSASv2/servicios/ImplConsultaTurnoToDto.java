package appCitas.AppCitasSASv2.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;

@Service
public class ImplConsultaTurnoToDto implements IntfConsultaTurnoToDto {

	
	@Override
	public ConsultaTurnoDTO consultaTurnoToDto(ConsultaTurno u) {
		
		try {
			ConsultaTurnoDTO dto = new ConsultaTurnoDTO();
			
			dto.setIdConsultaTurno(u.getIdConsultaTurno());
			dto.setTramoHoraTurno(u.getTramoHoraTurno());
			dto.setNumConsulta(u.getNumConsulta());
			
		}catch(Exception e) {
			System.out.println(
				"\n[ERROR ImplConsultaTurnoToDto - consultaTurnoToDto()] - Al convertir consultaTurno a consultaTurnoDTO (return null): "
						+ e);
		}
		return null;
	}
	
	
	@Override
	public List<ConsultaTurnoDTO> listConsultaTurnoToDto(List<ConsultaTurno> listaConsultaTurno){
		try {
			List<ConsultaTurnoDTO> listaDto = new ArrayList<>();
			for (ConsultaTurno u : listaConsultaTurno) {
				listaDto.add(this.consultaTurnoToDto(u));
			}
			return listaDto;
		}catch(Exception e) {
			System.out.println(
				"\n[ERROR ImplConsultaTurnoToDto - listConsultaTurnoToDto()] - Al convertir lista de consultaTurno a lista de consultaTurnoDTO (return null): "
						+ e);
		}
		return null;
	}
}
