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

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.dto.EmpleadoDTO;
import appCitas.AppCitasSAS.servicios.IntfEmpleadoServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginEmpleadoControlador {

	@Autowired
	private IntfEmpleadoServicio empleadoServicio;
	
	
	@GetMapping("/auth/loginEmpleados")
	public String loginEmpleados(Model model) {
		model.addAttribute("empleadoDTO", new EmpleadoDTO());
		return "loginEmpleados";
	}
	
	
	@GetMapping("/auth/registrarEmpleado")
	public String registrarEmpleadoGet(Model model) {
		model.addAttribute("empleadoDTO", new EmpleadoDTO());
		return "registroEmpleado";
	}

	
	@PostMapping("/auth/registrarEmpleado")
	public String registrarEmpleadoPost(@ModelAttribute EmpleadoDTO empleadoDTO, Model model) {

		EmpleadoDTO nuevoEmpleado = empleadoServicio.registrar(empleadoDTO);
		
		if (nuevoEmpleado != null && nuevoEmpleado.getIdentificadorEmpleado() != null) {
			// Si el usuario y el DNI no son null es que el registro se completo correctamente
			model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo empleado OK");
			return "loginEmpleados";
		} else {
			// Se verifica si el DNI ya existe para mostrar error personalizado en la vista
			if (empleadoDTO.getIdentificadorEmpleado() == null) {
				model.addAttribute("mensajeErrorDni", "Ya existe un empleado con ese IDENTIFICADOR");
				return "registroEmpleado";
			} else {
				model.addAttribute("mensajeErrorMail", "Ya existe un empleado con ese email");
				return "registroEmpleado";
			}
		}
	}
	
	
	@GetMapping("/privada/homeEmpleado")
	public String loginCorrectoEmpleado(Model model, Authentication authentication) {
		Empleados empleado = empleadoServicio.buscarPorEmail(authentication.getName());
		String email = empleado.getEmailEmpleado();
		model.addAttribute("nombreEmpleado", email);
		System.out.println(authentication.getAuthorities());
		return "homeEmpleado";
	}
}
