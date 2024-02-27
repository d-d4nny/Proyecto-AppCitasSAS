package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.repositorios.DoctorRepositorio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorToDao;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorToDto;
import jakarta.persistence.PersistenceException;

@Service
public class ImplDoctorServicio implements IntfDoctorServicio {

    @Autowired
    private IntfDoctorToDao toDao;

    @Autowired
    private IntfDoctorToDto toDto;

    @Autowired
    private DoctorRepositorio repositorio;

    /**
     * Registra un nuevo doctor.
     * 
     * @param doctoresDto Datos del doctor a registrar.
     * @return DoctoresDTO objeto con los datos del doctor registrado.
     */
    @Override
    public DoctoresDTO registrar(DoctoresDTO doctoresDto) {
        try {
            Doctores doctorDaoByNombreCompleto = repositorio.findFirstByNombreCompletoDoctor(doctoresDto.getNombreCompletoDoctor());

            if (doctorDaoByNombreCompleto != null) {
                return null; // Si no es null es que ya está registrado
            }

            boolean yaExisteElId = repositorio.existsById(doctoresDto.getIdDoctor());

            if (yaExisteElId) {
                return doctoresDto;
            }

            Doctores doctorDao = toDao.doctoresToDao(doctoresDto);
            repositorio.save(doctorDao);

            return doctoresDto;

        } catch (IllegalArgumentException iae) {
            System.out.println("[Error ImplDoctorServicio - registrar() ]" + iae.getMessage());
        } catch (Exception e) {
            System.out.println("[Error ImplDoctorServicio - registrar() ]" + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza los datos de un doctor.
     * 
     * @param doctorModificado Datos actualizados del doctor.
     */
    @Override
    public void actualizarDoctor(DoctoresDTO doctorModificado) {

        try {
            Doctores doctorActual = repositorio.findById(doctorModificado.getIdDoctor()).orElse(null);

            doctorActual.setNombreCompletoDoctor(doctorModificado.getNombreCompletoDoctor());
            doctorActual.setEspecialidadDoctor(doctorModificado.getEspecialidadDoctor());
            doctorActual.setConsultaTurno(doctorModificado.getConsultaTurno());

            repositorio.save(doctorActual);
        } catch (PersistenceException pe) {
            System.out.println(
                    "[Error DoctorServicioImpl - actualizarDoctor()] Al modificar al doctor " + pe.getMessage());
        }
    }

    /**
     * Elimina un doctor por su ID.
     * 
     * @param id ID del doctor a eliminar.
     * @return Doctores objeto eliminado o null si no se encontró.
     */
    @Override
    public Doctores eliminar(long id) {
        Doctores doctor = repositorio.findById(id).orElse(null);
        if (doctor != null) {
            repositorio.delete(doctor);
        }
        return doctor;
    }

    /**
     * Busca un doctor por su ID.
     * 
     * @param id ID del doctor a buscar.
     * @return Doctores objeto encontrado o null si no se encontró.
     */
    @Override
    public Doctores buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * Obtiene la lista de todos los doctores.
     * 
     * @return Lista de DoctoresDTO que representan a todos los doctores.
     */
    @Override
    public List<DoctoresDTO> buscarTodos() {
        return toDto.listDoctoresToDto(repositorio.findAll());
    }
}
