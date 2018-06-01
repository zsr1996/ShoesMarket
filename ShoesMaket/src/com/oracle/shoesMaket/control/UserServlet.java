package com.oracle.shoesMaket.control;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.oracle.shoesMaket.model.bean.User;
import com.oracle.shoesMaket.model.dao.UserDAOImp;
import com.oracle.shoesMaket.util.MD5;



/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  ServletConfig  config;
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config=config;
	}
	private UserDAOImp dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		dao = new UserDAOImp();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("method")==null) {//如果是上传文件的方法，则会进入到这个分支
			System.out.println("开始修改信息");
			updateUserInfo(request,response);
		}else
		{
		String methodName = request.getParameter("method");
		switch (methodName) {
		case "login": {
			login(request, response);
			break;
		}
		case "register": {
			register(request, response);
			break;
		}
		case "loadProfile": {
			loadProfile(request, response);
			break;
		}
		default:
			break;
		}
	}
	}

	
	
	/**
	 * 这里是处理注冊的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*System.out.println("registerMethod");
		//1.先去除用户输入的验证码
		String  code=request.getParameter("code");
		System.out.println("您在网页上输入的验证码："+code);
		//2.取出系统生成的验证码?
		String  systemCode=request.getSession().getAttribute("generateCode").toString();
		if(!code.equalsIgnoreCase(systemCode)) {//equlas会比较内容和大小写，   equalsingonrecase
			System.out.println("验证码验证失败了，立马调到前台页�?");
			request.setAttribute("registerResultMessage", "codeError");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			//在后台servlet中，代码里面如果转发和重定向后面继续写其他业务代码，会报�?
				return ;*/

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String sex=request.getParameter("sex");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user=new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setSex(sex);
		user.setEmail(email);
		user.setPassword(MD5.MD5(password));//在将表单提交过来的密封装到user对象前，先用md5算法把密码加密?
		
		boolean  result=dao.add(user);
		if(result) {
			request.setAttribute("loginResultMessage", "registerSuccess");
			System.out.println("注册成功！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else
		{
			request.setAttribute("loginResultMessage", "registerFail");
		}
		
	}

	/**
	 * 处理登陆的方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginMethod");
	//下面代码是判断验证码的业务代�?
		
		//1.先去除用户输入的验证�?
		String code=request.getParameter("code");
		System.out.println("您在网页上输入的验证码："+code);
		//2.取出系统生成的验证码�?
		String systemCode=request.getSession().getAttribute("generateCode").toString();
		if(!code.equalsIgnoreCase(systemCode)) {//equlas会比较内容和大小写，   equalsingonrecase
			System.out.println("验证码验证失败了，立马调到前台页面");
			request.setAttribute("loginResultMessage", "codeError");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			//在后台servlet中，代码里面如果转发和重定向后面继续写其他业务代码，会报�?
				return ;
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		// 查询数据库
		System.out.println("验证码验证通过了，开始验证用户名和密码");
		System.out.println("您在网页上输入的用户为："+username);
		System.out.println("您在网页上输入的密码为："+password);
		User user = dao.login(username, password);
		if(user==null)
		{
			request.setAttribute("loginResultMessage", "userError");
			System.out.println("用户名密码验证失败了，立马调到前台页面");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return ;
		}
		System.out.println("登录成功了跳到主界面");
		request.getSession().setAttribute("loginedUser", user);// 回话范围内存储用户资料，这样能保证用户在多次绘画中可以保留用户登录的信息
		response.sendRedirect("index.jsp");		
	}
	
	protected void loadProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("开始查询用户信息");
		String userid=request.getParameter("userid");
		System.out.println(userid);
		User user=dao.getUserInfoByUserId(Integer.parseInt(userid));
		System.out.println("查询到用户信息");
		System.out.println(user.toString());
		request.setAttribute("user", user);
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}
	protected void updateUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用smartUpload来读取表单上传的文件和表单中的数据
		SmartUpload su = new SmartUpload();//创建一个smartUpload上传插件的对象
		// 上传初始化
		su.initialize(config,request,response);//,读取request，response中的数据到smartupload插件中
		try {
			su.upload();//把这个表单提交的数据读取到upload插件里
			su.save("/images");
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		Request  re=su.getRequest();//如果要读取表单中的文本数据，必须要使用的smartUplod里面的request
		String email=re.getParameter("email");
		String  firstname=re.getParameter("firstname");
		String  lastname=re.getParameter("lastname");
		String sex=	re.getParameter("sex");
		String age=re.getParameter("age");
		String phone=re.getParameter("phone");
		String userid=re.getParameter("userid");

		/**
		 * 将表单取到的数据封装成一个user对象
		 */
		User user=new User();
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);;
		user.setUserid(Integer.parseInt(userid));
		user.setSex(sex);
		user.setAge(Integer.parseInt(age));
		user.setPhone(Integer.parseInt(phone));
	
		File  uploadFile=su.getFiles().getFile(0);//从smartupload插件中读取出页面上传的多个文件对象
		System.out.println(uploadFile.getFileName());
		System.out.println(uploadFile.getSize());
		try {
			System.out.println(request.getRealPath("/images/uploadFiles/"));
		//	UUID //javaUUID ,算法，生成同一空间同一时间下绝不重复的字符串 36 
			String  uuidName=UUID.randomUUID().toString();
			java.io.File    destFile=new java.io.File(request.getRealPath("/images/uploadFiles/"));
			String childPath="";
			for(int n=0;n<uuidName.length();n++)
			{
				childPath+=uuidName.charAt(n)+"/";
			}
			java.io.File  f=new java.io.File(destFile,childPath);
			f.mkdirs();
			java.io.File  file=new java.io.File(f,uuidName+"."+uploadFile.getFileExt());
			
			uploadFile.saveAs(file.getAbsolutePath());
			String  urlPath=file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("images"),file.getAbsolutePath().length());
			System.out.println(urlPath);
			user.setImage(urlPath);//将新上传的图片的路径设置到user对象中，传到dao里面修改新的头像地址
			
			boolean result=dao.update(user);
			System.out.println(result);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}