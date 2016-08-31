package pel.samples.app.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1413668337033154586L;

	private Long id;
	private Customer customer;
	private Double orderTax;
	private Double orderTotal;
	private List<OrderItem> orderItems = new ArrayList<OrderItem>(0);
	
	public Order() {}
	
	public Order(Customer customer, Double orderTax, Double orderTotal,
			List<OrderItem> orderItems) {
		this.customer = customer;
		this.orderTax = orderTax;
		this.orderTotal = orderTotal;
		this.orderItems = orderItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getOrderTax() {
		return orderTax;
	}

	public void setOrderTax(Double orderTax) {
		this.orderTax = orderTax;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((orderTax == null) ? 0 : orderTax.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		if (orderTax == null) {
			if (other.orderTax != null)
				return false;
		} else if (!orderTax.equals(other.orderTax))
			return false;
		return true;
	}
	
}
