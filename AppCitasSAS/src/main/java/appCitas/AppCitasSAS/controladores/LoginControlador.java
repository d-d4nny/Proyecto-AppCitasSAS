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
import appCitas.AppCitasSAS.dto.PacienteDTO;
import appCitas.AppCitasSAS.servicios.IntfEmpleadoServicio;
import appCitas.AppCitasSAS.servicios.IntfPacienteServicio;


@Controller
public class LoginControlador {

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

	    @GetMapping("/privada/homeEmpleado")
	    public String loginCorrectoEmpleado(Model model, Authentication authentication) {
	        Empleados empleado = empleadoServicio.buscarPorEmail(authentication.getName());
	        String email = empleado.getEmailEmpleado();
	        model.addAttribute("nombreEmpleado", email);

	        // Verifica los roles específicos y redirige según el rol
	        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN_ADMIN"))) {
	            // Redirige a la página específica para el rol "ROLE_ADMIN_ADMIN"
	            return "redirect:/privada/homeAdminAdmin";
	            
	        } else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
	            // Redirige a la página específica para el rol "ROLE_ADMIN"
	            return "redirect:/privada/homeAdmin";
	        } else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_DOCTOR"))) {
	            // Redirige a la página específica para el rol "ROLE_DOCTOR"
	            return "redirect:/privada/homeDoctor";
	        }

	        // Si no se encuentra un rol específico, redirige a una página por defecto
	        return "redirect:/privada/homeEmpleado";
	    }
	    
    @Autowired
    private IntfPacienteServicio pacienteServicio;

    @GetMapping("/auth/loginPacientes")
    public String login(Model model) {
        model.addAttribute("pacienteDTO", new PacienteDTO());
        return "loginPacientes";
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

    @GetMapping("/privada/homePaciente")
    public String loginCorrecto(Model model, Authentication authentication) {
        Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());
        String email = paciente.getEmailPaciente();
        model.addAttribute("nombrePaciente", email);
        return "homePaciente";
    }
}
