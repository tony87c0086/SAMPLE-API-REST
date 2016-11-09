<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Uknowho REST API Portal</title>
</head>
<body>
<h1>
	Welcome! UKnoWho I am! Happy Coding!
</h1>

<p>
The following HTTP response codes apply to all requests to the API services.
Additional response codes are covered in the usage information for each
operation.
</p>

<table>
  <tr><th rowspan="1" colspan="1" nowrap>Code</th><th rowspan="1" colspan="1" nowrap>Description</th><th rowspan="1" colspan="1" nowrap>Cause</th></tr>
  <tr><td rowspan="1" colspan="1" nowrap>200</td><td rowspan="1" colspan="1" nowrap>OK</td><td rowspan="1" colspan="1" nowrap>Success</td></tr>
  <tr><td rowspan="1" colspan="1" nowrap>400</td><td rowspan="1" colspan="1" nowrap>Bad Request</td><td rowspan="1" colspan="1" nowrap>Unsupported or invalid parameters, or missing required parameters.</td></tr>
  <tr><td rowspan="1" colspan="1" nowrap>401</td><td rowspan="1" colspan="1" nowrap>Unauthorized</td><td rowspan="1" colspan="1" nowrap>User is not authorized.</td></tr>
  <tr><td rowspan="1" colspan="1" nowrap>403</td><td rowspan="1" colspan="1" nowrap>Forbidden</td><td rowspan="1" colspan="1" nowrap>User does not have access to this resource.</td></tr>
  <tr><td rowspan="1" colspan="1" nowrap>404</td><td rowspan="1" colspan="1" nowrap>Not Found</td><td rowspan="1" colspan="1" nowrap>No matching pattern for incoming URI.</td></tr>
  <tr><td rowspan="1" colspan="1" nowrap>405</td><td rowspan="1" colspan="1" nowrap>Method Not Allowed</td><td rowspan="1" colspan="1" nowrap>The service does not support the HTTP method used by the client.</td></tr>
  <tr><td rowspan="1" colspan="1" nowrap>406</td><td rowspan="1" colspan="1" nowrap>Unacceptable Type</td><td rowspan="1" colspan="1" nowrap>Unable to provide content type matching the client's Accept header.</td></tr>
  <tr><td rowspan="1" colspan="1" nowrap>412</td><td rowspan="1" colspan="1" nowrap>Precondition Failed</td><td rowspan="1" colspan="1" nowrap>A non-syntactic part of the request was rejected. For example, an 
        empty POST or PUT body.
    </td></tr>
  <tr><td rowspan="1" colspan="1" nowrap>415</td><td rowspan="1" colspan="1" nowrap>Unsupported Media Type</td><td rowspan="1" colspan="1" nowrap>A PUT or POST payload cannot be accepted.</td></tr>
</table>

</body>
</html>
