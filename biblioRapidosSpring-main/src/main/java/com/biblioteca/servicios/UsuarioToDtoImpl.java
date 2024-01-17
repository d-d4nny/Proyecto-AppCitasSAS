package com.biblioteca.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.dao.Usuario;
import com.biblioteca.dto.UsuarioDTO;

@Service
public class UsuarioToDtoImpl implements IUsuarioToDto {

	@Override
	public UsuarioDTO usuarioToDto(Usuario u) {
		
		UsuarioDTO dto = new UsuarioDTO();
		
		try {
			dto.setNombreUsuario(u.getNombreUsuario());
			dto.setApellidosUsuario(u.getApellidosUsuario());
			dto.setDniUsuario(u.getDniUsuario());
			dto.setTlfUsuario(u.getTlfUsuario());
			dto.setEmailUsuario(u.getEmailUsuario());
			dto.setClaveUsuario(u.getClaveUsuario());
			dto.setToken(u.getToken());
			dto.setExpiracionToken(u.getExpiracionToken());
			dto.setId(u.getIdUsuario());
			
			return dto;
		} catch (Exception e) {
			System.out.println(
					"\n[ERROR UsuarioToDtoImpl - usuarioToDto()] - Error al convertir usuario DAO a usuarioDTO (return null): "
							+ e);
			return null;
		}
	}
	
	@Override
	public List<UsuarioDTO> listaUsuarioToDto(List<Usuario> listaUsuario){
		try {
				
			List<UsuarioDTO> listaDto = new ArrayList<>();
			for (Usuario u : listaUsuario) {
				listaDto.add(this.usuarioToDto(u));
			}
			return listaDto;

		} catch (Exception e) {
			System.out.println(
					"\n[ERROR UsuarioToDtoImpl - listauUsuarioToDto()] - Error al convertir lista de usuario DAO a lista de usuarioDTO (return null): "
							+ e);
		}
		return null;
	}

}
