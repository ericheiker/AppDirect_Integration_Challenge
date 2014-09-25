package com.eheiker.appdirect.service.myapp;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for generic CRUD operations on a service for a specific type.
 *
 * @author Eric Heiker
 */
public interface CrudService<M, ID extends Serializable> {

    /**
     * Create an entity.
     *
     * @param resource
     * @return created entity
     * @throws IllegalArgumentException when resource is null
     */
    M create(final M resource);

    /**
     * Get all the entities.
     *
     * @return a {@link java.util.List} of entities
     */
    List<M> getAll();

    /**
     * Get an entity from its id.
     *
     * @param id entity's id
     * @return an entity or null if no entity exists with the provided id
     * @throws IllegalArgumentException when id is null or empty
     */
    M getById(final ID id);

    /**
     * Update an entity.
     *
     * @param id entity's id
     * @param resource
     * @return created entity
     * @throws IllegalArgumentException when resource is null
     */
    M update(final ID id, final M resource);

}
