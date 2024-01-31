package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Paciente;

@Service
public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

	/**
	 * Busca al primer usuario que tiene la dirección de correo electrónico especificada
	 * @param email La dirección de correo electrónico del usuario a buscar.
	 * @return El primer usuario encontrado con la dirección de correo electrónico
	 *         especificada o null en caso contrario.
	 */
	public Paciente findFirstByEmailPaciente(String emailPaciente);
	
	/**
	 * Busca si un usuario tiene el DNI especificado.
	 * @param dni El DNI del usuario a buscar.
	 * @return true si el usuario tiene el DNI especificado, false en caso contrario.
	 */
	public boolean existsByDniPaciente(String dniPaciente);
	
	
	/**
	 * Busca un usuario por su token de recuperación.
	 * @param token de recuperacion del usuario que se le estableció cuando se inicio el proceso de recuperacion
	 * @return el usuario buscado por el token
	 */
	public Paciente findByToken(String token);
	
	/**
	 * Checkea si existe un usuario con el nombre de usuario especificado.
	 * @param nombreUsuario El nombre de usuario del usuario a buscar.
	 * @return true si existe un usuario con el nombre de usuario especificado, false en caso contrario.
	 */
	public boolean existsByNombreCompletoPaciente(String nombreCompletoPaciente);
}
