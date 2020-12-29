package payroll;

import junit.framework.TestCase;
import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDatabaseImplementation.PayrollDatabaseImplementation;
import payroll.PayrollDomain.*;
import payroll.PayrollImplementation.*;
import payroll.TransactionApplication.Transaction;
import payroll.TransactionImplementation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestPayroll extends TestCase {
	private final PayrollFactoryImplementation pf = new PayrollFactoryImplementation();
	private final TransactionFactoryImplementation tf = new TransactionFactoryImplementation(pf);

	public void setUp() {
		GlobalDatabase.payrollDB = new PayrollDatabaseImplementation();
	}

	public void testAddSalariedEmployee() {
		int empId = 1;
		Transaction t = tf.makeAddSalariedEmployee(empId, "Bob", "Home", 1000.00);
		t.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
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
		Transaction t = tf.makeAddHourlyEmployee(empId, "Stefan", "Swiss", 10.00);
		t.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
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
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Charles", "US", 500.00, 3.2);
		t.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
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
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNotNull(e);

		Transaction dt = tf.makeDeleteEmployeeTransaction(empId);
		dt.Execute();
		e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNull(e);
	}

	public void testTimeCardTransaction() {
		int empId = 2;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Transaction tct = tf.makeTimeCardTransaction(new GregorianCalendar(2001, Calendar.OCTOBER, 31), 8.0, empId);
		tct.Execute();

		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
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
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Tim", "New York", 500.00, 2.0);
		t.Execute();
		Transaction srt = tf.makeSalesReceiptTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 9), 100.0, empId);
		srt.Execute();

		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
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
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Transaction tct = tf.makeTimeCardTransaction(new GregorianCalendar(2001, Calendar.OCTOBER, 31), 8.0, empId);
		tct.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNotNull(e);

		int memberId = 86;
		Affiliation af = pf.makeUnionAffiliation(memberId, 12.5);
		e.SetAffiliation(af);
		GlobalDatabase.payrollDB.AddUnionMember(memberId, e);
		Transaction sct = tf.makeServiceChargeTransaction(memberId, new GregorianCalendar(2001, Calendar.OCTOBER, 31), 12.95);
		sct.Execute();
		double sc = af.GetServiceCharge(new GregorianCalendar(2001, Calendar.OCTOBER, 31));
		assertEquals(12.95, sc, .001);
	}

	public void testChangeNameTransaction() {
		int empId = 2;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Transaction cnt = tf.makeChangeNameTransaction(empId, "Bob");
		cnt.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNotNull(e);
		assertEquals("Bob", e.GetName());
	}

	public void testChangeAddressTransaction() {
		int empId = 3;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Andrew", "SFA", 22.13);
		t.Execute();
		Transaction cat = tf.makeChangeAddressTransaction(empId, "LA");
		cat.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNotNull(e);
		assertEquals("LA", e.GetAddress());
	}

	public void testChangeHourlyTransaction() {
		int empId = 3;
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.Execute();
		Transaction cht = tf.makeChangeHourlyTransaction(empId, 27.25);
		cht.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
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
		Transaction t = tf.makeAddHourlyEmployee(empId, "Eric", "SFA", 28.00);
		t.Execute();
		Transaction cst = tf.makeChangeSalariedTransaction(empId, 2500.00);
		cst.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
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
		Transaction t = tf.makeAddHourlyEmployee(empId, "Simon", "England", 15.00);
		t.Execute();
		Transaction cct = tf.makeChangeCommissionedTransaction(empId, 2000.00, .25);
		cct.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
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
		Transaction t = tf.makeAddHourlyEmployee(empId, "John", "SFA", 16.00);
		t.Execute();
		Transaction cdt = tf.makeChangeDirectTransaction(empId, "Swiss", "John");
		cdt.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.GetMethod();
		assertNotNull(pm);
		DirectMethod dm = (DirectMethod) pm;
		assertNotNull(dm);
		assertEquals("Swiss", dm.GetBank());
	}

	public void testChangeMailMethodTransaction() {
		int empId = 6;
		Transaction t = tf.makeAddHourlyEmployee(empId, "John", "SFA", 16.00);
		t.Execute();
		Transaction cmt = tf.makeChangeMailTransaction(empId, "Swiss");
		cmt.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.GetMethod();
		assertNotNull(pm);
		MailMethod mm = (MailMethod) pm;
		assertNotNull(mm);
		assertEquals("Swiss", mm.GetAddress());
	}

	public void testHoldTransaction() {
		int empId = 6;
		Transaction t = tf.makeAddHourlyEmployee(empId, "John", "Swiss", 17.00);
		t.Execute();
		Transaction cht = tf.makeChangeHoldTransaction(empId);
		cht.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.GetMethod();
		assertNotNull(pm);
		HoldMethod hm = (HoldMethod) pm;
		assertNotNull(hm);
	}

	public void testChangeMemberTransaction() {
		int empId = 2;
		int memberId = 7734;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Transaction cmt = tf.makeChangeMemberTransaction(empId, memberId, 99.42);
		cmt.Execute();
		Employee e = GlobalDatabase.payrollDB.GetEmployee(empId);
		assertNotNull(e);
		Affiliation af = e.GetAffiliation();
		assertNotNull(af);
		UnionAffiliation uf = (UnionAffiliation) af;
		assertNotNull(uf);
		assertEquals(99.42, uf.GetDues());
		Employee member = GlobalDatabase.payrollDB.GetUnionMember(memberId);
		assertNotNull(member);
		assertEquals(e, member);
	}

	public void testPaySingleSalariedEmployee() {
		int empId = 1;
		Transaction t = tf.makeAddSalariedEmployee(empId, "Bob", "Home", 1000.0);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 30);
		Transaction pt = tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck((PaydayTransaction) pt, empId, payDate, 1000.0);
	}

	public void testPaySingleSalariedEmployeeOnWrongDate() {
		int empId = 1;
		Transaction t = tf.makeAddSalariedEmployee(empId, "Bob", "Home", 1000.00);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 29);
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}

	public void testPaySingleHourlyEmployeeNoTimeCards() {
		int empId = 2;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 0.0);
	}

	public void testPaySingleHourlyEmployeeOneTimeCard() {
		int empId = 2;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Transaction tc = tf.makeTimeCardTransaction(payDate, 2.0, empId);
		tc.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 30.5);
	}

	public void testPaySingleHourlyEmployeeOvertimeOneTimeCard() {
		int empId = 2;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Transaction tc = tf.makeTimeCardTransaction(payDate, 9.0, empId);
		tc.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
	}

	public void testPaySingleHourlyEmployeeOnWrongDate() {
		int empId = 2;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 8);
		Transaction tc = tf.makeTimeCardTransaction(payDate, 9.0, empId);
		tc.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}

	public void testPaySingleHourlyEmployeeTwoCards() {
		int empId = 2;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Transaction tc = tf.makeTimeCardTransaction(payDate, 2.0, empId);
		tc.Execute();
		Transaction tc2 = tf.makeTimeCardTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 8), 5.0, empId);
		tc2.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 7 * 15.25);
	}

	public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() {
		int empId = 2;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Calendar dateInPreviousPayPeriod = new GregorianCalendar(2001, Calendar.NOVEMBER, 2);
		Transaction tc = tf.makeTimeCardTransaction(payDate, 2.0, empId);
		tc.Execute();
		Transaction tc2 = tf.makeTimeCardTransaction(dateInPreviousPayPeriod, 5.0, empId);
		tc2.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2 * 15.25);
	}

	public void testPaySingleCommissionedEmployeeNoSalesReceipts() {
		int empId = 3;
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2500.00);
	}

	public void testPaySingleCommissionedEmployeeOneSalesReceipt() {
		int empId = 3;
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Transaction srt = tf.makeSalesReceiptTransaction(payDate, 13000.0, empId);
		srt.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2500.0 + .032 * 13000);
	}

	public void testPaySingleCommissionedEmployeeTwoSalesReceipt() {
		int empId = 3;
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Transaction srt = tf.makeSalesReceiptTransaction(payDate, 13000.0, empId);
		srt.Execute();
		Transaction srt2 = tf.makeSalesReceiptTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 8), 24000, empId);
		srt2.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2500.0 + .032 * 13000 + .032 * 24000);
	}

	public void testPaySingleCommissionedEmployeeWrongDate() {
		int empId = 3;
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 16);
		Transaction srt = tf.makeSalesReceiptTransaction(payDate, 13000, empId);
		srt.Execute();
		Transaction srt2 = tf.makeSalesReceiptTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 15), 24000, empId);
		srt2.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}

	public void testPaySingleCommissionedEmployeeSpanMultiplePayPeriods() {
		int empId = 3;
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		Calendar earlyDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 23);
		Calendar lateDate = new GregorianCalendar(2001, Calendar.DECEMBER, 7);
		Transaction srt = tf.makeSalesReceiptTransaction(payDate, 13000, empId);
		Transaction srt2 = tf.makeSalesReceiptTransaction(earlyDate, 24000, empId);
		Transaction srt3 = tf.makeSalesReceiptTransaction(lateDate, 15000, empId);
		srt.Execute();
		srt2.Execute();
		srt3.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		ValidatePaycheck(pt, empId, payDate, 2500.0 + .032 * 13000);
	}

	public void testSalariedUnionMemberDues() {
		int empId = 1;
		Transaction t = tf.makeAddSalariedEmployee(empId, "Bob", "Home", 1000.0);
		t.Execute();
		int memberId = 7734;
		Transaction cmt = tf.makeChangeMemberTransaction(empId, memberId, 9.42);
		cmt.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 30);

		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertEquals(pc.GetPayPeriodEndDate(), payDate);
		assertEquals(1000.0, pc.GetGrossPay());
		assertEquals("Hold", pc.GetField("Disposition"));
		assertEquals(9.42 * 5, pc.GetDeductions());
		assertEquals(1000.0 - 9.42 * 5, pc.GetNetPay());
	}

	public void testHourlyUnionMemberServiceCharge() {
		int empId = 1;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.24);
		t.Execute();
		int memberId = 7734;
		Transaction cmt = tf.makeChangeMemberTransaction(empId, memberId, 9.42);
		cmt.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Transaction sct = tf.makeServiceChargeTransaction(memberId, payDate, 19.42);
		sct.Execute();
		Transaction tct = tf.makeTimeCardTransaction(payDate, 8.0, empId);
		tct.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNotNull(pc);
		assertEquals(pc.GetPayPeriodEndDate(), payDate);
		assertEquals(8 * 15.24, pc.GetGrossPay());
		assertEquals("Hold", pc.GetField("Disposition"));
		assertEquals(9.42 + 19.42, pc.GetDeductions());
		assertEquals(8 * 15.24 - (9.42 + 19.42), pc.GetNetPay());
	}

	public void testServiceChargeSpanningMultiplePayPeriods() {
		int empId = 1;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.24);
		t.Execute();
		int memberId = 7734;
		Transaction cmt = tf.makeChangeMemberTransaction(empId, memberId, 9.42);
		cmt.Execute();
		Calendar earlyDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 2);
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Calendar lateDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 16);
		Transaction sct = tf.makeServiceChargeTransaction(memberId, payDate, 19.42);
		Transaction sct2 = tf.makeServiceChargeTransaction(memberId, earlyDate, 100.00);
		Transaction sct3 = tf.makeServiceChargeTransaction(memberId, lateDate, 200.00);
		sct.Execute();
		sct2.Execute();
		sct3.Execute();
		Transaction tct = tf.makeTimeCardTransaction(payDate, 8.0, empId);
		tct.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNotNull(pc);
		assertEquals(pc.GetPayPeriodEndDate(), payDate);
		assertEquals(8 * 15.24, pc.GetGrossPay());
		assertEquals("Hold", pc.GetField("Disposition"));
		assertEquals(9.42 + 19.42, pc.GetDeductions());
		assertEquals(8 * 15.24 - (9.42 + 19.42), pc.GetNetPay());
	}

	public void testHourlyUnionMemberDues() {
		int empId = 1;
		Transaction t = tf.makeAddHourlyEmployee(empId, "Bill", "Home", 15.24);
		t.Execute();
		int memberId = 7734;
		Transaction cmt = tf.makeChangeMemberTransaction(empId, memberId, 9.42);
		cmt.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		Transaction tct = tf.makeTimeCardTransaction(payDate, 8.0, empId);
		tct.Execute();
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNotNull(pc);
		assertEquals(pc.GetPayPeriodEndDate(), payDate);
		assertEquals(8 * 15.24, pc.GetGrossPay());
		assertEquals("Hold", pc.GetField("Disposition"));
		assertEquals(9.42, pc.GetDeductions());
		assertEquals(8 * 15.24 - 9.42, pc.GetNetPay());
	}

	public void testCommissionedUnionMemberDues() {
		int empId = 3;
		Transaction t = tf.makeAddCommissionedEmployee(empId, "Lance", "Home", 2500, .032);
		t.Execute();
		int memberId = 7734;
		Transaction cmt = tf.makeChangeMemberTransaction(empId, memberId, 9.42);
		cmt.Execute();
		Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
		PaydayTransaction pt = (PaydayTransaction) tf.makePaydayTransaction(payDate);
		pt.Execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNotNull(pc);
		assertEquals(pc.GetPayPeriodEndDate(), payDate);
		assertEquals(2500.0, pc.GetGrossPay());
		assertEquals("Hold", pc.GetField("Disposition"));
		assertEquals(2 * 9.42, pc.GetDeductions());
		assertEquals(2500.0 - 2 * 9.42, pc.GetNetPay());
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
