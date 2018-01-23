package gdp.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.errors.ApiException;

import gdp.api.entities.Annonce;
import gdp.api.entities.Collaborateur;
import gdp.api.entities.TrajetInfo;
import gdp.api.repository.AnnonceRepository;
import gdp.api.repository.CollaborateurRepository;
import gdp.api.services.GoogleApiService;

@RestController
@RequestMapping("maps")
public class MapsController {
	@Autowired GoogleApiService googleApiSvc;


	@GetMapping(path = "/autocomplete/{input}")
	public List<String> getPredictions(@PathVariable String input) {
		return googleApiSvc.autocompleteAdress(input);
	}

	@GetMapping(path = "/directions")
	public TrajetInfo getTrajetInfo(@RequestParam("origin") String origin,@RequestParam("destination") String destination) throws ApiException, InterruptedException, IOException {
		return googleApiSvc.getTrajetInfo(origin, destination);
	}
	
}
