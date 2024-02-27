package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Paciente;
import appCitas.AppCitasSASv2.dto.PacienteDTO;

public interface IntfPacienteToDao {

    /**
     * Convierte un objeto de tipo PacienteDTO a Paciente.
     *
     * @param pacienteDTO Objeto PacienteDTO a convertir.
     * @return Objeto Paciente resultante de la conversión.
     */
    Paciente pacienteToDao(PacienteDTO pacienteDTO);

    /**
     * Convierte una lista de objetos PacienteDTO a una lista de objetos Paciente.
     *
     * @param listaPacienteDTO Lista de objetos PacienteDTO a convertir.
     * @return Lista de objetos Paciente resultante de la conversión.
     */
    List<Paciente> listPacienteToDao(List<PacienteDTO> listaPacienteDTO);
}
