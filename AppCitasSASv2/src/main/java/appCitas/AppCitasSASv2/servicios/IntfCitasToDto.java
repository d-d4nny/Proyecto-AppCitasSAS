package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;

public interface IntfCitasToDto {

	
	public CitasDTO citasToDto(Citas u);
	
	
	public List<CitasDTO> listCitasToDto(List<Citas> listaCitas);
}
