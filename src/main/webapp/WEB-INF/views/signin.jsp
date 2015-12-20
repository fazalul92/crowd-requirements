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

<title>Signin to Multiparty Privacy Study</title>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/signin.css" rel="stylesheet">

<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

</head>

<body>
 <div class="container">
  <form:form class="form-signin" method="POST"  modelAttribute="user">
   <h2 class="form-signin-heading">Please sign in</h2>
   <label for="mturkId" class="sr-only"> Amazon Mechanical Turk
    ID</label>
   <form:input class="form-control" path="mturkId"
    placeholder="Amazon Mechanical Turk Worker ID" required="required"
    autofocus="autofocus" />

   <button class="btn btn-lg btn-primary btn-block" type="submit">
    Sign in</button>
    
   <p>
    <br> <b>Note:</b> Your worker ID is not your name or email. You
    can find it on your mechanical turk dashboard.
   </p>

  </form:form>
 </div>
 <!-- /container -->

 <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
 <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
