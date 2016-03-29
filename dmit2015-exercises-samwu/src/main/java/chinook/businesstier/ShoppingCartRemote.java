package chinook.businesstier;

import java.util.List;

import javax.ejb.Remote;

import chinook.entity.Customer;
import chinook.entity.InvoiceLine;

@Remote
public interface ShoppingCartRemote {

	void initialize(Customer customer);
	void addInvoiceLine(InvoiceLine item);
	void removeInvoiceLine(InvoiceLine item);
	List<InvoiceLine> getInvoiceLines();
	void release();
	
}
