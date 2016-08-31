package pel.samples.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pel.samples.app.model.OrderItem;
import pel.samples.app.model.Product;

@Transactional
@Component
public class RunUniDirectManyToOne {
	
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
	public void getProductAsProxyUsingLoad(long productId) {
		System.out.println("########################### getProductAsProxyUsingLoad  ##############################");
		Product p = (Product) getSession().load(Product.class, productId);
		System.out.println("@@@ No DataBase call has happened using Session.load()... let's see after accessing the entity's property");
		System.out.println(p);
	}	

	@Transactional
	public void getProductAndAddOrderItems(long productId) {
		System.out.println("########################### getProductAndAddOrderItems  ##############################");
		Product p = (Product) getSession().get(Product.class, productId);
		
		OrderItem item1 = new OrderItem(p, 5);
		OrderItem item2 = new OrderItem(p, 3);
		OrderItem item3 = new OrderItem(p, 1);
		
		getCurrentSession().persist(item1);
		getCurrentSession().save(item2);
		getCurrentSession().persist(item3);
	}		
	
	
	//@Transactional
	public void getOrderItemsByProduct(long productId) {
		System.out.println("########################### getProductAsProxyUsingLoad  ##############################");
		List<OrderItem> results = (List<OrderItem>) getSession().createQuery("from pel.samples.app.model.OrderItem").list();
		for (OrderItem oi : results) {
			System.out.println(oi);
		}
	}	
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/config/spring/unidirect-many2one-config.xml");
		
		RunUniDirectManyToOne tst = context.getBean(RunUniDirectManyToOne.class);
		tst.getProductAsProxyUsingLoad(3);
		tst.getProductAndAddOrderItems(3);
		tst.getOrderItemsByProduct(3);
	}
	
}
