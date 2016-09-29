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
import javax.servlet.http.HttpSession;

import tom.bean.Login;
import tom.bean.UpdateRegister;
import tom.util.DBHelper;

@WebServlet("/UpdateRegisterMessage")
public class UpdateRegisterMessage extends HttpServlet {
	
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
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		String name = login.getName();// ��õ�¼����
		UpdateRegister updateRegister = new UpdateRegister();// ��ȡUpdateRegister��JavaBean
		request.setAttribute("updateRegister", updateRegister);
		// ��ȡUpdateReg.jspҳ���б�������
		request.setCharacterEncoding("utf-8");
		String sex = request.getParameter("newSex");
		int age = Integer.parseInt(request.getParameter("newAge"));
		String email = request.getParameter("newEmail");
		String phone = request.getParameter("newPhone");
		String message = request.getParameter("newMessage");

		// ��ȡ����
		try {
			String sql="update members set sex='"+ sex
					+ "',age='" + age 
					+ "',email='" + email
					+ "',phone='" + phone
					+ "',message='" + message
					+ "' where name='" + name +"'";
			int m = stmt.executeUpdate(sql);
			if(m!=0){
				updateRegister.setName(name);
				updateRegister.setNewAge(age);
				updateRegister.setNewSex(sex);
				updateRegister.setNewEmail(email);
				updateRegister.setNewPhone(phone);
				updateRegister.setNewMessage(message);
			}else{
				response.setContentType("text/html;charset=GB2312");
				PrintWriter out=response.getWriter();
				out.println("<html><body>");
				out.println("��Ϣ��д����������Ϣ���зǷ��ַ�");
				out.println("�����޸�ע����Ϣ:<a href='jsp/updateReg.jsp'>");
				out.println("</body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/updateRegisterMessage.jsp");
		dispatcher.forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");// ��ȡ�û���¼ʱ��JavaBean
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
