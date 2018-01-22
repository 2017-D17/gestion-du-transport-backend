package gdp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gdp.api.entities.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {

}
