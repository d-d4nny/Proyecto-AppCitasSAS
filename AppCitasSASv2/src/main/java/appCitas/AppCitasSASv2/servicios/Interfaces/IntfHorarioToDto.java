package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Horarios;
import appCitas.AppCitasSASv2.dto.HorariosDTO;

public interface IntfHorarioToDto {

	 HorariosDTO horariosToDto(Horarios horarios);

	 List<HorariosDTO> listHorariosToDto(List<Horarios> listaHorarios);
}
