package com.proveedores.provedorees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proveedores.provedorees.model.Provedorees;
import com.proveedores.provedorees.repository.ProvedoreesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProvedoreesService {
    @Autowired
    private ProvedoreesRepository provedoreesRepository;

    public ResponseEntity<List<Provedorees>> getAllProvedores() {
        log.info("Obteniendo todos los proveedores");
        return ResponseEntity.ok(provedoreesRepository.findAll());
    }

    public ResponseEntity<Provedorees> getProvedorById(Long idProveedor) {
        log.info("Obteniendo proveedor con id " + idProveedor);
        Optional<Provedorees> provedor = provedoreesRepository.findById(idProveedor);
        return provedor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Provedorees> crearProvedor(Provedorees provedorees) {
        log.info("Creando proveedor");
        return ResponseEntity.ok(provedoreesRepository.save(provedorees));
    }

    public ResponseEntity<Provedorees> actualizarProvedor(Long idProveedor, Provedorees provedorees) {
        Optional<Provedorees> provedor = provedoreesRepository.findById(idProveedor);
        if (provedor.isPresent()) {
            Provedorees provedorActualizado = provedor.get();
            provedorActualizado.setNombreEmpresa(provedorees.getNombreEmpresa());
            provedorActualizado.setContactoNombre(provedorees.getContactoNombre());
            provedorActualizado.setTelefono(provedorees.getTelefono());
            provedorActualizado.setCategoriaInsumo(provedorees.getCategoriaInsumo());
            log.info("Proveedor actualizado");
            return ResponseEntity.ok(provedoreesRepository.save(provedorActualizado));
        } else {
            log.error("Proveedor no encontrado");
            return null;
        }
    }

    public void eliminarProvedor(Long idProveedor) {
        log.info("Eliminando proveedor");
        provedoreesRepository.deleteById(idProveedor);
    }
}
