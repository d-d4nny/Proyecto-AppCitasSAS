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

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.IntfPacienteServicio;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class LoginControlador {

	    
	 @Autowired
	 private IntfPacienteServicio pacienteServicio;
	
	
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
	     // Lógica específica para usuarios normales
	     return "homePaciente";
	 }

	 @GetMapping("/privada/Administracion")
	 public String homeAdmin(Model model, Authentication authentication) {
	     // Lógica específica para administradores
	     return "homeEmpleado";
	 }
		
		@GetMapping("/privada/listado")
		public String listadoPacientes(Model model, HttpServletRequest request) {
			List<PacienteDTO> pacientes = pacienteServicio.buscarTodos();
			System.out.println(pacientes);
			model.addAttribute("pacientes", pacientes);
			if(request.isUserInRole("ROLE_ADMIN")) {
				return "listado";	
			} 
			return "home";
		}
		
		@GetMapping("/privada/eliminar/{id}")
		public String eliminarPaciente(@PathVariable Long id, Model model, HttpServletRequest request) {
			Paciente paciente = pacienteServicio.buscarPorId(id);
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
