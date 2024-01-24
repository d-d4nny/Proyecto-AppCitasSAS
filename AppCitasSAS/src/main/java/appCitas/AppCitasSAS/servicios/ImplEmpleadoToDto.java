package appCitas.AppCitasSAS.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.dto.EmpleadoDTO;

@Service
public class ImplEmpleadoToDto implements IntfEmpleadoToDto {
	
	@Override
	public EmpleadoDTO empleadoToDto(Empleados u) {

		try {
			EmpleadoDTO dto = new EmpleadoDTO();
			dto.setIdEmpleado(u.getIdEmpleado());
			dto.setNombreCompletoEmpleado(u.getNombreCompletoEmpleado());
			dto.setIdentificadorEmpleado(u.getIdentificadorEmpleado());
			dto.setContrasenaEmpleado(u.getContrasenaEmpleado());
			dto.setToken(u.getToken());
			dto.setExpiracionToken(u.getExpiracionToken());
			
			return dto;
		}catch(Exception e) {
			System.out.println(
					"\n[ERROR EmpleadoToDtoImpl - empleadoToDto()] - Error al convertir empleado DAO a empleadoDTO (return null): "
							+ e);
			return null;
		}
	}
	
	@Override
	public List<EmpleadoDTO> listEmpleadoToDto(List<Empleados> listaEmpleados) {
		try {
				
			List<EmpleadoDTO> listaDto = new ArrayList<>();
			for (Empleados u : listaEmpleados) {
				listaDto.add(this.empleadoToDto(u));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR EmpleadoToDtoImpl - listaEmpleadoToDto()] - Error al convertir lista de empleado DAO a lista de empleadoDTO (return null): "
							+ e);
		}
		return null;
	}

}
