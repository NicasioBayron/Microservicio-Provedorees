package com.proveedores.provedorees.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provedorees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor", nullable = false)
    private Long idProveedor;

    @Column(name = "nombreEmpresa", nullable = false)
    private String nombreEmpresa;

    @Column(name = "contactoNombre", nullable = false)
    private String contactoNombre;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "categoriaInsumo", nullable = false)
    private String categoriaInsumo;
}
