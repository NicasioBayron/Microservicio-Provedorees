package com.proveedores.provedorees.assemblers;

import com.proveedores.provedorees.controller.ProvedoreesController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.proveedores.provedorees.model.Provedorees;

//Assembler Hateoas
@Component
public class ProvedoreesAssembler implements RepresentationModelAssembler<Provedorees, EntityModel<Provedorees>> {

    @Override
    public EntityModel<Provedorees> toModel(Provedorees provedorees) {
        return EntityModel.of(provedorees,
                linkTo(methodOn(ProvedoreesController.class).getProvedorById(provedorees.getIdProveedor()))
                        .withSelfRel().withType("GET"),
                linkTo(methodOn(ProvedoreesController.class).actualizarProvedor(provedorees.getIdProveedor(),
                        provedorees)).withRel("Actualizar Proveedor").withType("PUT"),
                linkTo(methodOn(ProvedoreesController.class).eliminarProvedor(provedorees.getIdProveedor()))
                        .withRel("Eliminar Proveedor").withType("DELETE"));
    }
}
