package thefirstgroup.crud_practice.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thefirstgroup.crud_practice.model.dao.EmployeeMapper;
import thefirstgroup.crud_practice.model.dto.EmployeeDTO;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Transactional
    public int editSearch(int empId) {
        return employeeMapper.editSearch(empId);
    }

    public List<EmployeeDTO> findAllEmployees() {
        return employeeMapper.findAllEmployees();
    }

    public EmployeeDTO searchEmployee(int empId) {
        return employeeMapper.searchEmployee(empId);
    }

    @Transactional
    public void deleteEmployeeById(int empId) {
        employeeMapper.deleteEmployeeById(empId);
    }

}
