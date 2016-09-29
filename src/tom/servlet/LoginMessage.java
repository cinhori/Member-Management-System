package tom.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import tom.util.DBHelper;

@WebServlet("/LoginMessage")
public class LoginMessage extends HttpServlet {
	private static final long serialVersionUID = 1;
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

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取表单的内容
				String name = request.getParameter("logname");
				String pwd = request.getParameter("password");

				// 获取连接
				try {			
					//创建sql语句，查询会员名称和密码
					String sql="select * from members where name='" + name +"'and password='" + pwd + "'";
					//执行sql语句
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next()){
						//获取JavaBean对象，封装数据
						Login login=new Login();
						login.setName(name);
						login.setPasswd(pwd);
						HttpSession session=request.getSession(true);
						session.setAttribute("login", login);
						RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/loginMessage.jsp");
						dispatcher.forward(request, response);
					}else{
						response.setContentType("text/html;charset=GB2312");
						PrintWriter out=response.getWriter();
						out.println("<html><body>");
						out.println("会员登录失败");
						out.println("<a href='jsp/login.jsp'>重新登录</a>");
						out.println("</body></html>");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

}
