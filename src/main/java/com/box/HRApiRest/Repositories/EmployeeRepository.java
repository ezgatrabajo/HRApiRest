package com.box.HRApiRest.Repositories;

import com.box.HRApiRest.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}