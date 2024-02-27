package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;

public interface IntfConsultaTurnoToDto {

    /**
     * Convierte un objeto ConsultaTurno (DAO) a un objeto ConsultaTurnoDTO.
     *
     * @param consultaTurno Objeto ConsultaTurno (DAO) a convertir.
     * @return Objeto ConsultaTurnoDTO resultante de la conversión.
     */
    ConsultaTurnoDTO consultaTurnoToDto(ConsultaTurno consultaTurno);

    /**
     * Convierte una lista de objetos ConsultaTurno (DAO) a una lista de objetos ConsultaTurnoDTO.
     *
     * @param listaConsultaTurno Lista de objetos ConsultaTurno (DAO) a convertir.
     * @return Lista de objetos ConsultaTurnoDTO resultante de la conversión.
     */
    List<ConsultaTurnoDTO> listConsultaTurnoToDto(List<ConsultaTurno> listaConsultaTurno);
}
