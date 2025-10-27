package edu.dosw.taller.model.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa una receta con sus detalles.
 */
@Document(collection = "recetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receta {

    @Id
    private String id;

    private String titulo;

    private List<String> ingredientes;

    private List<String> pasosPreparacion;

    private TipoChef tipoChef;

    private String nombreChef;

    private int temporada;
}

