//LoginServlet.java
import java.io.*;
import java.util.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;


public class LoginServlet extends HttpServlet
{
	Connection con;
	PrintWriter out=null;
	public void init()
	{
		try
		{

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");

		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}

	public void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		String number=req.getParameter("number");
		String password=req.getParameter("password");
		try
		{
			out=res.getWriter();
			PreparedStatement pst=con.prepareStatement("select * from userinformation where mobilenumber=? and password=?");
			pst.setString(1,number);
			pst.setString(2,password);
			ResultSet rs=pst.executeQuery();
			ServletContext sc=getServletContext();
			if(rs.next())
			{
				RequestDispatcher rd=sc.getRequestDispatcher("/userhome.html");
				rd.forward(req,res);
			}
			else
			{
				out.println("<font color=red>provide correct informaion</fon>");
				RequestDispatcher rd=sc.getRequestDispatcher("/login.html");
				rd.include(req,res);
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
}