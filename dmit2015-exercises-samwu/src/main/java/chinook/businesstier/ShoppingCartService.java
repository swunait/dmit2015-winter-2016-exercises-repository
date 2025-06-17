package chinook.businesstier;

import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;
import jakarta.inject.Inject;

import chinook.entity.InvoiceLine;
import chinook.entity.Track;

@Stateful
public class ShoppingCartService implements ShoppingCartLocal,
	ShoppingCartRemote
{
	private List<InvoiceLine> invoiceLines = new ArrayList<>();
	private boolean initialized = false;
	
	@Inject
	private TrackService trackService;

	@Override
	public void initialize() {
		initialized = true;		
	}

	protected void check() {
		if( !initialized ) {
			throw new RuntimeException("The shopping cart is not initialized");
		}
	}
	
	@Override
	public void addInvoiceLine(int trackId) {
		Track currentTrack = trackService.findOneByTrackId(trackId);
		if( currentTrack != null ) {
			InvoiceLine item = new InvoiceLine();
			item.setQuantity(1);
			item.setTrack(currentTrack);
			item.setUnitPrice( currentTrack.getUnitPrice() );			
			invoiceLines.add(item);
		} else {
			throw new RuntimeException("Invalid trackId");
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
	}

	

}
