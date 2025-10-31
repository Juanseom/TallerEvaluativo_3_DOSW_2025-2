package edu.dosw.taller.controller;

import edu.dosw.taller.controller.dtos.RecetaRequestDTO;
import edu.dosw.taller.controller.dtos.RecetaResponseDTO;
import edu.dosw.taller.model.entities.TipoChef;
import edu.dosw.taller.model.services.RecetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaControllerTest {

    @Mock
    private RecetaService recetaService;

    @InjectMocks
    private RecetaController recetaController;

    private RecetaRequestDTO recetaRequest;
    private RecetaResponseDTO recetaResponse;

    @BeforeEach
    void setUp() {
        recetaRequest = new RecetaRequestDTO();
        recetaRequest.setTitulo("Paella Valenciana");
        recetaRequest.setIngredientes(Arrays.asList("Arroz", "Pollo", "Mariscos"));
        recetaRequest.setPasosPreparacion(Arrays.asList("1. Cocer el arroz", "2. Agregar los mariscos"));
        recetaRequest.setNombreAutor("Jorge Rauch");
        recetaRequest.setTipoAutor(TipoChef.JURADO);
        recetaRequest.setTemporada(0);

        recetaResponse = new RecetaResponseDTO();
        recetaResponse.setTitulo("Paella Valenciana");
        recetaResponse.setIngredientes(Arrays.asList("Arroz", "Pollo", "Mariscos"));
        recetaResponse.setPasosPreparacion(Arrays.asList("1. Cocer el arroz", "2. Agregar los mariscos"));
        recetaResponse.setNombreAutor("Jorge Rauch");
        recetaResponse.setTipoAutor("JURADO");
        recetaResponse.setTemporada(0);
    }

    @Test
    @DisplayName("Debe registrar una receta exitosamente")
    void debeRegistrarRecetaExitosamente() {
        when(recetaService.registrarReceta(any(RecetaRequestDTO.class))).thenReturn(recetaResponse);

        ResponseEntity<RecetaResponseDTO> response = recetaController.registrarReceta(recetaRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Paella Valenciana", response.getBody().getTitulo());
        verify(recetaService, times(1)).registrarReceta(any(RecetaRequestDTO.class));
    }

    @Test
    @DisplayName("Debe listar todas las recetas")
    void debeListarTodasLasRecetas() {
        List<RecetaResponseDTO> recetas = Arrays.asList(recetaResponse);
        when(recetaService.listarTodasLasRecetas()).thenReturn(recetas);

        ResponseEntity<List<RecetaResponseDTO>> response = recetaController.listarTodasLasRecetas();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Paella Valenciana", response.getBody().get(0).getTitulo());
        verify(recetaService, times(1)).listarTodasLasRecetas();
    }

    @Test
    @DisplayName("Debe consultar receta por título")
    void debeConsultarRecetaPorTitulo() {
        when(recetaService.consultarRecetaPorTitulo("Paella Valenciana")).thenReturn(recetaResponse);

        ResponseEntity<RecetaResponseDTO> response = recetaController.consultarRecetaPorTitulo("Paella Valenciana");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Paella Valenciana", response.getBody().getTitulo());
        verify(recetaService, times(1)).consultarRecetaPorTitulo("Paella Valenciana");
    }

    @Test
    @DisplayName("Debe listar recetas por tipo de chef")
    void debeListarRecetasPorTipoChef() {
        List<RecetaResponseDTO> recetas = Arrays.asList(recetaResponse);
        when(recetaService.listarRecetasPorTipoChef(TipoChef.JURADO)).thenReturn(recetas);

        ResponseEntity<List<RecetaResponseDTO>> response = recetaController.listarRecetasPorTipoChef(TipoChef.JURADO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("JURADO", response.getBody().get(0).getTipoAutor());
        verify(recetaService, times(1)).listarRecetasPorTipoChef(TipoChef.JURADO);
    }

    @Test
    @DisplayName("Debe listar recetas por temporada")
    void debeListarRecetasPorTemporada() {
        recetaResponse.setTemporada(3);
        List<RecetaResponseDTO> recetas = Arrays.asList(recetaResponse);
        when(recetaService.listarRecetasPorTemporada(3)).thenReturn(recetas);

        ResponseEntity<List<RecetaResponseDTO>> response = recetaController.listarRecetasPorTemporada(3);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(3, response.getBody().get(0).getTemporada());
        verify(recetaService, times(1)).listarRecetasPorTemporada(3);
    }

    @Test
    @DisplayName("Debe buscar recetas por ingrediente")
    void debeBuscarRecetasPorIngrediente() {
        List<RecetaResponseDTO> recetas = Arrays.asList(recetaResponse);
        when(recetaService.buscarRecetasPorIngrediente("Arroz")).thenReturn(recetas);

        ResponseEntity<List<RecetaResponseDTO>> response = recetaController.buscarRecetasPorIngrediente("Arroz");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertTrue(response.getBody().get(0).getIngredientes().contains("Arroz"));
        verify(recetaService, times(1)).buscarRecetasPorIngrediente("Arroz");
    }

    @Test
    @DisplayName("Debe actualizar una receta")
    void debeActualizarReceta() {
        when(recetaService.actualizarReceta(eq("Paella Valenciana"), any(RecetaRequestDTO.class)))
                .thenReturn(recetaResponse);

        ResponseEntity<RecetaResponseDTO> response = recetaController.actualizarReceta("Paella Valenciana", recetaRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Paella Valenciana", response.getBody().getTitulo());
        verify(recetaService, times(1)).actualizarReceta(eq("Paella Valenciana"), any(RecetaRequestDTO.class));
    }

    @Test
    @DisplayName("Debe eliminar una receta")
    void debeEliminarReceta() {
        doNothing().when(recetaService).eliminarReceta("Paella Valenciana");

        ResponseEntity<Void> response = recetaController.eliminarReceta("Paella Valenciana");

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(recetaService, times(1)).eliminarReceta("Paella Valenciana");
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando la receta no existe")
    void debeLanzarExcepcionCuandoRecetaNoExiste() {
        when(recetaService.consultarRecetaPorTitulo("Receta Inexistente"))
                .thenThrow(new RuntimeException("Receta no encontrada con título: Receta Inexistente"));

        assertThrows(RuntimeException.class, () -> {
            recetaController.consultarRecetaPorTitulo("Receta Inexistente");
        });
        verify(recetaService, times(1)).consultarRecetaPorTitulo("Receta Inexistente");
    }

    @Test
    @DisplayName("Debe devolver lista vacía cuando no hay recetas de un tipo de chef")
    void debeRetornarListaVaciaCuandoNoHayRecetasDeTipoChef() {
        when(recetaService.listarRecetasPorTipoChef(TipoChef.TELEVIDENTE)).thenReturn(Arrays.asList());

        ResponseEntity<List<RecetaResponseDTO>> response = recetaController.listarRecetasPorTipoChef(TipoChef.TELEVIDENTE);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(recetaService, times(1)).listarRecetasPorTipoChef(TipoChef.TELEVIDENTE);
    }
}
