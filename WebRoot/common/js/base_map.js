 var pointInfo=[];  
 /* var pointInfo=[{x:113.460433,y:39.005847,pname:"A",img_url:"common/img/green.png"},
                 {x:114.619462,y:38.99149,pname:"B",img_url:"common/img/green.png"},
                 {x:114.959812,y:40.166003,pname:"C",img_url:"common/img/green.png"}];*/  
  //110.719235,40.021171
  //111.91506,39.367374
  var marker,icon,preImg;
  var firstImg="common/img/red.png";
  var changeImg="common/img/blue.png";
  var iconSize=20;
  var map=new BMap.Map("map");
  map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
  map.enableScrollWheelZoom();
  /*map.addEventListener("click", function(e){    
	  console.log(e.point.lng+","+e.point.lat);   
  });*/
   //页面加载获取信息
	$.ajax({
		url:"MapServlet",
		type:"post",
		dataType:"json",
		success:function(data){
			//console.log("data.length...."+data.length);
			pointInfo=data;
			if(pointInfo==undefined){
				//console.log("无数据。。。")
			}
		},
		async:false
	});
  showPointInfo();
  //显示点信息
  function showPointInfo(){
	  var len=pointInfo.length;
	  if(len==0){
		  alert("无点信息!");
		  return;
	  }
	  for(var i=0;i<len;i++){
		  var tempObj=pointInfo[i];
		  //var img_url=tempObj.img_url;
		  var img_url=firstImg;
		  icon=new BMap.Icon(img_url,new BMap.Size(iconSize,iconSize),{imageSize:new BMap.Size(iconSize,iconSize)});
		  marker = new BMap.Marker(new BMap.Point(tempObj.x,tempObj.y),{
				title:tempObj.pname,
				icon:icon
		  });
		  marker.addr=tempObj.addr;
		  marker.pname=tempObj.pname;
		  map.addOverlay(marker);
		  marker.addEventListener("mouseover", function(e){   
			  preImg=this.getIcon().imageUrl;
			  var this_icon=this.getIcon();
			  this_icon.setImageUrl(changeImg);
			  this.setIcon(this_icon);
		  });
		  marker.addEventListener("mouseout", function(e){   
			  var this_icon=this.getIcon();
			  this_icon.setImageUrl(preImg);
			  this.setIcon(this_icon);
		  });
		  marker.addEventListener("click", function(){
			  var opts = {
					  width : 250,
					  height: 100,
					  title : this.pname
			  }
			  var infoWindow = new BMap.InfoWindow("地址："+this.addr, opts);
			  map.openInfoWindow(infoWindow,this.getPosition());
		  });
	  }
  }
