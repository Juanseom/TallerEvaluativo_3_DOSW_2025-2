package edu.dosw.taller.controller;

import edu.dosw.taller.controller.dtos.RecetaRequestDTO;
import edu.dosw.taller.controller.dtos.RecetaResponseDTO;
import edu.dosw.taller.model.entities.TipoChef;
import edu.dosw.taller.model.services.RecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
@Tag(name = "Recetas", description = "API para la gestión de recetas de cocina")
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping
    @Operation(
            summary = "Registrar una nueva receta",
            description = "Permite registrar una receta de un televidente, participante o chef"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Receta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<RecetaResponseDTO> registrarReceta(
            @Valid @RequestBody RecetaRequestDTO recetaRequest) {
        RecetaResponseDTO response = recetaService.registrarReceta(recetaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar todas las recetas",
            description = "Obtiene todas las recetas registradas en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<RecetaResponseDTO>> listarTodasLasRecetas() {
        List<RecetaResponseDTO> recetas = recetaService.listarTodasLasRecetas();
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{titulo}")
    @Operation(
            summary = "Consultar receta por título",
            description = "Obtiene una receta específica por su título"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta encontrada"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<RecetaResponseDTO> consultarRecetaPorTitulo(
            @Parameter(description = "Título de la receta", example = "Paella Valenciana")
            @PathVariable String titulo) {
        RecetaResponseDTO receta = recetaService.consultarRecetaPorTitulo(titulo);
        return ResponseEntity.ok(receta);
    }

    @GetMapping("/tipo/{tipoChef}")
    @Operation(
            summary = "Listar recetas por tipo de chef",
            description = "Filtra recetas según el tipo de autor: TELEVIDENTE, PARTICIPANTE o JURADO"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Tipo de chef inválido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<RecetaResponseDTO>> listarRecetasPorTipoChef(
            @Parameter(description = "Tipo de chef: TELEVIDENTE, PARTICIPANTE o JURADO")
            @PathVariable TipoChef tipoChef) {
        List<RecetaResponseDTO> recetas = recetaService.listarRecetasPorTipoChef(tipoChef);
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/temporada/{temporada}")
    @Operation(
            summary = "Listar recetas por temporada",
            description = "Obtiene todas las recetas de una temporada específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Número de temporada inválido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<RecetaResponseDTO>> listarRecetasPorTemporada(
            @Parameter(description = "Número de temporada") @PathVariable int temporada) {
        List<RecetaResponseDTO> recetas = recetaService.listarRecetasPorTemporada(temporada);
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/ingrediente/{ingrediente}")
    @Operation(
            summary = "Buscar recetas por ingrediente",
            description = "Busca todas las recetas que contienen un ingrediente específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Ingrediente inválido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<RecetaResponseDTO>> buscarRecetasPorIngrediente(
            @Parameter(description = "Nombre del ingrediente a buscar")
            @PathVariable String ingrediente) {
        List<RecetaResponseDTO> recetas = recetaService.buscarRecetasPorIngrediente(ingrediente);
        return ResponseEntity.ok(recetas);
    }

    @PutMapping("/{titulo}")
    @Operation(
            summary = "Actualizar una receta",
            description = "Actualiza los datos de una receta existente usando su título"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o título duplicado"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<RecetaResponseDTO> actualizarReceta(
            @Parameter(description = "Título de la receta a actualizar", example = "Paella Valenciana")
            @PathVariable String titulo,
            @Valid @RequestBody RecetaRequestDTO recetaRequest) {
        RecetaResponseDTO response = recetaService.actualizarReceta(titulo, recetaRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{titulo}")
    @Operation(
            summary = "Eliminar una receta",
            description = "Elimina una receta del sistema por su título"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Receta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Void> eliminarReceta(
            @Parameter(description = "Título de la receta a eliminar", example = "Paella Valenciana")
            @PathVariable String titulo) {
        recetaService.eliminarReceta(titulo);
        return ResponseEntity.noContent().build();
    }
}
