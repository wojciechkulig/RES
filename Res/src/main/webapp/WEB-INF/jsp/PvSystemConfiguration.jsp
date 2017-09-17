<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Konfiguracja</title>
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

@media screen and (min-width: 992px) {
	.flexbox {
		display: flex;
	}
}

.header {
	background-color: #F5F5F5;
	height: 70px;
	font-size: 27px;
	padding: 10px;
}
.chart{
	height:auto;
	width: 100%;
}
</style>
</head>
<body onload="showHideMethodOfSettlement();">
	<mytags:navbar />
	<div class="container panel panel-default"
		style="margin-top: 20px; padding: 20px">
		<spring:url value="/PvSystem" var="form" />
		<form:form action="${form}" class="form-horizontal"
			modelAttribute="pvSystemConfigurationDTO" method="POST">

			<div class="row flexbox">
				<div class="col-md-12">
					<div class="panel panel-default" style="width: 100%">

						<fieldset>
							<legend class="text-center header">Zdefiniuj profil
								zużycia energii</legend>
							<div class="form-group">
								<div class="col-md-10 col-md-offset-1">
									<label for="tafiffGroup">Wybierz grupę taryfową</label>
									<form:select class="form-control" path="tariffGroup"
										id="tafiffGroup">
										<c:forEach var="tariffGroup" items="${tariffGroups}">
											<form:option value="${tariffGroup}">${tariffGroup.name} </form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="form-group" id="methodOfSettlement"
								style="display: none">
								<div class="col-md-10 col-md-offset-1">
									<label for="tafiffGroup">Wybierz sposób rozliczania</label>
									<form:select class="form-control" path="methodOfSettlement">
										<form:option value="Energa">Energa</form:option>
										<form:option value="Innogy">Innogy</form:option>
										<form:option value="PGE">PGE</form:option>
										<form:option value="Tauron">Tauron</form:option>
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-10 col-md-offset-1">
									<label for="annualEnergyConsumption">Wprowadź roczne
										zużycie energii [kWh]</label>
									<form:input type="text" class="form-control"
										path="annualEnergyConsumption" id="annualEnergyConsumption" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-10 col-md-offset-1">
									<label for="numberOFSettlementPeriods">Wprowadź liczbę
										okresów rozliczeniowych w ciągu roku</label>
									<form:select type="text" class="form-control"
										path="numberOFSettlementPeriods"
										id="numberOFSettlementPeriods">
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="6">6</form:option>
										<form:option value="12">12</form:option>
									</form:select>
								</div>
							</div>

						</fieldset>

					</div>
				</div>
			</div>
			<div class="row flexbox">
				<div class="col-md-12">
					<div class="panel panel-default" style="width: 100%">

						<fieldset>
							<legend class="text-center header">Podaj parametry instalacji fotowoltaicznej</legend>
							<div class="form-group">
								<div class="col-md-10 col-md-offset-1">
									<label for="pvModule">Wybierz panel fotowoltaiczny</label>
									<form:select class="form-control" path="pvModule" id="pvModule">
										<c:forEach var="pvModule" items="${pvModules}">
											<form:option value="${pvModule}">${pvModule.name} </form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class ="form-group">
								<div class="col-md-10 col-md-offset-1">
									<label for="numberOfModules">Wprowadź liczbę paneli fotowoltaicznych</label>
									<form:input id ="numberOfModules" path="numberOfModules" class="form-control"/>
								</div>
							</div>
							

							<div class="form-group">
								<div class="col-md-12 text-center">
									<form:button type="submit" class="btn btn-primary btn-lg">Dalej</form:button>
								</div>
							</div>
						</fieldset>
					</div>
				</div>
			</div>
		</form:form>

		<c:if test="${not empty chartUrl}">
		<div class="row flexbox">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="text-center header">Bilans energii dla pierwszego roku</div>
					<img class="chart" alt="Bilans energii" src="${chartUrl}">
				</div>
				<a href='<spring:url value="/analizaEkonomiczna" htmlEscape="true"/>' class="btn btn-primary btn-lg">Przejdź do analizy ekonomicznej</a>
			</div>
			</div>
		</c:if>

	</div>
	<script type="text/javascript">
		var tariff = document.getElementById("tafiffGroup");
		function showHideMethodOfSettlement(){
		if (tariff.options[tariff.selectedIndex].text == "G12" || tariff.options[tariff.selectedIndex].text == "G12W") {
			console.log("tak");
			document.getElementById("methodOfSettlement").style.display = "block"
		} else {
			console.log("nie");
			document.getElementById("methodOfSettlement").style.display = "none";
		}
	};
		tariff.addEventListener("change",function(){
			if (tariff.options[tariff.selectedIndex].text == "G12" || tariff.options[tariff.selectedIndex].text == "G12W") {
				console.log("tak");
				document.getElementById("methodOfSettlement").style.display = "block"
			} else {
				console.log("nie");
				document.getElementById("methodOfSettlement").style.display = "none";
			}
		});
	</script>
</body>

</html>