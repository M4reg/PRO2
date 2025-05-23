package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Color;
import cz.uhk.pro2_e.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public void saveColor(Color color) {
        colorRepository.save(color);
    }

    @Override
    public Color getColor(long id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteColor(long id) {
        colorRepository.deleteById(id);
    }
}