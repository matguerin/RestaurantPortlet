<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<portlet:defineObjects/>

<c:set var="n"><portlet:namespace/></c:set>

<%-- Inclusion CSS --%>
<link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">

<%-- Inclusion JS --%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">$().carousel || document.write('<script src="${pageContext.servletContext.contextPath}/js/bootstrap.3.0.1.min.js"><\/script>')</script>

<%-- Portlet container --%>
<div class="pc sm">


<script type="text/javascript">

	var $portletContainers;
 
	$(document).ready(function() {
		$portletContainers = $(".portlet-container");
		// Resize event isn't fired on DOM Content Loaded, we launch the function manually
		onWindowResize();
	  $(window).resize(onWindowResize);
	});
	 
	function onWindowResize() {
		$portletContainers.each(function(index) {
			
			var $that = $(this);
			var portletWidth = $that.width()
			
			$that.removeClass("xs sm md lg");
			if(portletWidth < 768) { $that.addClass("xs"); }
			if(portletWidth >= 768 && portletWidth < 992) { $that.addClass("sm"); }
			if(portletWidth >= 992 && portletWidth < 1200) { $that.addClass("md"); }
			if(portletWidth >= 1200) { $that.addClass("lg"); }	
		});
	}
</script>