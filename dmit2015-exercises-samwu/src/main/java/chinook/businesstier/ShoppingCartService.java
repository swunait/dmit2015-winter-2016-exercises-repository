package chinook.businesstier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import chinook.entity.Customer;
import chinook.entity.InvoiceLine;

@Stateful
public class ShoppingCartService implements ShoppingCartLocal,
	ShoppingCartRemote
{
	private List<InvoiceLine> invoiceLines = new ArrayList<>();
	private Customer customer = null;
	private boolean initialized = false;

	@Override
	public void initialize(Customer customer) {
		this.customer = customer;
		initialized = true;		
	}

	protected void check() {
		if( !initialized ) {
			throw new RuntimeException("The shopping cart is not initialized");
		}
	}
	
	@Override
	public void addInvoiceLine(InvoiceLine item) {
		check();
		invoiceLines.add(item);
		
	}

	@Override
	public void removeInvoiceLine(InvoiceLine item) {
		check();
		invoiceLines.remove(item);
	}

	@Override
	public List<InvoiceLine> getInvoiceLines() {
		check();
		return invoiceLines;
	}

	@Remove
	public void release() {
		invoiceLines.clear();
		customer = null;		
	}

}
