package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.repositorios.InformeRepositorio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeServicio;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeToDao;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeToDto;
import jakarta.persistence.PersistenceException;

@Service
public class ImplInformeServicio implements IntfInformeServicio {

    @Autowired
    private IntfInformeToDao toDao;

    @Autowired
    private IntfInformeToDto toDto;

    @Autowired
    private InformeRepositorio repositorio;

    /**
     * Crea un nuevo informe.
     * 
     * @param informeDTO DTO del informe a ser creado.
     * @return Verdadero si el informe se creó correctamente, falso si hay algún error.
     */
    @Override
    public Boolean crearInforme(InformeDTO informeDTO) {
        try {
            repositorio.save(toDao.informeToDao(informeDTO));
            return true;
        } catch (PersistenceException e) {
            System.out.println("\n[ERROR InformeServicioImpl - crearInforme()] - Al registrar nuevo informe: " + e);
            return false;
        }
    }

    /**
     * Elimina un informe por su ID.
     * 
     * @param id ID del informe a eliminar.
     * @return El informe eliminado, o nulo si no se encuentra.
     */
    @Override
    public Informes eliminar(long id) {
        Informes informe = repositorio.findById(id).orElse(null);
        if (informe != null) {
            repositorio.delete(informe);
        }
        return informe;
    }

    /**
     * Busca un informe por su ID.
     * 
     * @param id ID del informe a buscar.
     * @return El informe encontrado, o nulo si no se encuentra.
     */
    @Override
    public Informes buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * Busca todos los informes.
     * 
     * @return Lista de DTOs de informes encontrados.
     */
    @Override
    public List<InformeDTO> buscarTodos() {
        return toDto.listInformesToDto(repositorio.findAll());
    }
}
