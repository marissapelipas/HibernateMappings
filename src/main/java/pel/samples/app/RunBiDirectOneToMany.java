package pel.samples.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pel.samples.app.model.Address;
import pel.samples.app.model.Customer;
import pel.samples.app.model.CustomerAddress;

@Transactional
@Component
public class RunBiDirectOneToMany {
	
	@Autowired
	@Qualifier("sample1SessionFactory")
    private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		System.out.println("sessionFactory = " + sessionFactory.hashCode());
		return sessionFactory.getCurrentSession();
	}		

	public Session getSession() {
		System.out.println("sessionFactory = " + sessionFactory.hashCode());
		return sessionFactory.openSession();
	}		
	
	@Transactional
	public void getCustomerAsProxyUsingLoad(long userId) {
		System.out.println("########################### getCustomerAsProxyUsingLoad  ##############################");
		Customer c = (Customer) getSession().load(Customer.class, userId);
		System.out.println("@@@ No DataBase call has happened using Session.load()... let's see after accessing the entity's property");
		System.out.println(c);
	}	
	
	@Transactional
	public void getCustomerAndAddAddresses(long userId) {
		System.out.println("########################### getCustomerAndAddAddresses  ##############################");
		Customer c = (Customer) getCurrentSession().get(Customer.class, userId);
		c.getAddresses().add(new CustomerAddress("13 Highland St", "Apt Suite 8", "Newtown", "MA", "05121", c));
		c.getAddresses().add(new CustomerAddress("20 Melrose St", "Apt 8", "Wellsley", "MA", "061734", c));
		
		//c.setFirstname("Zoe Kirsten");
		
		getCurrentSession().persist(c);
		//getCurrentSession().save(new CustomerAddress("20 Melrose St", "Apt 8", "Wellsley", "MA", "061734", c));
		//getCurrentSession().save(c);
		
		//System.out.println(c);
	}		
	
	@Transactional
	public void getCustomerAndShowAddresses(long userId) {
		System.out.println("########################### getCustomerAndShowAddresses  ##############################");
		Customer c = (Customer) getSession().load(Customer.class, userId);		
		System.out.println(c);
		
		for (Address ca : c.getAddresses()) {
			System.out.println(ca);
		}
	}	
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/config/spring/bidirect-one2many-config.xml");
		
		RunBiDirectOneToMany tst = context.getBean(RunBiDirectOneToMany.class);
		tst.getCustomerAsProxyUsingLoad(3);
		tst.getCustomerAndAddAddresses(3);
		tst.getCustomerAndShowAddresses(3);
	}
	
}
