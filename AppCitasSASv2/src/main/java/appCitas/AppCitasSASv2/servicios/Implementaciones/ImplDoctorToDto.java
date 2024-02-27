package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Doctores;
import appCitas.AppCitasSASv2.dto.DoctoresDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfDoctorToDto;

@Service
public class ImplDoctorToDto implements IntfDoctorToDto {

    /**
     * Convierte un objeto Doctores a un objeto DoctoresDTO.
     * 
     * @param u Objeto Doctores a convertir.
     * @return Objeto DoctoresDTO convertido o null si hay un error.
     */
    @Override
    public DoctoresDTO doctoresToDto(Doctores u) {

        try {
            DoctoresDTO dto = new DoctoresDTO();

            dto.setIdDoctor(u.getIdDoctor());
            dto.setNombreCompletoDoctor(u.getNombreCompletoDoctor());
            dto.setEspecialidadDoctor(u.getEspecialidadDoctor());
            dto.setConsultaTurno(u.getConsultaTurno());

            return dto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR doctorToDtoImpl - doctorToDto()] - Error al convertir doctor DAO a doctorToDto (return null): "
                            + e);
            return null;
        }
    }

    /**
     * Convierte una lista de objetos Doctores a una lista de objetos DoctoresDTO.
     * 
     * @param listaDoctores Lista de Doctores a convertir.
     * @return Lista de DoctoresDTO convertida o null si hay un error.
     */
    @Override
    public List<DoctoresDTO> listDoctoresToDto(List<Doctores> listaDoctores) {
        try {

            List<DoctoresDTO> listaDto = new ArrayList<>();
            for (Doctores u : listaDoctores) {
                listaDto.add(this.doctoresToDto(u));
            }
            return listaDto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR doctorToDtoImpl - listDoctoresToDto()] - Error al convertir lista de doctor DAO a lista de doctorDTO (return null): "
                            + e);
        }
        return null;
    }
}
