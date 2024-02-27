package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Citas;

/**
 * Interfaz de repositorio para la entidad Citas.
 */
@Service
public interface CitasRepositorio extends JpaRepository<Citas, Long> {

    /**
     * Encuentra la primera cita por su ID.
     *
     * @param idCita El ID de la cita a buscar.
     * @return La primera cita con el ID especificado, o null si no se encuentra.
     */
    public Citas findFirstByIdCita(long idCita);

    /**
     * Verifica si existe una cita con el ID especificado.
     *
     * @param idCita El ID de la cita a verificar.
     * @return true si existe una cita con el ID especificado, false de lo contrario.
     */
    public boolean existsByIdCita(long idCita);
}
