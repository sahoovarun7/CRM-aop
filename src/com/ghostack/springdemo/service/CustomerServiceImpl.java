package com.ghostack.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ghostack.springdemo.dao.CustomerDAO;
import com.ghostack.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}
	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}
	@Override
	@Transactional
	public Customer getCustomer(Integer custID) {
		return customerDAO.getCustomer(custID);
	}
	@Override
	@Transactional
	public void deleteCustomer(Integer custID) {
		 customerDAO.deleteCustomer(custID);
	}
	@Override
	@Transactional
	public List<Customer> searchCustomer(String searchName) {
		return customerDAO.searchCustomer(searchName);
	}

}
