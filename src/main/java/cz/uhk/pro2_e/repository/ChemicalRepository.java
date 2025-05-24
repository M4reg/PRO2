package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Chemical;
import cz.uhk.pro2_e.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChemicalRepository extends JpaRepository<Chemical, Long> {
    List<Chemical> findByUser(User user);
}
