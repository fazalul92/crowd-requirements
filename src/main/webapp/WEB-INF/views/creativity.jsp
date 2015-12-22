<%@include file="header.jsp"%>

<!-- progressbar -->
<ul id="progressbar">
 <li>Pre-Survey (1)</li>
 <li>Pre-Survey (2)</li>
 <li class="active">Pre-Survey (3)</li>
 <c:forEach begin="1" end="3" varStatus="loop">
  <li>Phase ${loop.index}</li>
 </c:forEach>
 <li>Post-Survey</li>
</ul>

<h3>Instructions</h3>
<div class="jumbotron lead">
 <p>Please use the rating scale below to describe how accurately
  each adjective below describes you.</p>
 <ul class="list-unstyled" style="font-weight: bold;">
  <li>1=Very Inaccurate</li>
  <li>2=Moderately Inaccurate</li>
  <li>3=Neither Inaccurate nor Accurate</li>
  <li>4=Moderately Accurate</li>
  <li>5=Very Accurate</li>
 </ul>
</div>

<form:form method="POST" modelAttribute="creativityResponseForm">
 <h3>Pre-Survey (3)</h3>
 <div class="jumbotron lead">
  <ol>
   <c:forEach var="creativityResponse"
    items="${creativityResponseForm.creativityResponses}"
    varStatus="loop">
    <form:input type="hidden"
     path="creativityResponses[${loop.index}].userId" name="userId"
     id="userId" />
    <form:input type="hidden"
     path="creativityResponses[${loop.index}].creativityQuestionId"
     name="creativityQuestionId" id="creativityQuestionId" />

    <li>
     <h3>${creativityQuestions[loop.index].description}</h3>
     <div class="form-group radio">
      <c:forEach begin="1" end="5" varStatus="likertloop">
       <label> <form:radiobutton
         path="creativityResponses[${loop.index}].description"
         value="${likertloop.index}" />${likertloop.index}
       </label>
      </c:forEach>
     </div>
    </li>

    <div class="has-error">
     <form:errors path="creativityResponses[${loop.index}].description"
      class="help-inline" />
    </div>
   </c:forEach>
  </ol>

  <div class="text-center">
   <button type="submit" class="btn btn-primary btn-lg">Submit
    Responses &raquo;</button>
   <p>
    <br> <b>Note:</b> After submitting the responses, you cannot
    edit them again.
   </p>
  </div>
 </div>
</form:form>

</div>
<!-- /container -->

<%@include file="footer.jsp"%>