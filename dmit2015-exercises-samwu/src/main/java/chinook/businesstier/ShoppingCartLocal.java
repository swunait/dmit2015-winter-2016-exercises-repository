package chinook.businesstier;

import java.util.List;

import javax.ejb.Local;

import chinook.entity.InvoiceLine;

@Local
public interface ShoppingCartLocal {

	void initialize();
	void addInvoiceLine(InvoiceLine item);
	void addInvoiceLine(int trackId);
	void removeInvoiceLine(InvoiceLine item);
	List<InvoiceLine> getInvoiceLines();
	void release();
	
}
