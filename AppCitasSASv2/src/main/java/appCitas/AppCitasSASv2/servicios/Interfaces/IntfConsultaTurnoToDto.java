package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;

public interface IntfConsultaTurnoToDto {

	
	public ConsultaTurnoDTO consultaTurnoToDto(ConsultaTurno u);
	
	
	public List<ConsultaTurnoDTO> listConsultaTurnoToDto(List<ConsultaTurno> listaConsultaTurno);
}
