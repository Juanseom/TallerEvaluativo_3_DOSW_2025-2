package edu.dosw.taller.controller.dtos;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO para la respuesta de una receta.
 */
@Data
@Schema(description = "DTO para la respuesta de una receta.")
public class RecetaResponseDTO {

    @Schema(description = "Título de la receta", example = "Paella Valenciana")
    private String titulo;

    @Schema(description = "Lista de ingredientes necesarios para la receta", example = "[\"Arroz\", \"Pollo\", \"Mariscos\"]")
    private List<String> Ingredientes;

    @Schema(description = "Lista de pasos para preparar la receta", example = "[\"1. Cocer el arroz\", \"2. Agregar los mariscos\"]")
    private List<String> pasosPreparacion;

    @Schema(description = "Nombre del autor de la receta", example = "Jorge Rauch")
    private String nombreAutor;

    @Schema(description = "Tipo de autor de la receta", example = "JURADO")
    private String tipoAutor;

    @Schema(description = "Temporada en la que se presentó la receta", example = "3")
    private int temporada;
}
