package edu.dosw.taller.model.services;

import edu.dosw.taller.controller.dtos.RecetaRequestDTO;
import edu.dosw.taller.controller.dtos.RecetaResponseDTO;
import edu.dosw.taller.model.entities.Receta;
import edu.dosw.taller.model.entities.TipoChef;
import edu.dosw.taller.model.persistence.repository.RecetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaServiceTest {

    @Mock
    private RecetaRepository recetaRepository;

    @InjectMocks
    private RecetaService recetaService;

    private RecetaRequestDTO recetaRequest;
    private Receta receta;

    @BeforeEach
    void setUp() {
        recetaRequest = new RecetaRequestDTO();
        recetaRequest.setTitulo("Paella Valenciana");
        recetaRequest.setIngredientes(Arrays.asList("Arroz", "Pollo", "Mariscos"));
        recetaRequest.setPasosPreparacion(Arrays.asList("1. Cocer el arroz", "2. Agregar los mariscos"));
        recetaRequest.setNombreAutor("Jorge Rauch");
        recetaRequest.setTipoAutor(TipoChef.JURADO);
        recetaRequest.setTemporada(0);

        receta = new Receta();
        receta.setId("123");
        receta.setTitulo("Paella Valenciana");
        receta.setIngredientes(Arrays.asList("Arroz", "Pollo", "Mariscos"));
        receta.setPasosPreparacion(Arrays.asList("1. Cocer el arroz", "2. Agregar los mariscos"));
        receta.setNombreChef("Jorge Rauch");
        receta.setTipoChef(TipoChef.JURADO);
        receta.setTemporada(0);
    }

    @Test
    @DisplayName("Debe registrar una receta exitosamente")
    void debeRegistrarRecetaExitosamente() {
        when(recetaRepository.existsByTitulo(recetaRequest.getTitulo())).thenReturn(false);
        when(recetaRepository.save(any(Receta.class))).thenReturn(receta);

        RecetaResponseDTO response = recetaService.registrarReceta(recetaRequest);

        assertNotNull(response);
        assertEquals("Paella Valenciana", response.getTitulo());
        assertEquals("JURADO", response.getTipoAutor());
        verify(recetaRepository, times(1)).existsByTitulo(recetaRequest.getTitulo());
        verify(recetaRepository, times(1)).save(any(Receta.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el título ya existe")
    void debeLanzarExcepcionCuandoTituloYaExiste() {
        when(recetaRepository.existsByTitulo(recetaRequest.getTitulo())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            recetaService.registrarReceta(recetaRequest);
        });

        assertEquals("Ya existe una receta con el título: Paella Valenciana", exception.getMessage());
        verify(recetaRepository, times(1)).existsByTitulo(recetaRequest.getTitulo());
        verify(recetaRepository, never()).save(any(Receta.class));
    }

    @Test
    @DisplayName("Debe registrar receta de participante con temporada")
    void debeRegistrarRecetaParticipanteConTemporada() {
        recetaRequest.setTipoAutor(TipoChef.PARTICIPANTE);
        recetaRequest.setTemporada(3);
        receta.setTipoChef(TipoChef.PARTICIPANTE);
        receta.setTemporada(3);

        when(recetaRepository.existsByTitulo(recetaRequest.getTitulo())).thenReturn(false);
        when(recetaRepository.save(any(Receta.class))).thenReturn(receta);

        RecetaResponseDTO response = recetaService.registrarReceta(recetaRequest);

        assertNotNull(response);
        assertEquals(3, response.getTemporada());
        assertEquals("PARTICIPANTE", response.getTipoAutor());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando participante no tiene temporada")
    void debeLanzarExcepcionCuandoParticipanteSinTemporada() {
        recetaRequest.setTipoAutor(TipoChef.PARTICIPANTE);
        recetaRequest.setTemporada(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            recetaService.registrarReceta(recetaRequest);
        });

        assertEquals("La temporada es obligatoria para recetas de PARTICIPANTE", exception.getMessage());
        verify(recetaRepository, never()).save(any(Receta.class));
    }

    @Test
    @DisplayName("Debe establecer temporada 0 para jurado y televidente")
    void debeEstablecerTemporada0ParaJuradoYTelevidente() {
        recetaRequest.setTemporada(5);
        when(recetaRepository.existsByTitulo(recetaRequest.getTitulo())).thenReturn(false);
        when(recetaRepository.save(any(Receta.class))).thenReturn(receta);

        recetaService.registrarReceta(recetaRequest);

        assertEquals(0, recetaRequest.getTemporada());
    }

    @Test
    @DisplayName("Debe listar todas las recetas")
    void debeListarTodasLasRecetas() {
        List<Receta> recetas = Arrays.asList(receta);
        when(recetaRepository.findAll()).thenReturn(recetas);

        List<RecetaResponseDTO> response = recetaService.listarTodasLasRecetas();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Paella Valenciana", response.get(0).getTitulo());
        verify(recetaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe consultar receta por título")
    void debeConsultarRecetaPorTitulo() {
        when(recetaRepository.findByTitulo("Paella Valenciana")).thenReturn(Optional.of(receta));

        RecetaResponseDTO response = recetaService.consultarRecetaPorTitulo("Paella Valenciana");

        assertNotNull(response);
        assertEquals("Paella Valenciana", response.getTitulo());
        verify(recetaRepository, times(1)).findByTitulo("Paella Valenciana");
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando receta no existe por título")
    void debeLanzarExcepcionCuandoRecetaNoExistePorTitulo() {
        when(recetaRepository.findByTitulo("Inexistente")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            recetaService.consultarRecetaPorTitulo("Inexistente");
        });

        assertEquals("Receta no encontrada con título: Inexistente", exception.getMessage());
    }

    @Test
    @DisplayName("Debe listar recetas por tipo de chef")
    void debeListarRecetasPorTipoChef() {
        List<Receta> recetas = Arrays.asList(receta);
        when(recetaRepository.findByTipoChef(TipoChef.JURADO)).thenReturn(recetas);

        List<RecetaResponseDTO> response = recetaService.listarRecetasPorTipoChef(TipoChef.JURADO);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("JURADO", response.get(0).getTipoAutor());
        verify(recetaRepository, times(1)).findByTipoChef(TipoChef.JURADO);
    }

    @Test
    @DisplayName("Debe listar recetas por temporada")
    void debeListarRecetasPorTemporada() {
        receta.setTemporada(3);
        List<Receta> recetas = Arrays.asList(receta);
        when(recetaRepository.findByTemporada(3)).thenReturn(recetas);

        List<RecetaResponseDTO> response = recetaService.listarRecetasPorTemporada(3);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(3, response.get(0).getTemporada());
        verify(recetaRepository, times(1)).findByTemporada(3);
    }

    @Test
    @DisplayName("Debe buscar recetas por ingrediente")
    void debeBuscarRecetasPorIngrediente() {
        List<Receta> recetas = Arrays.asList(receta);
        when(recetaRepository.findByIngredientesContainingIgnoreCase("Arroz")).thenReturn(recetas);

        List<RecetaResponseDTO> response = recetaService.buscarRecetasPorIngrediente("Arroz");

        assertNotNull(response);
        assertEquals(1, response.size());
        assertTrue(response.get(0).getIngredientes().contains("Arroz"));
        verify(recetaRepository, times(1)).findByIngredientesContainingIgnoreCase("Arroz");
    }

    @Test
    @DisplayName("Debe actualizar una receta existente")
    void debeActualizarRecetaExistente() {
        RecetaRequestDTO updateRequest = new RecetaRequestDTO();
        updateRequest.setTitulo("Paella Valenciana Actualizada");
        updateRequest.setIngredientes(Arrays.asList("Arroz", "Pollo"));
        updateRequest.setPasosPreparacion(Arrays.asList("1. Cocer"));
        updateRequest.setNombreAutor("Chef Actualizado");
        updateRequest.setTipoAutor(TipoChef.JURADO);
        updateRequest.setTemporada(0);

        when(recetaRepository.findByTitulo("Paella Valenciana")).thenReturn(Optional.of(receta));
        when(recetaRepository.existsByTitulo("Paella Valenciana Actualizada")).thenReturn(false);
        when(recetaRepository.save(any(Receta.class))).thenReturn(receta);

        RecetaResponseDTO response = recetaService.actualizarReceta("Paella Valenciana", updateRequest);

        assertNotNull(response);
        verify(recetaRepository, times(1)).findByTitulo("Paella Valenciana");
        verify(recetaRepository, times(1)).save(any(Receta.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción al actualizar con título duplicado")
    void debeLanzarExcepcionAlActualizarConTituloDuplicado() {
        RecetaRequestDTO updateRequest = new RecetaRequestDTO();
        updateRequest.setTitulo("Otro Título");

        when(recetaRepository.findByTitulo("Paella Valenciana")).thenReturn(Optional.of(receta));
        when(recetaRepository.existsByTitulo("Otro Título")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            recetaService.actualizarReceta("Paella Valenciana", updateRequest);
        });

        assertEquals("Ya existe otra receta con el título: Otro Título", exception.getMessage());
        verify(recetaRepository, never()).save(any(Receta.class));
    }

    @Test
    @DisplayName("Debe eliminar una receta")
    void debeEliminarReceta() {
        when(recetaRepository.findByTitulo("Paella Valenciana")).thenReturn(Optional.of(receta));
        doNothing().when(recetaRepository).delete(receta);

        recetaService.eliminarReceta("Paella Valenciana");

        verify(recetaRepository, times(1)).findByTitulo("Paella Valenciana");
        verify(recetaRepository, times(1)).delete(receta);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar receta inexistente")
    void debeLanzarExcepcionAlEliminarRecetaInexistente() {
        when(recetaRepository.findByTitulo("Inexistente")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            recetaService.eliminarReceta("Inexistente");
        });

        assertEquals("Receta no encontrada con título: Inexistente", exception.getMessage());
        verify(recetaRepository, never()).delete(any(Receta.class));
    }
}
