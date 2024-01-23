package appCitas.AppCitasSAS.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSAS.dao.Empleados;

public interface EmpleadosRepositorio extends JpaRepository<Empleados, Long> {
	
	/**
	 * Busca si un empleado tiene el IDENTIFICADOR especificado.
	 * @param identificadorEmpleado El IDENTIFICADOR del empleado a buscar.
	 * @return true si el empleado tiene el IDENTIFICADOR especificado, false en caso contrario.
	 */
	public boolean existsByIdentificadorEmpleado(int identificadorEmpleado);
	
	
	/**
	 * Busca un empleado por su token de recuperación.
	 * @param token de recuperacion del empleado que se le estableció cuando se inicio el proceso de recuperacion
	 * @return el empleado buscado por el token
	 */
	public Empleados findByToken(String token);
	
	/**
	 * Comprueba si existe un empleado con el nombre de empleado especificado.
	 * @param nombreCompletoEmpleados El nombre de empleado del empleado a buscar.
	 * @return true si existe un empleado con el nombre de empleado especificado, false en caso contrario.
	 */
	public boolean existsByNombreCompletoEmpleados(String nombreCompletoEmpleados);
}
