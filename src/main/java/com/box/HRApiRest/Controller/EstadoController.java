package com.box.HRApiRest.Controller;

import com.box.HRApiRest.Exceptions.EstadoNotFoundException;
import com.box.HRApiRest.Model.Estado;
import com.box.HRApiRest.Repositories.EstadoRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class EstadoController {

    private final EstadoRepository repository;

    EstadoController(EstadoRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/estados")
    public CollectionModel<EntityModel<Estado>> all() {

        List<EntityModel<Estado>> Estados = repository.findAll().stream()
                .map(Estado -> EntityModel.of(Estado,
                        linkTo(methodOn(EstadoController.class).one(Estado.getId())).withSelfRel(),
                        linkTo(methodOn(EstadoController.class).all()).withRel("Estados")))
                .collect(Collectors.toList());

        return CollectionModel.of(Estados, linkTo(methodOn(EstadoController.class).all()).withSelfRel());
    }



    @PostMapping("/estados")
    Estado newEstado(@RequestBody Estado newEstado) {
        return repository.save(newEstado);
    }

    // Single item

    @GetMapping("/estados/{id}")
    public EntityModel<Estado> one(@PathVariable Long id) {

        Estado Estado = repository.findById(id) //
                .orElseThrow(() -> new EstadoNotFoundException(id));

        return EntityModel.of(Estado, //
                linkTo(methodOn(EstadoController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EstadoController.class).all()).withRel("Estados"));
    }

    @PutMapping("/estados/{id}")
    Estado replaceEstado(@RequestBody Estado newEstado, @PathVariable Long id) {

        return repository.findById(id)
                .map(Estado -> {
                    Estado.setNombre(newEstado.getNombre());
                    Estado.setDescripcion(newEstado.getDescripcion());
                    return repository.save(Estado);
                })
                .orElseGet(() -> {
                    newEstado.setId(id);
                    return repository.save(newEstado);
                });
    }

    @DeleteMapping("/estados/{id}")
    void deleteEstado(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
