<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
</style>
</head>
<body>
	<div class="commonBody">
		<a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>清空缓存</span></a><a href='#' class='linkbutton' onClick="del()" style="margin-right:10px;"><span>初始化缓存</span></a>
		<hr/>
		<div style="height:10px;"></div>
		<table class="gridtable">
			<tr>
			    <th>缓存块名称</th><th>内存中个数</th><th>占用内存大小（字节）</th><th>硬盘中个数</th><th>占用硬盘大小（字节）</th><th>命中次数</th><th>漏掉次数</th>
			</tr>
			<#if caches?exists&&(caches?size>0)>
			<#list caches as cache>
			<tr>
			    <td>${cache.name}</td><td>${cache.memoryStoreSize}</td><td>${cache.memorySize}</td><td>${cache.diskStoreSize}</td><td>${cache.diskSize}</td><td>${cache.cacheHits}</td><td>${cache.cacheMisses}</td>
			</tr>
			</#list>
			</#if>
		</table>
		<div style="margin-top:5px;margin-bottom:5px;">注：现在缓存采用Ehcache实现，随着发送和聊天的并发增多，socketCache将采用Memcached，支持集群</div>
	</div>
</body>
</html>