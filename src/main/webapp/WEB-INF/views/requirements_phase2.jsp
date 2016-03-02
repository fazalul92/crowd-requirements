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

<h3>Main Task (1)</h3>

<div class="bg-info text-justify"
 style="padding: 5px; border-radius: 5px;">
 <p>Your main task is to read Smart Home requirements provided by
  others and rate them. You will do so in the next three pages.</p>

 <p>In this page, we will provide you with basic information about
  smart homes and requirements, but you may do additional research
  (e.g., on the Internet) to understand these technologies better. We
  also ask you to take a moment to come up with a few (at least three)
  smart home requirements before proceeding to the rating tasks.</p>

 <div class="row">
  <div class="col-sm-8">
   <blockquote style="font-size: 14px;">

    <h4>What is a Smart Home?</h4>
    <p>
     A Smart Home consists of smart things! Smart things are objects
     that can <b>sense</b> their surroundings, <b>compute</b> from the
     sensed data, and <b>communicate</b> the computed results to humans
     or other smart things. In addition to gadgets such as laptops and
     smart phones, an increasing variety of household objects such as
     TVs, light bulbs, heaters, stoves, doors and windows, sprinklers,
     and cars are becoming smart things. The picture to the right shows
     some Smart Home sensors.
    </p>

    <h4>What is a Smart Home requirement?</h4>
    <p>
     A Smart Home requirement indicates what you expect a Smart Home to
     do. A typical requirement is of the format "As a <b>role</b>, I
     want <b>feature</b>, so that <b>benefit.</b>" Example roles include
     <i>home occupant</i>, <i>parent</i>, <i>neighbor</i>, and <i>nanny</i>.
     You can imagine any role (even the ones you do not play in real
     life).
    </p>
    <p>
     For each requirement, also identify its <b>application domain</b>.
     For example, the requirements above belong to the <b>safety</b>
     domain. Other domains of interest to us include <b>health</b>, <b>energy</b>,
     and <b>entertainment</b>. If your requirement belongs to a
     different domain, choose the "other" domain and provide an
     indicative name. If you think a requirement belongs to multiple
     domains, choose the most indicative domain.
    </p>

    <p>
     For each requirements, add a few <b>tags</b> (keywords, e.g.,
     hashtags on Twitter), indicative of the requirement.
    </p>
   </blockquote>
  </div>
  <div class="col-sm-4 text-center" style="margin-top: 10px;">
   <a
    href="https://storiesbywilliams.files.wordpress.com/2014/01/smarthome.jpg"
    title="Click here to enlarge" target="_blank"> <img
    src="https://storiesbywilliams.files.wordpress.com/2014/01/smarthome.jpg"
    height=220px; />
   </a> <small>Click the image to enlarge it <a
    href="http://storiesbywilliams.com/2014/01/20/tech-news-google-buys-nest-seeking-conscious-homes/">
     (Source)</a>
   </small>
  </div>
 </div>

 <p>
  <b>Important:</b> You must provide <b>at least three</b> requirements.
  After you do so, a link will appear at the bottom of the page. You can
  use that link to proceed to the next step.
 </p>

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
    id="domainSelect" required="required">
    <option value="" selected disabled>Please select</option>
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

 <div class="text-center">
  <button type="submit" class="btn btn-primary btn-lg">Add
   Requirement</button>
 </div>
</form:form>

<h3>Your Smart Home Requirements</h3>

<ul class="nav nav-pills">
 <li><a href="#">Health <span class="badge">${domainCounts["Health"]}</span></a></li>
 <li><a href="#">Safety <span class="badge">${domainCounts["Safety"]}</span></a></li>
 <li><a href="#">Energy <span class="badge">${domainCounts["Energy"]}</span></a></li>
 <li><a href="#">Entertainment <span class="badge">${domainCounts["Entertainment"]}</span></a></li>
 <li><a href="#">Other <span class="badge">${domainCounts["Other"]}</span></a></li>
</ul>

<div>
 <table class="table">
  <tr>
   <th>#</th>
   <th class="col-sm-11 ">Requirements</th>
  </tr>

  <c:set var="count" value="0" scope="page" />
  <c:forEach var="previousRequirementResponse"
   items="${previousRequirementResponses}" varStatus="reqLoop">
   <tr>
    <td><c:set var="count" value="${count + 1}" scope="page" />
     ${count}.</td>
    <td><b>As a</b> ${previousRequirementResponse.role},<br /> <b>I
      want</b> ${previousRequirementResponse.feature},<br /> <b>so that</b>
     ${previousRequirementResponse.benefit}.<br /> Application Domain:
     <i>${previousRequirementResponse.applicationDomain}</i><br />
     Tags: <c:set var="tags"
      value="${fn:split(previousRequirementResponse.tags,',')}" /> <c:forEach
      var="tag" items="${tags}" varStatus="tagLoop">
      <span class="badge">${tag}</span>
     </c:forEach></td>
   </tr>
  </c:forEach>

 </table>
</div>

<c:if test="${count >= 3}">
 <div class="text-center">
  <h4>
   <a href="/crowd-requirements/requirements_ratings"
    onclick='return confirm("Are you done adding all requirements? If yes, click OK and proceed to the next step. If no, click Cancel and add more requirements.")'>
    Proceed to next phase &raquo; </a>
  </h4>
 </div>
</c:if>

<%@include file="footer.jsp"%>
