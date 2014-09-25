package com.eheiker.appdirect.service.myapp;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;

import com.eheiker.appdirect.repository.BaseRepository;

public abstract class BaseCrudService<M, ID extends Serializable> implements CrudService<M, ID> {

    @Override
    public M create(final M model) {
        if (model == null) {
            throw new IllegalArgumentException("Model cannot be null");
        }
        return getRepository().save(model);
    }

    @Override
    public List<M> getAll() {
        return getRepository().findAll();
    }

    @Override
    public M getById(final ID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return getRepository().findOne(id);
    }

    @Override
    public M update(final ID id, final M model) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (model == null) {
            throw new IllegalArgumentException("Model cannot be null");
        }

        if (!getRepository().exists(id)) {
            throw new DataRetrievalFailureException("No item with id " + id);
        }

        return getRepository().save(model);
    }

    protected abstract BaseRepository<M, Serializable> getRepository();
}
