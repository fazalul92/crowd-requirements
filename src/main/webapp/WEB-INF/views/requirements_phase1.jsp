<%@include file="header.jsp" %>

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
    <li><span class="badge">2</span>What should a smart home do to enhance health and safety of its occupants?</li>
	<li><span class="badge">0</span>What should a smart home do to save energy?</li>
	<li><span class="badge">7</span>What should a smart home do to ensure occupants stay happy?</li>
   </ul>
  </div>

  
  <form:form method="POST" modelAttribute="presurveyResponseForm">
   <h3>New Smart Home Requirement</h3>
   <div>
    <fieldset class="form-group">
     <label for="requirementTextarea">Requirement</label>
     <textarea class="form-control" id="requirementTextarea" rows="3"></textarea>
    </fieldset>
      
    <fieldset class="form-group">
     <label for="domainSelect">Application Domain</label>
     <select class="form-control" id="domainSelect">
      <option>Healthcare</option>
      <option>Energy</option>
      <option>Entertainment</option>
      <option>Automation</option>
      <option>Other</option>
     </select>
<!-- </fieldset>
    <fieldset class="form-group">
     <label for="applicationDomain">Application Domain</label>-->
     If other,
     <textarea class="form-control" id="tags" rows="1"></textarea>
    </fieldset>
	
	<fieldset class="form-group">
     <label for="tagsTextarea">Tags (separate multiple tags with a comma)</label>
      <textarea class="form-control" id="tags" rows="1"></textarea>
    </fieldset>
     
    <div class="has-error">
     <form:errors path="presurveyResponses[${loop.index}].description" class="help-inline" />
    </div>
    
    <button type="submit" class="btn btn-primary">Submit</button>
   </div>  
  </form:form>

 </div>
 <!-- /container -->
 
 <%@include file="footer.jsp" %>