<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/index.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.1.min.js"></script>
<style type="text/css">
 /*设置超链接样式*/
#tabDiv.a{
    color: #ffffff;
    text-decoration: none;
 }

#tabDiv.a:hover{
    color: #ffffff;
    text-decoration: underline;
    font-size: 12px;
 }

#tabDiv.a:visited{
    color: #ffffff;
    font-size: 12px;
}

#tabDiv{
    width:100%;
    padding-bottom: 10px;
    border-right: #C7C7C7 1px solid;
    border-top: #C7C7C7 1px solid;
    border-left: #C7C7C7 1px solid;
    border-bottom: #C7C7C7 1px solid;
    overflow:hidden;
 }

/*tab头的样式*/
#tabsHead{
     padding-left: 0px;
     height: 26px;
     background-color: #C7C7C7;
     font-size: 1em;
     margin: 0px 0px 0px;
     color: #5086a5;
     line-height: 26px;
 }

/*已选tab头（超链接）的样式*/
.curtab{
    padding-top: 0px;
    padding-right: 10px;
    padding-bottom: 0px;
    padding-left: 10px;
    border-right: #ffffff 1px solid;
    font-weight: bold;
    float: left;
    cursor: pointer;
    background: #ffffff;
}

 /*未选tab头（超链接）的样式*/
.tabs{
    border-right: #dddddd 1px solid;
    padding-top: 0px;
    padding-right: 10px;
    padding-bottom: 0px;
    padding-left: 10px;
    font-weight: normal;
    float: left;
    cursor: pointer;
}
</style>
<script  type="text/javascript">
<!--
    //显示tab（tabHeadId：tab头中当前的超链接；tabContentId要显示的层ID）
    function showTab(tabHeadId,tabContentId){
        //tab层
        var tabDiv = document.getElementById("tabDiv");
        //将tab层中所有的内容层设为不可见
        //遍历tab层下的所有子节点
        var taContents = tabDiv.childNodes;
        for(i=0; i<taContents.length; i++){
            //将所有内容层都设为不可见
            if(taContents[i].id!=null && taContents[i].id != 'tabsHead'){
                taContents[i].style.display = 'none';
            }
        }
        //将要显示的层设为可见
        document.getElementById(tabContentId).style.display = 'block';
        //遍历tab头中所有的超链接
        var tabHeads = document.getElementById('tabsHead').getElementsByTagName('a');
        for(i=0; i<tabHeads.length; i++){
            //将超链接的样式设为未选的tab头样式
            tabHeads[i].className='tabs';
        }
        //将当前超链接的样式设为已选tab头样式
        document.getElementById(tabHeadId).className='curtab';
        document.getElementById(tabHeadId).blur();
    }
-->

</script>
</head>
<body>
	<div class="commonBody">
		<div id="tabDiv">
	         <div id="tabsHead">
	             <a class="curtab" id="tabs1" href="javascript:showTab('tabs1','tabContent1')">今 天</a>
	             <a class="tabs" id="tabs2" href="javascript:showTab('tabs2','tabContent2')">昨 天</a>
	             <a class="tabs" id="tabs2" href="javascript:showTab('tabs3','tabContent3')">前 天</a>
	         </div>
	         
	         <div id="tabContent1" style="display:block;overflow:hidden;padding:5px;">
	         	<div style="margin-top:10px;margin-bottom:10px;">日志路径：${errorPath!""}</div>
	         	<#assign value = "今天没有错误日志！"/>
	            <#if errorLog?exists&&(errorLog?length>2)>
					<#assign value = errorLog/>
				</#if>
				<textarea name="log" style="width:99%;height:500px;color:red;">${value}</textarea>
	         </div>
	
	         <div id="tabContent2" style="display:none">
					暂不支持昨天LOG数据！
	         </div>
	         
	         <div id="tabContent3" style="display:none">
					暂不支持前天LOG数据！
	         </div>
		</div>
	</div>
	<script type="text/javascript">
		alert(21);
	<script>
</body>
</html>