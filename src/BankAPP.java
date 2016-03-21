import java.util.Scanner;
import java.util.Random;
public class BankAPP {
	static Scanner sc= new Scanner(System.in);
	static Random rnd = new Random();
	public static void main(String []args)
	{
		int choice;
		String customerName ,accountType ;
		int amount =0,accountNumber=0 ,customerID=0 ,transactionID =0;
		Bank bank = new Bank();
		BankDB bankdb=new BankDB();
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
			
			customerID=rnd.nextInt();
			bank.setCustomerID(customerID);
			
			transactionID=rnd.nextInt();
			bank.setTransactionID(transactionID);
			
			bankdb.InsertNewRecord(customerName, accountNumber, accountType, amount, customerID, transactionID);
			
			break;
					
		}
		case 2:
		{
			System.out.println("Enter the account number ");
			accountNumber=sc.nextInt();
			bank.setAccountNumber(accountNumber);
			bankdb.SearchRecord(accountNumber);
			
		}
		
		}
		
	}

}
