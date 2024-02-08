package appCitas.AppCitasSASv2.dto;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ConsultaTurnoDTO {
	
	
	
	// ATRIBUTOS
	
	
	private long idConsultaTurno;
	private int numConsulta;
	private Calendar tramoHoraTurno;
	private List<DoctoresDTO> doctoresConConsultaTurno;
	
	
	
	// GETTER / SETTER
	
	
	public long getIdConsultaTurno() {
		return idConsultaTurno;
	}
	public void setIdConsultaTurno(long idConsultaTurno) {
		this.idConsultaTurno = idConsultaTurno;
	}
	public int getNumConsulta() {
		return numConsulta;
	}
	public void setNumConsulta(int numConsulta) {
		this.numConsulta = numConsulta;
	}
	public Calendar getTramoHoraTurno() {
		return tramoHoraTurno;
	}
	public void setTramoHoraTurno(Calendar tramoHoraTurno) {
		this.tramoHoraTurno = tramoHoraTurno;
	}
	public List<DoctoresDTO> getDoctoresConConsultaTurno() {
		return doctoresConConsultaTurno;
	}
	public void setDoctoresConConsultaTurno(List<DoctoresDTO> doctoresConConsultaTurno) {
		this.doctoresConConsultaTurno = doctoresConConsultaTurno;
	}
	
	
	
	// METODOS
	
	
	public ConsultaTurnoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConsultaTurnoDTO(int numConsulta, Calendar tramoHoraTurno) {
		super();
		this.numConsulta = numConsulta;
		this.tramoHoraTurno = tramoHoraTurno;
	}
	public ConsultaTurnoDTO(long idConsultaTurno, int numConsulta, Calendar tramoHoraTurno,
			List<DoctoresDTO> doctoresConConsultaTurno) {
		super();
		this.idConsultaTurno = idConsultaTurno;
		this.numConsulta = numConsulta;
		this.tramoHoraTurno = tramoHoraTurno;
		this.doctoresConConsultaTurno = doctoresConConsultaTurno;
	}
	
	
	
	//CONSTRUCTORES

	
	@Override
	public int hashCode() {
		return Objects.hash(doctoresConConsultaTurno, idConsultaTurno, numConsulta, tramoHoraTurno);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaTurnoDTO other = (ConsultaTurnoDTO) obj;
		return Objects.equals(doctoresConConsultaTurno, other.doctoresConConsultaTurno)
				&& idConsultaTurno == other.idConsultaTurno && numConsulta == other.numConsulta
				&& Objects.equals(tramoHoraTurno, other.tramoHoraTurno);
	}
	@Override
	public String toString() {
		return "ConsultaTurnoDTO [idConsultaTurno=" + idConsultaTurno + ", numConsulta=" + numConsulta
				+ ", tramoHoraTurno=" + tramoHoraTurno + ", doctoresConConsultaTurno=" + doctoresConConsultaTurno + "]";
	}
}

