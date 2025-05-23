package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Model;
import cz.uhk.pro2_e.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public void saveModel(Model model) {
        modelRepository.save(model);
    }

    @Override
    public Model getModel(long id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteModel(long id) {
        modelRepository.deleteById(id);
    }
}