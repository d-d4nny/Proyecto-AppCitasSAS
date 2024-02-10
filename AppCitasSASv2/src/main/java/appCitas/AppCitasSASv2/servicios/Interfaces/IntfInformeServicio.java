package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;

public interface IntfInformeServicio {

	
	public Boolean crearInforme(InformeDTO informeDTO);
	
	
	public Informes buscarPorId(long id);
	
	
	public List<InformeDTO> buscarTodos();
	
	
	public Informes eliminar(long id);
}
