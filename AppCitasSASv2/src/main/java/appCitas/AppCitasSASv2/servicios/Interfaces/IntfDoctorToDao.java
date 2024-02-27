package appCitas.AppCitasSASv2.servicios.Interfaces;

import java.util.List;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;

public interface IntfDoctorToDao {

    /**
     * Convierte un objeto DoctoresDTO a un objeto Doctores.
     *
     * @param doctoresDTO Objeto DoctoresDTO que se va a convertir.
     * @return Objeto Doctores correspondiente al DoctoresDTO proporcionado.
     */
    Doctores doctoresToDao(DoctoresDTO doctoresDTO);

    /**
     * Convierte una lista de objetos DoctoresDTO a una lista de objetos Doctores.
     *
     * @param listaDoctoresDTO Lista de objetos DoctoresDTO que se va a convertir.
     * @return Lista de objetos Doctores correspondiente a la lista de DoctoresDTO proporcionada.
     */
    List<Doctores> listDoctoresToDao(List<DoctoresDTO> listaDoctoresDTO);
}
