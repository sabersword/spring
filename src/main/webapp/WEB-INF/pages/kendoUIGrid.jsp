<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Kendo UI Snippet</title>

	<script src=http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js></script>
	<script
		src="http://kendo.cdn.telerik.com/2015.3.1111/js/kendo.all.min.js"></script>
	<link
		href="http://kendo.cdn.telerik.com/2015.3.1111/styles/kendo.common.min.css"
		rel="stylesheet">
	<link
		href="http://kendo.cdn.telerik.com/2015.3.1111/styles/kendo.rtl.min.css"
		rel="stylesheet">
	<link
		href="http://kendo.cdn.telerik.com/2015.3.1111/styles/kendo.default.min.css"
		rel="stylesheet">
	<link
		href="http://kendo.cdn.telerik.com/2015.3.1111/styles/kendo.dataviz.min.css"
		rel="stylesheet">
	<link
		href="http://kendo.cdn.telerik.com/2015.3.1111/styles/kendo.dataviz.default.min.css"
		rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/examples-offline.css" rel="stylesheet"> 
</head>
<body>
<div id="example"> 
    <div id="grid"></div> 
    <script>
	       $(document).ready(function () {
	           var crudServiceBaseUrl = "http://localhost:8080/springwebblank",
	               dataSource = new kendo.data.DataSource({
	                   transport: {
	                       read:  {
	                           url: crudServiceBaseUrl + "/products",
	                           dataType: "jsonp"
	                       },
	                       update: {
	                           url: crudServiceBaseUrl + "/products/update",
	                           dataType: "jsonp"
	                       },
	                       destroy: {
	                           url: crudServiceBaseUrl + "/products/destroy",
	                           dataType: "jsonp"
	                       },
	                       create: {
	                           url: crudServiceBaseUrl + "/products/create",
	                           dataType: "jsonp"
	                       },
	                       parameterMap: function(options, operation) {
	                           if (operation !== "read" && options.models) {
	                               return {models: kendo.stringify(options.models)};
	                           }
	                       }
	                   },
	                   batch: true,
	                   pageSize: 20,
	                   schema: {
	                       model: {
	                           id: "productId",
	                           fields: {
	                               productId: { editable: false, nullable: true },
	                               productName: { validation: { required: true } },
	                               unitPrice: { type: "number", validation: { required: true, min: 1} },
	                               discontinued: { type: "boolean" },
	                               unitsInStock: { type: "number", validation: { min: 0, required: true } }
	                           }
	                       }
	                   }
	               });
	
	           $("#grid").kendoGrid({
	               dataSource: dataSource,
	               navigatable: true,
	               pageable: true,
	               height: 550,
	               toolbar: ["create", "save", "cancel"],
	               columns: [
	                   "productName",
	                   { field: "unitPrice", title: "Unit Price", format: "{0:c}", width: 120 },
	                   { field: "unitsInStock", title: "Units In Stock", width: 120 },
	                   { field: "discontinued", width: 120 },
	                   { command: "destroy", title: " ", width: 150 }],
	               editable: true
	           });
	       });
	   </script> 
    </div>   
	<script type="text/javascript">  
	    $(document).ready(function(){  
	        var saveDataAry=[];  
	        var data1={"productId":1,"productName":"Chaia","unitPrice":18,"unitsInStock":39,"discontinued":false};   
	        saveDataAry.push(data1);          
	        $.ajax({ 
	            type:"POST", 
	            url:"/springwebblank/products/update", 
	            dataType:"jsonp",  
	            jsonp:'callback',
	            contentType:"application/json",               
	            data:JSON.stringify(data1), 
	            success:function(data){ 
	            	var data0 = data[0];
	            } 
	         }); 
	    });  
	</script>



</body>
</html>