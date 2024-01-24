package appCitas.AppCitasSAS.servicios;

import java.util.List;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.dto.EmpleadoDTO;

public interface IntfEmpleadoToDao {

	
	public Empleados empleadoToDao(EmpleadoDTO empleadoDTO);
	
	
	public List<Empleados> listEmpleadoToDao(List<EmpleadoDTO>listaEmpleadoDTO);
}
