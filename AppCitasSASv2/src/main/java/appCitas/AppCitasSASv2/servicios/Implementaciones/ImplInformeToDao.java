package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeToDao;

@Service
public class ImplInformeToDao implements IntfInformeToDao {

    /**
     * Convierte un DTO de informe a un objeto DAO de informe.
     *
     * @param informeDTO DTO del informe a convertir.
     * @return Objeto DAO de informe resultante.
     */
    @Override
    public Informes informeToDao(InformeDTO informeDTO) {
        Informes informesDao = new Informes();

        try {
            informesDao.setIdInforme(informeDTO.getIdInforme());
            informesDao.setNombreInforme(informeDTO.getNombreInforme());
            informesDao.setDescInforme(informeDTO.getDescInforme());
            informesDao.setFchInforme(informeDTO.getFchInforme());
            informesDao.setPaciente(informeDTO.getPaciente());

            return informesDao;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR InformesToDaoImpl - informesToDao()] - Error al convertir informes DTO a informes DAO (return null): "
                            + e);
            return null;
        }
    }

    /**
     * Convierte una lista de DTOs de informes a una lista de objetos DAO de informes.
     *
     * @param listaInformeDTO Lista de DTOs de informes a convertir.
     * @return Lista de objetos DAO de informes resultante.
     */
    @Override
    public List<Informes> listInformesToDao(List<InformeDTO> listaInformeDTO) {
        List<Informes> listaInformesDao = new ArrayList<>();

        try {
            for (InformeDTO informesDTO : listaInformeDTO) {
                listaInformesDao.add(informeToDao(informesDTO));
            }
            return listaInformesDao;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR CitasToDaoImpl - listInformesToDao()] - Error al convertir lista de informes DTO a lista de informes DAO (return null): "
                            + e);
        }
        return null;
    }
}
