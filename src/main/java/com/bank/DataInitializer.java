package com.bank;

import com.bank.model.Branch;
import com.bank.model.Service;
import com.bank.repository.BranchRepository;
import com.bank.repository.ServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner loadData(BranchRepository branchRepository, ServiceRepository serviceRepository) {
        return args -> {
            branchRepository.saveAll(Arrays.asList(
                    new Branch("SBI Branch 1", "Address 1", "IFSC001", "SBI"),
                    new Branch("SBI Branch 2", "Address 2", "IFSC002", "SBI"),
                    new Branch("Yes Branch 1", "Address 3", "IFSC003", "YES"),
                    new Branch("Yes Branch 2", "Address 4", "IFSC004", "YES")
            ));

            serviceRepository.saveAll(Arrays.asList(
                    new Service("SBI Service 1", "Description 1", "SBI"),
                    new Service("SBI Service 2", "Description 2", "SBI"),
                    new Service("Yes Service 1", "Description 3", "YES"),
                    new Service("Yes Service 2", "Description 4", "YES")
            ));
        };
    }
}
