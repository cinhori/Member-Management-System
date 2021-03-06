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
		Login login = (Login) session.getAttribute("login");// 获取会员登录时的JavaBean
		boolean ok = true;// 定义布尔型变量，初始值为真
		if (login == null) {// 如果不是在登录状态时，重定向到登录页面
			ok = false;
			response.sendRedirect("jsp/login.jsp");
		}
		if (ok ==true) {// 如果已经登录
			String name = login.getName();// 获得登录名
			uploadPicture(request, response, name);// 接收上传文件
		}
	}
	
	private void uploadPicture(HttpServletRequest request,
			HttpServletResponse response, String logname) {
		UploadPic upload = new UploadPic();// 获取UploadPic的JavaBean对象
		String backNews = "";
		try {
			HttpSession session = request.getSession(true);
			request.setAttribute("upload", upload);
			String tempFileName=session.getId();
			System.out.println("sessionId is " + tempFileName);
			File f1 = new File
					("D:\\Java\\apache-tomcat-8.0.33\\webapps\\MemberMngmentSystem\\image", tempFileName);
			FileOutputStream out = new FileOutputStream(f1);// 实例化文件输出流对象
			InputStream in = request.getInputStream();// 实例化文件输入流对象
			byte[] b= new byte[10000];
			int n;
			while ((n = in.read(b)) != -1) {// 读取文件输出流
				out.write(b, 0, n);// 写入数据
			}
			out.close();// 关闭输出流
			in.close();// 关闭输入流
			
			RandomAccessFile random = new RandomAccessFile(f1, "r");// 实例化文件随机流
			// 读出f1的第二行，取出上传文件的名字
			String secondLine="";
			for (int i=1;i<= 2;i++) {
				secondLine = random.readLine();
			}
			int position = secondLine.lastIndexOf('=');// 获取第二行中目录符号'\'最后出现的位置
			// 截取文件名
			String fileName = secondLine.substring(position + 2,
					secondLine.length() - 1);
			byte cc[] = fileName.getBytes("ISO-8859-1");
			fileName = new String(cc);
			fileName = fileName.replaceAll(" ", "");
			// 文件是否由字母或数字组成判断名字
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
				response.sendRedirect("jsp/uploadPic.jsp");// 重定向到uploadPic.jsp页面
			}
			// 保存文件名是上传文件名加会员名为前缀
			String savedFileName = logname.concat(fileName);
			random.seek(0);
			long forthEndPosition = 0;// 获取第四行回车符号的位置
			int forth = 1;
			while ((n = random.readByte()) != -1 && (forth <= 4)) {
				if (n == '\n') {
					forthEndPosition = random.getFilePointer();
					forth++;
				}
			}
			// 根据客户上传文件的名字，将该文件存入磁盘
			File dir = new File("D:\\Java\\apache-tomcat-8.0.33\\webapps\\MemberMngmentSystem\\image",savedFileName);
			dir.mkdir();
			// 首先删除用户曾上传过的图像文件：
			File file[] = dir.listFiles();
			for (int k = 0; k < file.length; k++) {
				if (file[k].getName().startsWith(logname)) {
					file[k].delete();
				}
			}
			File savingFile = new File(dir, savedFileName);// 需要新保存的上传文件
			RandomAccessFile random2 = new RandomAccessFile(savingFile, "rw");
			random.seek(random.length());
			long endPosition = random.getFilePointer();
			long mark = endPosition;
			int j = 1;
			// 确定出文件f1中包含客户上传的文件的内容的最后位置，即倒数第6行
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
					backNews = fileName + "成功上传";
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
