package io.github.gabrielpadilh4.quarkus.repository;

import io.github.gabrielpadilh4.quarkus.entity.Service;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServiceRepository implements PanacheRepository<Service> {

}
