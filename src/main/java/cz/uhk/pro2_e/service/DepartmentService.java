package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Department;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface DepartmentService {
    List<Department> getAllDepartments();
    void saveDepartment(Department department);
    Department getDepartment(long id);
    void deleteDepartment(long id);
}
