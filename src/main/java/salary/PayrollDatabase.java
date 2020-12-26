package salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PayrollDatabase {
	private static Map<Integer, Employee> itsEmployee = new HashMap<>();
	private static Map<Integer, Employee> itsUnionMember = new HashMap<>();

	public static void AddEmployee(int empId, Employee e) {
		itsEmployee.put(empId, e);
	}

	public static Employee GetEmployee(int empId) {
		return itsEmployee.get(empId);
	}

	public static void Clear() {
		itsEmployee.clear();
	}

	public static void DeleteEmployee(int empId) {
		itsEmployee.remove(empId);
	}

	public static void AddUnionMember(int memberId, Employee e) {
		itsUnionMember.put(memberId, e);
	}

	public static Employee GetUnionMember(int memberId) {
		return itsUnionMember.get(memberId);
	}

	public static void RemoveUnionMember(int memberId) {

	}

	public static List<Integer> GetAllEmployeeIds() {
		return new ArrayList<>(itsEmployee.keySet());
	}
}
