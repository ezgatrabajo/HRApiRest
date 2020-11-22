package com.box.HRApiRest.Controller;

import com.box.HRApiRest.Exceptions.CategoriaNotFoundException;
import com.box.HRApiRest.Model.Categoria;
import com.box.HRApiRest.Repositories.CategoriaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class CategoriaController {

    private final CategoriaRepository repository;

    CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Categorias")
    public CollectionModel<EntityModel<Categoria>> all() {

        List<EntityModel<Categoria>> Categorias = repository.findAll().stream()
                .map(Categoria -> EntityModel.of(Categoria,
                        linkTo(methodOn(CategoriaController.class).one(Categoria.getId())).withSelfRel(),
                        linkTo(methodOn(CategoriaController.class).all()).withRel("Categorias")))
                .collect(Collectors.toList());

        return CollectionModel.of(Categorias, linkTo(methodOn(CategoriaController.class).all()).withSelfRel());
    }



    @PostMapping("/Categorias")
    Categoria newCategoria(@RequestBody Categoria newCategoria) {
        return repository.save(newCategoria);
    }

    // Single item

    @GetMapping("/Categorias/{id}")
    public EntityModel<Categoria> one(@PathVariable Long id) {

        Categoria Categoria = repository.findById(id) //
                .orElseThrow(() -> new CategoriaNotFoundException(id));

        return EntityModel.of(Categoria, //
                linkTo(methodOn(CategoriaController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CategoriaController.class).all()).withRel("Categorias"));
    }

    @PutMapping("/Categorias/{id}")
    Categoria replaceCategoria(@RequestBody Categoria newCategoria, @PathVariable Long id) {

        return repository.findById(id)
                .map(Categoria -> {
                    Categoria.setNombre(newCategoria.getNombre());
                    Categoria.setDescripcion(newCategoria.getDescripcion());
                    return repository.save(Categoria);
                })
                .orElseGet(() -> {
                    newCategoria.setId(id);
                    return repository.save(newCategoria);
                });
    }

    @DeleteMapping("/Categorias/{id}")
    void deleteCategoria(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
