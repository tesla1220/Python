package thefirstgroup.crud_practice.model.dao;

import org.apache.ibatis.annotations.Mapper;
import thefirstgroup.crud_practice.model.dto.EmployeeDTO;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    int editSearch(int empId);

    List<EmployeeDTO> findAllEmployees();

    EmployeeDTO searchEmployee(int empId);

    void deleteEmployeeById(int empId);
}
