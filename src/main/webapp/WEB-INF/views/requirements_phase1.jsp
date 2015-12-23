<%@include file="header.jsp"%>

<!-- progressbar -->
<ul id="progressbar">
 <li>Pre-Survey (1)</li>
 <li>Pre-Survey (2)</li>
 <li>Pre-Survey (3)</li>
 <li class="active">Phase [alpha]</li>
 <li>Phase [beta]</li>
 <li>Phase [gamma]</li>
 <li>Post-Survey</li>
</ul>

<h3>Requirements Phase [alpha]</h3>
<div class="jumbotron lead">
 <p>Smart Home Study</p>
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
  <div class="form-group row">
   <label for="requirementTextarea" class="col-sm-2 form-control-label">Requirement</label>
   <div class="col-sm-10">
    <form:textarea class="form-control" path="description"
     id="requirementTextarea" rows="2"></form:textarea>
   </div>
   <div class="has-error">
    <form:errors path="description" class="help-inline" />
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
    <form:errors path="description" class="help-inline" />
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

  <button type="submit" class="btn btn-primary">Submit</button>
 </div>
</form:form>

<h3>Your Requirements</h3>

<div>
 <table class="table">
  <tr>
   <td>#</td>
   <td class="col-sm-11 ">Requirements</td>
  </tr>
  
  <c:set var="count" value="0" scope="page" />
  <c:forEach var="appDomain" items="Health,Safety,Energy,Entertainment,Other" varStatus="domainLoop">
  <c:forEach var="previousRequirementResponse"
   items="${previousRequirementResponses[appDomain]}" varStatus="reqLoop">
   <tr>
   <td>
    <c:set var="count" value="${count + 1}" scope="page"/>
    ${count}.
   </td>
   <td>
   	${previousRequirementResponse.description}<br />
    Application Domain: <i>${previousRequirementResponse.applicationDomain}</i><br /> 
    Tags:
    <c:set var="tags" value="${fn:split(previousRequirementResponse.tags,',')}"/>
    <c:forEach var="tag" items="${tags}" varStatus="tagLoop">
     <span class="badge">${tag}</span>
    </c:forEach>
    
   </td>
   </tr>
  </c:forEach>
  </c:forEach>
  
 </table>
</div>

<%@include file="footer.jsp"%>