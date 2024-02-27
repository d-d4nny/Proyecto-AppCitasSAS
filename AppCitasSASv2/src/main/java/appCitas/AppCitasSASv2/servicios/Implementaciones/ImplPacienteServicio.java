package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.Base64;
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
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfEmailRecuperacion;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteToDao;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfPacienteToDto;
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

	/**
     * Registra a un nuevo paciente en el sistema.
    *
    * @param pacienteDto DTO del paciente a registrar.
    * @return DTO del paciente registrado o null en caso de errores.
    */
   @Override
   public PacienteDTO registrar(PacienteDTO pacienteDto) {

		try {
			// Comprueba si ya existe un usuario con el email que quiere registrar
			Paciente pacienteDaoByEmail = repositorio.findFirstByEmailPaciente(pacienteDto.getEmailPaciente());

			if (pacienteDaoByEmail != null && pacienteDaoByEmail.isCuentaConfirmada()) {
				System.out.println("Usuario ya registrado y confirmado");
				return pacienteDto;
			}
			if (pacienteDaoByEmail != null ) { // El email se encuentra registrado sin confirmar
				return null;
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
			pacienteDao.setRolPaciente(pacienteDto.getRolPaciente());
			repositorio.save(pacienteDao);
			
			if (pacienteDto.isCuentaConfirmada()) {
				pacienteDao.setCuentaConfirmada(true);
				repositorio.save(pacienteDao);
			} else {
				pacienteDao.setCuentaConfirmada(false);
				// Generar token de confirmación
				String token = passwordEncoder.encode(RandomStringUtils.random(30));
				pacienteDao.setToken(token);

				// Guardar el usuario en la base de datos
				repositorio.save(pacienteDao);

				// Enviar el correo de confirmación
				String nombreUsuario = pacienteDao.getNombreCompletoPaciente();
				emailServicio.enviarEmailConfirmacion(pacienteDto.getEmailPaciente(), nombreUsuario, token);
			}

			return pacienteDto;
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error ImplPacienteServicio - registrar() ]" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("[Error ImplPacienteServicio - registrar() ]" + e.getMessage());
		}
		return null;
	}
	
   /**
    * Inicializa un usuario administrador si no existe uno.
    */
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
			admin.setCuentaConfirmada(true);

			repositorio.save(admin);
		}
	}
	
	
   /**
    * Manejador de eventos que se ejecuta cuando la aplicación está lista.
    */
   @EventListener(ApplicationReadyEvent.class)
   public void onApplicationReady() {
		inicializarUsuarioAdmin();
	}
	
	
   /**
    * Inicia el proceso de restablecimiento de contraseña a través del correo electrónico.
    *
    * @param emailPaciente Correo electrónico del paciente.
    * @return true si la operación es exitosa, false si hay algún error.
    */
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

   /**
    * Modifica la contraseña de un paciente utilizando un token.
    *
    * @param paciente DTO del paciente con la nueva contraseña y el token.
    * @return true si la operación es exitosa, false si hay algún error.
    */
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
	
	
   /**
    * Actualiza la información del paciente en la base de datos.
    *
    * @param pacienteModificado DTO del paciente con la información actualizada.
    */
   @Override
	public void actualizarPaciente(PacienteDTO pacienteModificado) {

		try {
			Paciente pacienteActual = repositorio.findById(pacienteModificado.getIdPaciente()).orElse(null);

			pacienteActual.setNombreCompletoPaciente(pacienteModificado.getNombreCompletoPaciente());
			pacienteActual.setTlfPaciente(pacienteModificado.getTlfPaciente());
			pacienteActual.setDniPaciente(pacienteModificado.getDniPaciente());
			pacienteActual.setDireccionPaciente(pacienteModificado.getDireccionPaciente());
			pacienteActual.setProfilePicture(convertToByteArray(pacienteModificado.getProfilePicture()));
			repositorio.save(pacienteActual);
		} catch (PersistenceException pe) {
			System.out.println(
					"[Error UsuarioServicioImpl - actualizarUsuario()] Al modificar el usuario " + pe.getMessage());

		}

	}
	
	
	
   /**
    * Confirma la cuenta de un paciente utilizando un token.
    *
    * @param token Token de confirmación.
    * @return true si la operación es exitosa, false si hay algún error.
    */
   @Override
	public boolean confirmarCuenta(String token) {
		try {
			Paciente pacienteExistente = repositorio.findByToken(token);

			if (pacienteExistente != null && !pacienteExistente.isCuentaConfirmada()) {
				// Entra en esta condición si el usuario existe y su cuenta no se ha confirmado
				pacienteExistente.setCuentaConfirmada(true);
				pacienteExistente.setToken(null);
				repositorio.save(pacienteExistente);

				return true;
			} else {
				System.out.println("La cuenta no existe o ya está confirmada");
				return false;
			}
		} catch (IllegalArgumentException iae) {
			System.out.println("[Error PacienteServicioImpl - confirmarCuenta()] Error al confirmar la cuenta " + iae.getMessage());
			return false;
		} catch (PersistenceException e) {
			System.out.println("[Error PacienteServicioImpl - confirmarCuenta()] Error de persistencia al confirmar la cuenta" + e.getMessage());
			return false;
		}
	}

   /**
    * Verifica si la cuenta de un paciente está confirmada.
    *
    * @param emailPaciente Correo electrónico del paciente.
    * @return true si la cuenta está confirmada, false si no lo está o hay algún error.
    */
   @Override
	public boolean estaLaCuentaConfirmada(String emailPaciente) {
		try {
			Paciente pacienteExistente = repositorio.findFirstByEmailPaciente(emailPaciente);
			if (pacienteExistente != null && pacienteExistente.isCuentaConfirmada()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("[Error PacienteServicioImpl - estaLaCuentaConfirmada()] Error al comprobar si la cuenta ya ha sido confirmada" + e.getMessage());
		}	
		return false;
	}
	
	
	
   /**
    * Convierte un array de bytes a una cadena Base64.
    *
    * @param data Array de bytes a convertir.
    * @return Cadena Base64 resultante.
    */
	public   String convertToBase64(byte[] data) {
        if (data != null && data.length > 0) {
            return Base64.getEncoder().encodeToString(data);
        }
        return null;
    }
	

	/**
     * Convierte un array de bytes a una cadena Base64.
     *
     * @param data Array de bytes a convertir.
     * @return Cadena Base64 resultante.
     */
    public byte[] convertToByteArray(String base64String) {
        if (base64String != null && !base64String.isEmpty()) {
            return Base64.getDecoder().decode(base64String);
        }
        return null;
    }
	

    /**
     * Obtiene la información de un paciente a través de su token.
     *
     * @param token Token del paciente.
     * @return DTO del paciente o null si no se encuentra.
     */
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

    /**
     * Busca un paciente por su correo electrónico.
     *
     * @param email Correo electrónico del paciente.
     * @return Objeto Paciente o null si no se encuentra.
     */
    @Override
	public Paciente buscarPorEmail(String email) {
		return repositorio.findFirstByEmailPaciente(email);
	}
	
    /**
     * Elimina un paciente por su ID.
     *
     * @param id ID del paciente a eliminar.
     * @return Objeto Paciente eliminado o null si no se encuentra.
     */
    @Override
	public Paciente eliminar(long id) {
		Paciente paciente = repositorio.findById(id).orElse(null);
		if (paciente != null) {
			repositorio.delete(paciente);
		} 
		return paciente;
		
	}

    /**
     * Verifica si ya existe un paciente con un determinado DNI.
     *
     * @param dni DNI a verificar.
     * @return true si existe un paciente con el DNI proporcionado, false de lo contrario.
     */
    @Override
	public boolean buscarPorDni(String dni) {
		return repositorio.existsByDniPaciente(dni);
	}

    /**
     * Busca un paciente por su ID y devuelve su información en formato DTO.
     *
     * @param id ID del paciente a buscar.
     * @return DTO del paciente encontrado o null si no se encuentra.
     */
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
	
	

    /**
     * Obtiene la lista de todos los pacientes en formato DTO.
     *
     * @return Lista de DTOs de pacientes.
     */
    @Override
	public List<PacienteDTO> buscarTodos() {
		return toDto.listPacienteToDto(repositorio.findAll());
	}
}
