package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

public interface IntfDoctorToDto {

	
	public DoctoresDTO doctoresToDto(Doctores u);
	
	
	public List<DoctoresDTO> listDoctoresToDto(List<Doctores> listaDoctores);
}
