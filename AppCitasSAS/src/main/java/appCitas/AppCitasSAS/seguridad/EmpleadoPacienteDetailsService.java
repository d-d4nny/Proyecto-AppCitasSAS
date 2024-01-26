package appCitas.AppCitasSAS.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSAS.dto.LoginEmpleadoPacienteDTO;
import appCitas.AppCitasSAS.repositorios.EmpleadoPacienteRepositorio;

@Service("empleadoPacienteDetailsService")
public class EmpleadoPacienteDetailsService implements UserDetailsService {
	
	@Autowired
	private EmpleadoPacienteRepositorio empleadoPacienteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.printf("\nIntento de inicio de sesión para el Usuario: %s\n", username);

		LoginEmpleadoPacienteDTO empleadoPaciente = empleadoPacienteRepository.findFirstByEmailEmpleadoPaciente(username);

		UserBuilder builder = null;

		if (empleadoPaciente != null) {
			System.out.printf("\nUsuario encontrado en la base de datos: %s\n", empleadoPaciente.getEmail());


			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(empleadoPaciente.getPassword());
		} else {
			System.out.println("Usuario no encontrado en la base de datos");
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

		return builder.build();
	}
}
