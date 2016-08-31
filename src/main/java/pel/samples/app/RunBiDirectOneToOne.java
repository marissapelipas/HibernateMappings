package pel.samples.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pel.samples.app.model.Customer;
import pel.samples.app.model.UserAccount;

@Transactional
@Component
public class RunBiDirectOneToOne {
	
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
	public void getUserAsProxyUsingLoad(long userId) {
		System.out.println("########################### getUserAsProxyUsingLoad  ##############################");
		UserAccount u = (UserAccount) getSession().load(UserAccount.class, userId);
		System.out.println("@@@ No DataBase call has happened using Session.load()... let's see after accessing the entity's property");
		System.out.println(u);
	}		
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/config/spring/bidirect-one2one-config.xml");
		
		RunBiDirectOneToOne tst = context.getBean(RunBiDirectOneToOne.class);
		tst.getCustomerAsProxyUsingLoad(3);
		tst.getUserAsProxyUsingLoad(3);
	}
	
}
