package tom.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tom.bean.Login;
import tom.bean.Register;
import tom.util.DBHelper;

@WebServlet("/UpdateReg")
public class UpdateReg extends HttpServlet {

	private DBHelper db;
	private Statement stmt;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		db = new DBHelper();
		stmt = db.getStatement();
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void continueWork(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		String name = login.getName();
		Register register = new Register();
		request.setAttribute("register", register);
		// 获取连接
		try {
			String sql = "select * from members where name='" + name + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				register.setName(rs.getString(2));
				register.setSex(rs.getString(4));
				register.setAge(rs.getInt(5));
				register.setPhone(rs.getString(6));
				register.setEmail(rs.getString(7));
				register.setMessage(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/updateReg.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");// 获取用户登录时的JavaBean
		boolean ok = true;
		if (login == null) {
			ok = false;
			response.sendRedirect("jsp/login.jsp");
		}
		if (ok == true) {
			continueWork(request, response);
		}
	}

}
