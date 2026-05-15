package com.msa4spring.mappers;

import com.msa4spring.entities.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 인터페이스를 구현할 수 있는 클래스를 만들어줌.
@Mapper
public interface EmployeeMapper {
    List<Employee> getEmployees();

    Employee findByPk(long empId);

    int store(Employee employee);
}
