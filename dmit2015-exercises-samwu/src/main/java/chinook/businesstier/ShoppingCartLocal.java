package chinook.businesstier;

import java.util.List;

import javax.ejb.Local;

import chinook.entity.Customer;
import chinook.entity.InvoiceLine;

@Local
public interface ShoppingCartLocal {

	void initialize(Customer customer);
	void addInvoiceLine(InvoiceLine item);
	void removeInvoiceLine(InvoiceLine item);
	List<InvoiceLine> getInvoiceLines();
	void release();
	
}
