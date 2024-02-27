package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;

/**
 * Interfaz de repositorio para la entidad ConsultaTurno.
 */
public interface ConsultaTurnoRepositorio extends JpaRepository<ConsultaTurno, Long> {

    /**
     * Encuentra la primera consulta turno por su ID.
     *
     * @param idConsultaTurno El ID de la consulta turno a buscar.
     * @return La primera consulta turno con el ID especificado, o null si no se encuentra.
     */
    public ConsultaTurno findFirstByIdConsultaTurno(long idConsultaTurno);

    /**
     * Verifica si existe una consulta turno con el ID especificado.
     *
     * @param idConsultaTurno El ID de la consulta turno a verificar.
     * @return true si existe una consulta turno con el ID especificado, false de lo contrario.
     */
    public boolean existsByIdConsultaTurno(long idConsultaTurno);
}
