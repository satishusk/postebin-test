package com.example.postebintest.repository.impl;

import com.example.postebintest.data.Paste;
import com.example.postebintest.repository.PasteRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional
public class CommonPasteRepository implements PasteRepository {
  private final EntityManager entityManager;

  @Override
  public Paste save(Paste paste) {
    entityManager.persist(paste);
    return paste;
  }

  @Override
  public Optional<Paste> getByHashId(String hashId) {
    String queryString =
      "select p from Paste p " +
      "where p.expirationEndDateTime > :now and p.hashId = :hashId";
    return Optional.of(entityManager
      .createQuery(queryString, Paste.class)
      .setParameter("now", OffsetDateTime.now())
      .setParameter("hashId", hashId)
      .getSingleResult());
  }

  @Override
  public List<Paste> listActualPastes(int count) {
    String queryString =
      "select p from Paste p " +
      "where p.access = 'PUBLIC' and p.expirationEndDateTime > :now " +
      "order by p.timestamp desc";
    return entityManager.createQuery(queryString, Paste.class)
      .setParameter("now", OffsetDateTime.now())
      .setMaxResults(count)
      .getResultList();
  }
}
