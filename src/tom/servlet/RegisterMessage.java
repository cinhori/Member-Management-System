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
		// ��ȡ�����ݹ����Ĳ���
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
			showMessage("��Ա���Ʋ���Ϊ��", response);
		} else if (pwd == null || "".equals(pwd)) {
			showMessage("�������벻��Ϊ��", response);
		} else if (!pwd.equals(pwd1)) {
			showMessage("�����������벻һ��", response);
		} else {
			// ��ȡ����
			try {
				int age = 0;
				if(!(a == null || "".equals(a))){
					age = Integer.parseInt(a);
				}
				
				// ����sql���
				String sql = "insert into members(name,password,sex,age,phone,email,message) values ('" + name + "','" + pwd + "','" + sex + "','" + age + "','" + phone + "','" + email + "','" + message + "')";
				// ִ��sql��䣬��������
				int i = stmt.executeUpdate(sql);
				if(i!=0){
					Register register=new Register();//��ȡRegister����
					//������Ӧ������
					register.setName(name);
					register.setPassword(pwd1);
					register.setSex(sex);
					register.setAge(age);
					register.setPhone(phone);
					register.setEmail(email);
					register.setMessage(message);
					/*��JavaBeanд��request������*/
					request.setAttribute("register", register);
					//ע��ɹ�����ת�ض���
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/registerMessage.jsp");
					dispatcher.forward(request, response);
				}else{
					showMessage("�����쳣�����Ժ�����", response);
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
		out.println("<a href='jsp/register.jsp'>����ע��</a>");
		out.println("</body></html>");
	}
}
