import com.newqur.jpa.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
        session.save(new Employee("8", "zhangsan"));
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }

    @Test
    public void letterCombinations() {
        String digits = "235";
        String[] alphas = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> results = new ArrayList<String>();
        getRandomNoRepeat(digits, results, "", 0, alphas);
        System.out.println(results);
    }

    public void getRandomNoRepeat(String digits, List<String> results, String result, int index, String[] alphas) {
        if (index == digits.length()) {
            results.add(result);
            return;
        }
        // 遍历某个数字代表的所有字母
        int cursor = digits.charAt(index) - '2';
        String currentAlpha = alphas[cursor];
        for (int i = 0; i < currentAlpha.length(); i++) {
            getRandomNoRepeat(digits, results, result + currentAlpha.charAt(i), index+1, alphas);

        }
    }

    public List<String> letterCombinations(String digits) {
        String[] alphas = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> results = new ArrayList<String>();
        trace(digits, results, "", 0, alphas);
        return results;
    }

    public void trace(String digits, List<String> results, String result, int index, String[] alphas) {
        if (index == digits.length()) {
            results.add(result);
            return;
        }
        // 遍历某个数字代表的所有字母
        String currentAlpha = alphas[digits.charAt(index) - '2'];
        for (int i = 0; i < currentAlpha.length(); i++) {
            trace(digits, results, result + currentAlpha.charAt(i), index+1, alphas);

        }
    }

}
