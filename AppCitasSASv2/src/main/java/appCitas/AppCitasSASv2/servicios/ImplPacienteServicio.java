package appCitas.AppCitasSASv2.servicios;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;
import appCitas.AppCitasSASv2.repositorios.PacienteRepositorio;
import jakarta.persistence.PersistenceException;

@Service
public class ImplPacienteServicio implements IntfPacienteServicio {

	@Autowired
	private PacienteRepositorio repositorio;

	@Autowired
	private IntfPacienteToDao toDao;

	@Autowired
	private IntfPacienteToDto toDto;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IntfEmailRecuperacion emailServicio;

	@Override
	public PacienteDTO registrar(PacienteDTO pacienteDto) {

		try {
			// Comprueba si ya existe un usuario con el email que quiere registrar
			Paciente pacienteDaoByEmail = repositorio.findFirstByEmailPaciente(pacienteDto.getEmailPaciente());

			if (pacienteDaoByEmail != null) {
				return null; // Si no es null es que ya está registrado
			}
  
			// Ahora se comprueba si hay un usuario por el DNI que quiere registrar
			boolean yaExisteElDNI = repositorio.existsByDniPaciente(pacienteDto.getDniPaciente());

			if (yaExisteElDNI) {
				// Si es que ya hay un usuario con ese dni se setea a null para controlar el
				// error en controlador
				pacienteDto.setDniPaciente(null);
				return pacienteDto;
			}

			// Si llega a esta línea es que no existe el usuario con el email y el dni a
			// registrar
			pacienteDto.setContrasenaPaciente(passwordEncoder.encode(pacienteDto.getContrasenaPaciente()));
			Paciente pacienteDao = toDao.pacienteToDao(pacienteDto);
			pacienteDao.setRolPaciente("ROLE_USER");
			repositorio.save(pacienteDao);

			return pacienteDto;
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error ImplPacienteServicio - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error ImplPacienteServicio - registrar() ]" + e.getMessage());
		}
		return null;
	}
	
	private void inicializarUsuarioAdmin() {
		// Comprueba si ya existe un usuario admin
		if (!repositorio.existsByNombreCompletoPaciente("admin")) {
			// Si no existe, crea un nuevo usuario con rol de administrador
			Paciente admin = new Paciente();
			admin.setNombreCompletoPaciente("admin");
			admin.setContrasenaPaciente(passwordEncoder.encode("admin"));
			admin.setDniPaciente("-");
			admin.setEmailPaciente("admin@admin.com");
			admin.setRolPaciente("ROLE_ADMIN");

			repositorio.save(admin);
		}
	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		inicializarUsuarioAdmin();
	}
	
	
	@Override
	public boolean iniciarResetPassConEmail(String emailPaciente) {
		try {
			Paciente pacienteExistente = repositorio.findFirstByEmailPaciente(emailPaciente);

			if (pacienteExistente != null) {
				// Generar el token y establece la fecha de expiración
				String token = passwordEncoder.encode(RandomStringUtils.random(30));
				Calendar fechaExpiracion = Calendar.getInstance();
				fechaExpiracion.add(Calendar.MINUTE, 10);
				// Actualizar el usuario con el nuevo token y la fecha de expiración
				pacienteExistente.setToken(token);
				pacienteExistente.setExpiracionToken(fechaExpiracion);

				// Actualizar el usuario en la base de datos
				repositorio.save(pacienteExistente);

				// Enviar el correo de recuperación
				String nombrePaciente = pacienteExistente.getNombreCompletoPaciente();
				emailServicio.enviarEmailRecuperacion(emailPaciente, nombrePaciente, token);

				return true;

			} else {
				System.out.println("[Error ImplPacienteServicio - iniciarRecuperacionConEmail()] El paciente con email "
						+ emailPaciente + " no existe");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error ImplPacienteServicio - registrar() ]" + iae.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("[Error ImplPacienteServicio - iniciarRecuperacionConEmail()]" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean modificarContrasenaConToken(PacienteDTO paciente) {

		Paciente usuarioExistente = repositorio.findByToken(paciente.getToken());

		if (usuarioExistente != null) {
			String nuevaContraseña = passwordEncoder.encode(paciente.getPassword());
			usuarioExistente.setContrasenaPaciente(nuevaContraseña);
			usuarioExistente.setToken(null); // Se setea a null para invalidar el token ya consumido al cambiar de
												// password
			repositorio.save(usuarioExistente);

			return true;
		}

		return false;
	}
	
	
	@Override
	public void actualizarPaciente(PacienteDTO pacienteModificado) {

		try {
			Paciente pacienteActual = repositorio.findById(pacienteModificado.getIdPaciente()).orElse(null);

			pacienteActual.setNombreCompletoPaciente(pacienteModificado.getNombreCompletoPaciente());
			pacienteActual.setTlfPaciente(pacienteModificado.getTlfPaciente());

			repositorio.save(pacienteActual);
		} catch (PersistenceException pe) {
			System.out.println("[Error UsuarioServicioImpl - actualizarUsuario()] Al modificar el usuario " + pe.getMessage());
			
		}
		
	}
	

	@Override
	public PacienteDTO obtenerUsuarioPorToken(String token) {
		Paciente pacienteExistente = repositorio.findByToken(token);

		if (pacienteExistente != null) {
			PacienteDTO paciente = toDto.pacienteToDto(pacienteExistente);
			return paciente;
		} else {
			System.out.println("No existe el paciente con el token " + token);
			return null;
		}

	}

	@Override
	public Paciente buscarPorEmail(String email) {
		return repositorio.findFirstByEmailPaciente(email);
	}
	
	@Override
	public Paciente eliminar(long id) {
		Paciente paciente = repositorio.findById(id).orElse(null);
		if (paciente != null) {
			repositorio.delete(paciente);
		} 
		return paciente;
		
	}

	@Override
	public boolean buscarPorDni(String dni) {
		return repositorio.existsByDniPaciente(dni);
	}

	@Override
	public PacienteDTO buscarPorId(long id) {
		try {
			Paciente paciente = repositorio.findById(id).orElse(null);
			if (paciente != null) {
				return toDto.pacienteToDto(paciente);
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error UsuarioServicioImpl - buscarPorId()] Al buscar el usuario por su id " + iae.getMessage());
		}
		return null;
	}
	
	

	@Override
	public List<PacienteDTO> buscarTodos() {
		return toDto.listPacienteToDto(repositorio.findAll());
	}
}
