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

	public void testAddHourlyEmployee() {
		int empId = 1;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Stefan", "Swiss", 10.00);
		t.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		assertEquals("Stefan", e.GetName());

		PaymentClassification pc = e.GetClassification();
		HourlyClassification hc = (HourlyClassification) pc;
		assertNotNull(hc);
		assertEquals(10.00, hc.GetSalary());

		PaymentSchedule ps = e.GetSchedule();
		WeeklySchedule ws = (WeeklySchedule) ps;
		assertNotNull(ws);

		PaymentMethod pm = e.GetMethod();
		HoldMethod hm = (HoldMethod) pm;
		assertNotNull(hm);
	}

	public void testCommissionedEmployee() {
		int empId = 1;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Charles", "US", 500.00, 3.2);
		t.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		assertEquals("Charles", e.GetName());

		PaymentClassification pc = e.GetClassification();
		CommissionedClassification cc = (CommissionedClassification) pc;
		assertNotNull(cc);
		assertEquals(500.00, cc.GetSalary());

		PaymentSchedule ps = e.GetSchedule();
		BiweeklySchedule bs = (BiweeklySchedule) ps;
		assertNotNull(bs);

		PaymentMethod pm = e.GetMethod();
		HoldMethod hm = (HoldMethod) pm;
		assertNotNull(hm);
	}

	public void testDeleteEmployee() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);

		DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
		dt.Execute();
		e = PayrollDatabase.GetEmployee(empId);
		assertNull(e);
	}
}
