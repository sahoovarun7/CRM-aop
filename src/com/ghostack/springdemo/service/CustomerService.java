package com.ghostack.springdemo.service;

import java.util.List;

import com.ghostack.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();
	public void saveCustomer(Customer customer);
	public Customer getCustomer(Integer custID);
	public void deleteCustomer(Integer custID);
	public List<Customer> searchCustomer(String searchName);
}
