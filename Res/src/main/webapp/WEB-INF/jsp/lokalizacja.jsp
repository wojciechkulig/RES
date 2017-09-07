<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

@media screen and (min-width: 992px) {
	.flexbox {
		display: flex;
	}
}
.chart{
	height:auto;
	width: 100%;
}
.dataHeader {
	justify-content: center;
	align-items: center;
}
.panel {
	margin-bottom:0px;
}
.station {
	height: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
}
</style>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIsT25TdkWYC-qxm18oHluJv-vnmTtCy8&callback=initMap">
</script>
<script type="text/javascript">
	var marker;
	function initMap() {
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : 6,
			center : {
				lat : 51.759445,
				lng : 19.457216
			}
		});
		
		
		if(document.getElementById("lat").value != null){
			var position = {lat: Number(document.getElementById("lat").value), lng: Number(document.getElementById("lng").value)};
			marker = new google.maps.Marker({
				position: position,
				map: map
			});
		}
		
		map.addListener('click', function(e) {
			placeMarkerAndPanTo(e.latLng, map);
		});
	}
	
	function placeMarkerAndPanTo(latLng, map) {
		if (marker == null) {
			marker = new google.maps.Marker({
				position : latLng,
				map : map
			});
		} else {
			marker.setPosition(latLng);
			marker.setMap(map);
		}
		document.getElementById("lat").value = latLng.lat();
		document.getElementById("lng").value = latLng.lng();
	}
</script>
</head>
<body onload="initMap();">
	<mytags:navbar />
	<div class="container panel panel-default" style="margin-top: 20px; padding: 20px">
		<div class="row flexbox">
			<div class="col-md-6">
				<div class="panel panel-default" style="margin-bottom: 0px; height:100%">
					<spring:url value="/" var="form" />
					<form:form action="${form}" class="form-horizontal"
						modelAttribute="locationDTO" method="POST">
						<fieldset>
							<legend class="text-center header">Zdefiniuj parametry
								domu</legend>
							<form:input id="lat" path="lat" type="hidden" value="${locationDTO.lat}" />
							<form:input id="lng" path="lng" type="hidden" value="${locationDTO.lng}" />

							<div class="form-group">
								<div class="col-md-10 col-md-offset-1">
									<label for="azimuth">Wprowadź azymut instalacji</label>
									<form:select class="form-control" path="azimuth" id="azimuth">
										<form:option value="N">N</form:option>
										<form:option value="NE">NE</form:option>
										<form:option value="E">E</form:option>
										<form:option value="SE">SE</form:option>
										<form:option value="S">S</form:option>
										<form:option value="SW">SW</form:option>
										<form:option value="W">W</form:option>
										<form:option value="NW">NW</form:option>
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-10 col-md-offset-1">
									<label for="roof-inclination">Wprowadź kąt pochylenia
										instalacji</label>
									<form:select class="form-control" path="inclination"
										id="roof-inclination">
										<form:option value="0">0°</form:option>
										<form:option value="30">30°</form:option>
										<form:option value="45">45°</form:option>
										<form:option value="60">60°</form:option>
										<form:option value="90">90°</form:option>
									</form:select>
								</div>
							</div>


							<div class="form-group">
								<div class="col-md-10 col-md-offset-1">
									<label for="instalation-height">Wprowadź wysokość posadowienia instalacji</label>									
									<form:input type="text" class="form-control"
										path="instalationHeight" id="instalation-height" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12 text-center">
									<form:button type="submit" class="btn btn-primary btn-lg">Dalej</form:button>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
			<div class="col-md-6">
				<div>
					<div class="panel panel-default" style="margin-bottom: 0px;">
						<div class="text-center header">Zaznacz lokalizację</div>
						<div class="panel-body text-center">
							<div id="map"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${not empty pieUrl}">
		
		<div class="row flexbox dataHeader">
			<h3>Pobrane dane</h3>
		</div>
		
		<div class="row flexbox">
				
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="text-center header">Nasłonecznienie</div>
   			 		<img class="chart" alt="Irradiance" src="${pieUrl}">	
				</div>
			</div>
			
			<div class ="col-md-6">
				<div class="panel panel-default station" style="height:100%;">
					<div class="text-center header" style ="width:100%">Najbliższa stacja meteorologiczna</div>
					<ul>
					<li>Kraj: ${city.country}</li>
					<li>Miasto: ${city.cityName}</li>
					<li>Współrzędne geograficzne stacji:( ${city.latitude}, ${city.longitude} )</li>
					<li>Położenie nad poziomem morza: ${city.MASL}m</li>
					</ul>
					<a href='<spring:url value="/PvSystem" htmlEscape="true"/>' class="btn btn-primary btn-lg">Przejdź do doboru instalacji</a>
				</div>
		</div>	
		</div>
		</c:if>
	</div>

</body>
</html>