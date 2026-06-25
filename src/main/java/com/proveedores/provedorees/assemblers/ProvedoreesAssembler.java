package com.proveedores.provedorees.assemblers;

import com.proveedores.provedorees.DTO.ProvedoreesDTO;
import com.proveedores.provedorees.controller.ProvedoreesController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//Assembler Hateoas
@Component
public class ProvedoreesAssembler implements RepresentationModelAssembler<ProvedoreesDTO, EntityModel<ProvedoreesDTO>> {

        @Override
        public EntityModel<ProvedoreesDTO> toModel(ProvedoreesDTO provedoreesDTO) {
                return EntityModel.of(provedoreesDTO,
                                linkTo(methodOn(ProvedoreesController.class)
                                                .getProvedorById(provedoreesDTO.getIdProveedor()))
                                                .withSelfRel().withType("GET"),
                                linkTo(methodOn(ProvedoreesController.class).actualizarProvedor(
                                                provedoreesDTO.getIdProveedor(),
                                                provedoreesDTO)).withRel("Actualizar Proveedor").withType("PUT"),
                                linkTo(methodOn(ProvedoreesController.class)
                                                .eliminarProvedor(provedoreesDTO.getIdProveedor()))
                                                .withRel("Eliminar Proveedor").withType("DELETE"));
        }
}
