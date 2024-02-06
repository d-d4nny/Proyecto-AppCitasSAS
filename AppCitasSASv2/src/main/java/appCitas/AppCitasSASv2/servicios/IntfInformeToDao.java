package appCitas.AppCitasSASv2.servicios;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;

public interface IntfInformeToDao {

	
	public Informes informeToDao(InformeDTO informeDTO);
	
	
	public List<Informes> listInformesToDao(List<InformeDTO> listaInformesDTO);
	
}
