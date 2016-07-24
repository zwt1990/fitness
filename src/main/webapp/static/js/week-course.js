var WeekCourse = function() {
	   var pageIndex = 1,  //当前页索引
       pageSize = 30,  //每页条数
       myScroll,
       pullUpEl, pullUpOffset,
       generatedCount = 0,
       currPoint = {}
   ;
   var count_index = 0;//计次数
	function init(){
		initHeadInfo();
		initLocation();
		initEvent();
	}
	function initEvent(){
		initStoreEvent();
		initScoll();
	}
	
	function initLocation(){
	    var geolocation = new BMap.Geolocation();
	    geolocation.getCurrentPosition(function(r){
	        if(this.getStatus() == BMAP_STATUS_SUCCESS){
	            currPoint = r.point;//当前点的坐标
	            var addr = r.address;
	            console.log("当前位置坐标：", currPoint);
	            getDistince();
	        }
	    });
	    $(".address-item .addr").html($("#J_branch").find("option:selected").attr("addr"));
	}

	
	 //分店下拉框选择事件
	function initStoreEvent(){
	    $("#J_branch").on("change", function(){
	        count_index = 0;
	        getDistince();
	        pageIndex = 1;
	        $(".course-item").remove();
	        $(".no-data").remove();
	        queryCourse();
	        resize_store_select();
	    });
		
	}
    //日期选择事件
	function initDateSelect(){
	    $(".calendar-enginue .day-item").on("click", function(){
	        count_index = 0;
	        if($(this).is(".ce75")){
	            return false;
	        }
	        $(".calendar-enginue .day-item").removeClass(("ce75"));
	        $(this).addClass("ce75");
	        pageIndex = 1;
	        $(".course-item").remove();
	        $(".no-data").remove();
	        queryCourse();
	    });
		
	}
	
	function queryCourse(){
		$.ajax({
			url : 'weekCourse/getCourses',
			data : {
			
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
			     var html_tmp=_.template($("#course-temp").html(),data);
		         $("#pullUp").before(html_tmp);
		         $("#pullUp").hide();
		         $(".course-item").click(function(e){
		        	 location.href="schedule-detail.html?id="+ $(this).data('id');
		         })
		         
			},
			error : function(data) {
				alert('系统繁忙');
			}
		});
	}
	
	
	function initHeadInfo() {
	   var city_id_selected = $('#city_id').val();
        var show_cross_fit = $('#show_cross_fit').val();
		$.ajax({
			url : 'weekCourse/getHeaderInfo',
			data : {
				cityId :1,
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				htmlStoreOpts(data.stores);
			     var html_tmp=_.template($("#week-temp").html(),data.weeks);
		         $(".calendar-enginue").html(html_tmp);
		         initDateSelect();
		         queryCourse();
			},
			error : function(data) {
				alert('系统繁忙');
			}
		});
	}
	
	
	  function htmlStoreOpts(stores){
	        var city_list_str = '';
	        if(stores.length>0){
	            $(stores).each(function(index, element){
	                var selected = '';
	                if(element.id == my_store_id){
	                    selected = ' selected ';
	                }
	                city_list_str += '<option ' + selected + ' value="' + element.id + '" addr="' + element.address + '" lng="' + element.lng + '" lat="'+element.lat +  '">' + element.name + '</option>';
	            });
	        }else{
	            var msg = '此城市没有开通的店铺';
	            if(show_cross_fit){
	                msg = '该城市没有开通CrossFit的店铺';
	            }
	            alert(msg);
	        }
	        $('#J_branch').html(city_list_str);
	    }
	  
	  	var my_store_id = "10";
		var map = new BMap.Map("allmap");
		function createMap(lng, lat){
			var new_point = new BMap.Point(lng, lat);
	        map.centerAndZoom(new_point, 15);
	        map.enableScrollWheelZoom();//启用滚轮放大缩小
	        map.closeInfoWindow();//关闭在地图上打开的信息窗口
	        var local = new BMap.LocalSearch(map, {
	            renderOptions:{map: map}
	        });
			map.clearOverlays(); 
			var marker = new BMap.Marker(new_point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.panTo(new_point);	
		}
	    
	  
	  /**
	     * 计算距离
	     */
	    function getDistince(){
	        var jOption = $("#J_branch").find("option:selected"),
	            lat = jOption.attr("lat"),
	            lng = jOption.attr("lng"),
	            addr = jOption.attr("addr") ;
	        $(".address-item .addr").html(addr);
	        console.log("分店位置坐标", {lng:lng, lat:lat});
	        //计算距离
	        var nDistance = GetDistance(currPoint.lat,currPoint.lng, lat, lng);//单位为公里
	        sDis = nDistance > 1 ? (parseInt(nDistance) + "km") : (parseInt((nDistance * 1000)) + "m");
	        $(".search-item .distance").text(sDis);
	       createMap(lng, lat);
	    }
	  
	  
	    function load_baidu_map_info(is_init){
	        if(is_init){
	            var jOption = $("#J_branch").find("option:selected"),
	                lat = jOption.attr("lat"),
	                lng = jOption.attr("lng"),
	                addr = jOption.attr("addr");
	            createMap(lng, lat);
	        }
	        getDistince();
	        pageIndex = 1;
	        $(".course-item").remove();
	        $(".no-data").remove();
	        queryCourse();
	    }
	    
	    function initScoll(){
	    	  if(!$("#pullUp").length){
	                return false
	            }
	            pullUpEl = document.getElementById('pullUp');   
	            pullUpOffset = pullUpEl.offsetHeight;
	            
	            myScroll = new iScroll('J_wrapper', {
	                scrollbarClass: 'myScrollbar', 
	                useTransition: false,    
	                onRefresh: function () {
	                     if (pullUpEl.className.match('loading')) {
	                        pullUpEl.className = '';
	                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
	                    }
	                },
	                onScrollMove: function () {
	                    if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
	                        pullUpEl.className = 'flip';
	                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
	                        this.maxScrollY = this.maxScrollY;
	                    } else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
	                        pullUpEl.className = '';
	                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
	                        this.maxScrollY = pullUpOffset;
	                    }
	                },
	                onScrollEnd: function () {
	                     if (pullUpEl.className.match('flip')) {
	                        pullUpEl.className = 'loading';
	                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';                
	                        pullUpAction();
	                    }
	                }
	            });
	            
	            setTimeout(function () { document.getElementById('J_wrapper').style.left = '0'; }, 800);
	    }
	    
   	 /**
         * 滚动翻页 （自定义实现此方法）
         * myScroll.refresh();// 数据加载完成后，调用界面更新方法
         */
        function pullUpAction () {
            setTimeout(function () {
                pageIndex++;
                //queryCourse();
            }, 1000);
        }

        //初始化绑定iScroll控件 
        if($("#pullUp").length){
        }

        var EARTH_RADIUS = 6378137.0;    //单位M
        var PI = Math.PI;
        function Rad(d){
            return d * Math.PI / 180.0;
        }   
        function GetDistance(lat1,lng1,lat2,lng2){          
            var radLat1 = Rad(lat1);
            var radLat2 = Rad(lat2);
            var a = radLat1 - radLat2;
            var  b = Rad(lng1) - Rad(lng2);
            var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
            Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
            s = s * 6378.137 ;// EARTH_RADIUS;
            s = Math.round(s * 10000) / 10000; 
            return s;
        }   
        if( $(document).height()  <= $(window).height() ){
        	pullUpAction();
        } 
        
       $(window).scroll(function () {
            var scrollTop = $(this).scrollTop();
            var scrollHeight = $(document).height();
            var windowHeight = $(this).height();
            if (scrollTop + windowHeight == scrollHeight) {
    		pullUpAction();

            }
        });
	    
	return {
		init : function() {
			init();
		}
	}
}();
$(function(){
	WeekCourse.init();
})