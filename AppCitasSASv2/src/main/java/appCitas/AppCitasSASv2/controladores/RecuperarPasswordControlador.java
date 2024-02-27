package appCitas.AppCitasSASv2.controladores;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteServicio;

@Controller
@RequestMapping("/auth")
public class RecuperarPasswordControlador {

    @Autowired
    private IntfPacienteServicio pacienteServicio;

    /**
     * Muestra la vista para iniciar el proceso de recuperación de contraseña.
     * 
     * @param model El modelo utilizado por la vista.
     * @return La vista de iniciar recuperación.
     */
    @GetMapping("/iniciarRecuperacion")
    public String mostrarVistainiciarRecuperacion(Model model) {
        model.addAttribute("pacienteDTO", new PacienteDTO());
        return "iniciarRecuperacion";
    }

    /**
     * Procesa la solicitud para iniciar el proceso de recuperación de contraseña.
     * 
     * @param pacienteDTO Objeto que contiene la información del paciente.
     * @param model       El modelo utilizado por la vista.
     * @return La vista de inicio de sesión o de inicio de recuperación con mensajes
     *         de resultado.
     */
    @PostMapping("/iniciarRecuperacion")
    public String procesarInicioRecuperacion(@ModelAttribute PacienteDTO pacienteDTO, Model model) {

        boolean envioConExito = pacienteServicio.iniciarResetPassConEmail(pacienteDTO.getEmailPaciente());

        if (envioConExito) {
            model.addAttribute("mensajeExitoMail", "Proceso de recuperación OK");
            return "login";
        } else {
            model.addAttribute("mensajeErrorMail", "Error en el proceso de recuperación.");
        }
        return "iniciarRecuperacion";
    }

    /**
     * Muestra la vista para el proceso de recuperación de contraseña.
     * 
     * @param token El token de recuperación proporcionado.
     * @param model El modelo utilizado por la vista.
     * @return La vista de recuperación de contraseña con el formulario.
     */
    @GetMapping("/recuperar")
    public String mostrarVistaRecuperar(@RequestParam(name = "token") String token, Model model) {
        PacienteDTO paciente = pacienteServicio.obtenerUsuarioPorToken(token);
        if (paciente != null) {
            model.addAttribute("pacienteDTO", paciente);
        } else {
            model.addAttribute("pacienteDTO", new PacienteDTO());
            model.addAttribute("mensajeErrorTokenValidez", "Token no válido o paciente no encontrado");
            return "iniciarRecuperacion";
        }
        return "recuperar";
    }

    /**
     * Procesa la solicitud de recuperación de contraseña.
     * 
     * @param pacienteDTO Objeto que contiene la información del paciente y el
     *                    nuevo token.
     * @param model       El modelo utilizado por la vista.
     * @return La vista de inicio de sesión o de recuperación con mensajes de
     *         resultado.
     */
    @PostMapping("/recuperar")
    public String procesarRecuperacionContraseña(@ModelAttribute PacienteDTO pacienteDTO, Model model) {

        PacienteDTO pacienteExistente = pacienteServicio.obtenerUsuarioPorToken(pacienteDTO.getToken());

        if (pacienteExistente == null) {
            model.addAttribute("mensajeErrorTokenValidez", "Token no válido");
            return "iniciarRecuperacion";
        }
        if (pacienteExistente.getExpiracionToken().before(Calendar.getInstance())) {
            model.addAttribute("mensajeErrorTokenExpirado", "El token ha expirado");
            return "iniciarRecuperacion";
        }

        boolean modificadaPassword = pacienteServicio.modificarContrasenaConToken(pacienteDTO);

        if (modificadaPassword) {
            model.addAttribute("contraseñaModificadaExito", "Contraseña modificada OK");
            return "login";
        } else {
            model.addAttribute("contraseñaModificadaError", "Error al cambiar de contraseña");
            return "iniciarRecuperacion";
        }
    }
}
