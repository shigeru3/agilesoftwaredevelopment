package payroll.PayrollApplication;

import payroll.PayrollFactory.PayrollFactory;
import payroll.PayrollImplementation.PayrollFactoryImplementation;
import payroll.TextParserTransactionSource.TextParserTransactionSource;
import payroll.TransactionApplication.TransactionApplication;
import payroll.TransactionApplication.TransactionSource;
import payroll.TransactionFactory.TransactionFactory;
import payroll.TransactionImplementation.TransactionFactoryImplementation;

public class PayrollApplication extends TransactionApplication {
	private TransactionSource transactionSource;

	public PayrollApplication() {
		PayrollFactory payrollFactory = new PayrollFactoryImplementation();
		TransactionFactory transactionFactory = new TransactionFactoryImplementation(payrollFactory);
		transactionSource = new TextParserTransactionSource(transactionFactory);
	}

	@Override
	public void SetSource(String source) {
		transactionSource.SetSource(source);
		transactionSource.Execute();
	}

	public TransactionSource GetTransactionSource() {
		return transactionSource;
	}
}
