package com.proveedores.provedorees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proveedores.provedorees.model.Provedorees;
import com.proveedores.provedorees.service.ProvedoreesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/proveedores")
public class ProvedoreesController {
    @Autowired
    private ProvedoreesService provedoreesService;

    @GetMapping
    public ResponseEntity<List<Provedorees>> getAllProvedores() {
        return provedoreesService.getAllProvedores();
    }

    @GetMapping("/{idProveedor}")
    public ResponseEntity<?> getProvedorById(@PathVariable Long idProveedor) {
        return provedoreesService.getProvedorById(idProveedor);
    }

    @PostMapping
    public ResponseEntity<?> crearProvedor(@RequestBody Provedorees provedorees) {
        provedoreesService.crearProvedor(provedorees);
        return ResponseEntity.ok("Proveedor creado exitosamente");
    }

    @PutMapping("/{idProveedor}")
    public ResponseEntity<?> actualizarProvedor(@PathVariable Long idProveedor, @RequestBody Provedorees provedorees) {
        return provedoreesService.actualizarProvedor(idProveedor, provedorees);
    }

    @DeleteMapping("/{idProveedor}")
    public ResponseEntity<?> eliminarProvedor(@PathVariable Long idProveedor) {
        provedoreesService.eliminarProvedor(idProveedor);
        return ResponseEntity.ok("Proveedor eliminado exitosamente");
    }
}
