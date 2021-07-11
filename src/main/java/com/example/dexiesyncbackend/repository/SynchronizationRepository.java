package com.example.dexiesyncbackend.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class SynchronizationRepository {

    private final EntityManager entityManager;

    public Long getServerRevision() {
        javax.persistence.Query query = entityManager.createNativeQuery("SELECT last_value from server_revision_seq");
        return ((BigInteger) query.getSingleResult()).longValue();
    }

    public Long getNewClientIdentity() {
        Query query = entityManager.createNativeQuery("SELECT nextval('client_identity_seq')");
        return ((BigInteger) query.getSingleResult()).longValue();
    }
}
