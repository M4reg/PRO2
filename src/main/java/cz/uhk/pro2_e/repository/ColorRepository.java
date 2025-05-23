package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Color;
import cz.uhk.pro2_e.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    List<Color> findByUser(User user);
}