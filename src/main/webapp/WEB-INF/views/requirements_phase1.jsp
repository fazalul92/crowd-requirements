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
<div class="bg-info" style="padding:5px; border-radius:5px;">
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
 <div>
  <!-- <div class="form-group row">
   <label for="requirementTextarea" class="col-sm-2 form-control-label">Requirement</label>
   <div class="col-sm-10">
    <form:textarea class="form-control" path="description" id="requirementTextarea" rows="2"/>
    As a (role) I want (something) so that (benefit).
   </div>
   -->
  <div class="has-error">
   <form:errors path="description" class="help-inline" />
  </div>
 </div>
 <div class="form-group row">
  <label for="requirementTextarea" class="col-sm-2 form-control-label">Requirement</label>
  <div class="col-sm-10">
   <div class="form-group row">
    <label for="roleText" class="col-sm-1 form-control-label">As
     a</label>
    <div class="col-sm-11">
     <input type="text" placeholder="(role)" class="form-control" />
    </div>
   </div>

   <div class="form-group row">
    <label for="featureText" class="col-sm-1 form-control-label">I
     want</label>
    <div class="col-sm-11">
     <input type="text" placeholder="(feature)" class="form-control" />
    </div>
   </div>

   <div class="form-group row">
    <label for="benefitText" class="col-sm-1 form-control-label">so
     that</label>
    <div class="col-sm-11">
     <input type="text" placeholder="(benefit)" class="form-control" />
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
     <td>${previousRequirementResponse.description}<br />
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

  <div class="text-center">
   <a href="/crowd-requirements/requirements_phase2"  class="btn btn-primary btn-lg" onclick='confirm("Have you checked over ALL your requirements to verify they were recorded properly. If so, select OK. If you believe that your requirements were not recorded properly, select Cancel and reenter your requirements, and select Add Requirement after EVERY requirement before final submission.");'>
   Submit Responses &raquo;
   </a>
   <p class="text-justify">
   <b>Note:</b> After submitting the responses, you cannot edit your requirements again.<br/>
   Check over ALL your requirements to verify they were recorded properly. If you believe that your requirements were not recorded properly, reenter your requirements, and select Add Requirement after EVERY requirement before final submission.
   </p>
  </div>


<%@include file="footer.jsp"%>
