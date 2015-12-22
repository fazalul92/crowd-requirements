<%@include file="header.jsp" %>

  <!-- progressbar -->
  <ul id="progressbar">
   <li>Pre-Survey (1)</li>
   <li>Pre-Survey (2)</li>
   <li>Pre-Survey (3)</li>
   <li>Phase [alpha]</li>
   <li class="active">Phase [beta]</li>
   <li>Phase [gamma]</li>
   <li>Post-Survey</li>
  </ul>

  <h3>Requirements Phase [beta]</h3>
  <div class="jumbotron lead">
   <p>In this phase your task is to rate how feasible and novel are the requirements that you listed in phase [alpha], and provide a justification for your rating.</p>
  </div>
  
  <h3>Your Requirements</h3>
   <div>
	<ul>
		<li>
			<p>
			<b>Requirement 1.</b> Smart home should call police as soon as it recognizes a break-in.<br/>
			Feasibility: <i>Highly feasible</i>
			<ul>
				<li>Camera's can record, and trigger a phone call.</li>
			</ul>
			Novelty: <i>Highly novel</i>
			<ul>
				<li>Alarms already exists.</li>
			</ul>
			Privacy Concerns: <i>No</i>
			<ul>
				<li>No privacy concern.</li>
			</ul>
			<span class="badge">safety</span> <span class="badge">break-in</span> <span class="badge">police</span><br/>
			</p>
		</li>

		<li>
			<p>
			<b>Requirement 2.</b> Smart home should call nearest neighbour if it recognizes that an elderly person in the house broke down.<br/>
			Feasibility: <i>Highly feasible</i>
			<ul>
				<li>Camera's can record, and trigger a phone call.</li>
			</ul>
			Novelty: <i>Highly novel</i>
			<ul>
				<li>No such thing exists.</li>
			</ul>
			Privacy Concerns: <i>Yes</i>
			<ul>
				<li>Records video all the time.</li>
			</ul>
			<span class="badge">health</span> <span class="badge">doctor</span><br/>
			</p>
			<div class="panel panel-default" >
				<div class="panel-heading">Editing Requirement 2</div>
				<div class="panel-body">
				  <form>
					<fieldset class="form-group">
						<label>Feasibility?</label>
						<div class="radio">
						
						<label><input type="radio" name="optionsRadios" id="optionsRadios1" value="1" checked>1</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios2" value="2" checked>2</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios3" value="3" checked>3</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios4" value="4" checked>4</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios5" value="5" checked>5</label>
					</div>
					</fieldset>
					<!--
					<fieldset class="form-group">
					  <label for="requirementTextarea">In a few words describe, why do you think so? (Personal experience? Evidence?)</label>
						<textarea class="form-control" id="feasibleEvidence" rows="2"></textarea>
					</fieldset>
					-->
					<fieldset class="form-group">
						<label>Novelty?</label>
						<div class="radio">
						
						<label><input type="radio" name="optionsRadios" id="optionsRadios1" value="1" checked>1</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios2" value="2" checked>2</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios3" value="3" checked>3</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios4" value="4" checked>4</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios5" value="5" checked>5</label>
					</div>
					</fieldset>
					<!--
					<fieldset class="form-group">
					  <label for="requirementTextarea">In a few words describe, why do you think so? (Personal experience? Evidence?)</label>
					  <textarea class="form-control" id="novelEvidence" rows="2"></textarea>
					</fieldset>
					-->
					<fieldset class="form-group">
					  <label for="requirementTextarea">Potential risks?</label>
					  <textarea class="form-control" id="novelEvidence" rows="2"></textarea>
					</fieldset>
					<fieldset class="form-group">
						<label>How important is it to mitigate the above identified risks?</label>
						<div class="radio">
						<label><input type="radio" name="optionsRadios" id="optionsRadios1" value="0" checked>No</label>
						<label><input type="radio" name="optionsRadios" id="optionsRadios2" value="1" checked>Yes</label>
					</div>
					</fieldset>
			  

					<button type="submit" class="btn btn-primary">Submit</button>
				  </form>
				</div>
			</div>

		</li>
	</ul>
  </div>
  
 <%@include file="footer.jsp" %>