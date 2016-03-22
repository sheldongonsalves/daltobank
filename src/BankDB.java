import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDB {
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static Bank bank=new Bank();
	static Date date=new Date();
	
	
	private  Connection connect() 
	{

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
		    
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	private Connection closed()
	{
		try{
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public int InsertNewRecord(String customerName, int accountNumber, String accountType, int balance ,int customerID ,int transactionID)
	{
		
		int count =0;
		try
		{
			connect();
			String sql ="insert into bankcustomer (cid,cname) values (?,?)";
			String sql1 ="insert into bankaccount (custid,account_no,account_type) values (?,?,?)";
			String sql2 ="insert into banktransaction (tid,accountno,balance,date_time) values (?,?,?,?)";
			//inserts value into bank customer
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,customerID);
			pstmt.setString(2,customerName );
			//inserts value into bank account
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1,customerID);
			pstmt1.setInt(2,accountNumber );
			pstmt1.setString(3, accountType);
			//inserts value into bank transaction
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1,transactionID);
			pstmt2.setInt(2,accountNumber );
			pstmt2.setInt(3, balance);
			pstmt2.setString(4,date.toString());
			
			count =pstmt.executeUpdate();
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();
			closed();
		}
        catch (SQLException e)
		{
        	e.printStackTrace();
		}
			
	
		return count;
	}
	
	public Bank SearchRecord(int accountNumber)
	
	{
		try
		{
			connect();
			String sql ="Select * from banktransaction where accountno=? and date_time =(Select max(DATE_TIME)from banktransaction where accountno=?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,accountNumber);
			pstmt.setInt(2,accountNumber);
			
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				bank.setTransactionID(rs.getInt(1));
				bank.setAccountNumber(rs.getInt(2));
				bank.setBalance(rs.getInt(3));
				
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return bank;
	}
	public Bank enterTranaction(int transactionID , int accountNumber , int balance)
	{
		try
		{
		connect();
		String sql2 ="insert into banktransaction (tid,accountno,balance,date_time) values (?,?,?,?)";
		PreparedStatement pstmt2 = con.prepareStatement(sql2);
		pstmt2.setInt(1,transactionID);
		pstmt2.setInt(2,accountNumber );
		pstmt2.setInt(3, balance);
		pstmt2.setString(4,date.toString());
		pstmt2.executeUpdate();
		SearchRecord(accountNumber);
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return bank;
	}

}
