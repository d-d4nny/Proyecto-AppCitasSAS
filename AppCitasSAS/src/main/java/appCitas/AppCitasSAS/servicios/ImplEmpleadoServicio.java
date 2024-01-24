package appCitas.AppCitasSAS.servicios;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.dto.EmpleadoDTO;
import appCitas.AppCitasSAS.repositorios.EmpleadosRepositorio;

@Service
public class ImplEmpleadoServicio implements IntfEmpleadoServicio {

	@Autowired
	private EmpleadosRepositorio repositorio;
	
	@Autowired
	private IntfEmpleadoToDao toDao;
	
	@Autowired
	private IntfEmpleadoToDto toDto;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IntfEmailRecuperacion emailServicio;
	
	@Override
	public EmpleadoDTO registrar(EmpleadoDTO empleadoDto) {
		
		try {
			// Comprueba si ya existe un usuario con el email que quiere registrar
			Empleados empleadoDaoByEmail = repositorio.findFirstByEmailEmpleado(empleadoDto.getEmailEmpleado());

			if (empleadoDaoByEmail != null) {
				return null; // Si no es null es que ya está registrado
			}

			// Ahora se comprueba si hay un usuario por el IDENTIFICADOR que quiere registrar
			boolean yaExisteElIdentificador = repositorio.existsByIdentificadorEmpleado(empleadoDto.getIdentificadorEmpleado());

			if (yaExisteElIdentificador) {
				// Si es que ya hay un usuario con ese dni se setea a null para controlar el
				// error en controlador
				empleadoDto.setIdentificadorEmpleado(null);
				return empleadoDto;
			}

			// Si llega a esta línea es que no existe el usuario con el email y el dni a
			// registrar
			empleadoDto.setContrasenaEmpleado(passwordEncoder.encode(empleadoDto.getContrasenaEmpleado()));
			Empleados empleadoDao = toDao.empleadoToDao(empleadoDto);
			empleadoDao.setRolEmpleado("ROLE_DOCTOR");
			repositorio.save(empleadoDao);

			return empleadoDto;
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error ImplEmpleadoServicio - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error ImplEmpleadoServicio - registrar() ]" + e.getMessage());
		}
		return null;
	}
	
	
	private void inicializarUsuarioAdminAdmin() {
		// Comprueba si ya existe un usuario admin
		if (!repositorio.existsByNombreCompletoEmpleado("admin")) {
			// Si no existe, crea un nuevo usuario con rol de administrador
			Empleados admin = new Empleados();
			admin.setNombreCompletoEmpleado("admin");
			admin.setContrasenaEmpleado(passwordEncoder.encode("admin"));
			admin.setIdentificadorEmpleado("-");
			admin.setEmailEmpleado("admin@admin.com");
			admin.setRolEmpleado("ROLE_ADMIN_ADMIN");

			repositorio.save(admin);
		}
	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		inicializarUsuarioAdminAdmin();
	}
	
	
	@Override
	public boolean iniciarResetPassConEmail(String emailEmpleado) {
		try {
			Empleados empleadoExistente = repositorio.findFirstByEmailEmpleado(emailEmpleado);

			if (empleadoExistente != null) {
				// Generar el token y establece la fecha de expiración
				String token = passwordEncoder.encode(RandomStringUtils.random(30));
				Calendar fechaExpiracion = Calendar.getInstance();
				fechaExpiracion.add(Calendar.MINUTE, 10);
				// Actualizar el usuario con el nuevo token y la fecha de expiración
				empleadoExistente.setToken(token);
				empleadoExistente.setExpiracionToken(fechaExpiracion);

				// Actualizar el usuario en la base de datos
				repositorio.save(empleadoExistente);

				// Enviar el correo de recuperación
				String nombreEmpleado = empleadoExistente.getNombreCompletoEmpleado();
				emailServicio.enviarEmailRecuperacion(emailEmpleado, nombreEmpleado, token);

				return true;

			} else {
				System.out.println("[Error ImplEmpleadoServicio - iniciarRecuperacionConEmail()] El Empleado con email "
						+ emailEmpleado + " no existe");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error ImplEmpleadoServicio - registrar() ]" + iae.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("[Error ImplEmpleadoServicio - iniciarRecuperacionConEmail()]" + e.getMessage());
			return false;
		}
	}
	
	
	@Override
	public boolean modificarContrasenaConToken(EmpleadoDTO empleado) {

		Empleados empleadoExistente = repositorio.findByToken(empleado.getToken());

		if (empleadoExistente != null) {
			String nuevaContraseña = passwordEncoder.encode(empleado.getPassword());
			empleadoExistente.setContrasenaEmpleado(nuevaContraseña);
			empleadoExistente.setToken(null); // Se setea a null para invalidar el token ya consumido al cambiar de
												// password
			repositorio.save(empleadoExistente);

			return true;
		}

		return false;
	}
	
	
	@Override
	public EmpleadoDTO obtenerEmpleadoPorToken(String token) {
		Empleados empleadoExistente = repositorio.findByToken(token);

		if (empleadoExistente != null) {
			EmpleadoDTO empleado = toDto.empleadoToDto(empleadoExistente);
			return empleado;
		} else {
			System.out.println("No existe el empleado con el token " + token);
			return null;
		}

	}
	
	
	@Override
	public Empleados buscarPorEmail(String email) {
		return repositorio.findFirstByEmailEmpleado(email);
	}
	
	
	@Override
	public Empleados eliminar(long id) {
		Empleados empleado = repositorio.findById(id).orElse(null);
		if (empleado != null) {
			repositorio.delete(empleado);
		} 
		return empleado;	
	}

	
	@Override
	public boolean buscarPorIdentificador(String identificadorEmpleado) {
		return repositorio.existsByIdentificadorEmpleado(identificadorEmpleado);
	}

	
	@Override
	public Empleados buscarPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}

	
	@Override
	public List<EmpleadoDTO> buscarTodos() {
		return toDto.listEmpleadoToDto(repositorio.findAll());
	}
}
