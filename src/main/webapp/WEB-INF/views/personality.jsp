<%@include file="header.jsp"%>

<ul id="progressbar">
 <li>Pre-Survey (1)</li>
 <li class="active">Pre-Survey (2)</li>
 <li>Pre-Survey (3)</li>
 <c:forEach begin="1" end="3" varStatus="loop">
  <li>Phase ${loop.index}</li>
 </c:forEach>
 <li>Post-Survey</li>
</ul>

<h3>Instructions</h3>
<div class="jumbotron lead">
 <p>Please use the rating scale below to describe how accurately
  each statement describes you. Describe yourself as you generally are
  now, not as you wish to be in the future. Describe yourself as you
  honestly see yourself, in relation to other people you know of the
  same sex as you are, and roughly your same age. So that you can
  describe yourself in an honest manner, your responses will be kept in
  absolute confidence. Please read each statement carefully, and then
  fill in the bubble that corresponds to the number on the scale.</p>
 <ul class="list-unstyled" style="font-weight: bold;">
  <li>1=Very Inaccurate</li>
  <li>2=Moderately Inaccurate</li>
  <li>3=Neither Inaccurate nor Accurate</li>
  <li>4=Moderately Accurate</li>
  <li>5=Very Accurate</li>
 </ul>
</div>

<form:form method="POST" modelAttribute="personalityResponseForm">
 <h3>Pre-Survey (2)</h3>
 <div class="jumbotron lead">
  <ol>
   <c:forEach var="personalityResponse"
    items="${personalityResponseForm.personalityResponses}"
    varStatus="loop">
    <form:input type="hidden"
     path="personalityResponses[${loop.index}].userId" name="userId"
     id="userId" />
    <form:input type="hidden"
     path="personalityResponses[${loop.index}].personalityQuestionId"
     name="personalityQuestionId" id="personalityQuestionId" />

    <li>
     <h3>${personalityQuestions[loop.index].description}</h3>
     <div class="form-group radio">
      <c:forEach begin="1" end="5" varStatus="likertloop">
       <label> <form:radiobutton
         path="personalityResponses[${loop.index}].description"
         value="${likertloop.index}" />${likertloop.index}
       </label>
      </c:forEach>
     </div>
    </li>

    <div class="has-error">
     <form:errors path="personalityResponses[${loop.index}].description"
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

<%@include file="footer.jsp"%>