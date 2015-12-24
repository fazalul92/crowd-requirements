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
<div class="bg-info" style="padding:5px; border-radius:5px;">
 <p>In this phase your task is to rate how feasible and novel are
  the requirements that you listed in phase [alpha], and provide a
  justification for your rating.</p>
</div>

<h3>Your Requirements</h3>

<form:form method="POST" modelAttribute="requirementRatingResponseForm">

 <div>
  <table class="table">
   <tr>
    <th>#</th>
    <th class="col-sm-5 ">Requirements</th>
    <th>Feasibility</th>
    <th>Novelty</th>
   </tr>

   <c:forEach var="jRequirementRatingResponse"
    items="${requirementRatingResponseForm.requirementRatingResponses}"
    varStatus="loop">
    <form:input type="hidden"
     path="requirementRatingResponses[${loop.index}].requirementResponse.id"
     name="requirementResponseId" id="requirementResponseId" />
    <tr>
     <td>${loop.index + 1}.</td>
     <td>${jRequirementRatingResponse.requirementResponse.description}
      <br /> Application Domain: <i>${jRequirementRatingResponse.requirementResponse.applicationDomain}</i>
      <br /> <c:set var="jTags"
       value="${fn:split(jRequirementRatingResponse.requirementResponse.tags,',')}" />
      <c:forEach var="jTag" items="${jTags}">
       <span class="badge">${jTag}</span>
      </c:forEach>
     </td>
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
    </tr>
   </c:forEach>
  </table>
  <button type="submit" class="btn btn-primary">Submit</button>
 </div>
</form:form>

<%@include file="footer.jsp"%>