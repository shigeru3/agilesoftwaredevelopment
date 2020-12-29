package payroll.TextParserTransactionSource;

import payroll.TransactionApplication.TransactionSource;
import payroll.TransactionFactory.TransactionFactory;

public class TextParserTransactionSource extends TransactionSource {
	public String itsSource;
	public TransactionFactory itsTransactionFactory;
	
	public TextParserTransactionSource(TransactionFactory transactionFactory) {
		itsTransactionFactory = transactionFactory;
	}

	@Override
	public void Execute() {
		parse();
	}

	private void parse() {
	}

	@Override
	public void SetSource(String source) {
		itsSource = source;
	}
}
