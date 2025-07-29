package io.github.gabrielpadilh4.quarkus.repository;

import java.util.List;

import io.github.gabrielpadilh4.quarkus.entity.Vendor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VendorRepository implements PanacheRepository<Vendor> {

    public Vendor findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public List<Vendor> findByName(String name) {
        return find("lower(name)", name.toLowerCase()).list();
    }
    
}
