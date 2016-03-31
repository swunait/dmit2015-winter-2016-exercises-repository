package chinook.webtier;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import chinook.businesstier.CustomerService;
import chinook.businesstier.InvoiceService;
import chinook.businesstier.ShoppingCartLocal;
import chinook.entity.Customer;
import chinook.entity.InvoiceLine;
import helper.JSFHelper;

@Named("pos")
@SessionScoped
public class PointOfSaleController implements Serializable {
	
	@Inject
	private ShoppingCartLocal cart;		// getter
	
	@Inject
	private InvoiceService invoiceService;
	
	@Inject
	private CustomerService customerService;
	
	private int trackId;			// getter/setter
	private int customerId;			// getter/setter
	
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public ShoppingCartLocal getCart() {
		return cart;
	}
	
	public void doAddItem() {
		try {
			cart.addInvoiceLine(trackId);
			JSFHelper.addInfoMessage("Added item to cart.");
		} catch( Exception e ) {
			JSFHelper.addErrorMessage("Item not added to cart.");
		}
	}
	
	public void doRemoveItem(InvoiceLine item) {
		cart.removeInvoiceLine(item);
	}
	
	public void doCreateInvoice() {
		Customer customer = customerService.findOne(customerId);
		if( customer != null ) {
			invoiceService.createInvoice(cart, customer);
			JSFHelper.addInfoMessage("Created invoice successfully");
			cart.release();
		} else {
			JSFHelper.addErrorMessage("CustomerId is not valid");
		}
	}

}
