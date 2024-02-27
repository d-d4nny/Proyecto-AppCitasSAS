package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeToDto;

@Service
public class ImplInformeToDto implements IntfInformeToDto {

    /**
     * Convierte un objeto DAO de informe a un DTO de informe.
     *
     * @param informe Objeto DAO de informe a convertir.
     * @return DTO de informe resultante.
     */
    @Override
    public InformeDTO informeToDto(Informes informe) {
        try {
            InformeDTO dto = new InformeDTO();

            dto.setIdInforme(informe.getIdInforme());
            dto.setNombreInforme(informe.getNombreInforme());
            dto.setDescInforme(informe.getDescInforme());
            dto.setFchInforme(informe.getFchInforme());

            return dto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR InformeToDtoImpl - informeToDto()] - Error al convertir informe DAO a informeToDto (return null): "
                            + e);
        }
        return null;
    }

    /**
     * Convierte una lista de objetos DAO de informes a una lista de DTOs de informes.
     *
     * @param listaInformes Lista de objetos DAO de informes a convertir.
     * @return Lista de DTOs de informes resultante.
     */
    @Override
    public List<InformeDTO> listInformesToDto(List<Informes> listaInformes) {
        try {
            List<InformeDTO> listaDto = new ArrayList<>();
            for (Informes informe : listaInformes) {
                listaDto.add(this.informeToDto(informe));
            }
            return listaDto;
        } catch (Exception e) {
            System.out.println(
                    "\n[ERROR InformeToDtoImpl - listInformesToDto()] - Error al convertir lista de informes DAO a lista de informesDTO (return null): "
                            + e);
        }
        return null;
    }
}
