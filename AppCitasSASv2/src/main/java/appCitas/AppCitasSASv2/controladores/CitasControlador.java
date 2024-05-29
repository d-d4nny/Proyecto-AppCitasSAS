package appCitas.AppCitasSASv2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CitasControlador {

    @Autowired
    private IntfCitasServicio citasServicio;

    @Autowired
    private IntfConsultaTurnoServicio consultaTurnoServicio;

    @Autowired
    private IntfPacienteServicio pacienteServicio;

    /**
     * Muestra el formulario para crear una nueva cita.
     * 
     * @param model El modelo utilizado por la vista.
     * @return La vista del formulario de cita.
     */
    @GetMapping("/privada/crearCita")
    public String mostrarFormularioCita(Model model) {
        model.addAttribute("citasDTO", new CitasDTO());

        // Obtener la lista de doctores y agregarla al modelo
        List<ConsultaTurnoDTO> consultaTurno = consultaTurnoServicio.buscarTodos();
        model.addAttribute("consultaTurno", consultaTurno);

        return "formularioCita";
    }

    /**
     * Procesa la solicitud para crear una nueva cita.
     * 
     * @param citaDTO         Objeto que contiene la información de la cita.
     * @param authentication  Información de autenticación del usuario.
     * @return La redirección a la página de citas del paciente.
     */
    @PostMapping("/privada/crearCita")
    public String crearCitaPost(@ModelAttribute CitasDTO citaDTO, Authentication authentication) {
        try {
            Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());

            ConsultaTurno consultaTurno = consultaTurnoServicio.buscarPorId(citaDTO.getConsultaTurno().getIdConsultaTurno());

            // Establecer el paciente
            citaDTO.setPaciente(paciente);

            // Establecer el estado
            citaDTO.setEstadoCita("Pendiente");

            // Establecer l consultaTurno
            citaDTO.setConsultaTurno(consultaTurno);

            CitasDTO cita = citasServicio.registrar(citaDTO);

            // Redirigir según el resultado
            return cita != null ? "redirect:/privada/Pacientes" : "redirect:/privada/Pacientes";
        } catch (Exception e) {
            // Manejar las excepciones según sea necesario
            return "redirect:/error";
        }
    }

    /**
     * Elimina una cita específica.
     * 
     * @param id     Identificador único de la cita a eliminar.
     * @param model  El modelo utilizado por la vista.
     * @param request Objeto HttpServletRequest.
     * @return La redirección a la página de citas del paciente.
     */
    @GetMapping("/privada/eliminar-cita/{id}")
    public String eliminarCita(@PathVariable Long id, Model model, HttpServletRequest request) {
        try {
            citasServicio.eliminar(id);
            return "redirect:/privada/Pacientes";
        } catch (Exception e) {
            // Manejar las excepciones según sea necesario
            return "redirect:/error";
        }
    }

    /**
     * Cancela una cita específica.
     * 
     * @param id     Identificador único de la cita a cancelar.
     * @param model  El modelo utilizado por la vista.
     * @param request Objeto HttpServletRequest.
     * @return La redirección a la página de administración.
     */
    @GetMapping("/privada/cancelar-cita/{id}")
    public String cancelarCita(@PathVariable Long id, Model model, HttpServletRequest request) {
        try {
            citasServicio.cancelarCita(id);
            return "redirect:/privada/Administracion";
        } catch (Exception e) {
            // Manejar las excepciones según sea necesario
            return "redirect:/error";
        }
    }

    /**
     * Completa una cita específica.
     * 
     * @param id     Identificador único de la cita a completar.
     * @param model  El modelo utilizado por la vista.
     * @param request Objeto HttpServletRequest.
     * @return La redirección a la página de administración.
     */
    @GetMapping("/privada/completar-cita/{id}")
    public String completarCita(@PathVariable Long id, Model model, HttpServletRequest request) {
        try {
            citasServicio.completarCita(id);
            return "redirect:/privada/Administracion";
        } catch (Exception e) {
            // Manejar las excepciones según sea necesario
            return "redirect:/error";
        }
    }
}
