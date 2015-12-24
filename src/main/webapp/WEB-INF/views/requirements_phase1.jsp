<%@include file="header.jsp"%>

<!-- progressbar -->
<ul id="progressbar">
 <li>Pre-Survey (1)</li>
 <li>Pre-Survey (2)</li>
 <li>Pre-Survey (3)</li>
 <li class="active">Main Task (1)</li>
 <li>Main Task (2)</li>
 <li>Post-Survey</li>
 <li>Completion Code</li>
</ul>

<h3>Main Task(1)</h3>
<div class="bg-info" style="padding: 5px; border-radius: 5px;">
 <h4>Smart Home Study</h4>
 <ul>
  <li><span class="badge">2</span>What should a smart home do to
   enhance health and safety of its occupants?</li>
  <li><span class="badge">0</span>What should a smart home do to
   save energy?</li>
  <li><span class="badge">7</span>What should a smart home do to
   ensure occupants stay happy?</li>
 </ul>
</div>


<form:form method="POST" modelAttribute="requirementResponse">
 <form:input type="hidden" path="userId" name="userId" />
 <h3>New Smart Home Requirement</h3>
 <div class="form-group row">
  <label for="requirementTextarea" class="col-sm-2 form-control-label">Requirement</label>
  <div class="col-sm-10">
   <div class="form-group row">
    <label for="roleText" class="col-sm-1 form-control-label">As
     a</label>
    <div class="col-sm-11">
     <form:input type="text" placeholder="Role" class="form-control"
      path="role" required="required" />
    </div>
   </div>

   <div class="form-group row">
    <label for="featureText" class="col-sm-1 form-control-label">I
     want</label>
    <div class="col-sm-11">
     <form:input type="text" placeholder="Feature" class="form-control"
      path="feature" required="required" />
    </div>
   </div>

   <div class="form-group row">
    <label for="benefitText" class="col-sm-1 form-control-label">so
     that</label>
    <div class="col-sm-11">
     <form:input type="text" placeholder="Benefit" class="form-control"
      path="benefit" required="required" />
    </div>
   </div>
  </div>
 </div>

 <div class="form-group row">
  <label for="domainSelect" class="col-sm-2 form-control-label">Application
   Domain</label>
  <div class="col-sm-5">
   <form:select class="form-control" path="applicationDomain"
    id="domainSelect">
    <option>--Select--</option>
    <option>Health</option>
    <option>Safety</option>
    <option>Energy</option>
    <option>Entertainment</option>
    <option>Other</option>
   </form:select>
   <div class="has-error">
    <form:errors path="applicationDomain" class="help-inline" />
   </div>

  </div>

  <div class="col-sm-5">
   <form:input type="text" class="form-control"
    path="applicationDomainOther" id="inputDomain"
    placeholder="Other Domain"></form:input>
  </div>
 </div>

 <div class="form-group row">
  <label for="tagsText" class="col-sm-2 form-control-label">Tags
  </label>
  <div class="col-sm-10">
   <form:input type="text" class="form-control" path="tags"
    id="tagsText" placeholder="Separate multiple tags with a comma"></form:input>
  </div>
 </div>

 <button type="submit" class="btn btn-primary">Add Requirement</button>
</form:form>

<h3>Your Requirements</h3>

<div>
 <table class="table">
  <tr>
   <th>#</th>
   <th class="col-sm-11 ">Requirements</th>
  </tr>

  <c:set var="count" value="0" scope="page" />
  <c:forEach var="appDomain"
   items="Health,Safety,Energy,Entertainment,Other"
   varStatus="domainLoop">
   <c:forEach var="previousRequirementResponse"
    items="${previousRequirementResponses[appDomain]}"
    varStatus="reqLoop">
    <tr>
     <td><c:set var="count" value="${count + 1}" scope="page" />
      ${count}.</td>
     <td>Role: ${previousRequirementResponse.role}<br /> 
      Feature: ${previousRequirementResponse.feature}<br /> 
      Benefit: ${previousRequirementResponse.benefit}<br /> 
      Application Domain: <i>${previousRequirementResponse.applicationDomain}</i><br />
      Tags: <c:set var="tags"
       value="${fn:split(previousRequirementResponse.tags,',')}" /> <c:forEach
       var="tag" items="${tags}" varStatus="tagLoop">
       <span class="badge">${tag}</span>
      </c:forEach>

     </td>
    </tr>
   </c:forEach>
  </c:forEach>

 </table>
</div>

<!-- onclick='confirm("Are you done adding all requirements? If yes, click OK and proceed to the next step. If no, click Cancel and add more requirements.");'-->
<div class="text-center">
 <a href="/crowd-requirements/requirements_phase2"
  class="btn btn-primary btn-lg" data-toggle="confirmation"> Submit Responses &raquo; </a>
 <p>
  <b>Note:</b> You cannot add more requirements after submitting
  responses. Check the list above to make sure that ALL your
  requirements are recorded. If a requirement is not recorded properly,
  reenter it and select Add Requirement before submitting.
 </p>
</div>


<%@include file="footer.jsp"%>
