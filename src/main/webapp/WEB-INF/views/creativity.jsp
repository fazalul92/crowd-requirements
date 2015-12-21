<%@include file="header.jsp" %>

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
  
  <form:form method="POST" modelAttribute="creativityResponseForm">
   <h3>Pre-Survey (3)</h3>
   <div class="jumbotron lead">
    <ol>
     <c:forEach var="creativityResponse" items="${creativityResponseForm.creativityResponses}" varStatus="loop">
      <form:input type="hidden" path="creativityResponses[${loop.index}].userId" name="userId" id="userId" />
      <form:input type="hidden" path="creativityResponses[${loop.index}].creativityQuestionId" name="creativityQuestionId" id="creativityQuestionId" />
      
      <li>
       <h3>${creativityQuestions[loop.index].description}</h3>
       <div class="form-horizontal">
       <%-- <c:if test="${personalityQuestions[loop.index].questionType == 'multiple_choice'}">
        <c:set var="answerChoices" value="${fn:split(personalityQuestions[loop.index].answerChoices,'|')}"/>
        <c:forEach var="answerChoice" items="${answerChoices}">
         <div class="radio">
          <label> <form:radiobutton path="personalityResponses[${loop.index}].description" value="${answerChoice}" />
           ${answerChoice}
          </label>
         </div>
        </c:forEach>
       </c:if> --%>
       <c:forEach begin="1" end="5" varStatus="likertloop">
         <label> <form:radiobutton path="personalityResponses[${loop.index}].description" value="${likertloop.index}" />${likertloop.index} </label>
        </c:forEach>
       </div>
      </li>
      
      <div class="has-error">
       <form:errors path="creativityResponses[${loop.index}].description" class="help-inline" />
      </div>
     </c:forEach>
    </ol>
    
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
 
 <%@include file="footer.jsp" %>