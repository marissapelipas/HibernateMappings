package pel.samples.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Genaro Pelipas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/config/spring/bidirect-one2many-config.xml" })
@Transactional
public class SimpleSpringHibTest {

	@Autowired
	@Qualifier("sample1SessionFactory")
    private SessionFactory sessionFactory;
	
	private Session currentSession;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		currentSession = sessionFactory.getCurrentSession();	
	}

    @Test
    public void shouldHaveASessionFactory() {
        assertNotNull(sessionFactory);
    }
    
    @Test
    public void shouldHaveCustomerRecordsAtStart() {
        List<?> results = sessionFactory.getCurrentSession().createQuery("from Customer").list();
        assertTrue(!results.isEmpty());
    }
    
    
}
