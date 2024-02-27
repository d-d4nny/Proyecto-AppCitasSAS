package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

public interface IntfPacienteServicio {

    /**
     * Registra un nuevo paciente.
     *
     * @param pacienteDTO Datos del paciente a registrar.
     * @return Objeto PacienteDTO resultante del registro.
     */
    PacienteDTO registrar(PacienteDTO pacienteDTO);

    /**
     * Busca un paciente por su ID.
     *
     * @param id ID del paciente a buscar.
     * @return Objeto PacienteDTO encontrado, o null si no existe.
     */
    PacienteDTO buscarPorId(long id);

    /**
     * Busca un paciente por su dirección de correo electrónico.
     *
     * @param emailPaciente Correo electrónico del paciente a buscar.
     * @return Objeto Paciente encontrado, o null si no existe.
     */
    Paciente buscarPorEmail(String emailPaciente);

    /**
     * Verifica si existe un paciente con el número de DNI especificado.
     *
     * @param dni Número de DNI a verificar.
     * @return true si existe un paciente con ese DNI, false de lo contrario.
     */
    boolean buscarPorDni(String dni);

    /**
     * Obtiene la lista de todos los pacientes.
     *
     * @return Lista de objetos PacienteDTO.
     */
    List<PacienteDTO> buscarTodos();

    /**
     * Actualiza la información de un paciente.
     *
     * @param pacienteModificado Datos actualizados del paciente.
     */
    void actualizarPaciente(PacienteDTO pacienteModificado);

    /**
     * Obtiene un paciente por su token de autenticación.
     *
     * @param token Token de autenticación del paciente.
     * @return Objeto PacienteDTO encontrado, o null si no existe.
     */
    PacienteDTO obtenerUsuarioPorToken(String token);

    /**
     * Inicia el proceso de restablecimiento de contraseña enviando un correo electrónico.
     *
     * @param emailPaciente Correo electrónico del paciente para restablecer la contraseña.
     * @return true si el proceso se inicia con éxito, false si no se encuentra el paciente.
     */
    boolean iniciarResetPassConEmail(String emailPaciente);

    /**
     * Modifica la contraseña del paciente utilizando un token.
     *
     * @param paciente Datos del paciente con el nuevo token y contraseña.
     * @return true si la modificación se realiza con éxito, false si el token no es válido.
     */
    boolean modificarContrasenaConToken(PacienteDTO paciente);

    /**
     * Elimina un paciente por su ID.
     *
     * @param id ID del paciente a eliminar.
     * @return Objeto Paciente eliminado, o null si no existe.
     */
    Paciente eliminar(long id);

    /**
     * Convierte datos binarios a formato Base64.
     *
     * @param data Datos binarios a convertir.
     * @return Cadena Base64 resultante.
     */
    String convertToBase64(byte[] data);

    /**
     * Convierte una cadena Base64 a datos binarios.
     *
     * @param base64String Cadena Base64 a convertir.
     * @return Datos binarios resultantes.
     */
    byte[] convertToByteArray(String base64String);

    /**
     * Confirma la cuenta de un paciente utilizando un token.
     *
     * @param emailPaciente Correo electrónico del paciente.
     * @return true si la confirmación se realiza con éxito, false si el token no es válido o la cuenta ya está confirmada.
     */
    boolean confirmarCuenta(String emailPaciente);

    /**
     * Verifica si la cuenta de un paciente está confirmada.
     *
     * @param email Correo electrónico del paciente.
     * @return true si la cuenta está confirmada, false si no lo está.
     */
    boolean estaLaCuentaConfirmada(String email);
}
