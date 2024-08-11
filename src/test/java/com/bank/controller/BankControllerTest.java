package com.bank.controller;

import com.bank.model.Branch;
import com.bank.model.Service;
import com.bank.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class BankControllerTest {

    @Mock
    private BankService bankService;

    @InjectMocks
    private BankController bankController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(bankController).build();
        // Set up view resolver for MockMvc
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(bankController)
                .setViewResolvers(viewResolver) // Add view resolver here
                .build();
    }

    @Test
    void testGetBankBranches() throws Exception {
        String bankName = "SBI";
        when(bankService.getBranchesByBankName(bankName)).thenReturn(Arrays.asList(
                new Branch("SBI Branch 1", "Address 1", "IFSC001", "SBI"),
                new Branch("SBI Branch 2", "Address 2", "IFSC002", "SBI")
        ));

        mockMvc.perform(get("/api/bank/SBI/branches"))
                .andExpect(status().isOk())
                .andExpect(view().name("branches"))
                .andExpect(model().attributeExists("bankName"))
                .andExpect(model().attributeExists("branches"))
                .andExpect(model().attribute("bankName", "SBI"));
    }

    @Test
    void testGetBankServices() throws Exception {
        String bankName = "SBI";
        when(bankService.getServicesByBankName(bankName)).thenReturn(Arrays.asList(
                new Service("SBI Service 1", "Description 1", "SBI"),
                new Service("SBI Service 2", "Description 2", "SBI")
        ));

        mockMvc.perform(get("/api/bank/SBI/services"))
                .andExpect(status().isOk())
                .andExpect(view().name("services"))
                .andExpect(model().attributeExists("bankName"))
                .andExpect(model().attributeExists("services"))
                .andExpect(model().attribute("bankName", "SBI"));
    }
}
