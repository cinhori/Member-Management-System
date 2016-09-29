package tom.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import tom.bean.MemberInformation;
import tom.util.DBHelper;

@WebServlet("/FindMember")
public class FindMember extends HttpServlet {

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

	private void continueDo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MemberInformation memberInfo = new MemberInformation();// 获取MemberInformation的JavaBean
		request.setAttribute("memberInfo", memberInfo);
		String name = request.getParameter("name");//获取表单内容
		// 获取连接
		try {			
			String sql = "select * from members where name='" + name + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				String sex = rs.getString("sex");
				int age = rs.getInt("age");
				String phone = rs.getString("phone");
				String email= rs.getString("email");
				String message=rs.getString("message");
				String pic=rs.getString("pic");
				
				memberInfo.setName(name);
				memberInfo.setSex(sex);
				memberInfo.setAge(age);
				memberInfo.setPhone(phone);
				memberInfo.setEmail(email);
				memberInfo.setMessage(message);
				memberInfo.setPic(pic);
			}else{
				response.setContentType("text/html;charset=GB2312");
				PrintWriter out=response.getWriter();
				out.println("<html><body>");
				out.println("查询不到结果");
				out.println("回到浏览会员页面:<a href='lookMember.jsp'>");
				out.println("</body></html>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/findMember.jsp");
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
			continueDo(request, response);
		}
	}
}
