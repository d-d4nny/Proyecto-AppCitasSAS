package appCitas.AppCitasSAS.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSAS.dto.LoginEmpleadoPacienteDTO;

@Service
public interface EmpleadoPacienteRepositorio extends JpaRepository<LoginEmpleadoPacienteDTO, Long>{

	/**
	 * Busca al primer empleado que tiene la dirección de correo electrónico especificada
	 * @param email La dirección de correo electrónico del empleado a buscar.
	 * @return El primer empleado encontrado con la dirección de correo electrónico
	 *         especificada o null en caso contrario.
	 */
	public LoginEmpleadoPacienteDTO findFirstByEmailEmpleadoPaciente(String email);
}
