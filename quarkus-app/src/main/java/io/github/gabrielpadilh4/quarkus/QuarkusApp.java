package io.github.gabrielpadilh4.quarkus;

import java.util.List;

import io.github.gabrielpadilh4.quarkus.entity.Customer;
import io.github.gabrielpadilh4.quarkus.entity.Service;
import io.github.gabrielpadilh4.quarkus.entity.Vendor;
import io.github.gabrielpadilh4.quarkus.repository.CustomerRepository;
import io.github.gabrielpadilh4.quarkus.repository.ServiceRepository;
import io.github.gabrielpadilh4.quarkus.repository.VendorRepository;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.control.ActivateRequestContext;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

    private final ServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    
    public QuarkusApp(ServiceRepository serviceRepository,
                      CustomerRepository customerRepository,
                      VendorRepository vendorRepository) {
        this.serviceRepository = serviceRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    @ActivateRequestContext
    public int run(String... args) throws Exception {

        List<Service> services = this.serviceRepository.listAll();

        services.forEach(System.out::println);

        Service service = this.serviceRepository.findById(2L);
        System.out.println(service);

        List<Customer> customers = this.customerRepository.listAll();
        customers.forEach(System.out::println);

        List<Vendor> vendors = this.vendorRepository.listAll();
        vendors.forEach(System.out::println);

        Vendor vendor = this.vendorRepository.findByEmail("gmartinez0@hostgator.com");
        System.out.println(vendor);

        List<Vendor> vendorsByName = this.vendorRepository.findByName("voonyx");
        vendorsByName.forEach(System.out::println);

        Customer customerByEmail = this.customerRepository.findByEmail("fringilla@Nulla.net");
        System.out.println(customerByEmail);

        return 0;
    }
}
