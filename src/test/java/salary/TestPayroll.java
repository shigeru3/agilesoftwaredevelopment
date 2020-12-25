package salary;

import junit.framework.TestCase;

public class TestPayroll extends TestCase {
	public void testAddSalariedEmployee() {
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
		t.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		assertEquals("Bob", e.GetName());

		PaymentClassification pc = e.GetClassification();
		SalariedClassification sc = (SalariedClassification) pc;
		assertNotNull(sc);
		assertEquals(1000.00, sc.GetSalary());

		PaymentSchedule ps = e.GetSchedule();
		MonthlySchedule ms = (MonthlySchedule) ps;
		assertNotNull(ms);

		PaymentMethod pm = e.GetMethod();
		HoldMethod hm = (HoldMethod) pm;
		assertNotNull(hm);
	}
}
