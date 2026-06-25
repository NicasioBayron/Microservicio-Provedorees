package com.proveedores.provedorees.controller;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.ArgumentMatchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;

import com.proveedores.provedorees.model.Provedorees;
import com.proveedores.provedorees.service.ProvedoreesService;
import com.proveedores.provedorees.DTO.ProvedoreesDTO;
import com.proveedores.provedorees.assemblers.ProvedoreesAssembler;

@WebMvcTest(ProvedoreesController.class)
@Import(ProvedoreesAssembler.class)
public class ControllerProvedoreesTests {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ProvedoreesService provedoreesService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockitoBean
    private ProvedoreesAssembler provedoreesAssembler;

    @Test
    public void testListarProvedores() throws Exception {
        // 1. Creamos entidades de prueba reales para simular la salida del servicio
        ProvedoreesDTO p1 = new ProvedoreesDTO();
        p1.setIdProveedor(1L);
        p1.setNombreEmpresa("Proveedor Test");

        List<ProvedoreesDTO> listaEntidades = new ArrayList<>();
        listaEntidades.add(p1);
        // 2. Mockeamos el servicio
        when(provedoreesService.getAllProvedores()).thenReturn(listaEntidades);

        // 3. ¡CLAVE! Mockeamos el assembler también para el stream: que devuelva un
        // EntityModel válido y no un null
        when(provedoreesAssembler.toModel(any())).thenReturn(org.springframework.hateoas.EntityModel.of(p1));

        // 4. Ejecutamos contra la URL del controlador (/proveedores)
        mockMvc.perform(get("/proveedores"))
                .andExpect(status().isOk());
    }

    @Test
    public void testObtenerProvedor() throws Exception {
        // 1. Creamos la entidad real que va a escupir el servicio mockeado
        ProvedoreesDTO p1 = new ProvedoreesDTO();
        p1.setIdProveedor(1L);
        p1.setNombreEmpresa("Proveedor Test");

        // 2. Creamos el DTO representativo
        ProvedoreesDTO dto = new ProvedoreesDTO();
        dto.setIdProveedor(1L);
        dto.setNombreEmpresa("Proveedor Test");

        // 3. Mockeamos el servicio: devuelve la entidad
        when(provedoreesService.getProvedorById(1L)).thenReturn(p1);

        // 4. ¡EL PASO CLAVE! Como el controlador usa provedoreesAssembler.toModel(...),
        // obligamos al assembler mockeado a devolver un EntityModel para que no sea
        // null
        when(provedoreesAssembler.toModel(any())).thenReturn(org.springframework.hateoas.EntityModel.of(dto));

        // El .andDo(print()) nos va a chismear el JSON exacto en los logs de la consola
        // mockMvc.perform(get("/proveedores/1"))
        // .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
        // .andExpect(status().isOk());

        // Verificamos las propiedades reales que la consola nos demostró que existen en
        // la raíz
        mockMvc.perform(get("/proveedores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEmpresa").value("Proveedor Test")) // Muerde directo en la raíz
                .andExpect(jsonPath("$._links['Obtener todos los provedores']").exists()); // Verifica HATEOAS
    }

    @Test
    public void testCrearProvedor() throws Exception {
        // 1. Creamos la entidad real que va a simular devolver el servicio
        ProvedoreesDTO proveedorGuardado = new ProvedoreesDTO();
        proveedorGuardado.setIdProveedor(1L);
        proveedorGuardado.setNombreEmpresa("Proveedor Test"); // Cambia por los campos reales de tu modelo

        // 2. Creamos el DTO que va a enviar el cliente en el JSON
        ProvedoreesDTO proveedorReqDto = new ProvedoreesDTO();
        proveedorReqDto.setNombreEmpresa("Proveedor Test");

        // 3. Configuras el Mock: Cuando el controlador llame al servicio pasándole
        // CUALQUIER entidad, devuelve la guardada
        when(provedoreesService.crearProvedor(any(ProvedoreesDTO.class))).thenReturn(proveedorGuardado);

        // 4. Ejecutas la petición simulada a la URL correcta (/provedores) y esperas un
        // OK (200)
        mockMvc.perform(post("/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedorReqDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testActualizarProvedor() throws Exception {
        // 1. Creamos la entidad editada que va a devolver el servicio
        ProvedoreesDTO proveedorActualizado = new ProvedoreesDTO();
        proveedorActualizado.setIdProveedor(1L);
        proveedorActualizado.setNombreEmpresa("Proveedor Editado");

        // 2. Creamos el DTO con los cambios que manda el cliente
        ProvedoreesDTO proveedorReqDto = new ProvedoreesDTO();
        proveedorReqDto.setNombreEmpresa("Proveedor Editado");

        // 3. Configuras el Mock para el método actualizar
        when(provedoreesService.actualizarProvedor(eq(1L), any(ProvedoreesDTO.class))).thenReturn(proveedorActualizado);

        // 4. Ejecutas el PUT a la URL correcta
        mockMvc.perform(put("/proveedores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedorReqDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testEliminarProvedor() throws Exception {
        doNothing().when(provedoreesService).eliminarProvedor(1L);
        mockMvc.perform(delete("/proveedores/1"))
                .andExpect(status().isOk());
    }
}