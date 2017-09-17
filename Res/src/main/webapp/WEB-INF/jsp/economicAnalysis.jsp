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
<title>Insert title here</title>
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

.header2 {
	background-color: #F5F5F5;
	height: 60px;
	font-size: 22px;
	padding: 10px;
}

.chart {
	height: auto;
	width: 100%;
}
</style>
</head>
<body>
	<mytags:navbar />
	<div class="container panel panel-default"
		style="margin-top: 20px; padding: 20px">
		<spring:url value="/analizaEkonomiczna" var="form" />
		<form:form action="${form}" class="form-horizontal"
			modelAttribute="economicAnalysisData" method="POST">

			<div class="row flexbox">
				<div class="col-md-12">
					<div class="panel panel-default" style="width: 100%">
						<fieldset>
							<legend class="text-center header">Finansowanie</legend>
							<div class="form-group">
								<div class="col-md-10 col-md-offset-1">
									<table class="table table-bordered table-highlight"
										style="width: 100%">
										<thead>
											<tr>
												<th>Finansowanie</th>
												<th>zł</th>
												<th>% inwestycji</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td style="font-weight: bold">Koszt instalacji</td>
												<td style="font-weight: bold"><form:input type="text"
														path="totalBudget" id="totalBudget" style="width:100%" /></td>
												<td style="font-weight: bold"><input type="text"
													id="totalBudgetPercent" style="width: 100%" readonly /></td>
											</tr>
											<tr>
												<td>Wkład własny</td>
												<td><form:input type="text" path="ownContribution"
														id="ownContribution" style="width:100%" /></td>
												<td><input type="text" id="ownContributionPercent"
													style="width: 100%" readonly /></td>
											</tr>
											<tr>
												<td>bezzwrotna dotacja</td>
												<td><form:input type="text" path="subsidy" id="subsidy"
														style="width:100%" /></td>
												<td><input type="text" id="subsidyPercent"
													style="width: 100%" readonly /></td>
											</tr>
											<tr>
												<td>kredyt</td>
												<td><form:input type="text" path="credit.creditAmount"
														id="creditAmount" style="width:100%" /></td>
												<td><input type="text" id="creditAmountPercent"
													style="width: 100%" readonly /></td>
											</tr>
											<tr>
												<td style="font-weight: bold">podsumowanie</td>
												<td style="font-weight: bold"><input type="text"
													id="summary" style="width: 100%" /></td>
												<td style="font-weight: bold"><input type="text"
													id="summaryPercent" style="width: 100%" readonly /></td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
							<div id="credit-form" style="display: none; width: 100%">
								<div class="col-md-10 col-md-offset-1 panel panel-default"
									style="padding: 0px;">
									<fieldset>
										<legend class="text-center header2">Wprowadź
											informacje odnośnie kredytu</legend>

										<div class="form-group form-inline">
											<div class="col-md-10 col-md-offset-1">

												<div class="row">
													<div class="col-md-6">
														<label for="qualityRateFeeDay">Kwota kredytu</label>
													</div>
													<div class="col-md-6">
														<div class="input-group mb-2 mr-sm-2 mb-sm-0"
															style="width: 100%;">
															<div class="input-group-addon">&nbsp;zł</div>
															<input type="text" class="form-control"
																id="creditAmountInfo" readonly />
														</div>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group form-inline">
											<div class="col-md-10 col-md-offset-1">
												<div class="row">
													<div class="col-md-6">
														<label for="interestRate">Długość kredytu</label>
													</div>
													<div class="col-md-6">
														<div class="input-group mb-2 mr-sm-2 mb-sm-0"
															style="width: 100%;">
															<div class="input-group-addon">lat</div>
															<form:input type="text" class="form-control"
																path="credit.interestRate" id="interestRate" />
														</div>
													</div>
												</div>
											</div>
										</div>


										<div class="form-group form-inline">
											<div class="col-md-10 col-md-offset-1">
												<div class="row">
													<div class="col-md-6">
														<label for="annualInstalmentPeriods">Wprowadź
															ilość rat w ciągu roku</label>
													</div>
													<div class="col-md-6">
														<form:select type="text" class="form-control"
															path="credit.annualInstalmentPeriods"
															id="annualInstalmentPeriods" style="width:100%">
															<form:option value="1">1</form:option>
															<form:option value="2">2</form:option>
															<form:option value="3">3</form:option>
															<form:option value="4">4</form:option>
															<form:option value="6">6</form:option>
															<form:option value="12">12</form:option>
														</form:select>
													</div>
												</div>
											</div>
										</div>

										<div class="form-group form-inline">
											<div class="col-md-10 col-md-offset-1">
												<div class="row">
													<div class="col-md-6">
														<label for="isInstallmentFixed">Wprowadź rodzaj
															rat</label>
													</div>
													<div class="col-md-6">
														<form:select type="text" class="form-control"
															path="credit.installmentType" id="installmentType"
															style="width:100%">
															<form:option value="fixed">raty stałe</form:option>
															<form:option value="decreasing">raty malejące</form:option>
														</form:select>
													</div>
												</div>
											</div>
										</div>



									</fieldset>
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
							<legend class="text-center header">Zmienne składowe
								rachunku za prąd</legend>
							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">

									<div class="row">
										<div class="col-md-6">
											<label for="electricityPriceDayTariff">Energia
												elektryczna czynna dzienna</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">zł/kwh</div>
												<form:input type="text" class="form-control"
													path="electricityPriceDayTariff"
													id="electricityPriceDayTariff" />
											</div>
										</div>
									</div>

								</div>
							</div>
							<c:if test="${tariffGroupName !='G11'}">
								<div class="form-group form-inline">
									<div class="col-md-10 col-md-offset-1">

										<div class="row">
											<div class="col-md-6">
												<label for="electricityPriceNightTariff">Energia
													elektryczna czynna nocna</label>
											</div>
											<div class="col-md-6">
												<div class="input-group mb-2 mr-sm-2 mb-sm-0"
													style="width: 100%;">
													<div class="input-group-addon">zł/kwh</div>
													<form:input type="text" class="form-control"
														path="electricityPriceNightTariff"
														id="electricityPriceNightTariff" />
												</div>
											</div>
										</div>

									</div>
								</div>
							</c:if>

							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">

									<div class="row">
										<div class="col-md-6">
											<label for="variableGridFeeDay">Składnik zmienny
												stawki sieciowej dziennej</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">zł/kwh</div>
												<form:input type="text" class="form-control"
													path="variableGridFeeDay" id="variableGridFeeDay" />
											</div>
										</div>
									</div>

								</div>
							</div>
							<c:if test="${tariffGroupName !='G11'}">
								<div class="form-group form-inline">
									<div class="col-md-10 col-md-offset-1">

										<div class="row">
											<div class="col-md-6">
												<label for="variableGridFeeNight">Składnik zmienny
													stawki sieciowej nocnej</label>
											</div>
											<div class="col-md-6">
												<div class="input-group mb-2 mr-sm-2 mb-sm-0"
													style="width: 100%;">
													<div class="input-group-addon">zł/kwh</div>
													<form:input type="text" class="form-control"
														path="variableGridFeeNight" id="variableGridFeeNight" />
												</div>
											</div>
										</div>

									</div>
								</div>
							</c:if>

							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">

									<div class="row">
										<div class="col-md-6">
											<label for="qualityRateFeeDay">Stawka jakościowa
												dzienna</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">zł/kwh</div>
												<form:input type="text" class="form-control"
													path="qualityRateFeeDay" id="qualityRateFeeDay" />
											</div>
										</div>
									</div>

								</div>
							</div>
							<c:if test="${tariffGroupName !='G11'}">
								<div class="form-group form-inline">
									<div class="col-md-10 col-md-offset-1">

										<div class="row">
											<div class="col-md-6">
												<label for="qualityRateFeeNight">Stawka jakościowa
													nocna</label>
											</div>
											<div class="col-md-6">
												<div class="input-group mb-2 mr-sm-2 mb-sm-0"
													style="width: 100%;">
													<div class="input-group-addon">zł/kwh</div>
													<form:input type="text" class="form-control"
														path="qualityRateFeeNight" id="qualityRateFeeNight" />
												</div>
											</div>
										</div>

									</div>
								</div>
							</c:if>
							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">
									<div class="row">
										<div class="col-md-6">
											<label for="RESFee">Opłata OZE</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">zł/kwh</div>
												<form:input type="text" class="form-control" path="RESFee"
													id="RESFee" />
											</div>
										</div>
									</div>

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
							<legend class="text-center header"> Koszty
								eksploatacyjne instalacji</legend>
							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">

									<div class="row">
										<div class="col-md-6">
											<label for="operatingCosts">koszty eksploatacyjne
												instalacji</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">zł/rok</div>
												<form:input type="text" class="form-control"
													path="operatingCosts" id="operatingCosts" />
											</div>
										</div>
									</div>

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
							<legend class="text-center header"> Parametry finansowe
								ogólne</legend>

							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">

									<div class="row">
										<div class="col-md-6">
											<label for="referenceRate">stopa referencyjna</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">%</div>
												<form:input type="text" class="form-control"
													path="referenceRate" id="referenceRate" />
											</div>
										</div>
									</div>

								</div>
							</div>

							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">

									<div class="row">
										<div class="col-md-6">
											<label for="riskPremium">Oczekiwana premia za ryzyko
												inwestycyjne</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">%</div>
												<form:input type="text" class="form-control"
													path="riskPremium" id="riskPremium" />
											</div>
										</div>
									</div>

								</div>
							</div>

							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">

									<div class="row">
										<div class="col-md-6">
											<label for="inflation">Inflacja</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">%</div>
												<form:input type="text" class="form-control"
													path="inflation" id="inflation" />
											</div>
										</div>
									</div>

								</div>
							</div>

							<div class="form-group form-inline">
								<div class="col-md-10 col-md-offset-1">

									<div class="row">
										<div class="col-md-6">
											<label for="annualIncreaseOfElectricityPrice">Roczny
												wzrost cen energii</label>
										</div>
										<div class="col-md-6">
											<div class="input-group mb-2 mr-sm-2 mb-sm-0"
												style="width: 100%;">
												<div class="input-group-addon">%</div>
												<form:input type="text" class="form-control"
													path="annualIncreaseOfElectricityPrice"
													id="annualIncreaseOfElectricityPrice" />
											</div>
										</div>
									</div>

								</div>
							</div>

						</fieldset>
					</div>

				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12 text-center">
					<form:button type="submit" class="btn btn-primary btn-lg">Dalej</form:button>
				</div>
			</div>

			<c:if test="${not empty chart}">
				<div class="row flexbox">
					<div class="col-md-12">
						<div class="panel panel-default" style="width: 100%">
							<fieldset>
								<legend class="text-center header">Wyniki analizy</legend>
								<div class="col-md-6">
									<div class="panel panel-default">
										<div class="text-center header2">skumulowane
											zdyskontowane przepływy pieniezne</div>
										<img class="chart" alt="Bilans energii" src="${chart}">
									</div>
								</div>
								<div class="col-md-6">
									<div class="panel panel-default station" style="height: 100%;">
										<div class="text-center header2" style="width: 100%">Wskaźniki
											ekonomiczne</div>
										<table class="table table-bordered table-highlight"
											style="width: 100%">
											<thead>
												<tr>
													<th>Nazwa wskaźnika</th>
													<th>wartość</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>NPV</td>
													<td>${results.NPV } zł</td>
												</tr>
												<tr>
													<td>Skumulowane przepływy pieniężne po 15 latach</td>
													<td>${results.cumulativeCashFlowAfter15Years } zł</td>
												</tr>
												<tr>
													<td>IRR</td>
													<td>${results.IRR } %</td>
												</tr>
												<tr>
													<td>SPBT</td>
													<td>${results.SPBT } lat</td>
												</tr>
												<tr>
													<td>DPBT</td>
													<td>${results.DPBP } lat</td>
												</tr>
											</tbody>
										</table>

									</div>
								</div>
							</fieldset>
						</div>
					</div>
				</div>

			</c:if>

		</form:form>
	</div>
	<script type="text/javascript">
		var totalBudget = document.getElementById("totalBudget");
		var ownContribution = document.getElementById("ownContribution");
		var subsidy = document.getElementById("subsidy");
		var credit = document.getElementById("creditAmount");
		var summary = document.getElementById("summary");
		totalBudget.addEventListener("change", function() {
			document.getElementById("totalBudgetPercent").value = (totalBudget.value / totalBudget.value) * 100;
			document.getElementById("ownContributionPercent").value = (ownContribution.value / totalBudget.value) * 100;
			document.getElementById("subsidyPercent").value = (subsidy.value / totalBudget.value) * 100;
			document.getElementById("creditAmountPercent").value = (credit.value / totalBudget.value) * 100;
			document.getElementById("summary").value = Number(ownContribution.value) + Number(subsidy.value) + Number(credit.value);
			document.getElementById("summaryPercent").value = Number(document.getElementById("ownContributionPercent").value) + Number(document.getElementById("subsidyPercent").value) + Number(document.getElementById("creditAmountPercent").value);
		});
		ownContribution.addEventListener("change", function() {
			document.getElementById("totalBudgetPercent").value = (totalBudget.value / totalBudget.value) * 100;
			document.getElementById("ownContributionPercent").value = (ownContribution.value / totalBudget.value) * 100;
			document.getElementById("subsidyPercent").value = (subsidy.value / totalBudget.value) * 100;
			document.getElementById("creditAmountPercent").value = (credit.value / totalBudget.value) * 100;
			document.getElementById("summary").value = Number(ownContribution.value) + Number(subsidy.value) + Number(credit.value);
			document.getElementById("summaryPercent").value = Number(document.getElementById("ownContributionPercent").value) + Number(document.getElementById("subsidyPercent").value) + Number(document.getElementById("creditAmountPercent").value);
		});
		subsidy.addEventListener("change", function() {
			document.getElementById("totalBudgetPercent").value = (totalBudget.value / totalBudget.value) * 100;
			document.getElementById("ownContributionPercent").value = (ownContribution.value / totalBudget.value) * 100;
			document.getElementById("subsidyPercent").value = (subsidy.value / totalBudget.value) * 100;
			document.getElementById("creditAmountPercent").value = (credit.value / totalBudget.value) * 100;
			document.getElementById("summary").value = Number(ownContribution.value) + Number(subsidy.value) + Number(credit.value);
			document.getElementById("summaryPercent").value = Number(document.getElementById("ownContributionPercent").value) + Number(document.getElementById("subsidyPercent").value) + Number(document.getElementById("creditAmountPercent").value);
		});
		credit.addEventListener("change", function() {
			document.getElementById("totalBudgetPercent").value = (totalBudget.value / totalBudget.value) * 100;
			document.getElementById("ownContributionPercent").value = (ownContribution.value / totalBudget.value) * 100;
			document.getElementById("subsidyPercent").value = (subsidy.value / totalBudget.value) * 100;
			document.getElementById("creditAmountPercent").value = (credit.value / totalBudget.value) * 100;
			document.getElementById("summary").value = Number(ownContribution.value) + Number(subsidy.value) + Number(credit.value);
			document.getElementById("summaryPercent").value = Number(document.getElementById("ownContributionPercent").value) + Number(document.getElementById("subsidyPercent").value) + Number(document.getElementById("creditAmountPercent").value);
			document.getElementById("creditAmountInfo").value = credit.value;
	
			if (credit.value != 0) {
				document.getElementById("credit-form").style.display = "block";
			} else {
				document.getElementById("credit-form").style.display = "none";
			}
	
		});
	</script>
</body>
</html>