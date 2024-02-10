package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;

public interface IntfInformeToDto {

	
	public InformeDTO informeToDto(Informes u);
	
	
	public List<InformeDTO> listInformesToDto(List<Informes> listaInformes);
}
