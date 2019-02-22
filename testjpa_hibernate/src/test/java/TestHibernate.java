import com.newqur.jpa.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHibernate {

    @org.junit.Test
    public void test() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Employee("lisi"));
        session.getTransaction().commit();
        Query query = session.createQuery("from Employee");
        List<Employee> employees = query.list();
        long count = employees.stream().count();
        employees.forEach(p->{
            System.out.println("user id:" + p.getId() + " name:" + p.getName());
        });
        System.out.println("count:" + count);
        session.close();
        sessionFactory.close();
    }
}
