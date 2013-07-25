var nodes = new Array();

function Node(id, parentId, name, sequence, uri, subNodes) {
	this.id = id;
	this.parentId = parentId;
	this.name = name;
	this.sequence = sequence;
	this.uri = uri;
	this.subNodes = subNodes;
	this.draw = function() {
		var temp = escape(name);
		var nodediv = "<a href='#first_layer_node_"+temp+"' onClick='clickNode("+id+");' class='topBarNormal'>" + name + "</a>|";
		$('#topBarArea').append(nodediv);

		var subNodesdiv = "<div id='subNodeArea"+id+"' class='subNodeNormal'>";
		for ( var i = 0; i < subNodes.length; i++) {
			var subTemp = escape(subNodes[i].name);
			var lidiv = "<div class='subNodeItem'><img src='images/right.png' id='subNode"+subNodes[i].id+"'/><a href='"+subNodes[i].uri+"' target='content'>"+subNodes[i].name+"</a></div>";
			subNodesdiv=subNodesdiv+lidiv;
		}
		subNodesdiv = subNodesdiv+"</div>";
		$('#leftSideBarArea').append(subNodesdiv);
		
	}
}

function clickNode(id){
	for(var i=0;i<nodes.length;i++) {
		if(nodes[i].id==id){
			window.open(nodes[i].subNodes[0].uri,"content");
			$('#subNodeArea'+id).show("800");
		}else{
			$('#subNodeArea'+nodes[i].id).hide();
		}
	}
}

function exit() {
	$.post("exit.do", {
		reason : 1
	}, function(data) {
	});
	window.location.href='index.html'; 
}

function init() {
	$.ajax( {
		url : "init.do",
		dataType : "xml",
		type : "post",
		success : function(data) {
			$(data).find("node").each(function(i) {
				var id = $(this).children("id").text();
				var parentId = $(this).children("parentId").text();
				var name = $(this).children("name").text();
				var sequence = $(this).children("sequence").text();
				var uri = $(this).children("uri").text();

				var subNodesData = $(this).children("subNodes");
				var subNodes = new Array();
				$(subNodesData).find("subNode").each(function(i) {
					var subid = $(this).children("id").text();
					var subparentId = $(this).children("parentId").text();
					var subname = $(this).children("name").text();
					var subsequence = $(this).children("sequence").text();
					var suburi = $(this).children("uri").text();
					subNodes[i] = new Node(subid,subparentId, subname,subsequence, suburi, "");
				});
				nodes[i] = new Node(id, parentId, name, sequence, uri,subNodes);
				nodes[i].draw();
			});
			$('#loading-page').remove();
			
			var firstId = nodes[0].id;
			$('#subNodeArea'+firstId).show("800");
			
			window.open(nodes[0].subNodes[0].uri,"content");
		}
	});
}

function loadContent(uri){
	$.post(uri, {
		position : "here"
	}, function(data) {
		var resultCode = $(data).find("resultCode").text();
		if("777"==resultCode){
			window.location.href = "index.html";
		}else{
			var temp = $('#rightSideBarArea').html();
			$('#rightSideBarArea').html(data+temp);	
		}
	});
}
