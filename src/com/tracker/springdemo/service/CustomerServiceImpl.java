package com.tracker.springdemo.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tracker.springdemo.dao.CustomerDAO;
import com.tracker.springdemo.entity.Customer;


@Service
public class CustomerServiceImpl implements CustomerService 

{
	//need to inject customer DAO
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	@Transactional
	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();						
			
				// create a query  ... sort by first name
				Query<Customer> theQuery = 
						currentSession.createQuery("from Customer order by firstName",
													Customer.class);
				
				// execute query and get result list
				List<Customer> customers = theQuery.getResultList();
						
				// return the results		
				return customers;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {		
		return customerDAO.getCustomer(theId);
	}

	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDAO.deleteCustomer(theId);
	}
}
