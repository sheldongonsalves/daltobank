import java.util.Scanner;
import java.util.Random;
public class BankAPP {
	static Scanner sc= new Scanner(System.in);
	static Random rnd = new Random();
	
	static BankDB bankdb=new BankDB();
	static int choice;
	public static void main(String []args)
	{
		
		String customerName ,accountType ;
		int amount =0,accountNumber=0 ,customerID=0 ,transactionID =0;
		Bank bank = new Bank();
		System.out.println("Welcome to Dalton Bank");
		System.out.println("Enter:\n1.To create a new account\n2.To access existing account\n3.To exit");
		choice=sc.nextInt();
		sc.nextLine();
		switch(choice)
		{
		case 1:
		{
			System.out.println("Enter Your name:");
			customerName=sc.nextLine();
			bank.setCustomerName(customerName);;

			System.out.println("enter the account type Checking or Saving");
			accountType=sc.nextLine();
			bank.setAccountType(accountType);

			System.out.println("Enter an amount you want to deposit");
			amount=sc.nextInt();
			bank.setBalance(amount);


			System.out.println("Enter the account number of your choice");
			accountNumber=sc.nextInt();
			bank.setAccountNumber(accountNumber);

			customerID=1+rnd.nextInt(1000);
			bank.setCustomerID(customerID);

			transactionID=1+rnd.nextInt(1000);
			bank.setTransactionID(transactionID);

			bankdb.InsertNewRecord(customerName, accountNumber, accountType, amount, customerID, transactionID);

			break;

		}
		case 2:
		{
			System.out.println("Enter the account number ");
			accountNumber=sc.nextInt();

			Bank bank1 =bankdb.SearchRecord(accountNumber);
			System.out.println("transaction id:"+bank1.getTransactionID());
			System.out.println("Account Number:"+bank1.getAccountNumber());
			System.out.println("Balance:"+bank1.getBalance());
			transaction(bank1.getAccountNumber(),bank1.getBalance());
			

			break;


		}
		case 3:
		{
			System.out.println("You have chosen to exit");
			System.exit(0);
			break;
		}

		}

	}
	public static void transaction(int accountNumber ,int balance)
	{
		int amount=0,newamt=0,newTransactionId=0;
		Bank bank2 =new Bank();
		System.out.println("Do you want to \n1.Deposit\n2.Withdraw\n3.Transfer\n4.Exit\n");
		choice =sc.nextInt();
		switch(choice)
		{
		case 1:
		{
			System.out.println("Enter the amount you want to deposit");
			amount =sc.nextInt();
			newamt=balance+ amount;
			newTransactionId=1+rnd.nextInt(1000);
			Bank b1=bankdb.enterTranaction(newTransactionId, accountNumber, newamt);
			System.out.println("Your New Balance is :"+b1.getBalance());
			break;
			
		}
		case 2:
		{
			System.out.println("Enter the amount you want to withdraw");
			amount =sc.nextInt();
			if(balance >=amount)
			{
			newamt=balance- amount;
			}
			if(balance<amount)
			{
				System.out.println("You are Charged with a over draft fee of $35 on account of low balance");
				newamt=balance-amount-35;
			}
			newTransactionId=1+rnd.nextInt(1000);
			Bank b2=bankdb.enterTranaction(newTransactionId, accountNumber, newamt);
			System.out.println("Your New Balance is :"+b2.getBalance());
			break;
		}
		case 3:
		{
			
			break;
		}
		case 4:
		{
			System.exit(0);
		}
		}
	}
	
	public static void transferFunds()
	{
		int accountno=0,  amount =0;
		System.out.println("Enter the account number you want to transfer to");
		accountno=sc.nextInt();
		Bank bank1 =bankdb.SearchRecord(accountno);
		
		System.out.println("Enter the amount you want to transfer");
		amount=sc.nextInt();
		
		
	}
	
	

}
