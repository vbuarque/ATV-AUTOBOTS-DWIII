package com.autobots.automanager.adaptadores;

import java.util.UUID;

import org.hsqldb.lib.RCData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.autobots.automanager.entities.CredencialUsuarioSenha;
import com.autobots.automanager.entities.CredencialCodigoBarra;
import com.autobots.automanager.entities.Usuario;

import lombok.Data;

RCData
public class UsuarioCadastroAdaptador implements Adaptador<Usuario> {
	private final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();
	
	private Usuario usuario;
	private String nomeUsuario;
	private String senha;
	@Override
	public Usuario adaptar() {
		CredencialUsuarioSenha credencialUsuarioSenha = new CredencialUsuarioSenha();
		credencialUsuarioSenha.setNomeUsuario(nomeUsuario);
		credencialUsuarioSenha.setSenha(codificador.encode(senha));
		usuario.getCredenciais().add(credencialUsuarioSenha);
		
		CredencialCodigoBarra credencialCodigoBarra = new CredencialCodigoBarra();
		credencialCodigoBarra.setCodigo(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		usuario.getCredenciais().add(credencialCodigoBarra);
		return this.usuario;
	}
}