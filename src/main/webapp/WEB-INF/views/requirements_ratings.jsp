<%@include file="header.jsp"%>

<!-- progressbar -->
<ul id="progressbar">
 <li>Pre-Survey (1)</li>
 <li>Pre-Survey (2)</li>
 <li>Pre-Survey (3)</li>
 <li>Main Task (1)</li>
 <li class="active">Main Task (2)</li>
 <li>Post-Survey</li>
 <li>Completion Code</li>
</ul>

<h3>Main Task (2)</h3>
<div class="bg-info" style="padding: 5px; border-radius: 5px;">
 <p>
  In this phase, you will rate the creativity and clarity of the requirements shown
  to you and your own requirements. A creative requirement leads to <b>novel</b>
  and <b>useful</b> product. A clear requirement ***. Thus, rate novelty, usefulness and clarity of
  these requirements using the scale below. Please provide a honest
  assessment. Your self assessment will <b>NOT</b> affect any bonus you
  may receive.
 </p>
 <ol>
  <li>Very low</li>
  <li>Low</li>
  <li>Medium</li>
  <li>High</li>
  <li>Very High</li>
 </ol>
</div>

<h3>Requirement Creativity and Clarity Rating</h3>

<form:form method="POST" modelAttribute="requirementRatingResponseForm">

 <div>
  <table class="table">
   <tr>
    <th>#</th>
    <th class="col-sm-5 ">Requirements</th>
    <th>Usefulness</th>
    <th>Novelty</th>
    <th>Clarity</th>
   </tr>

   <c:forEach var="jRequirementRatingResponse"
    items="${requirementRatingResponseForm.requirementRatingResponses}"
    varStatus="loop">
    <form:input type="hidden"
     path="requirementRatingResponses[${loop.index}].requirementResponse.id"
     name="requirementResponseId" id="requirementResponseId" />
    <tr>
     <td>${loop.index + 1}.</td>
     <td><b>As a</b>
      ${jRequirementRatingResponse.requirementResponse.role},<br /> <b>I
       want</b> ${jRequirementRatingResponse.requirementResponse.feature},<br />
      <b>so that</b>
      ${jRequirementRatingResponse.requirementResponse.benefit}.<br />
      Application Domain: <i>${jRequirementRatingResponse.requirementResponse.applicationDomain}</i>
      <br /> <c:set var="jTags"
       value="${fn:split(jRequirementRatingResponse.requirementResponse.tags,',')}" />
      <c:forEach var="jTag" items="${jTags}">
       <span class="badge">${jTag}</span>
      </c:forEach></td>
     <td><div class="form-group radio">
       <c:forEach begin="1" end="5" varStatus="likertloop">
        <label> <form:radiobutton
          path="requirementRatingResponses[${loop.index}].novelty"
          value="${likertloop.index}" required="required" />${likertloop.index}
        </label>
       </c:forEach>
      </div></td>
     <td><div class="form-group radio">
       <c:forEach begin="1" end="5" varStatus="likertloop">
        <label> <form:radiobutton
          path="requirementRatingResponses[${loop.index}].feasibility"
          value="${likertloop.index}" required="required" />${likertloop.index}
        </label>
       </c:forEach>
      </div></td>
      <td><div class="form-group radio">
       <c:forEach begin="1" end="5" varStatus="likertloop">
        <label> <form:radiobutton
          path="requirementRatingResponses[${loop.index}].clarity"
          value="${likertloop.index}" required="required" />${likertloop.index}
        </label>
       </c:forEach>
      </div></td>
    </tr>
   </c:forEach>
  </table>

 </div>
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