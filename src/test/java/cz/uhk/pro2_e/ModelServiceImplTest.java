package cz.uhk.pro2_e;

import cz.uhk.pro2_e.model.Model;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.repository.ModelRepository;
import cz.uhk.pro2_e.service.ModelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModelServiceImplTest {

    @Mock
    private ModelRepository modelRepository;

    @InjectMocks
    private ModelServiceImpl modelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllModels() {
        List<Model> models = Arrays.asList(new Model(), new Model());
        when(modelRepository.findAll()).thenReturn(models);

        List<Model> result = modelService.getAllModels();

        assertEquals(2, result.size());
        verify(modelRepository, times(1)).findAll();
    }

    @Test
    void testSaveModelCallsRepository() {
        Model model = new Model();

        modelService.saveModel(model);

        verify(modelRepository, times(1)).save(model);
    }

    @Test
    void testGetModelFound() {
        Model model = new Model();
        model.setId(1L);
        when(modelRepository.findById(1L)).thenReturn(Optional.of(model));

        Model result = modelService.getModel(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(modelRepository, times(1)).findById(1L);
    }

    @Test
    void testGetModelNotFound() {
        when(modelRepository.findById(2L)).thenReturn(Optional.empty());

        Model result = modelService.getModel(2L);

        assertNull(result);
    }

    @Test
    void testDeleteModelCallsRepository() {
        modelService.deleteModel(1L);

        verify(modelRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllModelsByUser() {
        User user = new User();
        List<Model> userModels = Arrays.asList(new Model(), new Model());
        when(modelRepository.findByUser(user)).thenReturn(userModels);

        List<Model> result = modelService.getAllModelsByUser(user);

        assertEquals(2, result.size());
        verify(modelRepository, times(1)).findByUser(user);
    }
}
