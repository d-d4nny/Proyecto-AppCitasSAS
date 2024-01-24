package appCitas.AppCitasSAS.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.dto.EmpleadoDTO;

@Service
public class ImplEmpleadoToDao implements IntfEmpleadoToDao {
	
	@Override
	public Empleados empleadoToDao(EmpleadoDTO empleadoDTO) {
		
		Empleados empleadosDao = new Empleados();
		
		try {
			empleadosDao.setNombreCompletoEmpleado(empleadoDTO.getNombreCompletoEmpleado());
			empleadosDao.setIdentificadorEmpleado(empleadoDTO.getIdentificadorEmpleado());
			empleadosDao.setContrasenaEmpleado(empleadoDTO.getContrasenaEmpleado());
			
			return empleadosDao;
		}catch(Exception e) {
			System.out.println(
					"\n[ERROR EmpleadoToDaoImpl - empleadoToDao()] - Error al convertir empleado DTO a empleado DAO (return null): "
							+ e);
			return null;
		}
	}
	
	@Override
	public List<Empleados> listEmpleadoToDao(List<EmpleadoDTO> listaEmpleadoDTO) {

		List<Empleados> listaEmpleadoDao = new ArrayList<>();

		try {
			for (EmpleadoDTO empleadoDTO : listaEmpleadoDTO) {
				listaEmpleadoDao.add(empleadoToDao(empleadoDTO));
			}

			return listaEmpleadoDao;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR EmpleadoToDaoImpl - toListEmpleadoDao()] - Al convertir lista de empleadoDTO a lista de empleadoDAO (return null): "
							+ e);
		}
		return null;
	}

}
