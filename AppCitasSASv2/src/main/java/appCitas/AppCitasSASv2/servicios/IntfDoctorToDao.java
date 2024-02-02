package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

public interface IntfDoctorToDao {

	
	public Doctores doctoresToDao(DoctoresDTO doctoresDTO, ConsultaTurno consultaTurno);
	
	
	public List<Doctores> listDoctoresToDao(List<DoctoresDTO> listaDoctoresDTO);	
}
