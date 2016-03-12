package chinook.eistier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import chinook.domain.Top10CustomerByInvoiceTotal;
import chinook.entity.Customer;

public class CustomerDao extends AbstractDao<Customer> {
	private static final long serialVersionUID = 1L;

	public CustomerDao() {
		super(Customer.class);
	}
	
	public List<Customer> findAllCustomer() {
//		return getEntityManager().createNativeQuery(
//				"SELECT * FROM Customers",
//				Customer.class
//				).getResultList();
//		return getEntityManager().createNamedQuery(
//				"Customer.findAll"
//				).getResultList();
		return getEntityManager().createQuery(
				"SELECT c FROM Customer c ORDER BY c.customerName"
				).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Top10CustomerByInvoiceTotal> top10CustomerByInvoiceTotal() {
		List<Top10CustomerByInvoiceTotal> topCustomers = new ArrayList<>();
		List<Object[]> results = getEntityManager().createNativeQuery(
				""
				).getResultList();
		for(Object[] row : results ) {
			int customerId = (int) row[0];
			String customerName = (String) row[1];
			BigDecimal invoiceTotal = (BigDecimal) row[2];
			Top10CustomerByInvoiceTotal customer = new Top10CustomerByInvoiceTotal(
					customerId, customerName, invoiceTotal);
			topCustomers.add(customer);
		}		
		return topCustomers;
//		return getEntityManager().createNativeQuery(
//				"SELECT c.customerId, CONCAT(c.firstName, ' ', c.lastName) AS CustomerName, "
//				+ " SUM( i.total ) AS InvoiceTotal FROM Customer c INNER JOIN "
//				+ " Invoice i ON c.customerId = i.customerId "
//				+ " GROUP BY c.customerId ORDER BY InvoiceTotal DESC LIMIT 10"
//				,"Top25PercentCustomerResult"
//				).getResultList();
		
//		return getEntityManager().createQuery(
//				"SELECT new chinook.domain.Top10CustomerByInvoiceTotal( "
//				+ " c.customerId, CONCAT(c.firstName, ' ' , c.lastName) AS CustomerName, "
//				+ " SUM(i.total) As InvoiceTotal )"
//				+ " FROM Customer c JOIN c.invoices i "
//				+ " GROUP BY c.customerId "
//				+ " ORDER BY InvoiceTotal DESC"
//				)
//				.setMaxResults(10)
//				.getResultList();
	}
	
	
	public BigDecimal customerInvoiceTotal(Customer customer) {
		return (BigDecimal) getEntityManager().createQuery(
				"SELECT SUM(i.total) FROM Invoice i WHERE i.customer = :customerValue"
				)
				.setParameter("customerValue", customer)
				.getSingleResult();
	}
}
