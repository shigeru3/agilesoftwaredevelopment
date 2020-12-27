package salary;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
		TimeCardTransaction tct = new TimeCardTransaction(new GregorianCalendar(2001, Calendar.OCTOBER, 31), 8.0, empId);
		tct.Execute();

		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.GetClassification();
		HourlyClassification hc = (HourlyClassification) pc;
		assertNotNull(hc);

		TimeCard tc = hc.GetTimeCard(new GregorianCalendar(2001, Calendar.OCTOBER, 31));
		assertNotNull(tc);
		assertEquals(8.0, tc.GetHours());
	}

	public void testSalesReceiptTransaction() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Tim", "New York", 500.00, 2.0);
		t.Execute();
		SalesReceiptTransaction srt = new SalesReceiptTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 9), 100.0, empId);
		srt.Execute();

		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.GetClassification();
		CommissionedClassification cc = (CommissionedClassification) pc;
		assertNotNull(cc);

		SalesReceipt sr = cc.GetSalesReceipt(new GregorianCalendar(2001, Calendar.NOVEMBER, 9));
		assertNotNull(sr);
		assertEquals(100.0, sr.GetAmount());
	}

	public void testAddServiceCharge() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		TimeCardTransaction tct = new TimeCardTransaction(new GregorianCalendar(2001, Calendar.OCTOBER, 31), 8.0, empId);
		tct.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);

		int memberId = 86;
		Affiliation af = new UnionAffiliation(memberId, 12.5);
		e.SetAffiliation(af);
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

	public void testChangeCommissionedTransaction() {
		int empId = 5;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Simon", "England", 15.00);
		t.Execute();
		ChangeCommissionedTransaction cct = new ChangeCommissionedTransaction(empId, 2000.00, .25);
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

	public void testChangeDirectMethodTransaction() {
		int empId = 6;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "John", "SFA", 16.00);
		t.Execute();
		ChangeDirectTransaction cdt = new ChangeDirectTransaction(empId, "Swiss", "John");
		cdt.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.GetMethod();
		assertNotNull(pm);
		DirectMethod dm = (DirectMethod) pm;
		assertNotNull(dm);
		assertEquals("Swiss", dm.GetBank());
	}

	public void testChangeMailMethodTransaction() {
		int empId = 6;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "John", "SFA", 16.00);
		t.Execute();
		ChangeMailTransaction cmt = new ChangeMailTransaction(empId, "Swiss");
		cmt.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.GetMethod();
		assertNotNull(pm);
		MailMethod mm = (MailMethod) pm;
		assertNotNull(mm);
		assertEquals("Swiss", mm.GetAddress());
	}

	public void testHoldTransaction() {
		int empId = 6;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "John", "Swiss", 17.00);
		t.Execute();
		ChangeHoldTransaction cht = new ChangeHoldTransaction(empId, "Swiss");
		cht.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.GetMethod();
		assertNotNull(pm);
		HoldMethod hm = (HoldMethod) pm;
		assertNotNull(hm);
		assertEquals("Swiss", hm.GetAddress());
	}

	public void testChangeMemberTransaction() {
		int empId = 2;
		int memberId = 7734;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42);
		cmt.Execute();
		Employee e = PayrollDatabase.GetEmployee(empId);
		assertNotNull(e);
		Affiliation af = e.GetAffiliation();
		assertNotNull(af);
		UnionAffiliation uf = (UnionAffiliation) af;
		assertNotNull(uf);
		assertEquals(99.42, uf.GetDues());
		Employee member = PayrollDatabase.GetUnionMember(memberId);
		assertNotNull(member);
		assertEquals(e, member);
	}

	public void testPaySingleSalariedEmployee() {
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 30);
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 1000.0);
	}

	public void testPaySingleSalariedEmployeeOnWrongDate() {
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 29);
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}

	public void testPaySingleHourlyEmployeeNoTimeCards() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 0.0);
	}

	public void testPaySingleHourlyEmployeeOneTimeCard() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId);
		tc.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 30.5);
	}

	public void testPaySingleHourlyEmployeeOvertimeOneTimeCard() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		TimeCardTransaction tc = new TimeCardTransaction(payDate, 9.0, empId);
		tc.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
	}

	public void testPaySingleHourlyEmployeeOnWrongDate() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 8);
		TimeCardTransaction tc = new TimeCardTransaction(payDate, 9.0, empId);
		tc.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}

	public void testPaySingleHourlyEmployeeTwoCards() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId);
		tc.Execute();
		TimeCardTransaction tc2 = new TimeCardTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 8), 5.0, empId);
		tc2.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 7 * 15.25);
	}

	public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() {
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Calendar dateInPreviousPayPeriod = new GregorianCalendar(2001, Calendar.NOVEMBER, 2);
		TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId);
		tc.Execute();
		TimeCardTransaction tc2 = new TimeCardTransaction(dateInPreviousPayPeriod, 5.0, empId);
		tc2.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2 * 15.25);
	}

	public void testPaySingleCommissionedEmployeeNoSalesReceipts() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2500.00);
	}

	public void testPaySingleCommissionedEmployeeOneSalesReceipt() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		SalesReceiptTransaction srt = new SalesReceiptTransaction(payDate, 13000.0, empId);
		srt.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2500.0 + .032 * 13000);
	}

	public void testPaySingleCommissionedEmployeeTwoSalesReceipt() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		SalesReceiptTransaction srt = new SalesReceiptTransaction(payDate, 13000.0, empId);
		srt.Execute();
		SalesReceiptTransaction srt2 = new SalesReceiptTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 8), 24000, empId);
		srt2.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2500.0 + .032 * 13000 + .032 * 24000);
	}

	public void testPaySingleCommissionedEmployeeWrongDate() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 16);
		SalesReceiptTransaction srt = new SalesReceiptTransaction(payDate, 13000, empId);
		srt.Execute();
		SalesReceiptTransaction srt2 = new SalesReceiptTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 15), 24000, empId);
		srt2.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}

	public void testPaySingleCommissionedEmployeeSpanMultiplePayPeriods() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		Calendar earlyDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 23);
		Calendar lateDate = new GregorianCalendar(2001, Calendar.DECEMBER, 7);
		SalesReceiptTransaction srt = new SalesReceiptTransaction(payDate, 13000, empId);
		SalesReceiptTransaction srt2 = new SalesReceiptTransaction(earlyDate, 24000, empId);
		SalesReceiptTransaction srt3 = new SalesReceiptTransaction(lateDate, 15000, empId);
		srt.Execute();
		srt2.Execute();
		srt3.Execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2500.0 + .032 * 13000);
	}

	private void ValidatePaycheck(PaydayTransaction pt, int empId, Calendar payDate, double pay) {
		Paycheck pc = pt.GetPaycheck(empId);
		assertNotNull(pc);
		assertEquals(pc.GetPayPeriodEndDate(), payDate);
		assertEquals(pay, pc.GetGrossPay());
		assertEquals("Hold", pc.GetField("Disposition"));
		assertEquals(0.0, pc.GetDeductions());
		assertEquals(pay, pc.GetNetPay());
	}

}
