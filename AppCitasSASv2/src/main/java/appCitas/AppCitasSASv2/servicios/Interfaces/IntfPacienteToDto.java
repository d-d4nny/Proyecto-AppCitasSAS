package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

public interface IntfPacienteToDto {

    /**
     * Convierte un objeto de tipo Paciente a PacienteDTO.
     *
     * @param paciente Objeto Paciente a convertir.
     * @return Objeto PacienteDTO resultante de la conversión.
     */
    PacienteDTO pacienteToDto(Paciente paciente);

    /**
     * Convierte una lista de objetos Paciente a una lista de objetos PacienteDTO.
     *
     * @param listaPaciente Lista de objetos Paciente a convertir.
     * @return Lista de objetos PacienteDTO resultante de la conversión.
     */
    List<PacienteDTO> listPacienteToDto(List<Paciente> listaPaciente);
}
