package tom.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tom.bean.Login;
import tom.bean.UploadPic;
import tom.util.DBHelper;

@WebServlet("/UploadPicture")
public class UploadPicture extends HttpServlet {

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
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");// ��ȡ��Ա��¼ʱ��JavaBean
		boolean ok = true;// ���岼���ͱ�������ʼֵΪ��
		if (login == null) {// ��������ڵ�¼״̬ʱ���ض��򵽵�¼ҳ��
			ok = false;
			response.sendRedirect("jsp/login.jsp");
		}
		if (ok ==true) {// ����Ѿ���¼
			String name = login.getName();// ��õ�¼��
			uploadPicture(request, response, name);// �����ϴ��ļ�
		}
	}
	
	private void uploadPicture(HttpServletRequest request,
			HttpServletResponse response, String logname) {
		UploadPic upload = new UploadPic();// ��ȡUploadPic��JavaBean����
		String backNews = "";
		try {
			HttpSession session = request.getSession(true);
			request.setAttribute("upload", upload);
			String tempFileName=session.getId();
			System.out.println("sessionId is " + tempFileName);
			File f1 = new File
					("D:\\Java\\apache-tomcat-8.0.33\\webapps\\MemberMngmentSystem\\image", tempFileName);
			FileOutputStream out = new FileOutputStream(f1);// ʵ�����ļ����������
			InputStream in = request.getInputStream();// ʵ�����ļ�����������
			byte[] b= new byte[10000];
			int n;
			while ((n = in.read(b)) != -1) {// ��ȡ�ļ������
				out.write(b, 0, n);// д������
			}
			out.close();// �ر������
			in.close();// �ر�������
			
			RandomAccessFile random = new RandomAccessFile(f1, "r");// ʵ�����ļ������
			// ����f1�ĵڶ��У�ȡ���ϴ��ļ�������
			String secondLine="";
			for (int i=1;i<= 2;i++) {
				secondLine = random.readLine();
			}
			int position = secondLine.lastIndexOf('=');// ��ȡ�ڶ�����Ŀ¼����'\'�����ֵ�λ��
			// ��ȡ�ļ���
			String fileName = secondLine.substring(position + 2,
					secondLine.length() - 1);
			byte cc[] = fileName.getBytes("ISO-8859-1");
			fileName = new String(cc);
			fileName = fileName.replaceAll(" ", "");
			// �ļ��Ƿ�����ĸ����������ж�����
			String checkedStr = fileName.substring(0, fileName.indexOf("."));
			boolean isLetterOrDigit = true;
			for (int i = 0; i < checkedStr.length(); i++) {
				char c = checkedStr.charAt(i);
				if (!((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A') || (c <= '9' && c >= '0'))) {
					isLetterOrDigit = false;
					break;
				}
			}
			if (isLetterOrDigit == false) {
				response.sendRedirect("jsp/uploadPic.jsp");// �ض���uploadPic.jspҳ��
			}
			// �����ļ������ϴ��ļ����ӻ�Ա��Ϊǰ׺
			String savedFileName = logname.concat(fileName);
			random.seek(0);
			long forthEndPosition = 0;// ��ȡ�����лس����ŵ�λ��
			int forth = 1;
			while ((n = random.readByte()) != -1 && (forth <= 4)) {
				if (n == '\n') {
					forthEndPosition = random.getFilePointer();
					forth++;
				}
			}
			// ���ݿͻ��ϴ��ļ������֣������ļ��������
			File dir = new File("D:\\Java\\apache-tomcat-8.0.33\\webapps\\MemberMngmentSystem\\image",savedFileName);
			dir.mkdir();
			// ����ɾ���û����ϴ�����ͼ���ļ���
			File file[] = dir.listFiles();
			for (int k = 0; k < file.length; k++) {
				if (file[k].getName().startsWith(logname)) {
					file[k].delete();
				}
			}
			File savingFile = new File(dir, savedFileName);// ��Ҫ�±�����ϴ��ļ�
			RandomAccessFile random2 = new RandomAccessFile(savingFile, "rw");
			random.seek(random.length());
			long endPosition = random.getFilePointer();
			long mark = endPosition;
			int j = 1;
			// ȷ�����ļ�f1�а����ͻ��ϴ����ļ������ݵ����λ�ã���������6��
			while ((mark >= 0) && (j <= 6)) {
				mark--;
				random.seek(mark);
				n = random.readByte();
				if (n == '\n') {
					endPosition = random.getFilePointer();
					j++;
				}
			}
			random.seek(forthEndPosition);
			long startPoint = random.getFilePointer();
			while (startPoint < endPosition - 1) {
				n = random.readByte();
				random2.write(n);
				startPoint = random.getFilePointer();
			}
			random2.close();
			random.close();

			ResultSet rs = stmt
					.executeQuery("select * from members where name='"
							+ logname + "'");
			if (rs.next()) {
				if (isLetterOrDigit) {
					int mm = stmt.executeUpdate("UPDATE members SET pic= '"+savedFileName+
							"' where name = '" + logname + "'");
					backNews = fileName + "�ɹ��ϴ�";
					upload.setFileName(fileName);
					upload.setSavedFileName(savedFileName);
					upload.setBackNews(backNews);
				}
			}

			f1.delete();
		} catch (Exception e) {
			backNews = "" + e;
			upload.setBackNews(backNews);
			e.printStackTrace();
		}
		try {
			RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/showUploadMessage.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
