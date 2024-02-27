package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;

public interface IntfConsultaTurnoToDao {

    /**
     * Convierte un objeto ConsultaTurnoDTO a un objeto ConsultaTurno (DAO).
     *
     * @param consultaTurnoDTO DTO de la consulta de turno a convertir.
     * @return Objeto ConsultaTurno (DAO) resultante de la conversión.
     */
    ConsultaTurno consultaTurnoToDao(ConsultaTurnoDTO consultaTurnoDTO);

    /**
     * Convierte una lista de objetos ConsultaTurnoDTO a una lista de objetos ConsultaTurno (DAO).
     *
     * @param listaConsultaTurnoDTO Lista de DTOs de consultas de turno a convertir.
     * @return Lista de objetos ConsultaTurno (DAO) resultante de la conversión.
     */
    List<ConsultaTurno> listConsultaTurnoToDao(List<ConsultaTurnoDTO> listaConsultaTurnoDTO);
}
