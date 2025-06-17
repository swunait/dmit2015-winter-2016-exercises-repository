package chinook.businesstier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import chinook.domain.Top10CustomerByInvoiceTotal;
import chinook.eistier.CustomerDao;
import chinook.entity.Customer;

@Stateless
public class CustomerService {

	@Inject
	private CustomerDao customerDao;
	
	public Customer findOne(int customerId) {
		return customerDao.find(customerId);
	}
	
	public List<Top10CustomerByInvoiceTotal> top10CustomerByInvoiceTotal() {
		return customerDao.top10CustomerByInvoiceTotal();
	}
	
	
	public Collection<Customer> top10CustomersByInvoiceTotal() {
		Collection<Customer> top10Customers = new ArrayList<>();
		// retreive all the customers
		Collection<Customer> allCustomers = customerDao.findAll();
		// assign the invoice total for each customer
		for(Customer customer : allCustomers ) {
			// get the invoice total for the current customer
			BigDecimal invoiceTotal = customerDao.customerInvoiceTotal(customer);
			// not all customers have invoices
			// set invoiceTotal to zero if customer has no invoices
			if( invoiceTotal == null )
				invoiceTotal = new BigDecimal(0);
			// set the invoiceTotal for the customer
			customer.setInvoiceTotal(invoiceTotal);
		}
		// sort the collection of customers by the invoice total 
		top10Customers = allCustomers
				.stream()
				.sorted( (customer1, customer2) -> 
							customer2.getInvoiceTotal().compareTo(
							customer1.getInvoiceTotal())
						)
				.collect(Collectors.toList());
		// return only the first 10 items in the collection
		top10Customers = top10Customers
				.stream()
				.limit(10)
				.collect(Collectors.toList());
		return top10Customers;
	}
}
