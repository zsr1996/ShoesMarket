package com.oracle.shoesMaket.control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.shoesMaket.model.bean.Shoes;
import com.oracle.shoesMaket.model.dao.ShoesDAOImp;

/**
 * Servlet implementation class ShoppingCarServlet
 */
@WebServlet("/ShoppingCarServlet")
public class ShoppingCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShoesDAOImp dao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCarServlet() {
        super();
        dao=new ShoesDAOImp();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String method=request.getParameter("method");
		switch(method) {
		case "add":
		{
			add(request,response);
			break;
		}
		case "deleteShoes"://判断用户请求的参数中，告诉我们是进入哪个后台业务方法
		{	
			System.out.println("开始删除商品");
			deleteShoes(request, response);
			break;
		}
		case "deleteAll":
		{	
			deleteAll(request, response);
			break;
		}
		case "countAdd":
		{
			countAdd(request,response);
		}
		case "countReduce":
		{
			countReduce(request,response);
		}
		default:
			break;
		}
		
	}
	
	/**
	 * 这里是处理添加到购物车的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取首页商品传过来的id
		String shoeid=request.getParameter("shoeid");
		System.out.println(shoeid);
		//2.将这个车辆id存储到  ‘购物车’   里面
		//先判断session中有没有存储购物车集合，如果有，说明之前已经买过东西，如果没有说明没买过东西
		if(request.getSession().getAttribute("shoes")==null)
		{
			//
			HashMap<Shoes, Integer> shoppingcars=new HashMap<>();
			//
			shoppingcars.put(dao.getShoesInfoByShoesId(Integer.parseInt(shoeid)), 1);
			request.getSession().setAttribute("shoes", shoppingcars);
		}
		else {
			//已经存在购物车map集合，则取出session里面的购物车map集合
			HashMap<Shoes,Integer> shoppingcars=(HashMap<Shoes,Integer>) request.getSession().getAttribute("shoes");
			//因为session中已经存在购物车集合，说明之前把东西添加过购物车
			//如果该商品添加过购物车，则把原来的数量加上现在的数量，如果该商品没被添加过，则直接将这个商品添加到集合中
			System.out.println("已经存在map集合");
			if(shoppingcars.containsKey(new Shoes(Integer.parseInt(shoeid))))
			{
				System.out.println("已有该商品");
				Shoes s=dao.getShoesInfoByShoesId(Integer.parseInt(shoeid));
				int nowCount=shoppingcars.get(s)+1;
				System.out.println(nowCount);
				shoppingcars.put(s, nowCount);
			}
			else {
				shoppingcars.put(dao.getShoesInfoByShoesId(Integer.parseInt(shoeid)), 1);
			}
			request.getSession().setAttribute("shoes", shoppingcars);
		}
		//数据添加之后直接跳到购物车页面
		response.sendRedirect("shoppingCar.jsp");
	}
	
	/**
	 * 这里是处理删除该商品的代码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteShoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要删除鞋子的id
		String id=request.getParameter("shoeid");
		System.out.println(id);
	
		//
		HashMap<Shoes,Integer> shoppingcars=(HashMap<Shoes,Integer>)request.getSession().getAttribute("shoes");
		System.out.println("122"+shoppingcars.toString());
		for(Shoes s:shoppingcars.keySet())
		{
			
			if(s.getShoeid()==Integer.parseInt(id))
			{
				shoppingcars.remove(s);
				break;
			}
		}
		response.sendRedirect("shoppingCar.jsp");
	}
	
	/**
	 * 这里是处理商品数量加一的代码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void countAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	/**
	 * 这里是处理商品数量减一的代码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void countReduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * 这里是处理删除全部商品的代码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("shoes");
		response.sendRedirect("shoppingCar.jsp");
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
