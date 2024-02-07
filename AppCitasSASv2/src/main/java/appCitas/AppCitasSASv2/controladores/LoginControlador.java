package appCitas.AppCitasSASv2.controladores;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.repositorios.PacienteRepositorio;
import appCitas.AppCitasSASv2.servicios.IntfCitasServicio;
import appCitas.AppCitasSASv2.servicios.IntfDoctorServicio;
import appCitas.AppCitasSASv2.servicios.IntfInformeServicio;
import appCitas.AppCitasSASv2.servicios.IntfPacienteServicio;
import appCitas.AppCitasSASv2.utils.ImageUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginControlador {

	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	@Autowired
	private IntfCitasServicio citasServicio;
	
	@Autowired
	private IntfInformeServicio informeServicio;
	    
	@Autowired
	private IntfPacienteServicio pacienteServicio;
	
	@Autowired
	private IntfDoctorServicio doctoresServicio;
	
	
	 @GetMapping("/auth/login")
		public String login(Model model) {
			// Se agrega un nuevo objeto PacienteDTO al modelo para el formulario de login
			model.addAttribute("pacienteDTO", new PacienteDTO());
			return "login";
		}
  

	 @GetMapping("/auth/registrar")
		public String registrarGet(Model model) {
			model.addAttribute("pacienteDTO", new PacienteDTO());
			return "registro";
		}

	 
	 @PostMapping("/auth/registrar")
		public String registrarPost(@ModelAttribute PacienteDTO pacienteDTO, Model model) {

		 PacienteDTO nuevoPaciente = pacienteServicio.registrar(pacienteDTO);
			
			if (nuevoPaciente != null && nuevoPaciente.getDniPaciente() != null) {
				// Si el usuario y el DNI no son null es que el registro se completo correctamente
				model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo usuario OK");
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
	 
	 
 	@GetMapping("/privada/home")
	public String loginCorrecto(Model model, Authentication authentication) {
		Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());
		String email = paciente.getEmailPaciente();
		model.addAttribute("nombrePaciente", email);
		System.out.println(authentication.getAuthorities());
		
		if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
	        return "redirect:/privada/Administracion";
	    } else {
	        return "redirect:/privada/Pacientes";
	    }
	}
	 
	 @GetMapping("/privada/Pacientes")
	 public String homeUser(Model model, Authentication authentication) {
		 List<CitasDTO> citas = citasServicio.buscarTodos();
		 List<InformeDTO> informes = informeServicio.buscarTodos(); 
		 //Informes informe = informeServicio.buscarPorId(informes.get(0).getIdInforme());
		 Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());
		 System.out.println(citas);
		 System.out.println(informes);
		 //model.addAttribute("informe", informe);
		 model.addAttribute("citas", pacienteServicio.buscarPorEmail(authentication.getName()).getCitasDePaciente());
		 model.addAttribute("informes", pacienteServicio.buscarPorEmail(authentication.getName()).getInformesDePaciente());
		 model.addAttribute("paciente", paciente);
		 
	     return "homePaciente";
	 }
	
	 
	 @GetMapping("/privada/editar-paciente/{id}")
		public String mostrarFormularioEdicion(@PathVariable Long id, Model model, HttpServletRequest request) {
			try {
				
				PacienteDTO pacienteDTO = pacienteServicio.buscarPorId(id);
				if(pacienteDTO == null) {
					return "homePaciente";
				}
				model.addAttribute("pacienteDTO", pacienteDTO);
				return "editarPaciente";
				
			} catch (Exception e) {
				model.addAttribute("Error", "Ocurrió un error al obtener el paciente para editar");
				return "homePaciente";
			}
		}


		@PostMapping("/privada/procesar-editar")
		public String procesarFormularioEdicion(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, Model model) {
			try {
				pacienteServicio.actualizarPaciente(pacienteDTO);
				model.addAttribute("edicionCorrecta", "El Usuario se ha editado correctamente");
				model.addAttribute("usuarios", pacienteServicio.buscarTodos());
				return "redirect:/privada/Pacientes";
			} catch (Exception e) {
				model.addAttribute("Error", "Ocurrió un error al editar el usuario");
				return "redirect:/privada/Pacientes";
			}
		}
		
		
		@PostMapping("privada/Pacientes/edit/picture")
		public String editPicture(HttpSession session, @RequestPart("profilePicture") MultipartFile file) {
			try {
				PacienteDTO user = (PacienteDTO) session.getAttribute("paciente");

				if (pacienteServicio.updateProfilePicture(user.getEmailPaciente(), file)) {
		            String convertedImage = ImageUtils.convertToBase64(file.getBytes());
					user.setProfilePicture(convertedImage);
					session.setAttribute("user", user);
	            } 
				return "redirect:/privada/Pacientes";
	        } catch (Exception e) {
	        	return "redirect:/privada/Pacientes";
			}
		} 
		
	 
		
	 
	 @GetMapping("/privada/crearCita")
	    public String mostrarFormularioCita(Model model) {
	        model.addAttribute("citasDTO", new CitasDTO());
	        
	        // Obtener la lista de doctores y agregarla al modelo
	        List<DoctoresDTO> doctores = doctoresServicio.buscarTodos();
	        model.addAttribute("doctores", doctores);

	        return "formularioCita";
	    }
	 
	 @PostMapping("/privada/crearCita")
	 public String crearCitaPost(@ModelAttribute CitasDTO citaDTO ,Authentication authentication) {
	     Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());
	     
	     Doctores doctor = doctoresServicio.buscarPorId(citaDTO.getDoctor().getIdDoctor());

	     // Establecer el paciente
	     citaDTO.setPaciente(paciente);

	     // Establecer el estado
	     citaDTO.setEstadoCita("pendiente");

	     // Establecer el doctor (en este caso, el doctor con ID 1)
	     // Puedes modificar esta lógica según tus necesidades
	     citaDTO.setDoctor(doctor);

	    
	     CitasDTO cita = citasServicio.registrar(citaDTO);


	     if (cita != null) {
	         // Si la cita se creó correctamente, redirige a la página de citas del paciente
	         return "redirect:/privada/Pacientes";
	     } else {
	         // Manejar el caso en que la creación de la cita no sea exitosa
	         // Puedes agregar un mensaje de error en el modelo si es necesario
	         return "redirect:/privada/Pacientes";
	     }
	 }

	

	 @GetMapping("/privada/Administracion")
	 public String homeAdmin(Model model, Authentication authentication) {
	     // Lógica específica para administradores
	     return "homeEmpleado";
	 }
		
		
	
	@GetMapping("/privada/eliminarCita/{id}")
		public String eliminarCita(@PathVariable Long id, Model model, HttpServletRequest request) {
			citasServicio.eliminar(id);
			return "redirect:/privada/Pacientes";	
		}
	 
	@GetMapping("/privada/eliminar/{id}")
	public String eliminarPaciente(@PathVariable Long id, Model model, HttpServletRequest request) {
		PacienteDTO paciente = pacienteServicio.buscarPorId(id);
		List<PacienteDTO> pacientes = pacienteServicio.buscarTodos();
		if(request.isUserInRole("ROLE_ADMIN") && paciente.getRolPaciente().equals("ROLE_ADMIN")) {
			model.addAttribute("noSePuedeEliminar", "No se puede eliminar a un admin");
			model.addAttribute("usuarios", pacientes);
			return "listado";
		}
		pacienteServicio.eliminar(id);
		return "redirect:/privada/listado";
		
	}

}
