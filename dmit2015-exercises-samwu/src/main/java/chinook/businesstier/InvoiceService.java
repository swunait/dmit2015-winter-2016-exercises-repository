package chinook.businesstier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import chinook.eistier.InvoiceDao;
import chinook.eistier.InvoiceLineDao;
import chinook.entity.Customer;
import chinook.entity.Invoice;
import chinook.entity.InvoiceLine;

@Stateless
public class InvoiceService {

	@Inject
	private InvoiceDao invoiceDao;
	
	@Inject
	private InvoiceLineDao invoiceLineDao;
	
	public void createInvoice(ShoppingCartLocal cart,Customer customer) {
		Invoice newInvoice = new Invoice();
		newInvoice.setBillingAddress(customer.getAddress());
		newInvoice.setBillingCity(customer.getCity());
		newInvoice.setBillingCountry(customer.getCountry());
		newInvoice.setBillingPostalCode(customer.getPostalCode());
		newInvoice.setBillingState(customer.getState());
		newInvoice.setCustomer( customer );
		Date currentDate = Calendar.getInstance().getTime();
		newInvoice.setInvoiceDate( currentDate );
		
		BigDecimal total = BigDecimal.ZERO;
		newInvoice.setTotal(total);
	
		invoiceDao.persist(newInvoice);
		
		List<InvoiceLine> invoiceLines = cart.getInvoiceLines();
				
		for( InvoiceLine lineItem : invoiceLines ) {
			lineItem.setInvoice(newInvoice);
			
			total = total.add( lineItem.getUnitPrice().multiply(
					new BigDecimal( lineItem.getQuantity() ) ) );
			
			invoiceLineDao.persist(lineItem);
		}
		newInvoice.setTotal(total);
		invoiceDao.edit(newInvoice);
	}
}
