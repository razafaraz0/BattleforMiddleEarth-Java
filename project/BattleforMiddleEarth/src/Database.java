import java.sql.*;
import java.util.*;
public class Database {
	
	
	Connection con=null;
	private boolean check = false;
	public static int count=0;
	private boolean flag;
	private int least;

	public boolean isFlag() {
		return flag;
	}




	public void setFlag(boolean flag) {
		this.flag = flag;
	}




	public Connection dbConnection()
	{
		
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Arif\\Desktop\\alan\\BattleForMiddleEarth\\BattleForMiddleEarth.sqlite");
	      System.out.println("Database is succesfully connected.");
	     
	    } 
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	     return con;
	}
	
	
	
	
	public void addUser(Account obj)
	{
		
		con = dbConnection();
		try {
			String mystr="Insert into Users(Username,Password,LastLevel) values ('"+ obj.getName() +"','"+ obj.getPassword() +"','"+obj.getCurrentLevel() +"')";
			 PreparedStatement pst = con.prepareStatement(mystr);
			 pst.execute();
			 obj.setId(getID(obj.getName()));
			 pst.close();
			 flag=true;
			 
			} catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      
			      flag= false;
			      
			}
		
		for(int i = 0; i <5; i++)
			createHighscoreSpace(obj);
		
		
	}
	private void createHighscoreSpace(Account obj)
	{
		
		try {
			String mystr="Insert into Scores(id,score) values ('"+ obj.getId() +"','"+ obj.getScore() +"')";
			 PreparedStatement pst = con.prepareStatement(mystr);
			 pst.execute();
			 pst.close();
	
			} catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      
		
			}
		
	}
	private int newScore(Account obj)
	{
		int y=0;
		try {
			
			Statement stmt = null;
			
			 stmt = con.createStatement();
			
		     ResultSet rs = stmt.executeQuery( "SELECT * FROM Scores WHERE id = '"+ obj.getId()+ "'" );
		     
		     y = rs.getInt("score");
		      while ( rs.next() ) 
		      {
		    	  
		          if(rs.getInt("score")< y)
		          {
		        	  y =rs.getInt("score");

		          }
		       }
		      stmt.close();
		      
			} 
			catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      
			}
		least = y;
		System.out.println(y);
		if(obj.getScore()>y)
			return obj.getScore();
		else
			return y;
		
	}
	public void addHighscore(Account obj)
	{
		int temp = newScore(obj);
		
		try {
			 String mystr="UPDATE Scores SET score="+ temp + " WHERE id= "+obj.getId()+" AND score = "+ least;
			 PreparedStatement pst = con.prepareStatement(mystr);
			 pst.execute();
			 pst.close();
			} 
			catch ( Exception e ) 
			{
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
	}
	
	public int getID(String name)
	{
		con = dbConnection();
		
		Account  i1= null;
		try {
			
			Statement stmt = null;
			
			 stmt = con.createStatement();
			
		     ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );
		     
		      while ( rs.next() ) 
		      {
		          if(name.equals(rs.getString("Username")))
		          {
		        	  
		        	  return rs.getInt("id");
			          
		          }
		       }
		      stmt.close();
		      
			} 
			catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      
			}
		
		return -1;
		
	}
	
	public Account isPassed(String name, String password)
	{
		con = dbConnection();
		
		Account  i1= null;
		try {
			
			Statement stmt = null;
			
			 stmt = con.createStatement();
			
		     ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );
		     
		      while ( rs.next() ) 
		      {
		          if(name.equals(rs.getString("Username")) && password.equals(rs.getString("Password")) )
		          {
		        	  
		        	  i1 = new Account(rs.getString("Username"),rs.getString("Password"),rs.getInt("LastLevel"));
		        	  i1.setId(rs.getInt("id"));
			          check = true;
		          }
		       }
		      stmt.close();
		      
			} 
			catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      
			}
		
		
		
	      return i1;
		
	}
	
	
	public void setPassword(Account i1)
	{
		con = dbConnection();
		try {
			 String mystr="UPDATE Users SET Password='"+ i1.getPassword()+ "' WHERE id= '"+i1.getId()+"'";
			 PreparedStatement pst = con.prepareStatement(mystr);
			 pst.execute();
			 pst.close();
			} catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
		
	}
	public void setUsername(Account i1)
	{
		con = dbConnection();
		try {
			 String mystr="UPDATE Users SET Username='"+ i1.getName()+ "' WHERE id= '"+i1.getId()+"'";
			 PreparedStatement pst = con.prepareStatement(mystr);
			 pst.execute();
			 pst.close();
			} catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
		
	}
	public void setLatestLevetl(Account i1)
	{
		con = dbConnection();
		try {
			 String mystr="UPDATE Users SET LastLevel='"+ i1.getCurrentLevel()+ "' WHERE id= '"+i1.getId()+"'";
			 PreparedStatement pst = con.prepareStatement(mystr);
			 pst.execute();
			 pst.close();
			} catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
		
	}
	
	public void getHighscores(Account obj)
	{
		
		try {
			
			Statement stmt = null;
			
			 stmt = con.createStatement();
			
		     ResultSet rs = stmt.executeQuery( "SELECT * FROM Scores WHERE id = '"+ obj.getId()+ "'" );
		     
		     
		      while ( rs.next() ) 
		      {
		    	  obj.getHighscores().add(rs.getInt("score"));
		        
		      }
		      stmt.close();
		      
			} 
			catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      
			}
	
		
		
	}
	
	
	

}
