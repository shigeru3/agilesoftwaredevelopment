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

	public void testTimeCardTransaction() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		TimeCardTransaction tct = new TimeCardTransaction(20011031, 8.0, empId);
		tct.Execute();

		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.GetClassification();
		HourlyClassification hc = (HourlyClassification) pc;
		assertNotNull(hc);

		TimeCard tc = hc.GetTimeCard(20011031);
		assertNotNull(tc);
		assertEquals(8.0, tc.GetHours());
	}

	public void testSalesReceiptTransaction() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Tim", "New York", 500.00, 2.0);
		t.Execute();
		SalesReceiptTransaction srt = new SalesReceiptTransaction(20201225, 100, empId);
		srt.Execute();

		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.GetClassification();
		CommissionedClassification cc = (CommissionedClassification) pc;
		assertNotNull(cc);

		SalesReceipt sr = cc.GetSalesReceipt(20201225);
		assertNotNull(sr);
		assertEquals(100, sr.GetAmount());
	}

	public void testAddServiceCharge() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		TimeCardTransaction tct = new TimeCardTransaction(20011031, 8.0, empId);
		tct.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);

		Affiliation af = new UnionAffiliation(12.5);
		e.SetAffiliation(af);
		int memberId = 86;
		PayrollDatabase.AddUnionMember(memberId, e);
		ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 20011031, 12.95);
		sct.Execute();
		double sc = af.GetServiceCharge(20011031);
		assertEquals(12.95, sc, .001);
	}

	public void testChangeNameTransaction() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bob");
		cnt.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		assertEquals("Bob", e.GetName());
	}

	public void testChangeAddressTransaction() {
		int empId = 3;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Andrew", "SFA", 22.13);
		t.Execute();
		ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, "LA");
		cat.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		assertEquals("LA", e.GetAddress());
	}

	public void testChangeHourlyTransaction() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.Execute();
		ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.25);
		cht.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.GetClassification();
		assertNotNull(pc);
		HourlyClassification hc = (HourlyClassification) pc;
		assertNotNull(hc);
		assertEquals(27.25, hc.GetRate());
		PaymentSchedule ps = e.GetSchedule();
		WeeklySchedule ws = (WeeklySchedule) ps;
		assertNotNull(ws);
	}

	public void testChangeSalariedTransaction() {
		int empId = 4;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Eric", "SFA", 28.00);
		t.Execute();
		ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, 2500.00);
		cst.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.GetClassification();
		assertNotNull(pc);
		SalariedClassification sc = (SalariedClassification) pc;
		assertNotNull(sc);
		assertEquals(2500.00, sc.GetSalary());
		PaymentSchedule ps = e.GetSchedule();
		MonthlySchedule ms = (MonthlySchedule) ps;
		assertNotNull(ms);
	}

	public void testCommissionedTransaction() {
		int empId = 5;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Simon", "England", 15.00);
		t.Execute();
		ChangeCommissionedTransaction cct = new ChangeCommissionedTransaction(empId, 2000.00);
		cct.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.GetClassification();
		assertNotNull(pc);
		CommissionedClassification cc = (CommissionedClassification) pc;
		assertNotNull(cc);
		assertEquals(2000.00, cc.GetSalary());
		PaymentSchedule ps = e.GetSchedule();
		BiweeklySchedule bs = (BiweeklySchedule) ps;
		assertNotNull(bs);
	}
}
