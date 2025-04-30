package cz.uhk.pro2_e.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "lecturers")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String academicTitle;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "lecturer")
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
