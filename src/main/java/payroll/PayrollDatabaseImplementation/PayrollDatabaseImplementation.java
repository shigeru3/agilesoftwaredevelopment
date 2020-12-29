package payroll.PayrollDatabaseImplementation;

import payroll.PayrollDatabase.PayrollDatabase;
import payroll.PayrollDomain.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollDatabaseImplementation implements PayrollDatabase {
	private static Map<Integer, Employee> itsEmployee = new HashMap<>();
	private static Map<Integer, Employee> itsUnionMember = new HashMap<>();

	public void AddEmployee(int empId, Employee e) {
		itsEmployee.put(empId, e);
	}

	public Employee GetEmployee(int empId) {
		return itsEmployee.get(empId);
	}

	public void Clear() {
		itsEmployee.clear();
	}

	public void DeleteEmployee(int empId) {
		itsEmployee.remove(empId);
	}

	public void AddUnionMember(int memberId, Employee e) {
		itsUnionMember.put(memberId, e);
	}

	public Employee GetUnionMember(int memberId) {
		return itsUnionMember.get(memberId);
	}

	public void RemoveUnionMember(int memberId) {

	}

	public List<Integer> GetAllEmployeeIds() {
		return new ArrayList<>(itsEmployee.keySet());
	}
}
