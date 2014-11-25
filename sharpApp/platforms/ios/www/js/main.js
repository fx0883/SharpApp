
document.addEventListener("deviceready",
function(){
var dicName = "data/";

	var $pro = window.$pro ? window.$pro : function(){};
	$pro.config = {
		num : 9,							//左侧列表目录数
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
        console.log("getDirListContent=== ");
		if( $pro.config.DirList.length != 0 ) return;
		$pro.event.readFileContent("config.ini",function(data){
			var arr = data.split(/title[\d]=/);
            console.log(arr);
			for(var i=1; i<arr.length; i++){
                console.log(arr[i]);
				$pro.config.DirList.push(arr[i]);
			}
            $pro.ui.createDirList();
            
		});
	};

	/**
	* ViewAPI
	* 生成左侧列表
	*/
	$pro.ui.createDirList = function(){
        console.log($pro.config.DirList.length);
		if( $pro.config.DirList.length == 0 ) return;
		for(var i=0; i < $pro.config.num; i++){
			$("."+$pro.config.leftClass).append('<li><a href="#" data-rel="close"><img src="images/'+(i+1)+$pro.config.iconAte+'" /><h2>'+$pro.config.DirList[i]+'</h2></a></li>');
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