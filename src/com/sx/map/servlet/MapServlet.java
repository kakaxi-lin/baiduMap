package com.sx.map.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.sx.map.po.Point;
import com.sx.map.service.MapService;

/**
 * @author yk
 * Created on 2016-3-2 上午10:32:18
 */
public class MapServlet extends HttpServlet{
	MapService ms=new MapService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		List<Point> pointList=null;
		try {
			pointList = ms.getPointInfo();
			System.out.println("点的长度..."+pointList.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jsonArray=new JSONArray();
		for(Point p:pointList){
			try {
				JSONObject object=new JSONObject();
				object.put("pname", p.getDwmc());
				object.put("x", p.getX());
				object.put("y", p.getY());
				object.put("addr", p.getQywz());
				object.put("pid", p.getQybh());
				jsonArray.put(object);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.close();
	}

}
