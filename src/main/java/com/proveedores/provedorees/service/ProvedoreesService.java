package com.proveedores.provedorees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proveedores.provedorees.model.Provedorees;
import com.proveedores.provedorees.repository.ProvedoreesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProvedoreesService {
    @Autowired
    private ProvedoreesRepository provedoreesRepository;

    public List<Provedorees> getAllProvedores() {
        log.info("Obteniendo todos los proveedores");
        return provedoreesRepository.findAll();
    }

    public Provedorees getProvedorById(Long idProveedor) {
        log.info("Obteniendo proveedor con id " + idProveedor);
        Optional<Provedorees> provedor = provedoreesRepository.findById(idProveedor);
        return provedor.orElse(null);
    }

    public Provedorees crearProvedor(Provedorees provedorees) {
        log.info("Creando proveedor");
        return provedoreesRepository.save(provedorees);
    }

    public Provedorees actualizarProvedor(Long idProveedor, Provedorees provedorees) {
        Optional<Provedorees> provedor = provedoreesRepository.findById(idProveedor);
        if (provedor.isPresent()) {
            Provedorees provedorActualizado = provedor.get();
            provedorActualizado.setNombreEmpresa(provedorees.getNombreEmpresa());
            provedorActualizado.setContactoNombre(provedorees.getContactoNombre());
            provedorActualizado.setTelefono(provedorees.getTelefono());
            provedorActualizado.setCategoriaInsumo(provedorees.getCategoriaInsumo());
            log.info("Proveedor actualizado");
            return provedoreesRepository.save(provedorActualizado);
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
