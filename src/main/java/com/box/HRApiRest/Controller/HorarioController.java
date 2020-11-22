package com.box.HRApiRest.Controller;

import com.box.HRApiRest.Exceptions.HorarioNotFoundException;
import com.box.HRApiRest.Model.Horario;
import com.box.HRApiRest.Repositories.HorarioRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class HorarioController {

    private final HorarioRepository repository;

    HorarioController(HorarioRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Horarios")
    public CollectionModel<EntityModel<Horario>> all() {

        List<EntityModel<Horario>> Horarios = repository.findAll().stream()
                .map(Horario -> EntityModel.of(Horario,
                        linkTo(methodOn(HorarioController.class).one(Horario.getId())).withSelfRel(),
                        linkTo(methodOn(HorarioController.class).all()).withRel("Horarios")))
                .collect(Collectors.toList());

        return CollectionModel.of(Horarios, linkTo(methodOn(HorarioController.class).all()).withSelfRel());
    }



    @PostMapping("/Horarios")
    Horario newHorario(@RequestBody Horario newHorario) {
        return repository.save(newHorario);
    }

    // Single item

    @GetMapping("/Horarios/{id}")
    public EntityModel<Horario> one(@PathVariable Long id) {

        Horario Horario = repository.findById(id) //
                .orElseThrow(() -> new HorarioNotFoundException(id));

        return EntityModel.of(Horario, //
                linkTo(methodOn(HorarioController.class).one(id)).withSelfRel(),
                linkTo(methodOn(HorarioController.class).all()).withRel("Horarios"));
    }

    @PutMapping("/Horarios/{id}")
    Horario replaceHorario(@RequestBody Horario newHorario, @PathVariable Long id) {

        return repository.findById(id)
                .map(Horario -> {
                    Horario.setApertura(newHorario.getApertura());
                    Horario.setCierre(newHorario.getCierre());
                    Horario.setDia(newHorario.getDia());
                    Horario.setObservaciones(newHorario.getObservaciones());
                    Horario.setId(newHorario.getId());
                    return repository.save(Horario);
                })
                .orElseGet(() -> {
                    newHorario.setId(id);
                    return repository.save(newHorario);
                });
    }

    @DeleteMapping("/Horarios/{id}")
    void deleteHorario(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
