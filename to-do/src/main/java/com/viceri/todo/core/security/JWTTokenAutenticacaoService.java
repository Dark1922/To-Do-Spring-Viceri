package com.viceri.todo.core.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.viceri.todo.domain.models.Usuario;
import com.viceri.todo.domain.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService {

	private static final long EXPIRATION_TIME = 864000000;

	private static final String SECRET = "*SenhaExtremamenteSecreta";

	private static final String TOKEN_PREFIXO = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	public void addAuthentication(HttpServletResponse response, String username) throws IOException {

		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		String token = TOKEN_PREFIXO + " " + JWT;

		response.addHeader(HEADER_STRING, token);

		response.addHeader("Acess-Control-Allow-Origin", "*");

		ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class).atualizarTokenUser(JWT,
				username);

		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}

	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(HEADER_STRING);

		try {

			if (token != null) {

				String tokenLimpo = token.replace(TOKEN_PREFIXO, "").trim();

				String user = Jwts.parser().setSigningKey(SECRET)

						.parseClaimsJws(tokenLimpo.replace(TOKEN_PREFIXO, ""))

						.getBody().getSubject();

				if (user != null) {
					Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
							.findByLogin(user);

					if (usuario != null) {

						if (tokenLimpo.equalsIgnoreCase(usuario.getToken())) {

							return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getPassword(),
									usuario.getAuthorities());
						}
					}
				}

			}

		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			try {
				response.getOutputStream().print(
						"Seu TOKEN está expirado, " + "faça o login ou informe um novo TOKEN para AUTENTICACÃO.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

}
