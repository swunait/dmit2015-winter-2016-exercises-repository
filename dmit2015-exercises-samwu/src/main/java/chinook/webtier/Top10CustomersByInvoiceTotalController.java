package chinook.webtier;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import chinook.businesstier.CustomerService;
import chinook.domain.Top10CustomerByInvoiceTotal;
import chinook.entity.Customer;

@Named
@RequestScoped
public class Top10CustomersByInvoiceTotalController {

	@Inject
	private CustomerService customerService;
	
	public Collection<Customer> top10Customers() {
		return customerService.top10CustomersByInvoiceTotal();
	}
	
	public List<Top10CustomerByInvoiceTotal> top10CustomersByInvoiceTotal() {
		return customerService.top10CustomerByInvoiceTotal();
	}
}
