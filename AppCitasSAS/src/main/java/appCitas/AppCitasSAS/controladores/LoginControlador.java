package appCitas.AppCitasSAS.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.dao.Paciente;
import appCitas.AppCitasSAS.dto.EmpleadoDTO;
import appCitas.AppCitasSAS.dto.LoginEmpleadoPacienteDTO;
import appCitas.AppCitasSAS.dto.PacienteDTO;
import appCitas.AppCitasSAS.servicios.IntfEmpleadoServicio;
import appCitas.AppCitasSAS.servicios.IntfPacienteServicio;


@Controller
public class LoginControlador {

	
	 @Autowired
	 private IntfEmpleadoServicio empleadoServicio; 
	 
	    
	 @Autowired
	 private IntfPacienteServicio pacienteServicio;
	
	
	 	@GetMapping("/auth/login")
		public String login(Model model) {
			// Se agrega un nuevo objeto UsuarioDTO al modelo para el formulario de login
			return "login";
		} 
	
	 	@PostMapping("/auth/login")
	    public String login(@ModelAttribute LoginEmpleadoPacienteDTO loginDTO, Model model) {
	        if ("empleado".equals(loginDTO.getTipo())) {
	            // Lógica de inicio de sesión para empleados
	        	model.addAttribute("empleadoDTO", new EmpleadoDTO());
	            // Utiliza empleadoDetailsService para la autenticación
	            Empleados empleado = empleadoServicio.buscarPorEmail(loginDTO.getEmail());
	            
	            // Verifica los roles específicos y redirige según el rol
	            if (empleado.getRolEmpleado().contains("ROLE_ADMIN_ADMIN")) {
	                // Redirige a la página específica para el rol "ROLE_ADMIN_ADMIN"
	                return "homeAdmin";
	            } else if (empleado.getRolEmpleado().contains("ROLE_ADMIN")) {
	                // Redirige a la página específica para el rol "ROLE_ADMIN"
	                return "homeAdmin";
	            } else if (empleado.getRolEmpleado().contains("ROLE_DOCTOR")) {
	                // Redirige a la página específica para el rol "ROLE_DOCTOR"
	                return "homeEmpleado";
	            } else {
	                // Otro rol, manejar según sea necesario
	                return "homeEmpleado";
	            }
	        } else if ("paciente".equals(loginDTO.getTipo())) {
	            // Lógica de inicio de sesión para pacientes
	        	model.addAttribute("pacienteDTO", new PacienteDTO());
	            // Utiliza pacienteDetailsService para la autenticación
	            return "homePaciente";
	        } else {
	            // Tipo desconocido, manejar según sea necesario
	            return "error";
	        }
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
	            model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo empleado OK");
	            return "loginEmpleados";
	        } else {
	            if (empleadoDTO.getIdentificadorEmpleado() == null) {
	                model.addAttribute("mensajeErrorDni", "Ya existe un empleado con ese IDENTIFICADOR");
	                return "registroEmpleado";
	            } else {
	                model.addAttribute("mensajeErrorMail", "Ya existe un empleado con ese email");
	                return "registroEmpleado";
	            }
	        }
	    }    
	    
	    

    @GetMapping("/auth/registrarPaciente")
    public String registrarGet(Model model) {
        model.addAttribute("pacienteDTO", new PacienteDTO());
        return "registroCliente";
    }

    @PostMapping("/auth/registrarPaciente")
    public String registrarPost(@ModelAttribute PacienteDTO pacienteDTO, Model model) {
        PacienteDTO nuevoPaciente = pacienteServicio.registrar(pacienteDTO);

        if (nuevoPaciente != null && nuevoPaciente.getDniPaciente() != null) {
            model.addAttribute("mensajeRegistroExitoso", "Registro del nuevo paciente OK");
            return "loginPacientes";
        } else {
            if (pacienteDTO.getDniPaciente() == null) {
                model.addAttribute("mensajeErrorDni", "Ya existe un paciente con ese DNI");
                return "registroCliente";
            } else {
                model.addAttribute("mensajeErrorMail", "Ya existe un paciente con ese email");
                return "registroCliente";
            }
        }
    }

}
