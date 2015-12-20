<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="resources/favicon.ico">

<title>Multiparty Privacy Study</title>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/multiparty-privacy.css" rel="stylesheet">

</head>

<body>
 <div class="container">
  <!-- Static navbar -->
  <nav class="navbar navbar-default">
   <div class="container-fluid">
    <div class="navbar-header">
     <button type="button" class="navbar-toggle collapsed"
      data-toggle="collapse" data-target="#navbar" aria-expanded="false"
      aria-controls="navbar">
      <span class="sr-only">Toggle navigation</span> <span
       class="icon-bar"></span> <span class="icon-bar"></span> <span
       class="icon-bar"></span>
     </button>
     <a class="navbar-brand" href="#">Smart Home Requirements</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
     <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <!-- <li><a href="#">Contact</a></li> -->
     </ul>
     <ul class="nav navbar-nav navbar-right">
      <li class="active"><a href="#">MTurk ID: ${user.mturkId}</a></li>
     </ul>
    </div>
    <!--/.nav-collapse -->
   </div>
   <!--/.container-fluid -->
  </nav>

  <!-- progressbar -->
  <ul id="progressbar">
   <li class="active">Pre-Survey</li>
   <c:forEach begin="1" end="5" varStatus="loop">
    <li>Picture ${loop.index}</li>
   </c:forEach>
   <li>Post-Survey</li>
  </ul>

  <h3>Instructions</h3>
  <div class="jumbotron lead">
   <p>Smart home requirements collection...</p>
    
   <ul>
    <li><b>Do not</b> use the browser's <b>refresh</b>, <b>back</b>,
     or <b>forward</b> buttons.</li>
    <li>Use the <b>Submit Responses</b> button at the end of the
     page to proceed to the next page.
    </li>
	  <li>After submitting responses in a page, you <b>cannot modify</b> 
	   your responses in that page.
		</li>
		<li>Answering all questions is mandatory, unless explicitly
     marked as optional.</li>
    <li>If something goes wrong (does not happen often), please
     start again from the beginning. If the problem persists, please
     contact <b>pmuruka AT ncsu.edu</b> with details of the problem.
    </li>
    <li>This HIT may contain <b>adult content</b>. Worker discretion
     is advised.</li>
    <li>You must be <b>18 or older</b> to participate in the study.</li>
   </ul>
  </div>
  
  <ol>
     <c:forEach items="${presurveyQuestions}" var="presurveyQuestion">
      <li class="active">${presurveyQuestion.description}</li>
     </c:forEach>
  </ol>

 </div>
 <!-- /container -->

 <!-- Bootstrap core JavaScript
    ================================================== -->
 <!-- Placed at the end of the document so the pages load faster -->
 <script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="resources/js/bootstrap.min.js"></script>
 <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
 <!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>
