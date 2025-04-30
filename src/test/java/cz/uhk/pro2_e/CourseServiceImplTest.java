package cz.uhk.pro2_e;

import cz.uhk.pro2_e.model.Course;
import cz.uhk.pro2_e.repository.CourseRepository;
import cz.uhk.pro2_e.service.CourseServiceImpl;
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

public class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = Arrays.asList(new Course(), new Course());
        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseService.getAllCourses();

        assertEquals(2, result.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testSaveCourse() {
        Course course = new Course();
        courseService.saveCourse(course);

        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testGetCourse() {
        Course course = new Course();
        course.setId(1L);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course result = courseService.getCourse(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCourseNotFound() {
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());

        Course result = courseService.getCourse(2L);

        assertNull(result);
        verify(courseRepository, times(1)).findById(2L);
    }

    @Test
    void testDeleteCourse() {
        courseService.deleteCourse(1L);

        verify(courseRepository, times(1)).deleteById(1L);
    }
}
