package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Chemical;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ChemicalService {
    List<Chemical> getAllChemicals();
    void saveChemical(Chemical chemical);
    Chemical getChemical(long id);
    void deleteChemical(long id);
}