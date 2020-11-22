package com.box.HRApiRest.Controller;

import com.box.HRApiRest.Exceptions.MarcaNotFoundException;
import com.box.HRApiRest.Model.Marca;
import com.box.HRApiRest.Repositories.MarcaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class ProductoController {

    private final MarcaRepository repository;

    ProductoController(MarcaRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Marcas")
    public CollectionModel<EntityModel<Marca>> all() {

        List<EntityModel<Marca>> Marcas = repository.findAll().stream()
                .map(Marca -> EntityModel.of(Marca,
                        linkTo(methodOn(ProductoController.class).one(Marca.getId())).withSelfRel(),
                        linkTo(methodOn(ProductoController.class).all()).withRel("Marcas")))
                .collect(Collectors.toList());

        return CollectionModel.of(Marcas, linkTo(methodOn(ProductoController.class).all()).withSelfRel());
    }



    @PostMapping("/Marcas")
    Marca newMarca(@RequestBody Marca newMarca) {
        return repository.save(newMarca);
    }

    // Single item

    @GetMapping("/Marcas/{id}")
    public EntityModel<Marca> one(@PathVariable Long id) {

        Marca Marca = repository.findById(id) //
                .orElseThrow(() -> new MarcaNotFoundException(id));

        return EntityModel.of(Marca, //
                linkTo(methodOn(ProductoController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ProductoController.class).all()).withRel("Marcas"));
    }

    @PutMapping("/Marcas/{id}")
    Marca replaceMarca(@RequestBody Marca newMarca, @PathVariable Long id) {

        return repository.findById(id)
                .map(Marca -> {
                    Marca.setNombre(newMarca.getNombre());
                    Marca.setDescripcion(newMarca.getDescripcion());
                    return repository.save(Marca);
                })
                .orElseGet(() -> {
                    newMarca.setId(id);
                    return repository.save(newMarca);
                });
    }

    @DeleteMapping("/Marcas/{id}")
    void deleteMarca(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
