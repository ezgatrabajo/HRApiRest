package com.box.HRApiRest.Repositories;

import com.box.HRApiRest.Model.Estado;
import com.box.HRApiRest.Model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

}