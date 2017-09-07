<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	padding-top: 40px;
}
#map {
	min-width: 300px;
	min-height: 400px;
	width: 100%;
	height: 100%;
}

.header {
	background-color: #F5F5F5;
	height: 70px;
	font-size: 27px;
	padding: 10px;
	//zrob tutaj cos zeby te tytuly sie nie wylewaly
	overflow:initial;
}

.center {
	margin: auto;
	width: 50%;
}
</style>
<title>Insert title here</title>
</head>
<body>



<mytags:navbar />
	<spring:url value="/PvModule/add" var="addPvModule" />
	<div class="row" style="margin-top: 20px;">
		<div class="well well-sm center">
			<form:form action="${addPvModule}" class="form-horizontal"
				modelAttribute="pvModule" method="POST">
				<fieldset>
					<legend class="text-center header">Wprowadź parametry
						panelu fotowoltaicznego</legend>
					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="name" class="col-sm-5 col-form-label">Nazwa</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="name"
									id="name" />
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="nominalPower" class="col-sm-5 col-form-label">nominalPower</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="nominalPower" id="nominalPower" />
							</div>
						</div>
					</div>					

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="moduleEfficiencySTC" class="col-sm-5 col-form-label">moduleEfficiencySTC</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="moduleEfficiencySTC" id="moduleEfficiencySTC" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="vmppSTC" class="col-sm-5 col-form-label">vmppSTC</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="vmppSTC" id="vmppSTC" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="vocSTC" class="col-sm-5 col-form-label">vocSTC</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="vocSTC" id="vocSTC" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="iscSTC" class="col-sm-5 col-form-label">iscSTC</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="iscSTC" id="iscSTC" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="moduleSurface" class="col-sm-5 col-form-label">moduleSurface</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="moduleSurface" id="moduleSurface" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="NOCT" class="col-sm-5 col-form-label">NOCT</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="NOCT" id="NOCT" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="temperatureCoefficientOfVoc" class="col-sm-5 col-form-label">temperatureCoefficientOfVoc</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="temperatureCoefficientOfVoc" id="temperatureCoefficientOfVoc" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="temperatureCoefficientOfIsc" class="col-sm-5 col-form-label">temperatureCoefficientOfIsc</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="temperatureCoefficientOfIsc" id="temperatureCoefficientOfIsc" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="temperatureCoefficientOfPmax" class="col-sm-5 col-form-label">temperatureCoefficientOfPmax</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="temperatureCoefficientOfPmax" id="temperatureCoefficientOfPmax" />
							</div>
						</div>
					</div>


					<div class="form-group">
						<div class="col-md-12 text-center">
							<form:button type="submit" class="btn btn-primary btn-lg">Dodaj</form:button>
						</div>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>





</body>
</html>