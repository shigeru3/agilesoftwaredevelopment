package salary;

public class Employee {
	private int itsEmpId;
	private String itsName;
	private String itsAddress;
	private PaymentClassification paymentClassification;
	private PaymentSchedule paymentSchedule;
	private PaymentMethod paymentMethod;
	private Affiliation itsAffiliation;

	public Employee(int empId, String name, String address) {
		itsEmpId = empId;
		itsName = name;
		itsAddress = address;
	}

	public String GetName() {
		return itsName;
	}

	public PaymentClassification GetClassification() {
		return paymentClassification;
	}

	public PaymentSchedule GetSchedule() {
		return paymentSchedule;
	}

	public PaymentMethod GetMethod() {
		return paymentMethod;
	}

	public void SetClassification(PaymentClassification pc) {
		paymentClassification = pc;
	}

	public void SetSchedule(PaymentSchedule ps) {
		paymentSchedule = ps;
	}

	public void SetMethod(PaymentMethod pm) {
		paymentMethod = pm;
	}

	public void SetAffiliation(Affiliation af) {
		itsAffiliation = af;
	}

	public Affiliation GetAffiliation() {
		return itsAffiliation;
	}

	public void SetName(String name) {
		itsName = name;
	}

	public void SetAddress(String address) {
		itsAddress = address;
	}

	public String GetAddress() {
		return itsAddress;
	}
}
