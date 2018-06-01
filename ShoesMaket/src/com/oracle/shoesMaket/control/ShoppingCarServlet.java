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
		case "deleteShoes"://�ж��û�����Ĳ����У����������ǽ����ĸ���̨ҵ�񷽷�
		{	
			System.out.println("��ʼɾ����Ʒ");
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
	 * �����Ǵ�����ӵ����ﳵ�ķ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ��ҳ��Ʒ��������id
		String shoeid=request.getParameter("shoeid");
		System.out.println(shoeid);
		//2.���������id�洢��  �����ﳵ��   ����
		//���ж�session����û�д洢���ﳵ���ϣ�����У�˵��֮ǰ�Ѿ�������������û��˵��û�������
		if(request.getSession().getAttribute("shoes")==null)
		{
			//
			HashMap<Shoes, Integer> shoppingcars=new HashMap<>();
			//
			shoppingcars.put(dao.getShoesInfoByShoesId(Integer.parseInt(shoeid)), 1);
			request.getSession().setAttribute("shoes", shoppingcars);
		}
		else {
			//�Ѿ����ڹ��ﳵmap���ϣ���ȡ��session����Ĺ��ﳵmap����
			HashMap<Shoes,Integer> shoppingcars=(HashMap<Shoes,Integer>) request.getSession().getAttribute("shoes");
			//��Ϊsession���Ѿ����ڹ��ﳵ���ϣ�˵��֮ǰ�Ѷ�����ӹ����ﳵ
			//�������Ʒ��ӹ����ﳵ�����ԭ���������������ڵ��������������Ʒû����ӹ�����ֱ�ӽ������Ʒ��ӵ�������
			System.out.println("�Ѿ�����map����");
			if(shoppingcars.containsKey(new Shoes(Integer.parseInt(shoeid))))
			{
				System.out.println("���и���Ʒ");
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
		//�������֮��ֱ���������ﳵҳ��
		response.sendRedirect("shoppingCar.jsp");
	}
	
	/**
	 * �����Ǵ���ɾ������Ʒ�Ĵ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteShoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҪɾ��Ь�ӵ�id
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
	 * �����Ǵ�����Ʒ������һ�Ĵ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void countAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	/**
	 * �����Ǵ�����Ʒ������һ�Ĵ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void countReduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * �����Ǵ���ɾ��ȫ����Ʒ�Ĵ���
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
