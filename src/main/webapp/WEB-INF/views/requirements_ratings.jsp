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
  In this phase, you will review requirements provided by others and
  rate them. You will be shown a <b>total of 30</b> requirements in <b>three
   batches</b> of 10 each. You will rate each requirement's clarity,
  usefulness, and novelty. Please provide a honest assessment.
 </p>
 <dl class="dl-horizontal">
  <dt>Clarity:</dt>
  <dd>A clear requirement is unambiguous and provides an
   appropriate level of detail.</dd>
  <dt>Usefulness:</dt>
  <dd>A useful requirement leads to products that provide value or
   utility to their users.</dd>
  <dt>Novelty:</dt>
  <dd>A novel requirement is one a user finds as original,
   unexpected, i.e., something that is not commonplace, mundane, or
   conventional.</dd>
 </dl>

 <p>
  Rate each of these attributes for each requirement on a scale of: <b>(1)
   very low</b>, <b>(2) low</b>, <b>(3) medium</b>, <b>(4) high</b>, and <b>(5)
   very high</b>.
 </p>
</div>

<h3>Requirement Rating: Batch ${sessionScope.ratingStepEntity} of 3</h3>

<form:form method="POST" modelAttribute="requirementRatingResponseForm">

 <div>
  <table class="table">
   <tr>
    <th>#</th>
    <th class="col-sm-5 ">Requirements</th>
    <th>Clarity</th>
    <th>Usefulness</th>
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
          path="requirementRatingResponses[${loop.index}].clarity"
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
          path="requirementRatingResponses[${loop.index}].novelty"
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