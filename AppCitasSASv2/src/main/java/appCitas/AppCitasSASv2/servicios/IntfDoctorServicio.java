package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

public interface IntfDoctorServicio {

	
	public DoctoresDTO registrar(DoctoresDTO doctoresDTO, ConsultaTurno consultaTurno);
	
	
	public Doctores buscarPorId(long id);
	
	
	public List<DoctoresDTO> buscarTodos();
	
	
	public Doctores eliminar(long id);
	
}
