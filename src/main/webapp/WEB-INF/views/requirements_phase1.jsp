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
    <div class="form-group row">
     <label for="requirementTextarea" class="col-sm-2 form-control-label">Requirement</label>
     <div class="col-sm-10">
     	<textarea class="form-control" id="requirementTextarea" rows="2"></textarea>
     </div>
    </div>
    <div class="form-group row">
     <label for="domainSelect" class="col-sm-2 form-control-label">Application Domain</label>
     <div class="col-sm-5">
      <select class="form-control" id="domainSelect">
      <option>Healthcare</option>
      <option>Energy</option>
      <option>Entertainment</option>
      <option>Automation</option>
      <option>Other</option>
      </select>
     
     </div>
     <div class="col-sm-5">
      <input type="text" class="form-control" id="inputDomain" placeholder="Other Domain">
     </div>
    </div>
	
	<div class="form-group row">
     <label for="riskTextarea" class="col-sm-2 form-control-label">Potential Risks</label>
     <div class="col-sm-10">
     	<textarea class="form-control" id="riskTextarea" rows="2"></textarea>
     </div>
    </div>
	
	<div class="form-group row">
     <label for="tagsText" class="col-sm-2 form-control-label">Tags </label>
     <div class="col-sm-10">
      <input type="text" class="form-control" id="tagsText" placeholder="Separate multiple tags with a comma">
     </div>
	</div>
     
    <div class="has-error">
     <form:errors path="presurveyResponses[${loop.index}].description" class="help-inline" />
    </div>
    
    <button type="submit" class="btn btn-primary">Submit</button>
   </div>  
  </form:form>
  
  <div>
  	<ul>
  	   <!-- Loop LI -->
  		<li>
  			Requirements:  <br/>
  			Application Domain: <br/>
  			Potential Risk: <br/>
  			Tags: 
  		</li>
  	</ul>
  </div>
  
 </div>
 <!-- /container -->
 
 <%@include file="footer.jsp" %>