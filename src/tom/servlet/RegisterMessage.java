package tom.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tom.bean.Register;
import tom.util.DBHelper;

@WebServlet("/RegisterMessage")
public class RegisterMessage extends HttpServlet {

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
		// 获取表单传递过来的参数
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		String pwd1 = request.getParameter("password1");
		String sex = request.getParameter("sex");
		String a = request.getParameter("age");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");

		if (name == null || "".equals(name)) {
			showMessage("会员名称不能为空", response);
		} else if (pwd == null || "".equals(pwd)) {
			showMessage("设置密码不能为空", response);
		} else if (!pwd.equals(pwd1)) {
			showMessage("两次输入密码不一致", response);
		} else {
			// 获取连接
			try {
				int age = 0;
				if(!(a == null || "".equals(a))){
					age = Integer.parseInt(a);
				}
				
				// 创建sql语句
				String sql = "insert into members(name,password,sex,age,phone,email,message) values ('" + name + "','" + pwd + "','" + sex + "','" + age + "','" + phone + "','" + email + "','" + message + "')";
				// 执行sql语句，插入数据
				int i = stmt.executeUpdate(sql);
				if(i!=0){
					Register register=new Register();//获取Register对象
					//设置相应的属性
					register.setName(name);
					register.setPassword(pwd1);
					register.setSex(sex);
					register.setAge(age);
					register.setPhone(phone);
					register.setEmail(email);
					register.setMessage(message);
					/*将JavaBean写入request对象中*/
					request.setAttribute("register", register);
					//注册成功，跳转重定向
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/registerMessage.jsp");
					dispatcher.forward(request, response);
				}else{
					showMessage("数据异常，请稍后重试", response);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void showMessage(String message, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charSet=GB2312");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println(message + ",");
		out.println("<a href='jsp/register.jsp'>继续注册</a>");
		out.println("</body></html>");
	}
}
