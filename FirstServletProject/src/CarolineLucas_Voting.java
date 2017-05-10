

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
 * Servlet implementation class CarolineLucas_Voting
 */
@WebServlet("/CarolineLucas_Voting")
public class CarolineLucas_Voting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarolineLucas_Voting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Document document = Jsoup.connect("https://www.theyworkforyou.com/mp/24910/caroline_lucas/brighton%2C_pavilion#votes").get();
		ArrayList results = new ArrayList();
		String text = "";
		Elements links = document.select("ul.vote-descriptions");
		
		out.println("<H1>Voting record</H1>"); 
        
	       for (Element link : links) {
	            text = link.text();
	            results.add(text);
	       }
	       for(int i =0; i< results.size(); i++){
	           out.print("<ul>");
	    	   out.print(results.get(i));
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
