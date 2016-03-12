package chinook.domain;

import java.math.BigDecimal;

public class Top10CustomerByInvoiceTotal {

	private int customerId;
	private String customerName;
	private BigDecimal invoiceTotal;

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getInvoiceTotal() {
		return invoiceTotal;
	}
	public void setInvoiceTotal(BigDecimal invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}
	
	public Top10CustomerByInvoiceTotal() {
		super();
	}
	
	public Top10CustomerByInvoiceTotal(int customerId, String customerName, BigDecimal invoiceTotal) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.invoiceTotal = invoiceTotal;
	}
		
}
