package io.github.gabrielpadilh4.quarkus;

import java.util.List;

import io.github.gabrielpadilh4.quarkus.entity.Service;
import io.github.gabrielpadilh4.quarkus.repository.ServiceRepository;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.control.ActivateRequestContext;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

    private final ServiceRepository serviceRepository;
    
    public QuarkusApp(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    @ActivateRequestContext
    public int run(String... args) throws Exception {

        List<Service> services = this.serviceRepository.listAll();

        services.forEach(System.out::println);

        Service service = this.serviceRepository.findById(2L);
        System.out.println(service);

        return 0;
    }
}
