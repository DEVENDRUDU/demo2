//RegistrationServlet.java
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

public class RegistrationServlet extends HttpServlet
{
	Connection con;
	PrintWriter pw;
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
		String firstname=req.getParameter("firstname");
		String lastname=req.getParameter("lastname");
		String number=req.getParameter("number");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		String password=req.getParameter("password");
		String cpassword=req.getParameter("cpassword");
		try
		{
			 pw=res.getWriter();
			PreparedStatement pst=con.prepareStatement("insert into userinformation values(?,?,?,?,?,?)");

			pst.setString(1,firstname);
			pst.setString(2,lastname);
			pst.setString(3,number);
			pst.setString(4,email);
			pst.setString(5,address);
			pst.setString(6,password);

			pst.executeUpdate();
			pw.println("<html><body bgcolor=blue text=red>");
			pw.println("registered successfully<br><a href=login.html>login</a>");
			pw.println("</body></html>");
			
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
}