package payroll.PayrollFactory;

import payroll.PayrollDomain.*;
import payroll.PayrollImplementation.SalesReceipt;

import java.util.Calendar;

public interface PayrollFactory {
	PaymentSchedule makeBiweeklySchedule();

	PaymentClassification makeCommissionedClassification(double salary, double commissionRate);

	PaymentMethod makeDirectMethod(String bank, String account);

	PaymentMethod makeHoldMethod();

	PaymentClassification makeHourlyClassification(double hourlyRate);

	PaymentMethod makeMailMethod(String address);

	PaymentSchedule makeMonthlySchedule();

	PaymentClassification makeSalariedClassification(double salary);

	SalesReceipt makeSalesReceipt(Calendar salesDate, double amount);

	Affiliation makeUnionAffiliation(int memberId, double dues);

	PaymentSchedule makeWeeklySchedule();

	Affiliation makeNoAffiliation();

	Employee makeEmployee(int empId, String name, String address);

	Paycheck makePayCheck(Calendar getPayPeriodStartDate, Calendar payDate);
}
