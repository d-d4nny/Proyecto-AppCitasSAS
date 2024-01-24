package appCitas.AppCitasSAS.servicios;

import java.util.List;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.dto.EmpleadoDTO;

public interface IntfEmpleadoServicio {

	
	public EmpleadoDTO registrar(EmpleadoDTO empleadoDTO);
	
	
	public Empleados buscarPorId(long id);
	
	
	public Empleados buscarPorEmail(String emailEmpleado);
	
	
	public boolean buscarPorIdentificador(String identificadorEmpleado);
	
	
	public List<EmpleadoDTO> buscarTodos();
	
	
	public EmpleadoDTO obtenerEmpleadoPorToken(String token);
	
	
	public boolean iniciarResetPassConEmail(String emailEmpleado);
	
	
	public boolean modificarContrasenaConToken(EmpleadoDTO empleado);
	
	
	public Empleados eliminar (long id);
}
