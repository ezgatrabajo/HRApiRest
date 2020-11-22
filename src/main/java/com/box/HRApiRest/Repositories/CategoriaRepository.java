package com.box.HRApiRest.Repositories;

import com.box.HRApiRest.Model.Categoria;
import com.box.HRApiRest.Model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}