package com.insurance.agreement.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class Dao<T> {

  @PersistenceContext
  public EntityManager entityManager;

  public T save(T object) {
    entityManager.persist(object);
    return object;
  }

  public T update(T object) {
    return entityManager.merge(object);
  }

  public T delete(T object) {
    entityManager.detach(object);
    return object;
  }
}
