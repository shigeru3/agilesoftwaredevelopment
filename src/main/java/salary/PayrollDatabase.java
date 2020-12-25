package salary;

import java.util.HashMap;
import java.util.Map;

class PayrollDatabase {
	private static Map<Integer, Employee> itsEmployee = new HashMap<>();

	public static void AddEmployee(int empId, Employee e) {
		itsEmployee.put(empId, e);
	}

	public static Employee GetEmployee(int empId) {
		return itsEmployee.get(empId);
	}

	public static void Clear() {
		itsEmployee.clear();
	}
}