package io.github.gabrielpadilh4.quarkus.web.rest;

import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestQuery;

import io.github.gabrielpadilh4.quarkus.entity.Service;
import io.github.gabrielpadilh4.quarkus.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/rest/services")
@Produces("application/json")
@Consumes("application/json")
public class ServiceEndpoint {

    private final ServiceRepository serviceRepository;

    public ServiceEndpoint(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GET
    public List<Service> getServices() {
        return this.serviceRepository.listAll();
    }

    @POST
    @ResponseStatus(201)
    @Transactional
    public Service addService(Service service) {
        this.serviceRepository.persist(service);
        return service;
    }

    @GET
    @Path("/{id}")
    public Service getService(@RestQuery("id") long id) {
        Service service = this.serviceRepository.findById(id);
        if (service == null) {
            throw new NotFoundException();
        }
        return service;
    }

    @PUT
    @Path("/{id}")
    @ResponseStatus(204)
    @Transactional
    public void updateService(@RestQuery("id") long id, Service service) {

        if (id != service.getId()) {
            throw new BadRequestException();
        }

        Service entity = this.serviceRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }

        entity.setName(service.getName());
        entity.setPrice(service.getPrice());

        this.serviceRepository.persist(entity);
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(205)
    @Transactional
    public void deleteService(@RestQuery("id") long id) {
        this.serviceRepository.deleteById(id);
    }

}
