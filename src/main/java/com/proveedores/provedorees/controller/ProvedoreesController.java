package com.proveedores.provedorees.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proveedores.provedorees.DTO.ProvedoreesDTO;
import com.proveedores.provedorees.assemblers.ProvedoreesAssembler;
import com.proveedores.provedorees.service.ProvedoreesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/proveedores")
public class ProvedoreesController {
    private final ProvedoreesService provedoreesService;
    private final ProvedoreesAssembler provedoreesAssembler;

    public ProvedoreesController(ProvedoreesService provedoreesService, ProvedoreesAssembler provedoreesAssembler) {
        this.provedoreesService = provedoreesService;
        this.provedoreesAssembler = provedoreesAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<ProvedoreesDTO>> getAllProvedores() {
        log.info("Obteniendo todos los provedores");
        List<EntityModel<ProvedoreesDTO>> provedores = provedoreesService.getAllProvedores().stream()
                .map(provedoreesAssembler::toModel)
                .collect(Collectors.toList());
        CollectionModel<EntityModel<ProvedoreesDTO>> modelo = CollectionModel.of(provedores,
                linkTo(methodOn(ProvedoreesController.class).crearProvedor(null)).withRel("Crear Provedor")
                        .withType("POST"));
        return modelo;
    }

    @GetMapping("/{idProveedor}")
    public EntityModel<ProvedoreesDTO> getProvedorById(@PathVariable Long idProveedor) {
        log.info("OBTENIENDO PROVEEDOR CON ID: " + idProveedor);
        ProvedoreesDTO provedorees = provedoreesService.getProvedorById(idProveedor);
        EntityModel<ProvedoreesDTO> modelo = provedoreesAssembler.toModel(provedorees);
        modelo.add(linkTo(methodOn(ProvedoreesController.class).getAllProvedores())
                .withRel("Obtener todos los provedores"));
        return modelo;
    }

    @PostMapping
    public ResponseEntity<ProvedoreesDTO> crearProvedor(@RequestBody ProvedoreesDTO provedoreesDTO) {
        log.info("Creando provedor");
        ProvedoreesDTO provedorees = provedoreesService.crearProvedor(provedoreesDTO);
        return ResponseEntity.ok(provedorees);
    }

    @PutMapping("/{idProveedor}")
    public ResponseEntity<ProvedoreesDTO> actualizarProvedor(@PathVariable Long idProveedor,
            @RequestBody ProvedoreesDTO provedoreesDTO) {
        log.info("Actualizando provedor");
        ProvedoreesDTO provedorees = provedoreesService.actualizarProvedor(idProveedor, provedoreesDTO);
        return ResponseEntity.ok(provedorees);
    }

    @DeleteMapping("/{idProveedor}")
    public ResponseEntity<String> eliminarProvedor(@PathVariable Long idProveedor) {
        log.info("Eliminando provedor");
        provedoreesService.eliminarProvedor(idProveedor);
        return ResponseEntity.ok("Proveedor eliminado exitosamente");
    }
}
