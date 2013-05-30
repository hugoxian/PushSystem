<?xml version="1.0" encoding="UTF-8"?>
<nodes>
	<#list nodes as node>
	<node>
		<id>${node.id}</id>
		<parentId>${node.parentId}</parentId>
		<name>${node.name}</name>
		<sequence>${node.sequence}</sequence>
		<uri>${node.uri!""}</uri>
		<#if node.subNodes?exists&&(node.subNodes?size>0)>
		<subNodes>
		<#list node.subNodes as subNode>
			<subNode>
				<id>${subNode.id}</id>
				<parentId>${subNode.parentId}</parentId>
				<name>${subNode.name}</name>
				<sequence>${subNode.sequence}</sequence>
				<uri>${subNode.uri!""}</uri>
			</subNode>
		</#list>
		</subNodes>
		</#if>
	</node>
	</#list>
</nodes>