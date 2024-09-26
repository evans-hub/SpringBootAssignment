package com.example.customerApi.Services;

import com.example.customerApi.Models.Customer;
import com.example.customerApi.DTOs.CustomerDTO;
import com.example.customerApi.Repositories.CustomerRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAccountBalance(customerDTO.getAccountBalance());

        Customer savedCustomer = customerRepository.save(customer);
        kafkaProducer.sendCustomerUpdateMessage("Customer created: " + savedCustomer.getId());  // Send Kafka message
        return savedCustomer;
    }

    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        Customer updatedCustomer = customerRepository.save(customer);
        kafkaProducer.sendCustomerUpdateMessage("Customer updated: " + updatedCustomer.getId());  // Send Kafka message
        return updatedCustomer;
    }
    public String getCustomerBalance(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return String.format("Customer Balance: $%.2f", customer.getAccountBalance());
    }
}
