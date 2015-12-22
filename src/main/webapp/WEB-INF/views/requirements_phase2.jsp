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
  <table class="table">
  	<tr>
  		<td>#</td>
  		<td class="col-sm-5 ">Requirements</td>
  		<td>Feasibility</td>
  		<td>Novelty</td>
  		<td>Risks</td>
  		<td>Comment</td>
  	</tr>
  	
  	<tr>
  		<td>1.</td>
  		<td>
  			Smart home should call police as soon as it recognizes a break-in. <br/>
  			Application Domain: <i>Health and safety</i><br/>
  			<span class="badge">safety</span> <span class="badge">break-in</span> <span class="badge">police</span>
  		</td>
  		<td>
  			<select class="form-control">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		</td>
  		<td>
  			<select class="form-control">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		</td>
  		<td>
  			<select class="form-control">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		</td>
  		<td><textarea class="form-control" id="comment" rows="2">No potential risk</textarea></td>
  	</tr>
  	
  	<tr>
  		<td>2.</td>
  		<td>
  			Smart home should call nearest neighbour if it recognizes that an elderly person in the house broke down.<br/>
  			Application Domain: <i>Health and safety</i><br/>
  			<span class="badge">safety</span> <span class="badge">health</span> <span class="badge">doctor</span>
  		</td>
  		<td>
  			<select class="form-control">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		</td>
  		<td>
  			<select class="form-control">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		</td>
  		<td>
  			<select class="form-control">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		</td>
  		<td><textarea class="form-control" id="comment" rows="2">Privacy concern</textarea></td>
  	</tr>
  	
  </table>
  <button type="submit" class="btn btn-primary">Submit</button>
  </div>
   
 </div>
 <!-- /container -->
 
 <%@include file="footer.jsp" %>