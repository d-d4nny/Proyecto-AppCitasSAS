package appCitas.AppCitasSAS.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSAS.dao.Paciente;
import appCitas.AppCitasSAS.repositorios.PacienteRepositorio;


/**
 * Para que spring gestione la autentiación y la autorización de la aplicación,
 * es necesario una clase en el proyecto que implemente la interfaz UserDetailsService y
 * sobre escribir el método loadUserByUsername de la misma.
 */
@Service("pacienteDetailsService")
public class PacienteDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PacienteRepositorio usuarioRepository;
	
	/**
	 * Se debe sobrescribir este método de la interface {@link UserDetailsService}
	 * para que spring se encargue de procesar las solicitudes de autenticación del usuario.
	 * Buscando un usuario por su nombre de usuario y después devolviendo
	 * un objeto de tipo UserDetails para que spring pueda completar la autenticación
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    System.out.printf("\nIntento de inicio de sesión para el paciente: %s\n", username);

		//El nombre de usuario en la aplicación es el email
		Paciente user = usuarioRepository.findFirstByEmailPaciente(username);
		
		//Construir la instancia de UserDetails con los datos del usuario
		UserBuilder builder = null;
		if (user != null) {
	    	System.out.printf("\nPaciente encontrado en la base de datos: %s\n", user.getEmailPaciente());

			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getContrasenaPaciente());
			builder.authorities(user.getRolPaciente());
		} else {
	    	System.out.println("Paciente no encontrado en la base de datos");
			throw new UsernameNotFoundException("Paciente no encontrado");
		}
		return builder.build();
	}

}
