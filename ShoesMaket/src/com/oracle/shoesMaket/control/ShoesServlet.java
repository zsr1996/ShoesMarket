package com.oracle.shoesMaket.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.shoesMaket.model.bean.PageBean;
import com.oracle.shoesMaket.model.bean.Shoes;
import com.oracle.shoesMaket.model.dao.ShoesDAOImp;

/**
 * Servlet implementation class ShoesServlet
 */
@WebServlet("/ShoesServlet")
public class ShoesServlet extends HttpServlet {
	private ShoesDAOImp  dao;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoesServlet() {
        super();
        dao=new ShoesDAOImp();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String methodName=request.getParameter("method");
		switch(methodName)
		{
		case "index":
		{
			index(request,response);break;
		}
		case "details":
		{
			details(request,response);break;
		}
		case "search":
		{
			search(request,response);break;
		}	
		case "listShoesByPage":
		{
			listShoesByPage(request,response);break;
		}		
		}
	}
	
	/**
	 * 这里是处理首页信息的代码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Shoes> allShoes=dao.listShoesByCount(15);  //从数据库查询15双鞋子的信息并保存在这个数组里
		
		request.setAttribute("allShoes", allShoes);
		request.getRequestDispatcher("index.jsp").forward(request, response);		
	}
	
	/**
	 * 这里是处理详细的代码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shoeid=request.getParameter("shoeid");
		
		Shoes shoeInfo=dao.getShoesInfoByShoesId(Integer.parseInt(shoeid));
		System.out.println(shoeInfo);
		request.setAttribute("shoe",shoeInfo);
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}
    
    /**
     * 这里是处理搜索功能的代码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  pinpai=null;
		System.out.println("66666");
		if(request.getParameter("manufacturer")!=null)
		  pinpai=new String(request.getParameter("manufacturer").getBytes("ISO-8859-1"),"UTF-8");//品牌名
		//System.out.println("1"+pinpai);
		//2.从数据库搜索相应的鞋子信息
				ArrayList<Shoes>  searchedShoes=dao.searchShoesByCondition(pinpai);
				request.setAttribute("shoes", searchedShoes);//把鞋子的信息存在request里面
		//3.当数据准备完成后应该跳到下个页面
				request.getRequestDispatcher("products.jsp").forward(request, response); //request对象负责跳到下个页面
			// response.sendRedirect("shoesList.jsp");//response对象的这个方法是重定向，它也会让当前jsp跳转到下一个制定的jsp
			
	}
	
	/**
	 * 这里是处理分页显示鞋子信息的代码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listShoesByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  page=request.getParameter("page");
		String count=request.getParameter("count");
		System.out.println(page);
		System.out.println(count);
		ArrayList<Shoes> shoes=dao.listShoesByPage(Integer.parseInt(page),Integer.parseInt(count));
		/**
		 * 通过计算将分页中所有要用到的数字全部算出来
		 */
		int nowPage=Integer.parseInt(page);//当前页
		int allCount=dao.getAllCountOfShoes();//因为分页在页面上要知道最大页，所以，我们要调用dao中查询所有条数的方法，获得car有多少条
		int pageOfCount=Integer.parseInt(count);
		int allPages=(allCount%pageOfCount==0)?allCount/pageOfCount:(allCount/pageOfCount+1);//求总页数
		int perviousPage=nowPage<=1?1:(nowPage-1);
		int nextPage=nowPage>=allPages?allPages:(nowPage+1);
		
		/**
		 * 将所有的分页 中的属性封装成一个分页bean，将一个对象整体保存传到页面上
		 */
		PageBean  pageBean=new PageBean();
		pageBean.setNowPage(nowPage);
		pageBean.setPreviousPage(perviousPage);
		pageBean.setNextPage(nextPage);
		pageBean.setAllCount(allCount);
		pageBean.setAllPages(allPages);
		pageBean.setEverPageCount(pageOfCount);
		
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("shoes", shoes);
		request.getRequestDispatcher("shoesList.jsp").forward(request, response);
	
	
	}//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
