package com.bank.controller;

import com.bank.model.Branch;
import com.bank.model.Service;
import com.bank.service.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{bankName}/branches")
    public String getBankBranches(@PathVariable String bankName, Model model) {
        model.addAttribute("bankName", bankName);

        List<Branch> branches = bankService.getBranchesByBankName(bankName);

        model.addAttribute("branches", branches);
        return "branches";
    }


    @GetMapping("/{bankName}/services")
    public String getBankServices(@PathVariable String bankName, Model model) {
        model.addAttribute("bankName", bankName);

        List<Service> services = bankService.getServicesByBankName(bankName);
        model.addAttribute("services", services);
        return "services";
    }

}

