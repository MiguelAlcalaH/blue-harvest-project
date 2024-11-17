package com.blueharvest.bank.blueharvest.application;


import com.blueharvest.bank.blueharvest.application.CustomerService;
import com.blueharvest.bank.blueharvest.application.impl.CustomerServiceImpl;
import com.blueharvest.bank.blueharvest.domain.Customer;
import com.blueharvest.bank.blueharvest.infrastructure.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerDetails_Found() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "Pepe", "Hidalgo");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        Customer result = customerService.getCustomerDetails(customerId);

        // Assert
        assertEquals(customerId, result.getId());
        assertEquals("Pepe", result.getName());
    }

    @Test
    public void testGetCustomerDetails_NotFound() {
        // Arrange
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> customerService.getCustomerDetails(customerId));
    }
}