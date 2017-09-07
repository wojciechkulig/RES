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
	<spring:url value="/inverter/add" var="addInverter" />
	<div class="row" style="margin-top: 20px;">
		<div class="well well-sm center">
			<form:form action="${addInverter}" class="form-horizontal"
				modelAttribute="inverter" method="POST">
				<fieldset>
					<legend class="text-center header">Wprowadź parametry
						falownika</legend>
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
							<label for="nominalPowerAC" class="col-sm-5 col-form-label">nominalPowerAC</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="nominalPowerAC" id="nominalPowerAC" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="maxInputVoltage" class="col-sm-5 col-form-label">maxInputVoltage</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="maxInputVoltage" id="maxInputVoltage" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="minVoltageMPP" class="col-sm-5 col-form-label">minVoltageMPP</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="minVoltageMPP" id="minVoltageMPP" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="maxVoltageMPP" class="col-sm-5 col-form-label">maxVoltageMPP</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="maxVoltageMPP" id="maxVoltageMPP" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="nominalVoltageDC" class="col-sm-5 col-form-label">nominalVoltageDC</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="nominalVoltageDC" id="nominalVoltageDC" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="efficiencyEuro" class="col-sm-5 col-form-label">efficiencyEuro</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="efficiencyEuro" id="efficiencyEuro" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="maxInputCurrent" class="col-sm-5 col-form-label">maxInputCurrent</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="maxInputCurrent" id="maxInputCurrent" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="numberOfDcInputs" class="col-sm-5 col-form-label">numberOfDcInputs</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="numberOfDcInputs" id="numberOfDcInputs" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1"
							style="padding-left: 0px; padding-right: 0px">
							<label for="numberOfDcInputs" class="col-sm-5 col-form-label">numberOfDcInputs</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="numberOfDcInputs" id="numberOfDcInputs" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-10 col-md-offset-1">
							<label for="threePhase">Podłączenie</label>
							<form:select class="form-control" path="threePhase" id="threePhase">
								<option value="false">1-fazowe</option>
								<option value="true">3-fazowe</option>
							</form:select>
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