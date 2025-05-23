package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Model;
import cz.uhk.pro2_e.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ModelService {
    List<Model> getAllModels();
    void saveModel(Model model);
    Model getModel(long id);
    void deleteModel(long id);
    List<Model> getAllModelsByUser(User user);

}