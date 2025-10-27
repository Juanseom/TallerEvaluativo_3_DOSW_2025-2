package edu.dosw.taller.controller.dtos;

import java.util.List;

import edu.dosw.taller.model.entities.TipoChef;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para la solicitud de creación o actualización de una receta.
 */
@Data
@Schema(description = "DTO para la solicitud de creación o actualización de una receta.")
public class RecetaRequestDTO {

    @NotBlank(message = "El título no puede estar vacío")
    @Schema(description = "Título de la receta", example = "Paella Valenciana")
    private String titulo;

    @NotBlank(message = "Los ingredientes no pueden estar vacíos")
    @Schema(description = "Lista de ingredientes necesarios para la receta", example = "[\"Arroz\", \"Pollo\", \"Mariscos\"]")
    private List<String> Ingredientes;

    @NotBlank(message = "Los pasos de preparación no pueden estar vacíos")
    @Schema(description = "Lista de pasos para preparar la receta", example = "[\"1. Cocer el arroz\", \"2. Agregar los mariscos\"]")
    private List<String> pasosPreparacion;

    @Schema(description = "Nombre del autor de la receta", example = "Jorge Rauch")
    private String nombreAutor;

    @NotBlank(message = "El tipo de autor no puede estar vacío")
    @Schema(description = "Tipo de autor de la receta", example = "JURADO")
    private TipoChef tipoAutor;

    @Schema(description = "Temporada en la que se presentó la receta", example = "3")
    private int temporada;
}
