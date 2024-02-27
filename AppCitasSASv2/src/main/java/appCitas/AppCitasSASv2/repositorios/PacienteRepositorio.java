package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Paciente;

/**
 * Interfaz de repositorio para la entidad Paciente.
 */
@Service
public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

    /**
     * Busca al primer usuario que tiene la dirección de correo electrónico especificada.
     *
     * @param emailPaciente La dirección de correo electrónico del usuario a buscar.
     * @return El primer usuario encontrado con la dirección de correo electrónico especificada o null en caso contrario.
     */
    public Paciente findFirstByEmailPaciente(String emailPaciente);

    /**
     * Busca si un usuario tiene el DNI especificado.
     *
     * @param dniPaciente El DNI del usuario a buscar.
     * @return true si el usuario tiene el DNI especificado, false en caso contrario.
     */
    public boolean existsByDniPaciente(String dniPaciente);

    /**
     * Busca un usuario por su token de recuperación.
     *
     * @param token El token de recuperación del usuario.
     * @return El usuario buscado por el token o null si no se encuentra.
     */
    public Paciente findByToken(String token);

    /**
     * Verifica si existe un usuario con el nombre de usuario especificado.
     *
     * @param nombreCompletoPaciente El nombre completo del usuario a verificar.
     * @return true si existe un usuario con el nombre de usuario especificado, false en caso contrario.
     */
    public boolean existsByNombreCompletoPaciente(String nombreCompletoPaciente);
}
