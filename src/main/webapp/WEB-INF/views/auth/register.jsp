<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    $(document).ready(function() {
        $('#email').blur(function() {
            var email = $(this).val();
            if(email) {
                $.getJSON('${pageContext.request.contextPath}/user/check-email.action',
                    { 'user.email': email }, function(data) {
                    if(data.emailExists) {
                        $('#email-error').html('Email already exists').show();
                    } else {
                        $('#email-error').hide();
                    }
                });
            }
        });
    });
    </script>
    <style>
        .error-message { color: red; }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="mb-4">User Registration</h2>

                <s:form action="register" method="POST" namespace="/user">
                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <s:textfield name="user.username" cssClass="form-control" />
                        <s:fielderror fieldName="user.username" cssClass="error-message"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <s:textfield name="user.email" cssClass="form-control" id="email" />
                        <s:fielderror fieldName="user.email" cssClass="error-message"/>
                        <div id="email-error" class="error-message" style="display:none;"></div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <s:password name="user.password" cssClass="form-control" />
                        <s:fielderror fieldName="user.password" cssClass="error-message"/>
                    </div>
                    <s:submit value="Register" cssClass="btn btn-primary" />
                </s:form>
            </div>
        </div>
    </div>
</body>
</html>