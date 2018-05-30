package academy.ennate.repository;
import org.springframework.stereotype.Repository;
import academy.ennate.entity.Employee;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Employee> findAll() {
        TypedQuery<Employee> query =  entityManager.createNamedQuery("Employee.findAll",Employee.class);
        List<Employee> resultlist = query.getResultList();

//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee("fenil","abc.com", 1000000));
//        employees.add(new Employee("fen","abc.com", 1000000));
//        employees.add(new Employee("fil","abc.com", 1000000));
          return resultlist;
    }

    public Employee findOne(String id) {
       //Employee emp = new Employee("fenil","abc.com", 1000000);
        //return emp;
        //return null;
        return entityManager.find(Employee.class,id);
    }

    public Employee findByEmail(String email){
        TypedQuery<Employee> query =  entityManager.createNamedQuery("Employee.findByEmail",Employee.class);
        query.setParameter("paramEmail",email);
        List<Employee> resultlist = query.getResultList();
        if(resultlist != null && resultlist.size() == 1)
            return resultlist.get(0);
        else
            return null;
    }

    public Employee create(Employee emp){
        entityManager.persist(emp);
        return emp;
    }

    public Employee update(Employee emp){
        return entityManager.merge(emp);
    }

    public void delete(Employee emp){
        entityManager.remove(emp);
    }

}
