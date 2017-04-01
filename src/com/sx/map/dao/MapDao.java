package com.sx.map.dao;

import java.util.ArrayList;
import java.util.List;

import com.sx.db.AbsDaoSupport;
import com.sx.map.po.Point;

/**
 * @author yk
 * Created on 2016-3-2 ионГ10:38:45
 */
public class MapDao extends AbsDaoSupport{
	public List<Point> getPointInfo() throws Exception{
		String sql="select qybh,dwmc,x,y,qywz from aj_qyjbxx where dwxz=?";
		List<Point> list=this.querySqlForList(sql, new String[]{"00"}, Point.class);
		return list==null?new ArrayList<Point>():list;
	}

}
