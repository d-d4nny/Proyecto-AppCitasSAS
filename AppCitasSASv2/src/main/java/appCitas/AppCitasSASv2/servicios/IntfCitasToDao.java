package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dto.CitasDTO;

public interface IntfCitasToDao {
	
	
	public Citas citasToDao(CitasDTO citasDTO);
	
	
	public List<Citas> listCitasToDao(List<CitasDTO> listaCitasDTO);

}
