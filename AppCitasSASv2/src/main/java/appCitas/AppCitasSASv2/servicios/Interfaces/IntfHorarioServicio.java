package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Horarios;
import appCitas.AppCitasSASv2.dto.HorariosDTO;

public interface IntfHorarioServicio {

	HorariosDTO registrar(HorariosDTO horariosDTO);
    
	Horarios buscarPorId(long id);

    List<HorariosDTO> buscarTodos();

    Horarios eliminar(long id);
}
