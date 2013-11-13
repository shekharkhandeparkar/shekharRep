package example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				UserBean user = new UserBean();
				user.setName(request.getParameter("name"));
				user.setLastname(request.getParameter("lastname"));
				System.out.println(user.getName());

				
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(user);
			//	session.save(user2);
				session.getTransaction().commit();
				session.close();

			/*	try{  
					try{
					Class.forName("com.mysql.jdbc.Driver");
					}
					catch(Exception e)
					{
						System.out.print("no driver");
					}
					 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
							 "root","macroy");
					 Statement st= con.createStatement();
					 ResultSet rs;
					 int i=st.executeUpdate("insert into login values ('"+user.getName()+"','"+user.getLastname()+"')"); 
					}catch(Exception e){
						System.out.println(e.getMessage());
					}  
				*/	
				HttpSession datasession = request.getSession(true); 
				datasession.setAttribute("currentSessionUser",user);
				response.sendRedirect("new.jsp");
	}

}
