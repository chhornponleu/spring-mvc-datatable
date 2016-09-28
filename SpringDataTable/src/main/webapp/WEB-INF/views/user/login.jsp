<%@ page trimDirectiveWhitespaces="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href='<spring:url value="/assets/css/bootstrap.min.css"></spring:url>' />
<link rel="stylesheet" href='<spring:url value="/assets/css/login.css"></spring:url>' />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-body">
						<img src='<spring:url value="/assets/images/wing-logo.png"></spring:url>' style="width: 200px;" />
						<br>
						${SPRING_SECURITY_LAST_EXCEPTION.message}
						<hr>
						<form action='<spring:url value="/user/authenticate"></spring:url>' method="POST" role="form">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							
							<div class="form-group">
								<input name="username" required="required" class="form-control" placeholder="user name" type="text" autofocus autocomplete="off">
							</div>
							
							<div class="form-group">
								<input name="password" required="required" class="form-control" placeholder="Password"  type="password" value="" autocomplete="off">
							</div>
							
							<button type="submit" class="btn btn-block btn-success">Login</button>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>