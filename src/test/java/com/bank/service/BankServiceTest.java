package com.bank.service;

import com.bank.model.Branch;
import com.bank.model.Service;
import com.bank.repository.BranchRepository;
import com.bank.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BankServiceTest {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private BankService bankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBranchesByBankName() {
        String bankName = "ABC";
        List<Branch> mockBranches = Arrays.asList(
                new Branch("ABC Branch 1", "Address 1", "IFSC001", "ABC"),
                new Branch("ABC Branch 2", "Address 2", "IFSC002", "ABC")
        );

        when(branchRepository.findByBankNameIgnoreCase(bankName)).thenReturn(mockBranches);

        List<Branch> branches = bankService.getBranchesByBankName(bankName);

        assertEquals(2, branches.size());
        assertEquals("ABC Branch 1", branches.get(0).getName());
    }

    @Test
    void testGetServicesByBankName() {
        String bankName = "ABC";
        List<Service> mockServices = Arrays.asList(
                new Service("ABC Service 1", "Description 1", "ABC"),
                new Service("ABC Service 2", "Description 2", "ABC")
        );

        when(serviceRepository.findByBankNameIgnoreCase(bankName)).thenReturn(mockServices);

        List<Service> services = bankService.getServicesByBankName(bankName);

        assertEquals(2, services.size());
        assertEquals("ABC Service 1", services.get(0).getServiceName());
    }
}
