package gdp.api.services;

import static gdp.api.security.SecurityConstants.EXPIRATION_TIME;
import static gdp.api.security.SecurityConstants.SECRET;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import gdp.api.entities.Collaborateur;
import gdp.api.repository.CollaborateurRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	@Autowired
	CollaborateurRepository collabRepo;

	public String makeToken(Authentication auth) {
		String email = ((User) auth.getPrincipal()).getUsername();
		Collaborateur collab = collabRepo.findByEmail(email);
		if (collab == null) {
			return null;
		}
		String matricule = collab.getMatricule();
		String role = auth.getAuthorities().toArray()[0].toString();
		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).claim("role", role)
				.claim("matricule", matricule).claim("nom", collab.getNom()).claim("prenom", collab.getPrenom())
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		return token;
	}
}
