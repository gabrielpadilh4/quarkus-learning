package io.github.gabrielpadilh4.quarkus.repository;

import java.util.List;

import io.github.gabrielpadilh4.quarkus.entity.Service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ServiceRepository {

    private final EntityManager em;

    public ServiceRepository(EntityManager em) {
        this.em = em;
    }

    public List<Service> getAllServices() {
        List<Service> services = this.em.createQuery("select service from Service service", Service.class)
                .getResultList();
        return services;
    }
    
}
