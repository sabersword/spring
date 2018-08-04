<#macro loopFunc nodes>
<#list nodes as node>
	${node.node1}
	${node.node2}
	${node.node3}
</#list> 
</#macro>
<MSG011>
	<@loopFunc nodes=root.ap.business.loop/>
</MSG011>


