package cz.uhk.pro2_e;

import cz.uhk.pro2_e.model.Chemical;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.repository.ChemicalRepository;
import cz.uhk.pro2_e.service.ChemicalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChemicalServiceImplTest {

    @Mock
    private ChemicalRepository chemicalRepository;

    @InjectMocks
    private ChemicalServiceImpl chemicalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllChemicals() {
        List<Chemical> chemicals = Arrays.asList(new Chemical(), new Chemical());
        when(chemicalRepository.findAll()).thenReturn(chemicals);

        List<Chemical> result = chemicalService.getAllChemicals();

        assertEquals(2, result.size());
        verify(chemicalRepository, times(1)).findAll();
    }

    @Test
    void testSaveChemicalCallsRepository() {
        Chemical chemical = new Chemical();

        chemicalService.saveChemical(chemical);

        verify(chemicalRepository, times(1)).save(chemical);
    }

    @Test
    void testGetChemicalFound() {
        Chemical chemical = new Chemical();
        chemical.setId(1L);
        when(chemicalRepository.findById(1L)).thenReturn(Optional.of(chemical));

        Chemical result = chemicalService.getChemical(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(chemicalRepository, times(1)).findById(1L);
    }

    @Test
    void testGetChemicalNotFound() {
        when(chemicalRepository.findById(2L)).thenReturn(Optional.empty());

        Chemical result = chemicalService.getChemical(2L);

        assertNull(result);
    }

    @Test
    void testDeleteChemicalCallsRepository() {
        chemicalService.deleteChemical(1L);

        verify(chemicalRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllChemicalsByUser() {
        User user = new User();
        List<Chemical> chemicals = Arrays.asList(new Chemical(), new Chemical());
        when(chemicalRepository.findByUser(user)).thenReturn(chemicals);

        List<Chemical> result = chemicalService.getAllChemicalsByUser(user);

        assertEquals(2, result.size());
        verify(chemicalRepository, times(1)).findByUser(user);
    }
}
