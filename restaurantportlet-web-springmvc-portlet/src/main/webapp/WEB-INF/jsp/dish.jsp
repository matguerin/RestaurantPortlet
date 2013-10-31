<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div class="restaurant-portlet">

	<h1>
		${name}
	</h1>
	
	<c:if test="${not empty code}">
		<c:forEach var="codeNumber" items="${code}">
			<img src="<%= renderRequest.getContextPath() %><spring:message code="meal.code.${fn:trim(codeNumber)}.img" />"
			     alt="<spring:message code="meal.code.${fn:trim(codeNumber)}.description" />"
				 title="<spring:message code="meal.code.${fn:trim(codeNumber)}.name" />"
			/>									
		</c:forEach>
	</c:if>

	<c:if test="${not empty ingredients}">
		<p>
			<strong><spring:message code="meal.ingredients"/> :</strong>
			${ingredients}
		</p>
	</c:if>
	<c:if test="${not empty nutritionitems}">
		<p>
			<strong><spring:message code="meal.nutritionitems"/> :</strong>
			<ul>
			<c:forEach var="nutritionitem" items="${nutritionitems}">
				<li>
					<strong>${nutritionitem.name}</strong> : ${nutritionitem.value}
				</li>
			</c:forEach>
			</ul>
		</p>
	</c:if>
</div>