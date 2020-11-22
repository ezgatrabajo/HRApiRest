package com.box.HRApiRest.Repositories;

import com.box.HRApiRest.Model.Estado;
import com.box.HRApiRest.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}