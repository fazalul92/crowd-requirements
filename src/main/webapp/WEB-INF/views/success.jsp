<%@include file="header.jsp"%>

<!-- progressbar -->
<ul id="progressbar">
 <li>Pre-Survey (1)</li>
 <li>Pre-Survey (2)</li>
 <li>Pre-Survey (3)</li>
 <li>Main Task (1)</li>
 <li>Main Task (2)</li>
 <li>Post-Survey</li>
 <li class="active">Completion Code</li>
</ul>

<div class="jumbotron lead">
 <p>
  We sincerely <b>thank you</b> for completing the study. Your
  completion code is <b>${sessionScope.userEntity.completionCode}</b>
 </p>
</div>

<%@include file="footer.jsp"%>
