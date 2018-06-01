package com.oracle.shoesMaket.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CodeServlet
 */
@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
	
	 private static int width = 90;// 定义图片的width
	 private static int height = 30;// 定义图片的height                                                                                   
	 private static int codeCount = 4;// 定义图片上显示验证码的个�?                                                                               
	 private static int xx = 15;                                                                                                     
	 private static int fontHeight = 28;                                                                                             
	 private static  int codeY = 25;                                                                                                 
	 private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
	         'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };                             
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.先设置这个servlet的输出格�?  ,
		response.setContentType("image/jpeg");//通知浏览器，我这个servet运行完之后给浏览器什么的内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        
        
        
        //2.java代码负责动�?�生成一个内存中图片
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);      
        // Graphics2D gd = buffImg.createGraphics();                                               
        // Graphics2D gd = (Graphics2D) buffImg.getGraphics();                                     
        Graphics gd = buffImg.getGraphics();                                                       
        // 创建�?个随机数生成器类                                                                             
        Random random = new Random();                                                              
        // 将图像填充为白色                                                                                
        gd.setColor(Color.WHITE);                                                                  
        gd.fillRect(0, 0, width, height);                                                          
                                                                                                   
        // 创建字体，字体的大小应该根据图片的高度来定�??                                                                  
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);                                   
        // 设置字体�?                                                                                   
        gd.setFont(font);                                                                          
                                                                                                   
        // 画边框�??                                                                                    
        gd.setColor(Color.BLACK);                                                                  
        gd.drawRect(0, 0, width - 1, height - 1);                                                  
                                                                                                   
        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到�??                                                          
        gd.setColor(Color.BLACK);                                                                  
        for (int i = 0; i < 30; i++) {                                                             
            int x = random.nextInt(width);                                                         
            int y = random.nextInt(height);                                                        
            int xl = random.nextInt(12);                                                           
            int yl = random.nextInt(12);                                                           
            gd.drawLine(x, y, x + xl, y + yl);                                                     
        }                                                                                          
                                                                                                   
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证�?                                                     
        StringBuffer randomCode = new StringBuffer();                                              
        int red = 0, green = 0, blue = 0;                                                          
                                                                                                   
        // 随机产生codeCount数字的验证码�?                                                                    
        for (int i = 0; i < codeCount; i++) {                                                      
            // 得到随机产生的验证码数字�?                                                                       
            String code = String.valueOf(codeSequence[random.nextInt(36)]);                        
            // 产生随机的颜色分量来构�?�颜色�?�，这样输出的每位数字的颜色值都将不同�??                                                  
            red = random.nextInt(255);                                                             
            green = random.nextInt(255);                                                           
            blue = random.nextInt(255);                                                            
                                                                                                   
            // 用随机产生的颜色将验证码绘制到图像中�?                                                                 
            gd.setColor(new Color(red, green, blue));                                              
            gd.drawString(code, (i + 1) * xx, codeY);                                              
                                                                                                   
            // 将产生的四个随机数组合在�?起�??                                                                     
            randomCode.append(code);                                                               
        }                                                                                          
        //存放验证�?                                                                                    
//        map.put("code", randomCode);                                                               
        //存放生成的验证码BufferedImage对象                                                                  
        request.getSession().setAttribute("generateCode", randomCode);//将生成的验证码存储到session中，因为只有session能跨多个页面("多个servlet")
        //3.用servlet的输出流将这个图片写出去
        ImageIO.write(buffImg, "jpeg",response.getOutputStream());
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
