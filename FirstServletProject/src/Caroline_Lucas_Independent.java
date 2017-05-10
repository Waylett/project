

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
 * Servlet implementation class Caroline_Lucas_Independent
 */
@WebServlet("/Caroline_Lucas_Independent")
public class Caroline_Lucas_Independent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Caroline_Lucas_Independent() {
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
		ArrayList results = new ArrayList();
		ArrayList title = new ArrayList();
		Document document = Jsoup.connect("http://www.independent.co.uk/search/site/caroline%252Blucas").get();
		String url ="";
		String text = "";
		
		out.println("<H1>Independent articles</H1>"); 
		
		Elements links = document.select("div.search-snippet-info  a");
	    for (Element link : links) {
	        url = link.absUrl("href");
	        text = link.outerHtml();
            results.add(url);
            title.add(text);
	    }
	    for(int i =1; i< results.size(); i++){
	    	out.print("<ul>");
            out.printf("<a href= '%s'" + "target=_blank" + ">" + results.get(i) + "</a>",results.get(i));
            out.println("\n");
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
