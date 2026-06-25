package com.proveedores.provedorees.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proveedores.provedorees.model.Provedorees;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProvedoreesDTO {
    @JsonIgnore
    private Long idProveedor;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String nombreEmpresa;
    @NotBlank(message = "El nombre del contacto es obligatorio")
    private String contactoNombre;
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;
    @NotBlank(message = "La categoria del insumo es obligatoria")
    private String categoriaInsumo;

    public Provedorees toModel() {
        Provedorees provedorees = new Provedorees();
        provedorees.setIdProveedor(this.idProveedor);
        provedorees.setNombreEmpresa(this.nombreEmpresa);
        provedorees.setContactoNombre(this.contactoNombre);
        provedorees.setTelefono(this.telefono);
        provedorees.setCategoriaInsumo(this.categoriaInsumo);
        return provedorees;
    }

    public static ProvedoreesDTO fromModel(Provedorees provedorees) {
        ProvedoreesDTO provedoreesDTO = new ProvedoreesDTO();
        provedoreesDTO.setIdProveedor(provedorees.getIdProveedor());
        provedoreesDTO.setNombreEmpresa(provedorees.getNombreEmpresa());
        provedoreesDTO.setContactoNombre(provedorees.getContactoNombre());
        provedoreesDTO.setTelefono(provedorees.getTelefono());
        provedoreesDTO.setCategoriaInsumo(provedorees.getCategoriaInsumo());
        return provedoreesDTO;
    }
}
