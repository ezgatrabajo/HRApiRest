package com.box.HRApiRest.Repositories;

import com.box.HRApiRest.Model.Employee;
import com.box.HRApiRest.Model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}