<!DOCTYPE HTML>

<%@page session="false" contentType="text/html"
	pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>

<%@ page import="com.liferay.util.PwdGenerator"%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value="/resources/jquery/style.css"/>">
<style>
body { padding: 30px }
form { display: block; margin: 20px auto; background: #eee; border-radius: 10px; padding: 15px }

.progress { position:relative; width:400px; border: 1px solid #ddd; padding: 1px; border-radius: 3px; }
.bar { background-color: #B4F5B4; width:0%; height:20px; border-radius: 3px; }
.percent { position:absolute; display:inline-block; top:3px; left:48%; }
</style>
<portlet:defineObjects />
<h1>Fileupload POC</h1>
<portlet:resourceURL id="uploadMe" var="uploadMe" />
<portlet:renderURL var="renderURL" />
<body>

	
	
	<form:form name="fileUploader" commandName="uploadBean" method="post" enctype="multipart/form-data">
        <label> Select a File</label>
		<form:input path="filedata" type="file" id="filedata" />
 		<button type="submit" id="submitBtn" class="flatbtn-blu hidemodal" >Submit</button> 
  
  
	</form:form>
	<div class="progress">
        <div class="bar"></div >
        <%--<div class="percent">0%</div > --%>
    </div>

</body>
<script>
(function() {
   
var bar = $('.bar');
var percent = $('.percent');
var status = $('#status');
   
$('form').ajaxForm({
    beforeSend: function() {
        status.empty();
        var percentVal = '0%';
        bar.width(percentVal)
        percent.html(percentVal);
    },
    url: '${uploadMe}',
    type: 'post',  
    dataType: 'text json',
    uploadProgress: function(event, position, total, percentComplete) {
    	
        var percentVal = percentComplete + '%';
        bar.width(percentVal)
        percent.html(percentVal);
    },
    success: function() {
        var percentVal = '0%';
        bar.width(percentVal)
        percent.html(percentVal);
    },
	complete: function(xhr) {
		status.html(xhr.responseText);
	}
}); 

})();       
</script>





