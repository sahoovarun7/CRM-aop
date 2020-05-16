package com.ghostack.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ghostack.springdemo.aspect.CRMLogginAspect;
import com.ghostack.springdemo.entity.Customer;
import com.ghostack.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String getCustomerList(Model model){
		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);
		model.addAttribute("pageCount", CRMLogginAspect.pageCount);
		return "list-customer";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,BindingResult bind){
		if(bind.hasErrors())
			return "customer-form";
		
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	@GetMapping("/showSaveFormForUpdate")
	public String showUpdateForm(@RequestParam("customerID") Integer custID,Model model){
		Customer customer = customerService.getCustomer(custID);
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerID") Integer custID){
		
		customerService.deleteCustomer(custID);
		return "redirect:/customer/list";
	}
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("searchName") String searchName, Model model){
		List<Customer> customers = customerService.searchCustomer(searchName);
		model.addAttribute("customers",customers);
		model.addAttribute("pageCount", CRMLogginAspect.pageCount);
		return "list-customer";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder databinder){
		StringTrimmerEditor stm = new StringTrimmerEditor(true);
		databinder.registerCustomEditor(String.class, stm);
	}
}
