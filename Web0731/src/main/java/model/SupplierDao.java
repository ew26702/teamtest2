package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SupplierDao {
	EntityManager creatManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Web0731");
		return factory.createEntityManager();
	}

	public boolean saveExample() {
		EntityManager em = creatManager();
		em.getTransaction().begin();
		
		try {
			//把Supplier跟2種Coffee資料準備好
			Supplier sp = new Supplier();
			sp.setCity("Taipei");
			sp.setSupName("Gjun");
			sp.setState("TW");
			sp.setStreet("Kung Yuan Road");
			sp.setZip("101");
			sp.setSupId(1);

			Coffee c1 = new Coffee();
			c1.setCofName("Flavor A");
			c1.setPrice(new BigDecimal(200.0));
			c1.setSales(10);
			c1.setTotal(5);
			//c1.setSupplier(sp);

			Coffee c2 = new Coffee();
			c2.setCofName("Flavor B");
			c2.setPrice(new BigDecimal(100.0));
			c2.setSales(11);
			c2.setTotal(6);
			//c2.setSupplier(sp);
			
			//把兩個Coffee放到List中，再塞進Supplier裡
			ArrayList<Coffee> list = new ArrayList<>();
			list.add(c1);
			list.add(c2);
			sp.setCoffees(list);
			
			//然後只要新增Supplier就可以了
			em.persist(sp);

			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception e) {
			System.out.println("saveExample error: "+e.getMessage());
			return false;
		}
		
	}
	
	//C
	public List<Supplier> getAll(){
		EntityManager em = creatManager();
		em.getTransaction().begin();
		List<Supplier> l = null;
		try {
			l = em.createQuery("select s from Supplier s").getResultList();
			//l = em.createNativeQuery("select * from suppliers").getResultList();
			
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			System.out.println("getAll error: "+e.getMessage());
		}
		return l;
	}

}
