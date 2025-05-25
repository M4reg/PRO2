package cz.uhk.pro2_e;

import cz.uhk.pro2_e.model.Color;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.repository.ColorRepository;
import cz.uhk.pro2_e.service.ColorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ColorServiceImplTest {

    @Mock
    private ColorRepository colorRepository;

    @InjectMocks
    private ColorServiceImpl colorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllColors() {
        List<Color> colors = Arrays.asList(new Color(), new Color());
        when(colorRepository.findAll()).thenReturn(colors);

        List<Color> result = colorService.getAllColors();

        assertEquals(2, result.size());
        verify(colorRepository, times(1)).findAll();
    }

    @Test
    void testSaveColorCallsRepository() {
        Color color = new Color();

        colorService.saveColor(color);

        verify(colorRepository, times(1)).save(color);
    }

    @Test
    void testGetColorFound() {
        Color color = new Color();
        color.setId(1L);
        when(colorRepository.findById(1L)).thenReturn(Optional.of(color));

        Color result = colorService.getColor(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(colorRepository, times(1)).findById(1L);
    }

    @Test
    void testGetColorNotFound() {
        when(colorRepository.findById(2L)).thenReturn(Optional.empty());

        Color result = colorService.getColor(2L);

        assertNull(result);
    }

    @Test
    void testDeleteColorCallsRepository() {
        colorService.deleteColor(1L);

        verify(colorRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllColorsByUser() {
        User user = new User();
        List<Color> userColors = Arrays.asList(new Color(), new Color());
        when(colorRepository.findByUser(user)).thenReturn(userColors);

        List<Color> result = colorService.getAllColorsByUser(user);

        assertEquals(2, result.size());
        verify(colorRepository, times(1)).findByUser(user);
    }
}
