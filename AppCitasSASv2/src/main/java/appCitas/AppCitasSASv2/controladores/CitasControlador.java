package appCitas.AppCitasSASv2.controladores;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import appCitas.AppCitasSASv2.dao.Citas;
import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeServicio;
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
    
	@Autowired
	private IntfInformeServicio informeServicio;

    /**
     * Muestra el formulario para crear una nueva cita.
     * 
     * @param model El modelo utilizado por la vista.
     * @return La vista del formulario de cita.
     */
    @GetMapping("/privada/crearCita")
    public String mostrarFormularioCita(Model model) {        

        // Obtener la lista de doctores y agregarla al modelo
        List<ConsultaTurnoDTO> consultaTurno = consultaTurnoServicio.buscarTodos();
        model.addAttribute("consultaTurno", consultaTurno);
        model.addAttribute("citasDTO", new CitasDTO());
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

             // Obtener el día de la semana de la consulta
             DayOfWeek diaSemanaConsulta = consultaTurno.getHorario().getDiaSemana();

             // Obtener el día de la semana actual
             DayOfWeek diaSemanaActual = LocalDate.now().getDayOfWeek();

             // Calcular la diferencia de días para llegar al próximo día de la semana de la consulta
             int diasHastaProximoDia = (diaSemanaConsulta.getValue() - diaSemanaActual.getValue() + 7) % 7;
             LocalDate proximoDiaSemana = LocalDate.now().plusDays(diasHastaProximoDia);

             // Convertir LocalDate a Date
             Date fechaCita = Date.valueOf(proximoDiaSemana);

             // Establecer la fecha de la cita como el próximo día de la semana de la consulta
             citaDTO.setFechaCita(fechaCita);

             // Establecer la consultaTurno
             citaDTO.setConsultaTurno(consultaTurno);


            CitasDTO cita = citasServicio.registrar(citaDTO);
            
            if (cita != null) {
                // Redirigir al panel de pacientes
                return "redirect:/privada/Pacientes";
            } else {
                // Manejar el caso en que la cita no se registró correctamente
                return "redirect:/privada/Pacientes";
            }
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
            // Obtener la cita por su ID
            Citas cita = citasServicio.buscarPorId(id);
            
            // Verificar si la cita está en estado "Pendiente"
            if ("Pendiente".equals(cita.getEstadoCita())) {
                // Si está en estado "Pendiente", se cancela la cita
                citasServicio.cancelarCita(id);
                return "redirect:/privada/Administracion";
            } else {
                // Si no está en estado "Pendiente", se muestra un mensaje de error
                model.addAttribute("error", "La cita no puede ser cancelada porque su estado no es 'Pendiente'.");
                return "redirect:/privada/Administracion";
            }
        } catch (Exception e) {
            // Manejar las excepciones según sea necesario
            return "redirect:/privada/Administracion";
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
            // Obtener la cita por su ID
            Citas cita = citasServicio.buscarPorId(id);
            
            // Verificar si la cita está en estado "Pendiente"
            if ("Pendiente".equals(cita.getEstadoCita())) {
                // Si está en estado "Pendiente", se completa la cita
                citasServicio.completarCita(id);         
			
			PacienteDTO paciente = pacienteServicio.buscarPorId(cita.getPaciente().getIdPaciente());

			ConsultaTurno consultaTurno = consultaTurnoServicio.buscarPorId(cita.getConsultaTurno().getIdConsultaTurno());
                        
            InformeDTO informeDTO = new InformeDTO();
            informeDTO.setNombreInforme(paciente.getNombreCompletoPaciente() + " - " + cita.getFechaCita());
            informeDTO.setDescInforme("El dia " + cita.getFechaCita() + " el paciente " + paciente.getNombreCompletoPaciente() + " asistio a la consulta numero " + consultaTurno.getNumConsulta() + " de " + consultaTurno.getNombreConsulta() + " con el doctor " + consultaTurno.getDoctor().getNombreCompletoDoctor() + " por el siguiente motivo: " + cita.getMotivoCita());
            informeDTO.setFchInforme(Calendar.getInstance());
            informeDTO.setPaciente(cita.getPaciente());

            informeServicio.crearInforme(informeDTO);
            
            return "redirect:/privada/Administracion";
            } else {
                // Si no está en estado "Pendiente", se muestra un mensaje de error
                model.addAttribute("error", "La cita no puede ser completada porque su estado no es 'Pendiente'.");
                return "redirect:/privada/Administracion";
            }
        } catch (Exception e) {
            // Manejar las excepciones según sea necesario
            return "redirect:/privada/Administracion";
        }
    }
}
