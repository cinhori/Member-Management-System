package tom.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tom.bean.Login;

public class Exit extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// ��������
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		Login login=(Login)session.getAttribute("login");//����û���¼ʱ��JavaBean
		boolean ok=true;
		if(login==null){
			ok=false;
			response.sendRedirect("login.jsp");//�ض��򵽵�¼ҳ��
		}
		if(ok==true){
			continueDoGet(request,response);
		}
	}
	
	

	public void continueDoGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession(true);
		session.invalidate();//�����û���session����
		response.sendRedirect("index.jsp");//������ҳ
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
