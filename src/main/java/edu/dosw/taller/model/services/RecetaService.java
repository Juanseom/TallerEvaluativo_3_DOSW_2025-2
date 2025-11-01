package edu.dosw.taller.model.services;

import edu.dosw.taller.controller.dtos.RecetaRequestDTO;
import edu.dosw.taller.controller.dtos.RecetaResponseDTO;
import edu.dosw.taller.model.entities.Receta;
import edu.dosw.taller.model.entities.TipoChef;
import edu.dosw.taller.model.persistence.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de recetas.
 * Contiene la lógica de negocio para operaciones CRUD y búsquedas.
 */
@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;

    /**
     * Registra una nueva receta en el sistema.
     *
     * @param recetaRequest DTO con los datos de la receta a crear
     * @return DTO con la receta creada
     */
    public RecetaResponseDTO registrarReceta(RecetaRequestDTO recetaRequest) {
        if (recetaRepository.existsByTitulo(recetaRequest.getTitulo())) {
            throw new IllegalArgumentException("Ya existe una receta con el título: " + recetaRequest.getTitulo());
        }

        if (recetaRequest.getTipoAutor() == TipoChef.PARTICIPANTE) {
            if (recetaRequest.getTemporada() == null || recetaRequest.getTemporada() <= 0) {
                throw new IllegalArgumentException("La temporada es obligatoria para recetas de PARTICIPANTE");
            }
        } else {
            recetaRequest.setTemporada(0);
        }

        Receta receta = mapToEntity(recetaRequest);
        Receta recetaGuardada = recetaRepository.save(receta);
        return mapToResponseDTO(recetaGuardada);
    }

    /**
     * Lista todas las recetas registradas.
     *
     * @return Lista de DTOs con todas las recetas
     */
    public List<RecetaResponseDTO> listarTodasLasRecetas() {
        List<Receta> recetas = recetaRepository.findAll();
        return recetas.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    /**
     * Consulta una receta específica por su ID.
     *
     * @param titulo Identificador único de la receta
     * @return DTO con los datos de la receta
     */
    public RecetaResponseDTO consultarRecetaPorTitulo(String titulo) {
        Receta receta = recetaRepository.findByTitulo(titulo)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con título: " + titulo));
        return mapToResponseDTO(receta);
    }

    /**
     * Lista recetas filtradas por tipo de chef.
     *
     * @param tipoChef Tipo de chef (TELEVIDENTE, PARTICIPANTE, JURADO)
     * @return Lista de DTOs con las recetas filtradas
     */
    public List<RecetaResponseDTO> listarRecetasPorTipoChef(TipoChef tipoChef) {
        List<Receta> recetas = recetaRepository.findByTipoChef(tipoChef);
        return recetas.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    /**
     * Lista recetas de una temporada específica.
     *
     * @param temporada Número de la temporada
     * @return Lista de DTOs con las recetas de la temporada
     */
    public List<RecetaResponseDTO> listarRecetasPorTemporada(int temporada) {
        List<Receta> recetas = recetaRepository.findByTemporada(temporada);
        return recetas.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    /**
     * Busca recetas que contengan un ingrediente específico.
     *
     * @param ingrediente Nombre del ingrediente a buscar
     * @return Lista de DTOs con las recetas que contienen el ingrediente
     */
    public List<RecetaResponseDTO> buscarRecetasPorIngrediente(String ingrediente) {
        List<Receta> recetas = recetaRepository.findByIngredientesContainingIgnoreCase(ingrediente);
        return recetas.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    /**
     * Actualiza una receta existente.
     *
     * @param titulo            Titulo de la receta a actualizar
     * @param recetaRequest DTO con los nuevos datos
     * @return DTO con la receta actualizada
     */
    public RecetaResponseDTO actualizarReceta(String titulo, RecetaRequestDTO recetaRequest) {
        Receta recetaExistente = recetaRepository.findByTitulo(titulo)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con título: " + titulo));

        if (!recetaExistente.getTitulo().equals(recetaRequest.getTitulo())) {
            if (recetaRepository.existsByTitulo(recetaRequest.getTitulo())) {
                throw new IllegalArgumentException("Ya existe otra receta con el título: " + recetaRequest.getTitulo());
            }
        }

        updateEntityFromDTO(recetaExistente, recetaRequest);
        Receta recetaActualizada = recetaRepository.save(recetaExistente);
        return mapToResponseDTO(recetaActualizada);
    }

    /**
     * Elimina una receta del sistema.
     *
     * @param titulo Titulo de la receta a eliminar
     */
    public void eliminarReceta(String titulo) {
        Receta receta = recetaRepository.findByTitulo(titulo)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con título: " + titulo));
        recetaRepository.delete(receta);
    }

    /**
     * Convierte un DTO de request a entidad.
     *
     * @param dto DTO con los datos de la receta
     * @return Entidad Receta
     */
    private Receta mapToEntity(RecetaRequestDTO dto) {
        Receta receta = new Receta();
        receta.setTitulo(dto.getTitulo());
        receta.setIngredientes(dto.getIngredientes());
        receta.setPasosPreparacion(dto.getPasosPreparacion());
        receta.setNombreChef(dto.getNombreAutor());
        receta.setTipoChef(dto.getTipoAutor());
        receta.setTemporada(dto.getTemporada());
        return receta;
    }

    /**
     * Convierte una entidad a DTO de response.
     *
     * @param receta Entidad Receta
     * @return DTO de respuesta
     */
    private RecetaResponseDTO mapToResponseDTO(Receta receta) {
        RecetaResponseDTO dto = new RecetaResponseDTO();
        dto.setTitulo(receta.getTitulo());
        dto.setIngredientes(receta.getIngredientes());
        dto.setPasosPreparacion(receta.getPasosPreparacion());
        dto.setNombreAutor(receta.getNombreChef());
        dto.setTipoAutor(receta.getTipoChef().name());
        dto.setTemporada(receta.getTemporada());
        return dto;
    }

    /**
     * Actualiza una entidad existente con datos del DTO.
     *
     * @param receta        Entidad a actualizar
     * @param recetaRequest DTO con los nuevos datos
     */
    private void updateEntityFromDTO(Receta receta, RecetaRequestDTO recetaRequest) {
        receta.setTitulo(recetaRequest.getTitulo());
        receta.setIngredientes(recetaRequest.getIngredientes());
        receta.setPasosPreparacion(recetaRequest.getPasosPreparacion());
        receta.setNombreChef(recetaRequest.getNombreAutor());
        receta.setTipoChef(recetaRequest.getTipoAutor());
        receta.setTemporada(recetaRequest.getTemporada());
    }
}
