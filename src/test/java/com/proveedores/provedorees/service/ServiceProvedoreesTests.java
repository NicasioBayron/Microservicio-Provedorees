package com.proveedores.provedorees.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proveedores.provedorees.DTO.ProvedoreesDTO;
import com.proveedores.provedorees.model.Provedorees;
import com.proveedores.provedorees.repository.ProvedoreesRepository;

@ExtendWith(MockitoExtension.class)
public class ServiceProvedoreesTests {

    @Mock
    private ProvedoreesRepository provedoreesRepository;

    @InjectMocks
    private ProvedoreesService provedoreesService; // Mockito inyectará automáticamente el repositorio aquí dentro

    private Provedorees provedorees;
    private ProvedoreesDTO provedoreesDTO;

    @BeforeEach
    public void setUp() {
        // Inicializamos la entidad real con datos para que el servicio pueda trabajar
        // sin dar NullPointer
        provedorees = new Provedorees();
        provedoreesDTO = new ProvedoreesDTO();
        provedorees.setIdProveedor(1L);
        provedorees.setNombreEmpresa("Proveedor Test");
        provedorees.setContactoNombre("Contacto Test");
        provedorees.setTelefono("Telefono Test");
        provedorees.setCategoriaInsumo("Categoria Test");
        provedoreesDTO.setIdProveedor(1L);
        provedoreesDTO.setNombreEmpresa("Proveedor Test");
        provedoreesDTO.setContactoNombre("Contacto Test");
        provedoreesDTO.setTelefono("Telefono Test");
        provedoreesDTO.setCategoriaInsumo("Categoria Test");
    }

    @Test
    public void getAllProvedoresTest() {
        when(provedoreesRepository.findAll()).thenReturn(List.of(provedorees));

        List<ProvedoreesDTO> result = provedoreesService.getAllProvedores();

        assertEquals(1, result.size());
        assertEquals(provedorees.getIdProveedor(), result.get(0).getIdProveedor());
    }

    @Test
    void testGetProvedorById() {
        when(provedoreesRepository.findById(1L)).thenReturn(Optional.of(provedorees));

        ProvedoreesDTO result = provedoreesService.getProvedorById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdProveedor());
    }

    @Test
    void testCrearProvedor() {
        // Pasamos la entidad al método del servicio tal como lo definiste en el
        // controlador
        when(provedoreesRepository.save(any(Provedorees.class))).thenReturn(provedorees);

        ProvedoreesDTO result = provedoreesService.crearProvedor(provedoreesDTO);

        assertNotNull(result);
        assertEquals(provedorees.getIdProveedor(), result.getIdProveedor());
    }

    @Test
    void testActualizarProvedor() {
        ProvedoreesDTO updatedEntity = new ProvedoreesDTO();
        updatedEntity.setIdProveedor(1L);
        updatedEntity.setNombreEmpresa("Proveedor Editado");
        updatedEntity.setContactoNombre("Contacto Editado");
        updatedEntity.setTelefono("Telefono Editado");
        updatedEntity.setCategoriaInsumo("Categoria Editada");

        when(provedoreesRepository.findById(1L)).thenReturn(Optional.of(provedorees));
        when(provedoreesRepository.save(any(Provedorees.class))).thenReturn(updatedEntity.toModel());

        // Pasamos la entidad editada al servicio
        ProvedoreesDTO result = provedoreesService.actualizarProvedor(1L, updatedEntity);

        assertNotNull(result);
        assertEquals(updatedEntity.getNombreEmpresa(), result.getNombreEmpresa());
        assertEquals(updatedEntity.getContactoNombre(), result.getContactoNombre());
    }

    @Test
    void testEliminarProvedor() {
        doNothing().when(provedoreesRepository).deleteById(1L);

        provedoreesService.eliminarProvedor(1L);

        verify(provedoreesRepository, times(1)).deleteById(1L);
    }
}