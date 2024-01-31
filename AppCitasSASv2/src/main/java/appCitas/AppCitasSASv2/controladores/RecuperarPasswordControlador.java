package appCitas.AppCitasSASv2.controladores;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.IntfPacienteServicio;


@Controller
@RequestMapping("/auth")
public class RecuperarPasswordControlador {

	@Autowired
	private IntfPacienteServicio pacienteServicio;
	

	
	@GetMapping("/iniciarRecuperacion")
	public String mostrarVistainiciarRecuperacion(Model model) {
		model.addAttribute("pacienteDTO", new PacienteDTO());
		return "iniciarRecuperacion";
	}

	
	@PostMapping("/iniciarRecuperacion")
	public String procesarInicioRecuperacion(@ModelAttribute PacienteDTO pacienteDTO, Model model) {
		
		boolean envioConExito = pacienteServicio.iniciarResetPassConEmail(pacienteDTO.getEmailPaciente());
		
		if(envioConExito) {
	        model.addAttribute("mensajeExitoMail", "Proceso de recuperacion OK");
	        return "login";
		} else {
	        model.addAttribute("mensajeErrorMail", "Error en el proceso de recuperacion.");
		}
		return "iniciarRecuperacion";
	}
	
	
	@GetMapping("/recuperar")
	public String mostrarVistaRecuperar(@RequestParam(name = "token") String token, Model model) {
		PacienteDTO paciente = pacienteServicio.obtenerUsuarioPorToken(token);
		if(paciente != null) {
			model.addAttribute("pacienteDTO", paciente);
		} else {
	        model.addAttribute("pacienteDTO", new PacienteDTO()); 
	        model.addAttribute("mensajeErrorTokenValidez", "Token no válido o paciente no encontrado");
	        return "iniciarRecuperacion";
		}
        return "recuperar";
	}
	
	
	@PostMapping("/recuperar")
	public String procesarRecuperacionContraseña(@ModelAttribute PacienteDTO pacienteDTO, Model model) {
		
		PacienteDTO pacienteExistente = pacienteServicio.obtenerUsuarioPorToken(pacienteDTO.getToken());
	    
	    if (pacienteExistente == null) {
	    	model.addAttribute("mensajeErrorTokenValidez", "Token no válido");
	        return "iniciarRecuperacion";
	    }
	    if (pacienteExistente.getExpiracionToken().before(Calendar.getInstance())) {
	        model.addAttribute("mensajeErrorTokenExpirado", "El token ha expirado");
	        return "iniciarRecuperacion";
	    }
	    
		boolean modificadaPassword = pacienteServicio.modificarContrasenaConToken(pacienteDTO);
		
		if(modificadaPassword) {
			model.addAttribute("contraseñaModificadaExito", "Contraseña modificada OK");
	        return "login";
		} else {
			model.addAttribute("contraseñaModificadaError", "Error al cambiar de contraseña");
			return "iniciarRecuperacion";
		}	
	}




}
