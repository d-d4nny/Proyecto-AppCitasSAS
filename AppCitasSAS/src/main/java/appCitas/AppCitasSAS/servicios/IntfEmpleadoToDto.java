package appCitas.AppCitasSAS.servicios;

import java.util.List;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.dto.EmpleadoDTO;

public interface IntfEmpleadoToDto {

	
	public EmpleadoDTO empleadoToDto(Empleados u);

	
	public List<EmpleadoDTO> listEmpleadoToDto(List<Empleados> listaEmpleados);
}
