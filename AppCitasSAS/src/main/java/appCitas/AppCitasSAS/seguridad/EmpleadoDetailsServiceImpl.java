package appCitas.AppCitasSAS.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.repositorios.EmpleadosRepositorio;

@Service("empleadoDetailsService")
public class EmpleadoDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmpleadosRepositorio usuarioRepository;
	
	/**
	 * Se debe sobrescribir este método de la interface {@link UserDetailsService}
	 * para que spring se encargue de procesar las solicitudes de autenticación del usuario.
	 * Buscando un usuario por su nombre de usuario y después devolviendo
	 * un objeto de tipo UserDetails para que spring pueda completar la autenticación
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    System.out.printf("\nIntento de inicio de sesión para el empleado: %s\n", username);

		//El nombre de usuario en la aplicación es el identificador
		Empleados user = usuarioRepository.findFirstByEmailEmpleados(username);
		
		//Construir la instancia de UserDetails con los datos del usuario
		UserBuilder builder = null;
		if (user != null) {
	    	System.out.printf("\nEmpleado encontrado en la base de datos: %s\n", user.getEmailEmpleado());

			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getContrasenaEmpleado());
			builder.authorities(user.getRolEmpleado());
		} else {
	    	System.out.println("Empleado no encontrado en la base de datos");
			throw new UsernameNotFoundException("Empleado no encontrado");
		}
		return builder.build();
	}
}
