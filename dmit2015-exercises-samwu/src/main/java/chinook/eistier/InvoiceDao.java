package chinook.eistier;

import chinook.entity.Invoice;


public class InvoiceDao extends AbstractDao<Invoice> {
	private static final long serialVersionUID = 1L;

	public InvoiceDao() {
		super(Invoice.class);
	}
	
	
}
