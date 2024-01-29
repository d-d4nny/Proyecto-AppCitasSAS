package appCitas.AppCitasSAS.dto;

import java.util.Objects;

public class LoginEmpleadoPacienteDTO {

	//ATRIBUTOS
	private long idEmpleadoPaciente;
	private String email;
    private String password;
    private String tipo; // Puede ser "empleado" o "paciente"

    
    
    // Getters y setters

    
	public long getIdEmpleadoPaciente() {
		return idEmpleadoPaciente;
	}
	public void setIdEmpleadoPaciente(long idEmpleadoPaciente) {
		this.idEmpleadoPaciente = idEmpleadoPaciente;
	}
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

	
	//CONSTRUCTORES
	
	
	public LoginEmpleadoPacienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public LoginEmpleadoPacienteDTO(long idEmpleadoPaciente, String email, String password, String tipo) {
		super();
		this.idEmpleadoPaciente = idEmpleadoPaciente;
		this.email = email;
		this.password = password;
		this.tipo = tipo;
	}
	
	
	
	//METODOS
	
	
	@Override
	public int hashCode() {
		return Objects.hash(email, idEmpleadoPaciente, password, tipo);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginEmpleadoPacienteDTO other = (LoginEmpleadoPacienteDTO) obj;
		return Objects.equals(email, other.email) && idEmpleadoPaciente == other.idEmpleadoPaciente
				&& Objects.equals(password, other.password) && Objects.equals(tipo, other.tipo);
	}
	
	
	@Override
	public String toString() {
		return "LoginEmpleadoPacienteDTO [idEmpleadoPaciente=" + idEmpleadoPaciente + ", email=" + email + ", password="
				+ password + ", tipo=" + tipo + "]";
	}
}
