package chinook.businesstier;

import java.util.List;

import javax.ejb.Remote;

import chinook.entity.Customer;
import chinook.entity.InvoiceLine;

@Remote
public interface ShoppingCartRemote {

	void initialize();
	void addInvoiceLine(InvoiceLine item);
	void addInvoiceLine(int trackId);
	void removeInvoiceLine(InvoiceLine item);
	List<InvoiceLine> getInvoiceLines();
	void release();
	
}
