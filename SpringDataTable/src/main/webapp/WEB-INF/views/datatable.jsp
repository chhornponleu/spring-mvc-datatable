<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<spring:url var="baseURL" value="/"/>
<!DOCTYPE html>
<html>
	<head>
		<title>Data Table</title>
<%-- 		<link rel="stylesheet" href="${baseURL}/assets/vendors/bootstrap/css/bootstrap.css"> --%>
<%-- 		<link rel="stylesheet" href="${baseURL}/assets/vendors/datatables/css/jquery.dataTables.css"> --%>
		<link rel="stylesheet" href="${baseURL}/assets/vendors/material-lite/css/material.min.css">
		<link rel="stylesheet" href="${baseURL}/assets/vendors/datatables/css/dataTables.material.min.css">
		<style>
			.pagination:not()>li>a {
				border-radius: 50%;
				margin-left:10px;
			}
			
		</style>
	</head>
<body>
	<h1>Data Table Server</h1>
	
	<table id="table_id" class="mdl-data-table"  width="100%">
    	<thead>
    		<tr>
    			<th>User ID</th>
    			<th>User Name</th>
    		</tr>
    	</thead>
    	
	</table>
	
	
	<script src="${baseURL}/assets/vendors/datatables/js/jquery.js"></script>
	<script src="${baseURL}/assets/vendors/datatables/js/jquery.dataTables.js"></script>
	<script src="${baseURL}/assets/vendors/datatables/js/dataTables.material.js"></script>
	<script>
		var page = {
			init : function () {
				this.initDataTable();
			},
			initDataTable : function () {
				
				$('#table_id').DataTable({
					serverSide : true,
					searchDelay: 1350,
					ajax : {
						url : '${baseURL}user/datatable?${_csrf.parameterName}=${_csrf.token}',
						method : 'POST',
						contentType : 'application/json',
						dataType : 'json',
						data : function (data) {
			          		return JSON.stringify(data);
			          	}
					},
					columns: [
		          		{ data: 'userId', 'name': 'userId'},
		          		{ data: 'username', name : 'username' }
		          	],
		          	"scrollY": "200px",
		            //"scrollCollapse": true,
				});
			}
		};
		$(document).ready(function() {
			page.init();
		});
	</script>
</body>
</html>