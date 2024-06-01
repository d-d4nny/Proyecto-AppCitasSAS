package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;

public interface IntfConsultaTurnoServicio {

    /**
     * Registra una nueva consulta de turno.
     *
     * @param consultaTurnoDTO DTO de la consulta de turno a registrar.
     * @return DTO de la consulta de turno registrada.
     */
    ConsultaTurnoDTO registrar(ConsultaTurnoDTO consultaTurnoDTO);

    /**
     * Actualiza la información de una consulta de turno.
     *
     * @param consultaTurnoDTO DTO de la consulta de turno a actualizar.
     */
    void actualizarConsultaTurno(ConsultaTurnoDTO consultaTurnoDTO);

    /**
     * Busca una consulta de turno por su ID.
     *
     * @param id ID de la consulta de turno a buscar.
     * @return Objeto ConsultaTurno encontrado, o null si no se encuentra.
     */
    ConsultaTurno buscarPorId(long id);

    /**
     * Obtiene todas las consultas de turnos registradas.
     *
     * @return Lista de DTOs de consultas de turno.
     */
    List<ConsultaTurnoDTO> buscarTodos();

    /**
     * Elimina una consulta de turno por su ID.
     *
     * @param id ID de la consulta de turno a eliminar.
     * @return Objeto ConsultaTurno eliminado, o null si no se encuentra.
     */
    ConsultaTurno eliminar(long id);
    
    /**
     * Resetea el doctor asignado a todas las instancias de ConsultaTurno.
     */
    void resetearDoctor();
}
