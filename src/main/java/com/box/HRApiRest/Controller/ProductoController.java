package com.box.HRApiRest.Controller;

import com.box.HRApiRest.Exceptions.ProductoNotFoundException;
import com.box.HRApiRest.Model.Producto;
import com.box.HRApiRest.Repositories.ProductoRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductoController {

    private final ProductoRepository repository;

    ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/productos")
    public CollectionModel<EntityModel<Producto>> all() {

        List<EntityModel<Producto>> Productos = repository.findAll().stream()
                .map(Producto -> EntityModel.of(Producto,
                        linkTo(methodOn(ProductoController.class).one(Producto.getId())).withSelfRel(),
                        linkTo(methodOn(ProductoController.class).all()).withRel("Productos")))
                .collect(Collectors.toList());

        return CollectionModel.of(Productos, linkTo(methodOn(ProductoController.class).all()).withSelfRel());
    }



    @PostMapping("/productos")
    Producto newProducto(@RequestBody Producto newProducto) {
        return repository.save(newProducto);
    }

    // Single item

    @GetMapping("/productos/{id}")
    public EntityModel<Producto> one(@PathVariable Long id) {

        Producto Producto = repository.findById(id) //
                .orElseThrow(() -> new ProductoNotFoundException(id));

        return EntityModel.of(Producto, //
                linkTo(methodOn(ProductoController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ProductoController.class).all()).withRel("Productos"));
    }

    @PutMapping("/productos/{id}")
    Producto replaceProducto(@RequestBody Producto newProducto, @PathVariable Long id) {

        return repository.findById(id)
                .map(Producto -> {
                    Producto.setNombre(newProducto.getNombre());
                    Producto.setDescripcion(newProducto.getDescripcion());
                    return repository.save(Producto);
                })
                .orElseGet(() -> {
                    newProducto.setId(id);
                    return repository.save(newProducto);
                });
    }

    @DeleteMapping("/productos/{id}")
    void deleteProducto(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
