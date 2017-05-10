

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class Caroline_Lucas_Telegraph
 */
@WebServlet("/Caroline_Lucas_Telegraph")
public class Caroline_Lucas_Telegraph extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Caroline_Lucas_Telegraph() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Date date = new Date();
		Document document = Jsoup.connect("http://www.telegraph.co.uk/caroline-lucas/").get();
	    Elements links = document.select("div.p_hub a");
	    String url ="";
		String text = "";
		ArrayList results = new ArrayList();
		ArrayList title = new ArrayList();
		
		 for (Element link : links) {
	            url = link.absUrl("href");
	            text = link.outerHtml();
	            results.add(url);
	            title.add(text);
	        } 
		
		 out.println("<H1>Telegraph articles</H1>"); 
	
	      for(int i =0; i< 21; i++){
	    	  if(results.get(i + 1).equals(results.get(i))){
		    		results.remove(i);
		    	}
	    	  out.print("<ul>");
	          out.printf("<a href= '%s'" + "target=_blank" + ">" + results.get(i) + "</a>",results.get(i));
	          out.print(" ");
	          out.print("</ul>");
	          }
	         
	  	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
