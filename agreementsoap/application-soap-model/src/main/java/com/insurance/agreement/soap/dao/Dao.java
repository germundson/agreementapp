package com.insurance.agreement.soap.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Dao<T> {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());
  @PersistenceContext
  public EntityManager entityManager;

  public T save(T object) {

    if (Objects.isNull(object)) {
      logger.error("Trying to persist an null object ");
    }
    entityManager.persist(object);
    return object;
  }

  public T update(T object) {

    if (Objects.isNull(object)) {
      logger.error("Trying to update an null object ");
    }
    return entityManager.merge(object);
  }

  public T delete(T object) {
    if (Objects.isNull(object)) {
      logger.error("Trying to delete an null object ");
    }
    entityManager.detach(object);
    return object;
  }
}
