package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Chemical;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.repository.ChemicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChemicalServiceImpl implements ChemicalService {

    private final ChemicalRepository chemicalRepository;

    @Autowired
    public ChemicalServiceImpl(ChemicalRepository chemicalRepository) {
        this.chemicalRepository = chemicalRepository;
    }

    @Override
    public List<Chemical> getAllChemicals() {
        return chemicalRepository.findAll();
    }

    @Override
    public void saveChemical(Chemical chemical) {
        chemicalRepository.save(chemical);
    }

    @Override
    public Chemical getChemical(long id) {
        return chemicalRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteChemical(long id) {
        chemicalRepository.deleteById(id);
    }

    @Override
    public List<Chemical> getAllChemicalsByUser(User user) {
        return chemicalRepository.findByUser(user);
    }
}