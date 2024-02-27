package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.ConsultaTurno;
import appCitas.AppCitasSASv2.dto.ConsultaTurnoDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfConsultaTurnoToDto;

@Service
public class ImplConsultaTurnoToDto implements IntfConsultaTurnoToDto {

    /**
     * Convierte un objeto ConsultaTurno a un objeto ConsultaTurnoDTO.
     * 
     * @param u Objeto ConsultaTurno a convertir.
     * @return Objeto ConsultaTurnoDTO convertido o null si hay un error.
     */
    @Override
    public ConsultaTurnoDTO consultaTurnoToDto(ConsultaTurno u) {

        try {
            ConsultaTurnoDTO dto = new ConsultaTurnoDTO();

            dto.setIdConsultaTurno(u.getIdConsultaTurno());
            dto.setTramoHoraTurnoInicio(u.getTramoHoraTurnoInicio());
            dto.setTramoHoraTurnoFin(u.getTramoHoraTurnoFin());
            dto.setNumConsulta(u.getNumConsulta());

            return dto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR ImplConsultaTurnoToDto - consultaTurnoToDto()] - Al convertir consultaTurno a consultaTurnoDTO (return null): "
                            + e);
        }
        return null;
    }

    /**
     * Convierte una lista de objetos ConsultaTurno a una lista de objetos ConsultaTurnoDTO.
     * 
     * @param listaConsultaTurno Lista de ConsultaTurno a convertir.
     * @return Lista de ConsultaTurnoDTO convertida o null si hay un error.
     */
    @Override
    public List<ConsultaTurnoDTO> listConsultaTurnoToDto(List<ConsultaTurno> listaConsultaTurno) {
        try {
            List<ConsultaTurnoDTO> listaDto = new ArrayList<>();
            for (ConsultaTurno u : listaConsultaTurno) {
                listaDto.add(this.consultaTurnoToDto(u));
            }
            return listaDto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR ImplConsultaTurnoToDto - listConsultaTurnoToDto()] - Al convertir lista de consultaTurno a lista de consultaTurnoDTO (return null): "
                            + e);
        }
        return null;
    }
}
