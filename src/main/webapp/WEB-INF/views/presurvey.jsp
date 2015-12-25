<%@include file="header.jsp"%>

<!-- progressbar -->
<ul id="progressbar">
 <li class="active">Pre-Survey (1)</li>
 <li>Pre-Survey (2)</li>
 <li>Pre-Survey (3)</li>
 <li>Main Task (1)</li>
 <li>Main Task (2)</li>
 <li>Post-Survey</li>
 <li>Completion Code</li>
</ul>

<h3>Instructions</h3>
<div class="bg-info" style="padding: 5px; border-radius: 5px;">
 <p class="text-justify">
  Welcome to the <b>Smart Home</b> study. This study consists of <b>six
   steps</b>: three pre-surveys, two main surveys, and a post survey. You
  are required to complete these steps sequentially. After six steps,
  you will receive a <b>completion code</b>, which you must enter on the
  Amazon MTurk site to receive a payment. There is <b>no partial
   payment</b>. You must complete all steps to receive the payment. The main
  tasks may involve <b>bonus payments</b> as you will see later.
 </p>

 <p class="text-justify">The following are some general
  instructions.</p>
 <ul>
  <li><b>Do not</b> use the browser's <b>refresh</b>, <b>back</b>,
   or <b>forward</b> buttons.</li>
  <li>Use the <b>Submit Responses</b> button at the end of the page
   to proceed to the next page.
  </li>
  <li>After submitting responses in a page, you <b>cannot
    modify</b> your responses in that page.
  </li>
  <li>Answering all questions is mandatory, unless explicitly
   marked as optional.</li>
  <li>Do not close your browser window (this tab) before
   completing. If you do so, you may have to start again from the
   beginning.</li>
  <li>If something goes wrong (does not happen often), please start
   again from the beginning. If the problem persists, please contact <b>pmuruka
    AT ncsu.edu</b> with details of the problem.
  </li>
 </ul>
</div>

<form:form method="POST" modelAttribute="presurveyResponseForm">
 <h3>Pre-Survey (1)</h3>

   <c:forEach var="presurveyResponse"
    items="${presurveyResponseForm.presurveyResponses}" varStatus="loop">
    <form:input type="hidden"
     path="presurveyResponses[${loop.index}].userId" name="userId"
     id="userId" />
     <div class="form-group">
    <label for="presurveyResponse">${presurveyQuestions[loop.index].description}</label>
  
    <form:input type="hidden"
     path="presurveyResponses[${loop.index}].presurveyQuestionId"
     name="presurveyQuestionId" id="presurveyQuestionId" />

      <c:choose>
       <c:when
        test="${presurveyQuestions[loop.index].questionType == 'multiple_choice'}">
        <c:set var="answerChoices"
         value="${fn:split(presurveyQuestions[loop.index].answerChoices,'|')}" />
        <c:forEach var="answerChoice" items="${answerChoices}">
         <div class="radio">
          <label> <form:radiobutton
            path="presurveyResponses[${loop.index}].description"
            value="${answerChoice}" required="required" />
           ${answerChoice}
          </label>
         </div>
        </c:forEach>
       </c:when>
       <c:when
        test="${presurveyQuestions[loop.index].questionType == 'text'}">
        <form:textarea
         path="presurveyResponses[${loop.index}].description"
         class="form-control" rows="2"
         placeholder="${presurveyQuestions[loop.index].answerChoices}" />
       </c:when>
      </c:choose>
     </div>


    <div class="has-error">
     <form:errors path="presurveyResponses[${loop.index}].description"
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