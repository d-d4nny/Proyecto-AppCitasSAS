package appCitas.AppCitasSAS.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import appCitas.AppCitasSAS.dao.Paciente;
import appCitas.AppCitasSAS.dto.PacienteDTO;
import appCitas.AppCitasSAS.servicios.IntfPacienteServicio;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class LoginControlador {

	@Autowired
	private IntfPacienteServicio pacienteServicio;


	@GetMapping("/auth/loginPacientes")
	public String login(Model model) {
		// Se agrega un nuevo objeto UsuarioDTO al modelo para el formulario de login
		model.addAttribute("pacienteDTO", new PacienteDTO());
		return "loginPacientes";
	}

	
	@GetMapping("/auth/registrar")
	public String registrarGet(Model model) {
		model.addAttribute("pacienteDTO", new PacienteDTO());
		return "registro";
	}

	
	@PostMapping("/auth/registrar")
	public String registrarPost(@ModelAttribute PacienteDTO pacienteDTO, Model model) {

		PacienteDTO nuevoUsuario = pacienteServicio.registrar(pacienteDTO);
		
		if (nuevoUsuario != null && nuevoUsuario.getDniPaciente() != null) {
			// Si el usuario y el DNI no son null es que el registro se completo correctamente
			model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo paciente OK");
			return "loginPacientes";
		} else {
			// Se verifica si el DNI ya existe para mostrar error personalizado en la vista
			if (pacienteDTO.getDniPaciente() == null) {
				model.addAttribute("mensajeErrorDni", "Ya existe un paciente con ese DNI");
				return "registro";
			} else {
				model.addAttribute("mensajeErrorMail", "Ya existe un paciente con ese email");
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
		return "home";
	}
	
	@GetMapping("/privada/listado")
	public String listadoPacientes(Model model, HttpServletRequest request) {
		List<PacienteDTO> pacientes = pacienteServicio.buscarTodos();
		System.out.println(pacientes);
		model.addAttribute("usuarios", pacientes);
		if(request.isUserInRole("ROLE_ADMIN")) {
			return "listado";	
		} 
		return "home";
	}
	
	@GetMapping("/privada/eliminar/{id}")
	public String eliminarUsuario(@PathVariable Long id, Model model, HttpServletRequest request) {
		Paciente paciente = pacienteServicio.buscarPorId(id);
		List<PacienteDTO> pacientes = pacienteServicio.buscarTodos();
		if(request.isUserInRole("ROLE_ADMIN") && paciente.getRolPaciente().equals("ROLE_ADMIN")) {
			model.addAttribute("noSePuedeEliminar", "No se puede eliminar a un admin");
			model.addAttribute("pacientes", pacientes);
			return "listado";
		}
		pacienteServicio.eliminar(id);
		return "redirect:/privada/listado";
		
	}
	

}
