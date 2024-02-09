package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

public interface IntfDoctorServicio {

	
	public DoctoresDTO registrar(DoctoresDTO doctoresDTO);
	
	
	public void actualizarDoctor(DoctoresDTO doctoresDTO);
	
	
	public Doctores buscarPorId(long id);
	
	
	public List<DoctoresDTO> buscarTodos();
	
	
	public Doctores eliminar(long id);
	
}
