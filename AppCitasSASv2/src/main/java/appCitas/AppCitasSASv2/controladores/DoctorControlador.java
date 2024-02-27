package appCitas.AppCitasSASv2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DoctorControlador {

    @Autowired
    private IntfDoctorServicio doctoresServicio;

    @Autowired
    private IntfConsultaTurnoServicio consultaTurnoServicio;

    /**
     * Muestra el formulario para registrar un nuevo doctor.
     * 
     * @param model El modelo utilizado por la vista.
     * @return La vista del formulario de creación de doctor.
     */
    @GetMapping("/privada/crear-doctor")
    public String registrarGet(Model model) {
        model.addAttribute("doctoresDTO", new DoctoresDTO());

        List<ConsultaTurnoDTO> consultaTurno = consultaTurnoServicio.buscarTodos();
        model.addAttribute("consultaTurno", consultaTurno);
        return "crearDoctor";
    }

    /**
     * Procesa el formulario para registrar un nuevo doctor.
     * 
     * @param doctoresDTO Objeto que contiene la información del nuevo doctor.
     * @param model       El modelo utilizado por la vista.
     * @return La redirección a la página de administración.
     */
    @PostMapping("/privada/crear-doctor")
    public String registrarPost(@ModelAttribute DoctoresDTO doctoresDTO, Model model) {

        DoctoresDTO nuevoDoctor = doctoresServicio.registrar(doctoresDTO);

        if (nuevoDoctor != null && nuevoDoctor.getNombreCompletoDoctor() != null) {
            // Si el doctor y el nombre no son null es que el registro se completó correctamente
            model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo doctor OK");
            return "redirect:/privada/Administracion";
        } else {
            // Se verifica si el nombre ya existe para mostrar error personalizado en la vista
            model.addAttribute("mensajeErrorNombre", "Ya existe ese doctor");
            return "redirect:/privada/Administracion";
        }
    }

    /**
     * Muestra el formulario de edición de un doctor específico.
     * 
     * @param id     Identificador único del doctor a editar.
     * @param model  El modelo utilizado por la vista.
     * @param request Objeto HttpServletRequest.
     * @return La vista del formulario de edición de doctor o la vista principal en caso de error.
     */
    @GetMapping("/privada/editar-doctor/{id}")
    public String mostrarFormularioEdicionDoctor(@PathVariable Long id, Model model, HttpServletRequest request) {
        try {

            Doctores doctor = doctoresServicio.buscarPorId(id);

            List<ConsultaTurnoDTO> consultaTurno = consultaTurnoServicio.buscarTodos();
            model.addAttribute("consultaTurno", consultaTurno);
            if (doctor == null) {
                return "homeEmpleado";
            }
            model.addAttribute("doctoresDTO", doctor);
            return "editarDoctor";

        } catch (Exception e) {
            model.addAttribute("Error", "Ocurrió un error al obtener el doctor para editar");
            return "homeEmpleado";
        }
    }

    /**
     * Procesa el formulario de edición de un doctor.
     * 
     * @param doctoresDTO Objeto que contiene la información actualizada del doctor.
     * @param model       El modelo utilizado por la vista.
     * @return La redirección a la página de administración.
     */
    @PostMapping("/privada/procesar-editarDoctor")
    public String procesarFormularioEdicionDoctor(@ModelAttribute("doctoresDTO") DoctoresDTO doctoresDTO,
            Model model) {
        try {
            doctoresServicio.actualizarDoctor(doctoresDTO);
            model.addAttribute("edicionCorrecta", "El doctor se ha editado correctamente");
            model.addAttribute("doctores", doctoresServicio.buscarTodos());
            return "redirect:/privada/Administracion";
        } catch (Exception e) {
            model.addAttribute("Error", "Ocurrió un error al editar el doctor" + e);
            return "redirect:/privada/Administracion";
        }
    }

    /**
     * Elimina un doctor específico.
     * 
     * @param id     Identificador único del doctor a eliminar.
     * @param model  El modelo utilizado por la vista.
     * @param request Objeto HttpServletRequest.
     * @return La redirección a la página de administración.
     */
    @GetMapping("/privada/eliminar-doctor/{id}")
    public String eliminarDoctor(@PathVariable Long id, Model model, HttpServletRequest request) {
        doctoresServicio.eliminar(id);
        return "redirect:/privada/Administracion";
    }
}
