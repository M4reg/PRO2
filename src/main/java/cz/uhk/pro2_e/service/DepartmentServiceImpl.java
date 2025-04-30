package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Department;
import cz.uhk.pro2_e.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Department getDepartment(long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }
}
