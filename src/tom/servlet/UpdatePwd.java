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
import tom.bean.UpdatePassword;
import tom.util.DBHelper;

@WebServlet("/UpdatePwd")
public class UpdatePwd extends HttpServlet {

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

	public void continueWork(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");// 获取用户登录时的JavaBean
		String name = login.getName();// 获得用户登录时的登录名
		UpdatePassword passwordBean = new UpdatePassword();// 获取修改密码的JavaBean
		request.setAttribute("password", passwordBean);
		// 获取表单内容
		String oldpassword = request.getParameter("password");
		String newPassword1 = request.getParameter("newpassword1");
		String newPassword2 = request.getParameter("newpassword2");
		// 获取连接
		try {
			String sql1="select * from members where name='"+ name + "'and password='" + oldpassword + "'";
			ResultSet rs=stmt.executeQuery(sql1);
			if(rs.next()){
				if(newPassword1==newPassword2 || newPassword1.equals(newPassword1)){
					String sql2="update members set password='"+ newPassword1 
								+ "' where name='" + name + "'";
					int m=stmt.executeUpdate(sql2);
					if(m!=0){
						passwordBean.setBackNews("密码更新成功");
						passwordBean.setPassword(oldpassword);
						passwordBean.setNewPassword1(newPassword1);
					}else{
						passwordBean.setBackNews("密码更新失败");
					}
				}else{
					response.setContentType("text/html;charset=GB2312");
					PrintWriter out=response.getWriter();
					out.println("<html><body>");
					out.print("密码不一致");
					out.println("<a href='jsp/updatePwd.jsp'>重新输入信息,修改密码</a>");
					out.println("</body></html>");
				}
			}else{
				response.setContentType("text/html;charset=GB2312");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.print("密码错误");
				out.println("<a href='jspupdatePwd.jsp'>重新输入信息,修改密码</a>");
				out.println("</body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/updatePwdMessage.jsp");
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
