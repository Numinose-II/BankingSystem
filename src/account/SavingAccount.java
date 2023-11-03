package account;

import bank.Bank;
import constant.AccountType;

import java.math.BigDecimal;

import account.factory.SavingAccountPara;

// SavingAccount inherits from Account.
// It is a special type of account
public class SavingAccount extends Account {
	protected final AccountType type = AccountType.SAVING;		// account type = saving
    private BigDecimal targetAmount = new BigDecimal(100000);	// target amount
    
    

    
    public SavingAccount(String accNo, SavingAccountPara para) {
    	super(accNo, para);
        this.targetAmount = para.targetAmount;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

	// basic information of the saving account.
    @Override
    public String toString(){
        return super.toString() + String.format(" | Target Amount: %s\n", Bank.df.format(targetAmount));
    }
}