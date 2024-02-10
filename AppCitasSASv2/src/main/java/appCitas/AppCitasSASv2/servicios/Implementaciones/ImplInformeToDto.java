package appCitas.AppCitasSASv2.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import appCitas.AppCitasSASv2.dao.Informes;
import appCitas.AppCitasSASv2.dto.InformeDTO;
import appCitas.AppCitasSASv2.servicios.Interfaces.IntfInformeToDto;

@Service
public class ImplInformeToDto implements IntfInformeToDto {

	
	@Override
	public InformeDTO informeToDto(Informes u) {
		
		try {
			InformeDTO dto = new InformeDTO();
			
			dto.setIdInforme(u.getIdInforme());
			dto.setNombreInforme(u.getNombreInforme());
			dto.setDescInforme(u.getDescInforme());
			dto.setFchInforme(u.getFchInforme());
			
		}catch (Exception e) {
			System.out.println(
					"\n[ERROR InformeToDtoImpl - informeToDto()] - Error al convertir informe DAO a informeToDto (return null): "
							+ e);
		}
		return null;
	}
	
	
	@Override
	public List<InformeDTO> listInformesToDto(List<Informes> listaInformes){
		try {
			
			List<InformeDTO> listaDto = new ArrayList<>();
			for (Informes u : listaInformes) {
				listaDto.add(this.informeToDto(u));
			}
			return listaDto;
		}catch (Exception e) {
			System.out.println(
					"\n[ERROR InformeToDtoImpl - listaInformesToDto()] - Error al convertir lista de informes DAO a lista de informesDTO (return null): "
							+ e);
		}
		return null;
	}
}

