package main.db;

import java.util.ArrayList;
import java.util.HashMap;

import main.account.Account;
import main.account.Loan;
import main.account.factory.AccountFactory;
import main.utility.Formatter;

public class LoanList extends Sequenced {
	private static LoanList instance = null;
	private HashMap<String, Loan> loadRecordList = new HashMap<>();
	
	private LoanList() {}
	
	public static LoanList getInstance() {
		if (instance == null)
			instance = new LoanList();
		return instance;
	}

	public HashMap<String, Loan> getList() {
		return loadRecordList;
	}

	// add loan record
	public void addLoanRecord(Loan loan) {
		String loanId = getSequence();
		loadRecordList.put(loanId, loan);
		Account user = AccountList.findAccount(loan.getAccountId());
		user.addLoanRecord(loanId);
	}

	// return if account loan exist
	public boolean hasLoan(String accNo) {
		return loadRecordList.containsKey(accNo);
	}
	
	// find account by account number
	public Loan findLoadRecord(String loanId) {
		if (loadRecordList.containsKey(loanId))
			return loadRecordList.get(loanId);
		return null;
	}

	// find account by account number
	public ArrayList<Loan> findUserAllLoanRecord(String accNo) {
		if (AccountList.hasAccount(accNo)) {
			Account user = AccountList.findAccount(accNo);
			ArrayList<Loan> userLoanList = new ArrayList<>();
			for (String loanId: user.getLoanRecordId())
				userLoanList.add(findLoadRecord(loanId));
			return userLoanList;
		}
		return null;
	}
}
