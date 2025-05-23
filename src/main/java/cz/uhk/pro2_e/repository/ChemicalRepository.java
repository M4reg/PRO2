package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Chemical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemicalRepository extends JpaRepository<Chemical, Long> {
}