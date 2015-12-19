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
     <a class="navbar-brand" href="#">Multiparty Privacy Study</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
     <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <!-- <li><a href="#">Contact</a></li> -->
     </ul>
     <ul class="nav navbar-nav navbar-right">
      <li class="active"><a href="#">MTurk ID: ${presurveyResponse.mturkId}</a></li>
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
   <p>Welcome to the multiparty privacy study! The objective of our
    study is to understand how multiple users resolve multiparty privacy
    conflicts. This study consists of seven surveys: a demographics
    survey, five surveys related to privacy scenarios, and a post-study
    survey. At the end of the post-study survey, you will receive a
    completion code. You will need to enter this code on Mechanical Turk
    to receive payment for the HIT. Please keep in mind the following
    points.</p>
    
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

  <form:form method="POST" modelAttribute="presurveyResponse">
   <form:input type="hidden" path="mturkId" id="mturkId" />
   
   <h3>Demographics Survey</h3>
   <div class="jumbotron lead">
    <ol>
     <li>
      <h3>What is your gender?</h3>      
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="gender" value="male" />
         Male
        </label>
       </div>
       <div class="radio">
        <label> <form:radiobutton path="gender" value="female" />
         Female
        </label>
       </div>
       <div class="radio">
        <label> <form:radiobutton path="gender" value="other" />
         Other
        </label>
       </div>      
      </div>
     </li>
     
     <li>
      <h3>Which of the following categories includes your age?</h3>      
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="age" value="1820" />
         18&ndash;20
        </label>
       </div>
       <div class="radio">
        <label> <form:radiobutton path="age" value="2129" />
         21&ndash;29
        </label>
       </div>
       <div class="radio">
        <label> <form:radiobutton path="age" value="3039" />
         30&ndash;39
        </label>
       </div>
       <div class="radio">
        <label> <form:radiobutton path="age" value="4049" />
         40&ndash;49
        </label>
       </div>
       <div class="radio">
        <label> <form:radiobutton path="age" value="5059" />
         50&ndash;59
        </label>
       </div>
       <div class="radio">
        <label> <form:radiobutton path="age" value="60plus" />
         60 or Older
        </label>
       </div>                                             
      </div>
     </li>  
     
     <li>
      <h3>What is the highest level of school you have completed or
       the highest degree you have received?</h3>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="education" value="less_highschool" />
         Less than high school degree
        </label>
       </div>
      </div>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="education" value="highschool" />
         High school degree or equivalent (e.g., GED)
        </label>
       </div>
      </div>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="education" value="college_nodegree" />
         Some college but no degree
        </label>
       </div>
      </div>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="education" value="bachelor_degree" />
         Bachelor degree
        </label>
       </div>
      </div>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="education" value="graduate_degree" />
         Graduate degree
        </label>
       </div>
      </div>
     </li>
     
     <li>
      <h3>How often do you use social media (e.g., Facebook, Google
       Plus, Flickr, Twitter, LinkedIn)?</h3>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="socialmediaFrequency" value="daily" />
         Almost every day
        </label>
       </div>
      </div>      
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="socialmediaFrequency" value="weekly" />
         About once a week
        </label>
       </div>
      </div>      
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="socialmediaFrequency" value="monthly" />
         About once a month or less often than that
        </label>
       </div>
      </div>      
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="socialmediaFrequency" value="never" />
         Never used social media
        </label>
       </div>
      </div>
     </li>
     
     <li>
      <h3>How many pictures showing you and others have you ever shared
       via social media or email?</h3>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="sharingFrequency" value="several" />
         Several (&gt;10)
        </label>
       </div>
      </div>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="sharingFrequency" value="few" />
         A few (1&ndash;10)
        </label>
       </div>
      </div>
      <div class="form-horizontal">
       <div class="radio">
        <label> <form:radiobutton path="sharingFrequency" value="none" />
         None (0)
        </label>
       </div>
      </div>
     </li>      
     
    </ol>
    <div class="has-error">
     <form:errors path="education" class="help-inline" />
    </div>
    
    <div class="text-center">
     <button type="submit" class="btn btn-primary btn-lg">Submit
      Responses &raquo;</button>
     <p>
      <br> <b>Note:</b> After submitting the responses, you cannot edit
      them again.
     </p>
    </div>    
   </div>
  </form:form>

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
