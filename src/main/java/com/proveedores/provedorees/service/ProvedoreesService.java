package com.proveedores.provedorees.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proveedores.provedorees.DTO.ProvedoreesDTO;
import com.proveedores.provedorees.model.Provedorees;
import com.proveedores.provedorees.repository.ProvedoreesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProvedoreesService {
    @Autowired
    private ProvedoreesRepository provedoreesRepository;

    public List<ProvedoreesDTO> getAllProvedores() {
        log.info("Obteniendo todos los proveedores");
        return provedoreesRepository.findAll().stream().map(ProvedoreesDTO::fromModel).collect(Collectors.toList());
    }

    public ProvedoreesDTO getProvedorById(Long idProveedor) {
        log.info("Obteniendo proveedor con id " + idProveedor);
        Optional<Provedorees> provedor = provedoreesRepository.findById(idProveedor);
        return provedor.map(ProvedoreesDTO::fromModel).orElse(null);
    }

    public ProvedoreesDTO crearProvedor(ProvedoreesDTO provedoreesDTO) {
        log.info("Creando proveedor");
        Provedorees provedorees = provedoreesDTO.toModel();
        return ProvedoreesDTO.fromModel(provedoreesRepository.save(provedorees));
    }

    public ProvedoreesDTO actualizarProvedor(Long idProveedor, ProvedoreesDTO provedoreesDTO) {
        Optional<Provedorees> provedor = provedoreesRepository.findById(idProveedor);
        if (provedor.isPresent()) {
            Provedorees provedorActualizado = provedor.get();
            provedorActualizado.setNombreEmpresa(provedoreesDTO.getNombreEmpresa());
            provedorActualizado.setContactoNombre(provedoreesDTO.getContactoNombre());
            provedorActualizado.setTelefono(provedoreesDTO.getTelefono());
            provedorActualizado.setCategoriaInsumo(provedoreesDTO.getCategoriaInsumo());
            log.info("Proveedor actualizado");
            return ProvedoreesDTO.fromModel(provedoreesRepository.save(provedorActualizado));
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
