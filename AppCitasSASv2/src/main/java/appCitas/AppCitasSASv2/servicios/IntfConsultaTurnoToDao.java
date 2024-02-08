package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;

public interface IntfConsultaTurnoToDao {

	
	public ConsultaTurno consultaTurnoToDao(ConsultaTurnoDTO consultaTurnoDTO);
	
	
	public List<ConsultaTurno> listConsultaTurnoToDao(List<ConsultaTurnoDTO> listaConsultaTurnoDTO);
}
