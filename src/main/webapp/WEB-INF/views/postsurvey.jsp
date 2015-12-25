<%@include file="header.jsp"%>

<!-- progressbar -->
<ul id="progressbar">
 <li>Pre-Survey (1)</li>
 <li>Pre-Survey (2)</li>
 <li>Pre-Survey (3)</li>
 <li>Main Task (1)</li>
 <li>Main Task (2)</li>
 <li class="active">Post-Survey</li>
 <li>Completion Code</li>
</ul>

<h3>Instructions</h3>
<div class="bg-info" style="padding: 5px; border-radius: 5px;">
 <p class="text-justify">We are almost done... Please complete the
  following post survey.</p>
</div>

<form:form method="POST" modelAttribute="postsurveyResponseForm">
 <h3>Post-Survey</h3>

 <c:forEach var="postsurveyResponse"
  items="${postsurveyResponseForm.postsurveyResponses}" varStatus="loop">
  <form:input type="hidden"
   path="postsurveyResponses[${loop.index}].userId" name="userId"
   id="userId" />
  <div class="form-group">
   <label for="postsurveyResponse">${postsurveyQuestions[loop.index].description}</label>

   <form:input type="hidden"
    path="postsurveyResponses[${loop.index}].postsurveyQuestionId"
    name="postsurveyQuestionId" id="postsurveyQuestionId" />

   <c:choose>
    <c:when
     test="${postsurveyQuestions[loop.index].questionType == 'multiple_choice'}">
     <c:set var="answerChoices"
      value="${fn:split(postsurveyQuestions[loop.index].answerChoices,'|')}" />
     <c:forEach var="answerChoice" items="${answerChoices}">
      <div class="radio">
       <label> <form:radiobutton
         path="postsurveyResponses[${loop.index}].description"
         value="${answerChoice}" required="required" /> ${answerChoice}
       </label>
      </div>
     </c:forEach>
    </c:when>
    <c:when
     test="${postsurveyQuestions[loop.index].questionType == 'text_required'}">
     <form:textarea
      path="postsurveyResponses[${loop.index}].description"
      class="form-control" rows="2"
      placeholder="${postsurveyQuestions[loop.index].answerChoices}"
      required="required" />
    </c:when>
    <c:when
     test="${postsurveyQuestions[loop.index].questionType == 'text'}">
     <form:textarea
      path="postsurveyResponses[${loop.index}].description"
      class="form-control" rows="2"
      placeholder="${postsurveyQuestions[loop.index].answerChoices}" />
    </c:when>

   </c:choose>
  </div>


  <div class="has-error">
   <form:errors path="postsurveyResponses[${loop.index}].description"
    class="help-inline" />
  </div>
 </c:forEach>

 <div class="text-center">
  <button type="submit" class="btn btn-primary btn-lg">Submit
   Responses &raquo;</button>
  <p>
   <br> <b>Note:</b> After submitting the responses, you cannot
   edit them again.
  </p>
 </div>

</form:form>

<%@include file="footer.jsp"%>