package academy.ennate.service;

import academy.ennate.entity.Employee;
import academy.ennate.exception.EmployeeNotFoundException;
import academy.ennate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findOne(String id) {
        Employee employee = repository.findOne(id);
        if(employee == null){
            throw new EmployeeNotFoundException("Employee with id "+id+" not found");
        }
        else{
            return employee;
        }
    }

    @Override
    @Transactional
    public Employee create(Employee emp) {
        return repository.create(emp);
    }

    @Override
    @Transactional
    public Employee update(String id, Employee emp) {
        return repository.update(emp);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Employee emp =  repository.findOne(id);
        repository.delete(emp);
    }
}
