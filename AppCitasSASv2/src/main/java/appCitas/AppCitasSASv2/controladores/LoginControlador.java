package appCitas.AppCitasSASv2.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteServicio;


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
	 
	 
 	@GetMapping("/privada/home")
	public String loginCorrecto(Model model, Authentication authentication) {
 		
 		try {
            boolean cuentaConfirmada = pacienteServicio.estaLaCuentaConfirmada(authentication.getName());
            
            if (cuentaConfirmada) {
            	Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());
            	String email = paciente.getEmailPaciente();
            	model.addAttribute("paciente", email);
            	System.out.println(authentication.getAuthorities());
            	if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
        	        return "redirect:/privada/Administracion";
        	    } else {
        	        return "redirect:/privada/Pacientes";
        	    }
            } else {
                model.addAttribute("cuentaNoVerificada", "Error al confirmar su email");
                return "login";
            }
 		} catch (Exception e) {
            model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "login";
        }
	}
 	
 	 @GetMapping("/auth/confirmar-cuenta")
     public String confirmarCuenta(Model model, @RequestParam("token") String token) {
         try {
             boolean confirmacionExitosa = pacienteServicio.confirmarCuenta(token);

             if (confirmacionExitosa) {
                 model.addAttribute("cuentaVerificada", "Su dirección de correo ha sido confirmada correctamente");
             } else {
                 model.addAttribute("cuentaNoVerificada", "Error al confirmar su email");
             }

             return "login";
         } catch (Exception e) {
             model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
             return "login";
         }
     }
}
