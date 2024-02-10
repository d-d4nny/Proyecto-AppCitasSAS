package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;

public interface IntfCitasServicio {

	
	public CitasDTO registrar(CitasDTO citasDTO);
	
	
	public Citas buscarPorId(long id);
	
	
	public List<CitasDTO> buscarTodos();
	
	
	public Citas eliminar(long id);
}
