package cz.uhk.pro2_e;

import cz.uhk.pro2_e.model.Department;
import cz.uhk.pro2_e.repository.DepartmentRepository;
import cz.uhk.pro2_e.service.DepartmentServiceImpl;
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

public class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(2, result.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testSaveDepartment() {
        Department department = new Department();
        departmentService.saveDepartment(department);

        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void testGetDepartment() {
        Department department = new Department();
        department.setId(1L);
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentService.getDepartment(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(departmentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetDepartmentNotFound() {
        when(departmentRepository.findById(2L)).thenReturn(Optional.empty());

        Department result = departmentService.getDepartment(2L);

        assertNull(result);
    }

    @Test
    void testDeleteDepartment() {
        departmentService.deleteDepartment(1L);

        verify(departmentRepository, times(1)).deleteById(1L);
    }
}
