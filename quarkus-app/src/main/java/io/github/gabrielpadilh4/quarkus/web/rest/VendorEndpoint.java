package io.github.gabrielpadilh4.quarkus.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestQuery;

import io.github.gabrielpadilh4.quarkus.entity.Vendor;
import io.github.gabrielpadilh4.quarkus.repository.VendorRepository;
import io.netty.util.internal.StringUtil;
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

@Path("/rest/vendors")
@Produces("application/json")
@Consumes("application/json")
public class VendorEndpoint {

    private final VendorRepository vendorRepository;

    public VendorEndpoint(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GET
    public List<Vendor> getVendors(@RestQuery("email") String email, @RestQuery("name") String name) {
        if (StringUtil.isNullOrEmpty(email) && StringUtil.isNullOrEmpty(name)) {
            return this.vendorRepository.listAll();
        }

        List<Vendor> vendors = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(email) && !StringUtil.isNullOrEmpty(name)) {
            Vendor vendor = this.vendorRepository.findByEmailAndName(email, name);
            vendors.add(vendor);
        }

        if (!StringUtil.isNullOrEmpty(email)) {
            Vendor vendor = this.vendorRepository.findByEmail(email);
            vendors.add(vendor);
        }

        if (!StringUtil.isNullOrEmpty(name)) {
            Vendor vendor = this.vendorRepository.findByName(name);
            vendors.add(vendor);
        }

        return vendors;
    }

    @POST
    @ResponseStatus(201)
    @Transactional
    public Vendor addVendor(Vendor vendor) {
        this.vendorRepository.persist(vendor);
        return vendor;
    }

    @GET
    @Path("/{id}")
    public Vendor getVendor(@RestQuery("id") long id) {
        Vendor vendor = this.vendorRepository.findById(id);
        if (vendor == null) {
            throw new NotFoundException();
        }

        return vendor;
    }

    @PUT
    @Path("/{id}")
    @ResponseStatus(204)
    @Transactional
    public void updateVendor(@RestQuery("id") long id, Vendor vendor) {
        if (id != vendor.getId()) {
            throw new BadRequestException();
        }

        Vendor entity = this.vendorRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }

        entity.setEmail(vendor.getEmail());
        entity.setName(vendor.getName());
        entity.setContact(vendor.getContact());
        entity.setPhone(vendor.getPhone());
        entity.setAddress(vendor.getAddress());

        this.vendorRepository.persist(entity);
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(205)
    @Transactional
    public void deleteVendor(@RestQuery("id") long id) {
        this.vendorRepository.deleteById(id);
    }

}
