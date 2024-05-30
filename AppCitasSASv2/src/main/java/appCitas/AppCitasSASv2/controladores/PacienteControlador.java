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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.dto.HorariosDTO;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfHorarioServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PacienteControlador {

    @Autowired
    private IntfDoctorServicio doctoresServicio;

    @Autowired
    private IntfHorarioServicio horariosServicio;

    @Autowired
    private IntfCitasServicio citasServicio;

    @Autowired
    private IntfInformeServicio informeServicio;

    @Autowired
    private IntfPacienteServicio pacienteServicio;

    /**
     * Muestra la página de registro para nuevos pacientes.
     * 
     * @param model El modelo utilizado por la vista.
     * @return La vista de la página de registro.
     */
    @GetMapping("/auth/registrar")
    public String registrarGet(Model model) {
        try {
            model.addAttribute("pacienteDTO", new PacienteDTO());
            return "registro";
        } catch (Exception e) {
            model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "registro";
        }
    }

    /**
     * Procesa el formulario de registro para nuevos pacientes.
     * 
     * @param pacienteDTO      Objeto que contiene la información del nuevo paciente.
     * @param model            El modelo utilizado por la vista.
     * @param authentication   Información de autenticación del usuario.
     * @return La vista de la página de inicio de sesión o de registro con mensajes de resultado.
     */
    @PostMapping("/auth/registrar")
    public String registrarPost(@ModelAttribute PacienteDTO pacienteDTO, Model model, Authentication authentication) {

        PacienteDTO nuevoPaciente = pacienteServicio.registrar(pacienteDTO);

        String rolDelUsuario = "";

        if (authentication != null && authentication.isAuthenticated()) {
            rolDelUsuario = authentication.getAuthorities().iterator().next().getAuthority();
        }

        if (nuevoPaciente == null) {
            model.addAttribute("pacienteRegistradoPeroNoConfirmado",
                    "Ya existe un paciente con ese email sin confirmar");
            return "registro";
        } else if (nuevoPaciente != null && nuevoPaciente.getDniPaciente() != null
                && !rolDelUsuario.equals("ROLE_USER")) {
            model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo paciente OK");
            return "login";
        } else if (nuevoPaciente != null && nuevoPaciente.getDniPaciente() != null
                && !rolDelUsuario.equals("ROLE_ADMIN")) {
            model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo usuario admin OK");
            return "login";
        } else {
            // Se verifica si el DNI ya existe para mostrar error personalizado en la vista
            if (pacienteDTO.getDniPaciente() == null) {
                model.addAttribute("mensajeErrorDni", "Ya existe un usuario con ese DNI");
                return "registro";
            } else {
                model.addAttribute("mensajeErrorMail", "Ya existe un usuario con ese email");
                return "registro";
            }
        }
    }

    /**
     * Muestra la página principal del paciente con sus citas e informes.
     * 
     * @param model          El modelo utilizado por la vista.
     * @param authentication Información de autenticación del usuario.
     * @return La vista de la página principal del paciente.
     */
    @GetMapping("/privada/Pacientes")
    public String homeUser(Model model, Authentication authentication) {
        List<CitasDTO> citas = citasServicio.buscarTodos();
        List<InformeDTO> informes = informeServicio.buscarTodos();
        Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());
        PacienteDTO pacienteDTO = pacienteServicio.buscarPorId(paciente.getIdPaciente());
        model.addAttribute("citas", pacienteServicio.buscarPorEmail(authentication.getName()).getCitasDePaciente());
        model.addAttribute("informes", pacienteServicio.buscarPorEmail(authentication.getName()).getInformesDePaciente());
        model.addAttribute("pacienteDTO", pacienteDTO);
        model.addAttribute("paciente", paciente);

        return "homePaciente";
    }

    /**
     * Muestra la página principal del administrador con información de citas,
     * doctores, pacientes y turnos de consulta.
     * 
     * @param model          El modelo utilizado por la vista.
     * @param authentication Información de autenticación del usuario.
     * @return La vista de la página principal del administrador.
     */
    @GetMapping("/privada/Administracion")
    public String homeEmpleado(Model model, Authentication authentication) {
        List<CitasDTO> citas = citasServicio.buscarTodos();
        List<DoctoresDTO> doctores = doctoresServicio.buscarTodos();
        List<PacienteDTO> pacientes = pacienteServicio.buscarTodos();
        List<HorariosDTO> horarios = horariosServicio.buscarTodos();
        model.addAttribute("citas", citas);
        model.addAttribute("doctores", doctores);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("horarios", horarios);

        return "homeEmpleado";
    }

    /**
     * Muestra el formulario de edición de datos del paciente.
     * 
     * @param id     Identificador del paciente a editar.
     * @param model  El modelo utilizado por la vista.
     * @param request Información sobre la solicitud HTTP.
     * @return La vista del formulario de edición del paciente.
     */
    @GetMapping("/privada/editar-paciente/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, HttpServletRequest request) {
        try {
            PacienteDTO pacienteDTO = pacienteServicio.buscarPorId(id);
            if (pacienteDTO == null) {
                return "homePaciente";
            }
            model.addAttribute("pacienteDTO", pacienteDTO);
            return "editarPaciente";
        } catch (Exception e) {
            model.addAttribute("Error", "Ocurrió un error al obtener el paciente para editar");
            return "homePaciente";
        }
    }

    /**
     * Procesa el formulario de edición de datos del paciente.
     * 
     * @param pacienteDTO     Objeto que contiene la información actualizada del
     *                        paciente.
     * @param authentication  Información de autenticación del usuario.
     * @param model           El modelo utilizado por la vista.
     * @param imagen          Archivo de imagen seleccionado para actualizar la
     *                        foto de perfil.
     * @return La redirección a la página correspondiente según el rol del usuario
     *         con mensajes de resultado.
     */
    @PostMapping("/privada/procesar-editar")
    public String procesarFormularioEdicion(@ModelAttribute("usuarioDTO") PacienteDTO pacienteDTO,
            Authentication authentication, Model model, @RequestParam("file") MultipartFile imagen) {
        Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());
        String email = paciente.getEmailPaciente();
        model.addAttribute("nombrePaciente", email);
        System.out.println(authentication.getAuthorities());

        try {
            // si sube una imagen la enviamos a la bbdd sino que actualice y ya esta

            if (!imagen.isEmpty()) {

                String convertedImage = pacienteServicio.convertToBase64(imagen.getBytes());
                pacienteDTO.setProfilePicture(convertedImage);

            } else {
                // Si no se selecciona una nueva imagen, asegúrate de no sobrescribir el valor
                // existente
                PacienteDTO pacienteExistente = pacienteServicio.buscarPorId(pacienteDTO.getIdPaciente());
                if (pacienteExistente != null) {
                    pacienteDTO.setProfilePicture(pacienteExistente.getProfilePicture());
                }
            }
            pacienteServicio.actualizarPaciente(pacienteDTO);
            model.addAttribute("edicionCorrecta", "El Paciente se ha editado correctamente");
            model.addAttribute("pacientes", pacienteServicio.buscarTodos());
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/privada/Administracion";
            } else {
                return "redirect:/privada/Pacientes";
            }
        } catch (Exception e) {
            model.addAttribute("Error", "Ocurrió un error al editar el paciente" + e);
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/privada/Administracion";
            } else {
                return "redirect:/privada/Pacientes";
            }
        }
    }

    /**
     * Elimina al paciente con el ID proporcionado.
     * 
     * @param id     Identificador del paciente a eliminar.
     * @param model  El modelo utilizado por la vista.
     * @param request Información sobre la solicitud HTTP.
     * @return La redirección a la página de administración con mensajes de
     *         resultado.
     */
    @GetMapping("/privada/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Long id, Model model, HttpServletRequest request) {
    	try {
    		List<CitasDTO> citas = citasServicio.buscarTodos();
            List<DoctoresDTO> doctores = doctoresServicio.buscarTodos();
            List<HorariosDTO> horarios = horariosServicio.buscarTodos();
    
	    	PacienteDTO paciente = pacienteServicio.buscarPorId(id);
	        List<PacienteDTO> pacientes = pacienteServicio.buscarTodos();
	        
	        String emailUsuarioActual = request.getUserPrincipal().getName();
	
	        
	        if (request.isUserInRole("ROLE_USER")) {
				model.addAttribute("noAdmin", "No tiene los permisos suficientes para acceder al recurso");
				model.addAttribute("pacientes", pacientes);
				model.addAttribute("citas", citas);
	            model.addAttribute("doctores", doctores);
	            model.addAttribute("horarios", horarios);
				return "homeEmpleado";
			} else if (emailUsuarioActual.equals(paciente.getEmailPaciente())) {
				model.addAttribute("noTePuedesEliminar", "No puedes eliminarte a ti mismo como administrador");
				model.addAttribute("pacientes", pacientes);
				model.addAttribute("citas", citas);
	            model.addAttribute("doctores", doctores);
	            model.addAttribute("horarios", horarios);
				return "homeEmpleado";
			} else {
				
				if (paciente.getRolPaciente().equals("ROLE_ADMIN")) {
		            long countAdmins = pacientes.stream()
		                                        .filter(p -> "ROLE_ADMIN".equals(p.getRolPaciente()))
		                                        .count();
	
		            if (countAdmins <= 1) {
		            	model.addAttribute("noSePuedeEliminar", "No se puede eliminar el único admin restante");
		            	model.addAttribute("pacientes", pacientes);
						model.addAttribute("citas", citas);
			            model.addAttribute("doctores", doctores);
			            model.addAttribute("horarios", horarios);
		                return "homeEmpleado";
		            }
		        }
				if (paciente.getCitasDePaciente().size() > 0) {
					model.addAttribute("elUsuarioTieneCitas", "No se puede eliminar un usuario con citas");
					model.addAttribute("pacientes", pacientes);
					model.addAttribute("citas", citas);
		            model.addAttribute("doctores", doctores);
		            model.addAttribute("horarios", horarios);
					return "homeEmpleado";
				}
				pacienteServicio.eliminar(id);
		        model.addAttribute("eliminacionCorrecta", "El usuario se ha eliminado correctamente");
		        model.addAttribute("pacientes", pacientes);
				model.addAttribute("citas", citas);
	            model.addAttribute("doctores", doctores);
	            model.addAttribute("horarios", horarios);
		        return "homeEmpleado";
			}       
    	} catch (Exception e) {
			model.addAttribute("Error", "Ocurrió un error al eliminar el usuario");
			return "redirect:/privada/Administracion";
		}
    }
}
