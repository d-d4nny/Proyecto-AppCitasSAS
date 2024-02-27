package appCitas.AppCitasSASv2.servicios.Interfaces;

public interface IntfEmailRecuperacion {

    /**
     * Envía un correo electrónico de recuperación de contraseña.
     *
     * @param emailDestino   Dirección de correo electrónico de destino.
     * @param nombreUsuario  Nombre del usuario para personalizar el correo.
     * @param token          Token asociado a la recuperación de contraseña.
     */
    void enviarEmailRecuperacion(String emailDestino, String nombreUsuario, String token);

    /**
     * Envía un correo electrónico de confirmación de cuenta.
     *
     * @param emailDestino   Dirección de correo electrónico de destino.
     * @param nombrePaciente Nombre del paciente para personalizar el correo.
     * @param token          Token asociado a la confirmación de cuenta.
     */
    void enviarEmailConfirmacion(String emailDestino, String nombrePaciente, String token);
}
