import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "My First Servlet", urlPatterns = { "/FirstServlet" , "/FirstServlet.do"}, initParams = {@WebInitParam(name="id",value="1"),@WebInitParam(name="name",value="alex")})
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date date = new Date();
		ArrayList results = new ArrayList();
		ArrayList title = new ArrayList();
		Document document = Jsoup.connect("http://www.independent.co.uk/search/site/theresa%252Bmay").get();
		String url ="";
		String text = "";
		Elements links = document.select("div.search-snippet-info  a");
     
        
       out.println("<H1>Independent articles</H1>"); 
            
       for (Element link : links) {
            url = link.absUrl("href");
            text = link.outerHtml();
            results.add(url);
            title.add(text);
        } 
		/*
        int n =0;
        while(n < title.size() && n <results.size()){
        	out.print("<ul>");
        	out.print("<a href= '"+results.get(n)+"'" + "target=_blank" + ">" + title.get(n) + "</a>");
        	out.print(" ");
        	out.print("</ul>");
        	n++;
        }
        */
        
        
       
      for(int i =0; i< results.size(); i++){
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
	}

}