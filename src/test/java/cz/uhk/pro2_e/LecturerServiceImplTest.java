package cz.uhk.pro2_e;

import cz.uhk.pro2_e.model.Lecturer;
import cz.uhk.pro2_e.repository.LecturerRepository;
import cz.uhk.pro2_e.service.LecturerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LecturerServiceImplTest {

    @Mock
    private LecturerRepository lecturerRepository;

    @InjectMocks
    private LecturerServiceImpl lecturerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLecturers() {
        List<Lecturer> lecturers = Arrays.asList(new Lecturer(), new Lecturer());
        when(lecturerRepository.findAll()).thenReturn(lecturers);

        List<Lecturer> result = lecturerService.getAllLecturers();

        assertEquals(2, result.size());
        verify(lecturerRepository, times(1)).findAll();
    }

    @Test
    void testSaveLecturer() {
        Lecturer lecturer = new Lecturer();
        lecturerService.saveLecturer(lecturer);

        verify(lecturerRepository, times(1)).save(lecturer);
    }

    @Test
    void testGetLecturer() {
        Lecturer lecturer = new Lecturer();
        lecturer.setId(1L);
        when(lecturerRepository.findById(1L)).thenReturn(Optional.of(lecturer));

        Lecturer result = lecturerService.getLecturer(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(lecturerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetLecturerNotFound() {
        when(lecturerRepository.findById(2L)).thenReturn(Optional.empty());

        Lecturer result = lecturerService.getLecturer(2L);

        assertNull(result);
        verify(lecturerRepository, times(1)).findById(2L);
    }

    @Test
    void testDeleteLecturer() {
        lecturerService.deleteLecturer(1L);

        verify(lecturerRepository, times(1)).deleteById(1L);
    }
}
