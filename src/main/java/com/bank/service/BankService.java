package com.bank.service;

import com.bank.model.Branch;
import com.bank.model.Service;
import com.bank.repository.BranchRepository;
import com.bank.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class BankService {

    private final BranchRepository branchRepository;
    private final ServiceRepository serviceRepository;

    public BankService(BranchRepository branchRepository, ServiceRepository serviceRepository) {
        this.branchRepository = branchRepository;
        this.serviceRepository = serviceRepository;
    }

    public List<Branch> getBranchesByBankName(String bankName) {
        return branchRepository.findByBankNameIgnoreCase(bankName);
    }

    public List<Service> getServicesByBankName(String bankName) {
        return serviceRepository.findByBankNameIgnoreCase(bankName);
    }
}
