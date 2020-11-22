package com.box.HRApiRest.Repositories;

import com.box.HRApiRest.Model.Estado;
import com.box.HRApiRest.Model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

}