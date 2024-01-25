package appCitas.AppCitasSAS.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSAS.dao.Empleados;
import appCitas.AppCitasSAS.repositorios.EmpleadosRepositorio;

@Service("empleadoDetailsService")
public class EmpleadoDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmpleadosRepositorio empleadoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.printf("\nIntento de inicio de sesión para el empleado: %s\n", username);

		Empleados empleado = empleadoRepository.findFirstByEmailEmpleado(username);

		UserBuilder builder = null;

		if (empleado != null) {
			System.out.printf("\nEmpleado encontrado en la base de datos: %s\n", empleado.getEmailEmpleado());

			// Se asigna el rol directamente
			String rol = empleado.getRolEmpleado();

			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(empleado.getContrasenaEmpleado());
			builder.authorities(rol);
		} else {
			System.out.println("Empleado no encontrado en la base de datos");
			throw new UsernameNotFoundException("Empleado no encontrado");
		}

		return builder.build();
	}
}