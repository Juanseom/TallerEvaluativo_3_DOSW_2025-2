package edu.dosw.taller.model.persistence.repository;

import edu.dosw.taller.model.entities.Receta;
import edu.dosw.taller.model.entities.TipoChef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la gestión de recetas en MongoDB.
 * Proporciona métodos de consulta personalizados además de los CRUD básicos.
 */
@Repository
public interface RecetaRepository extends MongoRepository<Receta, String> {

    /**
     * Busca todas las recetas de un tipo de chef específico.
     *
     * @param tipoChef Tipo de chef (TELEVIDENTE, PARTICIPANTE, JURADO)
     * @return Lista de recetas del tipo especificado
     */
    List<Receta> findByTipoChef(TipoChef tipoChef);

    /**
     * Busca todas las recetas de una temporada específica.
     *
     * @param temporada Número de la temporada
     * @return Lista de recetas de la temporada
     */
    List<Receta> findByTemporada(int temporada);

    /**
     * Busca recetas que contengan un ingrediente específico (case-insensitive).
     *
     * @param ingrediente Nombre del ingrediente a buscar
     * @return Lista de recetas que contienen el ingrediente
     */
    @Query("{ 'ingredientes': { $regex: ?0, $options: 'i' } }")
    List<Receta> findByIngredientesContainingIgnoreCase(String ingrediente);

    /**
     * Busca una receta por su título.
     * @param titulo
     * @return Receta con el título especificado, si existe
     */
    Optional<Receta> findByTitulo(String titulo);

    /**
     * Verifica si existe una receta con el título dado.
      * @param titulo
     * @return true si existe una receta con el título especificado, false en caso contrario
     */
    boolean existsByTitulo(String titulo);
}
