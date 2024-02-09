package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;

public interface IntfConsultaTurnoServicio {

	
	public Boolean crearConsultaTurno(ConsultaTurnoDTO consultaTurnoDTO);
	
	
	public void actualizarConsultaTurno(ConsultaTurnoDTO consultaTurnoDTO);
	
	
	public ConsultaTurno buscarPorId(long id);
	
	
	public List<ConsultaTurnoDTO> buscarTodos();
	
	
	public ConsultaTurno eliminar(long id);
}
