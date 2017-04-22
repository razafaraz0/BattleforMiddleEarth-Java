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
	
	
	/*public int getLastIndex()
	{
		int x = 0;
		try {
			
			Statement stmt = null;
			 stmt = con.createStatement();
		     ResultSet rs = stmt.executeQuery( "SELECT * FROM UserInfo;" );
		      
		      while ( rs.next() )
		      {
		        
		        	  
		    	  x = rs.getInt("UserID");
			         
			          
		          
		       }
		      stmt.close();
			} catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
		
	      return x;
	}
	
	
		
		
		
		public boolean isCheck() {
			return check;
		}
		
		
		public void addFavourite(Series obj,User temp)
		
		
		{
			
				try
				{
				String mystr="Insert into Favourite(SeriesID,UserID) values ('"+ obj.getId() +"','"+ temp.getId() +"')";
				 PreparedStatement pst = con.prepareStatement(mystr);
				 pst.execute();
				 pst.close();
				 count++;
				 
				}
				catch ( Exception e ) 
				{
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
		}
		
		public ArrayList<Integer> getFavSeriesID(User obj)
		
		{
			ArrayList<Integer> arr =  new <Integer>ArrayList();
			
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM Favourite;" );
			     
			      while ( rs.next() ) 
			      {
			          if(obj.getId()==rs.getInt("UserID")) 
			          {
			        	  
			        	  System.out.println(rs.getInt("SeriesID"));
			        	  arr.add(rs.getInt("SeriesID"));
			          }
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
		      return arr;
	
		}
		
		
		public ArrayList<Series> getSeriesFromFav(ArrayList<Integer> arr)
		{		
			
			i8 = new Model();
			ArrayList<Series> k1 = new <Series>ArrayList();
			
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM SeriesInfo;" );
			     
			     
			      while ( rs.next()  ) 
			      {
			         for(int i = 0; i< arr.size();i++)
			         {
			        	 if(arr.get(i)==rs.getInt("SeriesID"))
			        	 {
			        		 Series i1 = i8.search(rs.getString("Series"));
			        		 k1.add(i1);
			        		 
			        	 }
			         }
			    	  
			      }
			      
			       
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			return k1;
			
		}
		
		public int getSeriesID(String name)
		{
			int x = 0;
			
				try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM SeriesInfo;" );
			     
			      while ( rs.next() ) 
			      {
			          if(name.equals(rs.getString("Series"))) 
			          {
			        	  
			        	 x= rs.getInt("SeriesID");
			          }
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
				
				return x;
			
		}
		
		public String getSeriesLink(String name)
		{
			String result="";

			try {
			
			Statement stmt = null;
			
			 stmt = con.createStatement();
			
		     ResultSet rs = stmt.executeQuery( "SELECT * FROM SeriesInfo;" );
		     
		      while ( rs.next() ) 
		      {
		          if(name.equals(rs.getString("Series"))) 
		          {
		        	  
		        	 result = rs.getString("OMDBID");
		          }
		       }
		      stmt.close();
		      
			} 
			catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
			return result;
			
		}
		
		public String getVideoLink(String name)
		{
			String result="";

			try {
			
			Statement stmt = null;
			
			 stmt = con.createStatement();
			
		     ResultSet rs = stmt.executeQuery( "SELECT * FROM SeriesInfo;" );
		     
		      while ( rs.next() ) 
		      {
		          if(name.equals(rs.getString("Series"))) 
		          {
		        	  
		        	 result = rs.getString("VideoLink");
		          }
		       }
		      stmt.close();
		      
			} 
			catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
			return result;
			
		}
		
		public String getRotten(String name)
		{
			double x=0;
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM SeriesInfo;" );
			     
			      while ( rs.next() ) 
			      {
			          if(name.equals(rs.getString("Series"))) 
			          {
			        	  
			        	 x = rs.getDouble("RottenTomatoes");
			          }
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			return ""+x;
		}
		
		
		
		
		public ArrayList<String> getAllComments(String SeriesName)
		{
			ArrayList<String> arr = new <String>ArrayList();
			
			int x = getSeriesID(SeriesName);
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM RateAndComment;" );
			     
			      while ( rs.next() ) 
			      {
			          if(x==rs.getInt("SeriesID"))
			          {
			        	  
			        	 arr.add(rs.getString("Comment"));
			          }
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
			return arr;
			
		}
		
		
		public String getSingleComment(User temp,String name)
		{
			String result= "";
			int x = getSeriesID(name);
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM RateAndComment;" );
			     
			      while ( rs.next() ) 
			      {
			          if(x==rs.getInt("SeriesID") && temp.getId()==rs.getInt("UserID"))
			          {
			        	  result = rs.getString("Comment");
			        	 
			          }
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
		return result;
		}
		
		public double computeOveralRate(String name)
		{
			double y =0;
			double count = 0;
			int x = getSeriesID(name);
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM RateAndComment;" );
			     
			      while ( rs.next() ) 
			      {
			          if(x==rs.getInt("SeriesID") )
			          {
			        	  y=y+rs.getInt("Rate");
			        	  count++;
			          }
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			return y/count;

		}
		
		
		public String getEpisodeLink(String name,int season,int episode)	
		{
			String result="";
			
			String name2= name+"Season"+season;
			
			try {
			
			Statement stmt = null;
			
			 stmt = con.createStatement();
			
		     ResultSet rs = stmt.executeQuery( "SELECT * FROM "+name2+";" );
		     
		      while ( rs.next() ) 
		      {
		    	  
		          if( season == rs.getInt("SeasonNumber")&& episode== rs.getInt("EpisodeNumber")) 
		          {
		        	  
		        	 result =result+ rs.getString("OMDB");
		          }
		       }
		      stmt.close();
		      
			} 
			catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
			return result;
			
		}
		public void insertCommentAndRate(String name,User temp)
		{

			try
			{
			String mystr="Insert into RateAndComment(UserID,SeriesID,Rate,Comment) values ('"+ temp.getId()  +"','"+ getSeriesID(name)+"','"+ temp.getRate()+"','"+ temp.getComment()+"')";
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
		
		public void deleteFromFavourites(User temp, String seriesName)
		{
			dbConnection();
			int x = getSeriesID(seriesName);
			seriesName = seriesName.replaceFirst("o", "O");
			try {
				String mystr = "DELETE FROM Favourite WHERE SeriesID ="+x+" AND "+" UserID ="+temp.getId();
			
				 PreparedStatement pst = con.prepareStatement(mystr);
				 pst.execute();
				 pst.close();
				
		  
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
		}
		public void updateImageURL(String url,User i1)
		{
			try {
				String mystr="Update UserInfo set imageURL='"+i1.getImageURL()+" where userID="+i1.getId();
				 PreparedStatement pst = con.prepareStatement(mystr);
				 pst.execute();
				 pst.close();
				} catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
		}
		
		public void updateUserInfo(User update)
		{
			try {
				String mystr="UPDATE UserInfo SET UserName='"+update.getUserName()+"' WHERE UserID='"+update.getId()+"'";
				 PreparedStatement pst = con.prepareStatement(mystr);
				 pst.execute();
				 pst.close();
				} catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
		}
		public void updateUserInfo2(User update)
		{
			try {
				String mystr="UPDATE UserInfo SET UserPassword='"+update.getPassword()+"' WHERE UserID='"+update.getId()+"'";
				 PreparedStatement pst = con.prepareStatement(mystr);
				 pst.execute();
				 pst.close();
				} catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
		}
		
		
		public ArrayList<String> getAllEpisode()
		{
			ArrayList<String> arr = new <String>ArrayList();
			
			
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM MrRobot;" );
			     
			      while ( rs.next() ) 
			      {
			           
			        	 arr.add(rs.getString("EpisodeName"));
			          
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
			return arr;
			
		}
		public ArrayList<String> getEpisodeAndSeason()
		{
			ArrayList<String> arr = new <String>ArrayList();
			
			
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM MrRobot;" );
			     
			      while ( rs.next() ) 
			      {
			           
			        	 arr.add(rs.getString("EpisodeandSeason"));
			          
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
			return arr;
			
		}
		public ArrayList<Integer> getDays()
		{
			ArrayList<Integer> arr = new <Integer>ArrayList();
			
			
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM MrRobot;" );
			     
			      while ( rs.next() ) 
			      {
			           
			        	 arr.add(rs.getInt("Day"));
			          
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
			return arr;
			
		}
		public ArrayList<Integer> getMonth()
		{
			ArrayList<Integer> arr = new <Integer>ArrayList();
			
			
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM MrRobot;" );
			     
			      while ( rs.next() ) 
			      {
			           
			        	 arr.add(rs.getInt("Month"));
			          
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
			return arr;
			
		}
		public ArrayList<Integer> getYear()
		{
			ArrayList<Integer> arr = new <Integer>ArrayList();
			
			
			try {
				
				Statement stmt = null;
				
				 stmt = con.createStatement();
				
			     ResultSet rs = stmt.executeQuery( "SELECT * FROM MrRobot;" );
			     
			      while ( rs.next() ) 
			      {
			           
			        	 arr.add(rs.getInt("Year"));
			          
			       }
			      stmt.close();
			      
				} 
				catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				}
			
			
			return arr;
			
		}*/

}
