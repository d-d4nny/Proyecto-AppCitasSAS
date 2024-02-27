package appCitas.AppCitasSASv2.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ConsultaTurnoControlador {

    @Autowired
    private IntfConsultaTurnoServicio consultaTurnoServicio;

    /**
     * Muestra el formulario de edición de un turno específico.
     * 
     * @param id     Identificador único del turno a editar.
     * @param model  El modelo utilizado por la vista.
     * @param request Objeto HttpServletRequest.
     * @return La vista del formulario de edición de turno o la vista principal en caso de error.
     */
    @GetMapping("/privada/editar-turno/{id}")
    public String mostrarFormularioEdicionTurno(@PathVariable Long id, Model model, HttpServletRequest request) {
        try {
            ConsultaTurno consultaTurno = consultaTurnoServicio.buscarPorId(id);
            if (consultaTurno == null) {
                return "homeEmpleado";
            }
            model.addAttribute("consultaTurnoDTO", consultaTurno);
            return "editarTurno";

        } catch (Exception e) {
            model.addAttribute("Error", "Ocurrió un error al obtener el turno para editar");
            return "homeEmpleado";
        }
    }

    /**
     * Procesa el formulario de edición de un turno.
     * 
     * @param consultaTurnoDTO Objeto que contiene la información actualizada del turno.
     * @param model            El modelo utilizado por la vista.
     * @return La redirección a la página de administración.
     */
    @PostMapping("/privada/procesar-editarTurno")
    public String procesarFormularioEdicionTurno(@ModelAttribute("consultaTurnoDTO") ConsultaTurnoDTO consultaTurnoDTO,
            Model model) {
        try {
            consultaTurnoServicio.actualizarConsultaTurno(consultaTurnoDTO);
            model.addAttribute("edicionCorrecta", "El turno se ha editado correctamente");
            model.addAttribute("turnos", consultaTurnoServicio.buscarTodos());
            return "redirect:/privada/Administracion";
        } catch (Exception e) {
            model.addAttribute("Error", "Ocurrió un error al editar el turno" + e);
            return "redirect:/privada/Administracion";
        }
    }

    /**
     * Muestra el formulario para crear un nuevo turno.
     * 
     * @param model El modelo utilizado por la vista.
     * @return La vista del formulario de creación de turno.
     */
    @GetMapping("/privada/crear-turno")
    public String registrarGet(Model model) {
        model.addAttribute("consultaTurnoDTO", new ConsultaTurnoDTO());
        return "crearTurno";
    }

    /**
     * Procesa el formulario de creación de un nuevo turno.
     * 
     * @param consultaTurnoDTO Objeto que contiene la información del nuevo turno.
     * @param model            El modelo utilizado por la vista.
     * @return La redirección a la página de administración.
     */
    @PostMapping("/privada/crear-turno")
    public String registrarPost(@ModelAttribute ConsultaTurnoDTO consultaTurnoDTO, Model model) {

        ConsultaTurnoDTO nuevaConsultaTurno = consultaTurnoServicio.registrar(consultaTurnoDTO);

        if (nuevaConsultaTurno != null) {
            // Si el turno no es null es que el registro se completó correctamente
            model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo turno OK");
            return "redirect:/privada/Administracion";
        } else {
            // Se verifica si el turno ya existe para mostrar error personalizado en la vista
            model.addAttribute("mensajeErrorTurno", "Ya existe ese turno");
            return "redirect:/privada/Administracion";
        }
    }
}
