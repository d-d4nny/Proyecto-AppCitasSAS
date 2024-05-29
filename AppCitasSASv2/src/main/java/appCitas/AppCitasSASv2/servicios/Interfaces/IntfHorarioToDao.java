package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Horarios;
import appCitas.AppCitasSASv2.dto.HorariosDTO;

public interface IntfHorarioToDao {

	Horarios horariosToDao(HorariosDTO horariosDTO);

    List<Horarios> listHorariosToDao(List<HorariosDTO> listaHorariosDTO);
}
