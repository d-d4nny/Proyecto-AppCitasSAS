package appCitas.AppCitasSASv2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import appCitas.AppCitasSASv2.dao.Doctores;

/**
 * Interfaz de repositorio para la entidad Doctores.
 */
public interface DoctorRepositorio extends JpaRepository<Doctores, Long> {

    /**
     * Encuentra el primer doctor por su nombre completo.
     *
     * @param nombreCompletoDoctor El nombre completo del doctor a buscar.
     * @return El primer doctor con el nombre completo especificado, o null si no se encuentra.
     */
    public Doctores findFirstByNombreCompletoDoctor(String nombreCompletoDoctor);

    /**
     * Verifica si existe un doctor con el ID especificado.
     *
     * @param idDoctor El ID del doctor a verificar.
     * @return true si existe un doctor con el ID especificado, false de lo contrario.
     */
    public boolean existsByIdDoctor(long idDoctor);
}
