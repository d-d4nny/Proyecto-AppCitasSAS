package appCitas.AppCitasSASv2.repositorios;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSASv2.dao.Informes;

/**
 * Interfaz de repositorio para la entidad Informes.
 */
public interface InformeRepositorio extends JpaRepository<Informes, Long> {

    /**
     * Encuentra el primer informe por su fecha de informe.
     *
     * @param fchInforme La fecha de informe a buscar.
     * @return El primer informe con la fecha de informe especificada, o null si no se encuentra.
     */
    public Informes findFirstByFchInforme(Calendar fchInforme);

    /**
     * Verifica si existe un informe con el ID especificado.
     *
     * @param idInforme El ID del informe a verificar.
     * @return true si existe un informe con el ID especificado, false de lo contrario.
     */
    public boolean existsByIdInforme(long idInforme);
}
