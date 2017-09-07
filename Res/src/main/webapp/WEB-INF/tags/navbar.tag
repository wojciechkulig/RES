<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">OZE</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value="/" />">Home</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">dodaj <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/PvModule/add" />">panel</a></li>
						<li><a href="<c:url value="/Inverter/add" />">falownik</a></li>
					</ul></li>
				<li><a href="<c:url value="/logout" />">wyloguj</a></li>
			</ul>
		</div>
	</div>
</div>