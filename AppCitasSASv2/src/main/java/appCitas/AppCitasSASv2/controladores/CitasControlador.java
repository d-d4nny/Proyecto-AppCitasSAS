package appCitas.AppCitasSASv2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CitasControlador {

	
	@Autowired
	private IntfCitasServicio citasServicio;
	
	@Autowired
	private IntfDoctorServicio doctoresServicio;
	
	@Autowired
	private IntfPacienteServicio pacienteServicio;
	
	
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
	     citaDTO.setEstadoCita("Pendiente");
	
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
	
	

 	@GetMapping("/privada/eliminar-cita/{id}")
	public String eliminarCita(@PathVariable Long id, Model model, HttpServletRequest request) {
		citasServicio.eliminar(id);
	    return "redirect:/privada/Pacientes";	    
	}
 	
 	
 	@GetMapping("/privada/cancelar-cita/{id}")
	public String cancelarCita(@PathVariable Long id, Model model, HttpServletRequest request) {
 		citasServicio.cancelarCita(id);
 		return "redirect:/privada/Administracion";
	}
 	
 	@GetMapping("/privada/completar-cita/{id}")
	public String completarCita(@PathVariable Long id, Model model, HttpServletRequest request) {
 		citasServicio.completarCita(id);
 		return "redirect:/privada/Administracion";
	}
}
