package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Color;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ColorService {
    List<Color> getAllColors();
    void saveColor(Color color);
    Color getColor(long id);
    void deleteColor(long id);
}