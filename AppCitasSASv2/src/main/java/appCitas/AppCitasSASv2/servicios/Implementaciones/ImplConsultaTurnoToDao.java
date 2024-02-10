package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoToDao;

@Service
public class ImplConsultaTurnoToDao implements IntfConsultaTurnoToDao {

	
	@Override
	public ConsultaTurno consultaTurnoToDao(ConsultaTurnoDTO consultaTurnoDTO) {
		
		ConsultaTurno consultaTurnoDao = new ConsultaTurno();
		
		try {
			consultaTurnoDao.setIdConsultaTurno(consultaTurnoDTO.getIdConsultaTurno());
			consultaTurnoDao.setTramoHoraTurnoInicio(consultaTurnoDTO.getTramoHoraTurnoInicio());
			consultaTurnoDao.setTramoHoraTurnoFin(consultaTurnoDTO.getTramoHoraTurnoFin());
			consultaTurnoDao.setNumConsulta(consultaTurnoDTO.getNumConsulta());
			
			return consultaTurnoDao;
		} catch (Exception e) {
			System.out.println(
				"\n[ERROR consultaTurnoToDao - toconsultaTurnoDao()] - Al convertir consultaTurnoDTO a consultaTurnoDAO (return null): "
						+ e);
			return null;
		}
	}
	
	@Override
	public List<ConsultaTurno> listConsultaTurnoToDao(List<ConsultaTurnoDTO> listaConsultaTurnoDTO) {
		
		List<ConsultaTurno> listaConsultaTurnoDao = new ArrayList<>();
		
		try {
			for (ConsultaTurnoDTO consultaTurnoDTO : listaConsultaTurnoDTO) {
				listaConsultaTurnoDao.add(consultaTurnoToDao(consultaTurnoDTO));
			}
			return listaConsultaTurnoDao;
		} catch (Exception e) {
			System.out.println(
				"\n[ERROR listConsultaTurnoToDao - toListConsultaTurnoDao()] - Al convertir lista de consultaTurnoDTO a lista de consultaTurnoDAO (return null): "
						+ e);
		}
		return null;
	}
}
