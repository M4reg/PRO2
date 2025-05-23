package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Model;
import cz.uhk.pro2_e.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByUser(User user);
}