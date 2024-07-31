package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OfficeDao {
	EntityManager creatManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Web0731");
		return factory.createEntityManager();
	}

	public boolean saveExample() {
		EntityManager em = creatManager();
		em.getTransaction().begin();
		try {
			Office o = new Office();
			o.setOfficeCode("9");
			o.setCity("Taipei");
			o.setPhone("0912345678");
			o.setAddressLine1("Line1");
			o.setAddressLine2("Line2");
			o.setState("North");
			o.setCountry("Taiwan");
			o.setPostalCode("200");
			o.setTerritory("NA");
			
			Employee e1 = new Employee();
			e1.setEmployeeNumber(1900);
			e1.setLastName("Lun");
			e1.setFirstName("Yi");
			e1.setExtension("9999");
			e1.setEmail("66@66.com");
			//e1.setOffice(o);
			e1.setReportsTo(1002);
			e1.setJobTitle("Manager");
			
			Employee e2 = new Employee();
			e2.setEmployeeNumber(1901);
			e2.setLastName("Lun2");
			e2.setFirstName("Yi2");
			e2.setExtension("99992");
			e2.setEmail("66@66.com2");
			//e2.setOffice(o);
			e2.setReportsTo(1002);
			e2.setJobTitle("Manager2");
			
			ArrayList<Employee> list = new ArrayList<>();
			list.add(e1);
			list.add(e2);
			o.setEmployees(list);
			
			em.persist(o);
			
			em.getTransaction().commit();
			em.close();
			return true;
		}catch(Exception e) {
			System.out.println("saveExample error: "+e.getMessage());
			return false;
		}
	}
	
	//R
	public List<Office> getAll(){
		EntityManager em = creatManager();
		em.getTransaction().begin();
		List<Office> l = null;
		try {
			l = em.createQuery("select o from Office o").getResultList();
			
			em.getTransaction().commit();
			em.close();
		}catch(Exception e) {
			System.out.println("getAll error: "+e.getMessage());
		}
		return l;
	}
	
}
