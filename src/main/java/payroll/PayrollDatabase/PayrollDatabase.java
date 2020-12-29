package payroll.PayrollDatabase;

import payroll.PayrollDomain.Employee;

import java.util.List;

public interface PayrollDatabase {
	void AddEmployee(int empId, Employee e);

	Employee GetEmployee(int empId);

	void Clear();

	void DeleteEmployee(int empId);

	void AddUnionMember(int memberId, Employee e);

	Employee GetUnionMember(int memberId);

	void RemoveUnionMember(int memberId);

	List<Integer> GetAllEmployeeIds();
}
