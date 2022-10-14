package com.JulianaCruz.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.JulianaCruz.blogpessoal.Repository.UsuarioRepository;
import com.JulianaCruz.blogpessoal.model.Usuario;
import com.JulianaCruz.blogpessoal.model.UsuarioLogin;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscarUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			if (buscarUsuario.isPresent()) {

				if (buscarUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}

			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			return Optional.of(usuarioRepository.save(usuario));
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
	}

	public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin) {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				return usuarioLogin;
			}

		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "usuario ou senha Inválidos!", null);
	}

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);
		return senhaEncoder;
	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	private String gerarBasicToken(String email, String password) {
		String estrutura = email + ":" + password;
		byte[] estruturaBase64 = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(estruturaBase64);
	}

}