
document.addEventListener("deviceready",function(){
	var dicName = "data/";
	var $pro = function(){};
	$pro.config = {
		allData : {},						//应用所有的数据
		DirList : [], 						//左侧列表目录数据
		leftClass : "left_list", 			//左侧列表样式名称
		iconAte : ".jpg",					//左侧列表图标格式后缀
		SecDirList : [],					//二级目录数据
		SecListClass : "directory_list",	//二级目录样式名称
		AimContainer : $("#aim_container")	//读取内容存放的目标容器
	};
	/**
	* Data
	*/
	$pro.data = {};
	/**
	* View
	*/
	$pro.ui = {};
	/**
	* Event
	*/
	$pro.event = {};

	/**
	* DataAPI
	* 获取左侧列表目录的数据
	*/
	$pro.data.getDirListContent = function(){
		if( $pro.config.DirList.length != 0 ) return;
		$pro.event.readFileContent("config.ini",function(data){
			var arr = data.split(/title[\d]=/);
			for(var i=1; i<arr.length; i++){
				$pro.config.DirList.push(arr[i]);
			}
            $pro.ui.createDirList();
            $("div[data-role=panel] ul").listview("refresh");  
		});
	};
	
	/**
	* DataAPI
	* id 选择左侧列表项的ID值
	* 左侧列表点击获取二级目录数据
	*/
	$pro.data.getSecDirData = function(id,callback){
		if( allData["list"+id] == undefined ){
			$pro.event.readFileContent("config"+id+".ini",function(data){
				var arr = data.split(/title[\d]=/);
				for(var i=1; i<arr.length; i++){
					allData["list"+id][arr[i]] = "";
				}
				if( callback && typeof callback == "function"){
					callback();
				}
			});
		} else {
			callback();
		}
	}

	/**
	* ViewAPI
	* 生成左侧列表
	*/
	$pro.ui.createDirList = function(){
		var _len = $pro.config.DirList.length;
		if( _len == 0 ) return;
		for(var i=0; i < _len; i++){
			$("."+$pro.config.leftClass).append('<li _data="'+i+'"><a href="#" data-rel="close"><img src="images/'+(i+1)+$pro.config.iconAte+'" /><h2>'+$pro.config.DirList[i]+'</h2></a></li>');
		}
		$pro.event.leftDirClick();
	};
	/**
	* ViewAPI
	* 生成二级目录
	*/
	$pro.ui.createSecDirList = function(){		
		var _len = $pro.config.SecDirList.length;
		if( _len == 0 ) return;
		$("."+$pro.config.SecListClass).empty();
		for(var i=0; i < _len; i++){
			$("."+$pro.config.SecListClass).append('<li><a href="#panel-fixed-page2">'+$pro.config.SecDirList[i]+'</a></li>');
		}
	};
	/**
	* EventAPI
	* 读取文件数据
	* docName "文件名称"，AimContainer “读取的文本存放的目标容器”，callback “读取后执行的回调函数”
	*/
	$pro.event.readFileContent = function( docName,callback ){
		// 等待加载PhoneGap
	    console.log("readFileContent");
        
		
		
		// PhoneGap加载完毕
		
		
        window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, gotFS, fail); 
		
		
		function gotFS(fileSystem) { 
            console.log(dicName+docName);
            console.log("gotFS");
            
			fileSystem.root.getFile( dicName+docName, {create: true, exclusive: false}, gotFileEntry, fail); 
		}  
		
        Function 
        
		function gotFileEntry(fileEntry) { 
            console.log("gotFileEntry");
			fileEntry.file(gotFile, fail); 
		}  
		
		function gotFile(file){ 
            console.log("file");
			readAsText(file); 
		}  
		
		function readDataUrl(file) { 
			var reader = new FileReader(); 
			reader.onloadend = function(evt) { 
				console.log("Read as data URL"); 
				console.log(evt.target.result); 
			}; 
			reader.readAsDataURL(file); 
		}  
		
		function readAsText(file) { 
			var reader = new FileReader(); 
			reader.onloadend = function(evt) { 
				console.log("Read as text1");
				console.log(evt.target.result);			
				//writeHtml(evt.target.result);
				if( callback && typeof callback == "function"){
					callback(evt.target.result);
				}
			}; 

			reader.readAsText(file);
		}

		function writeHtml(contextText)
		{
			contextText = contextText.replace(/\r\n/ig,"<br/><br/>");
			AimContainer.html(contextText);;
		}		
		
		function fail(evt) { 
            console.log("fail");
			console.log(evt.target.error.code); 
		}
		
	};
	
	/**
	* EventAPI
	* 动作：点击左侧列表项
	*/
	$pro.event.leftDirClick = function(){
		$("."+$pro.config.leftClass+" li").click(function(){
			var _idx = $(this).attr("_data");
			$pro.data.getSecDirData(_idx,$pro.ui.createSecDirList);
		});
	};
	
	/**
	* EventAPI
	* 动作：点击二级目录获取内容
	*/
	$pro.event.secDirClick = function(){
		$("."+$pro.config.SecListClass+" li").click(function(){
			
		});
	};
	
	$pro.init = function(){
        console.log("mainJSinit");
		$pro.init.InitData();
		$pro.init.InitUI();
	};	
	$pro.init.InitData = function(){
		$pro.data.getDirListContent();
	};
	$pro.init.InitUI = function(){
		
	};

	
	$pro.init();
	
}, false);