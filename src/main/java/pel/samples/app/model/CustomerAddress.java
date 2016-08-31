package pel.samples.app.model;

public class CustomerAddress extends Address {

	private static final long serialVersionUID = 2539856016631603413L;

	private Customer customer;

	public CustomerAddress() {
	}

	public CustomerAddress(String address1, String address2, String city,
			String state, String zip, Customer customer) {
		super(address1, address2, city, state, zip);
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
