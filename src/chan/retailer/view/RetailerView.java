package chan.retailer.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;

import chan.retailer.model.Model;

public class RetailerView implements View{

	@Override
	public void render(Model model, HttpServletRequest request, HttpServletResponse response) {		
		PrintWriter pw;
		try {
			response.setContentType("text/html");
			pw = response.getWriter();			
			String jsonVal = JSONValue.toJSONString(model.getModelData());			
			pw.append(jsonVal);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
