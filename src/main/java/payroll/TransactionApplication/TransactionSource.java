package payroll.TransactionApplication;

public abstract class TransactionSource implements Transaction {
	public abstract void SetSource(String source);
}
