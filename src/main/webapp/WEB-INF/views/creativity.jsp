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
<div class="bg-info" style="padding:5px; border-radius:5px;">
 <p class="text-justify">Please use the rating scale below to describe how accurately
  each adjective describes you. Describe yourself as you generally are
  now, not as you wish to be in the future. Describe yourself as you
  honestly see yourself, in relation to other people you know of the
  same sex as you are, and roughly your same age. So that you can
  describe yourself in an honest manner, your responses will be kept in
  absolute confidence. Please read each statement carefully, and then
  fill in the bubble that corresponds to the number on the scale.</p>
 <ol>
  <li>Very Inaccurate</li>
  <li>Moderately Inaccurate</li>
  <li>Neither Inaccurate nor Accurate</li>
  <li>Moderately Accurate</li>
  <li>Very Accurate</li>
 </ol>
</div>

<form:form method="POST" modelAttribute="creativityResponseForm">
 <h3>Pre-Survey (3)</h3>
 <!-- <div class="jumbotron lead"> -->
  <table class="table table-hover">
   <tr>
    <th class="col-sm-1">#</th>
    <th class="col-sm-8">Adjective</th>
    <th class="col-sm-3">Rating</th>
   </tr>
   <c:forEach var="creativityResponse"
    items="${creativityResponseForm.creativityResponses}"
    varStatus="loop">
    <form:input type="hidden"
     path="creativityResponses[${loop.index}].userId" name="userId"
     id="userId" />
    <form:input type="hidden"
     path="creativityResponses[${loop.index}].creativityQuestionId"
     name="creativityQuestionId" id="creativityQuestionId" />

    <tr>
     <td>${loop.index+1}.</td>
     <td>${creativityQuestions[loop.index].description}</td>
     <td>
     <div class="form-group radio">
      <c:forEach begin="1" end="5" varStatus="likertloop">
       <label> <form:radiobutton
         path="creativityResponses[${loop.index}].description"
         value="${likertloop.index}" />${likertloop.index}&nbsp;&nbsp;
       </label>
      </c:forEach>
     </div>
     
     <div class="has-error">
     <form:errors path="creativityResponses[${loop.index}].description"
      class="help-inline" />
     </div>
      
     </td>
    </tr>

    
   </c:forEach>
  </table>

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