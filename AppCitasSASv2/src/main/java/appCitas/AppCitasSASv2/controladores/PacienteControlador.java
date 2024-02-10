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

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.CitasDTO;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfCitasServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PacienteControlador {

	
	@Autowired
	private IntfDoctorServicio doctoresServicio;
	
	@Autowired
	private IntfConsultaTurnoServicio consultaTurnoServicio;
	
	@Autowired
	private IntfCitasServicio citasServicio;
	
	@Autowired
	private IntfInformeServicio informeServicio;
	    
	@Autowired
	private IntfPacienteServicio pacienteServicio;
	
	
	
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
	
	
	 @GetMapping("/privada/Pacientes")
	 public String homeUser(Model model, Authentication authentication) {
		 List<CitasDTO> citas = citasServicio.buscarTodos();
		 List<InformeDTO> informes = informeServicio.buscarTodos(); 
		 //Informes informe = informeServicio.buscarPorId(informes.get(0).getIdInforme());
		 Paciente paciente = pacienteServicio.buscarPorEmail(authentication.getName());
		 PacienteDTO pacienteDTO = pacienteServicio.buscarPorId(paciente.getIdPaciente());
		 System.out.println(citas);
		 System.out.println(informes);
		 //model.addAttribute("informe", informe);
		 model.addAttribute("citas", pacienteServicio.buscarPorEmail(authentication.getName()).getCitasDePaciente());
		 model.addAttribute("informes", pacienteServicio.buscarPorEmail(authentication.getName()).getInformesDePaciente());
		 model.addAttribute("pacienteDTO", pacienteDTO);
		 model.addAttribute("paciente", paciente);
		 
	     return "homePaciente";
	 }
	 
	 @GetMapping("/privada/Administracion")
	 public String homeEmpleado(Model model, Authentication authentication) {
		 List<CitasDTO> citas = citasServicio.buscarTodos();
		 List<DoctoresDTO> doctores = doctoresServicio.buscarTodos();
		 List<PacienteDTO> pacientes = pacienteServicio.buscarTodos();
		 List<ConsultaTurnoDTO> consultaTurnos = consultaTurnoServicio.buscarTodos();
		 model.addAttribute("citas", citas);
		 model.addAttribute("doctores", doctores);
		 model.addAttribute("pacientes", pacientes);
		 model.addAttribute("consultaTurnos", consultaTurnos);
		 
	     return "homeEmpleado";
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
		public String procesarFormularioEdicion(@ModelAttribute("usuarioDTO") PacienteDTO pacienteDTO, Authentication authentication, Model model,
				@RequestParam("file") MultipartFile imagen) {
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
		            // Si no se selecciona una nueva imagen, asegúrate de no sobrescribir el valor existente
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
	 
	@GetMapping("/privada/eliminar/{id}")
	public String eliminarPaciente(@PathVariable Long id, Model model, HttpServletRequest request) {
		PacienteDTO paciente = pacienteServicio.buscarPorId(id);
		List<PacienteDTO> pacientes = pacienteServicio.buscarTodos();
		if(request.isUserInRole("ROLE_ADMIN") && paciente.getRolPaciente().equals("ROLE_ADMIN")) {
			model.addAttribute("noSePuedeEliminar", "No se puede eliminar a un admin");
			model.addAttribute("usuarios", pacientes);
			return "redirect:/privada/Administracion";
		}
		pacienteServicio.eliminar(id);
	    return "redirect:/privada/Administracion";
		
	}
}
