<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Lab 5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous" />
</head>
<body>
    
    <div class="contatiner-fluid">
        <c:set var="pageTitle" value="로그인" scope="page" />
        <%@ include file="../fragments/header.jspf" %>
        
        <main>
            <div class="mt-2 card">
                <div class="card-header">
                    <h2>회원 가입</h2>
                </div>
                <div class="card-body">
                    <form id="SignInForm">
                        <div class="mt-2">
                            <input class="form-control"
                                type="text" name="userid" placeholder="아이디" required autofocus />
                        </div>
                        <div class="mt-2">
                            <input class="form-control"
                                type="password" name="password" placeholder="비밀번호" required />
                        </div>
                        <div class="card-footer mt-2 d-grid gap-2">
                            <button id="btnSignIn" class="btn btn-outline-success" type="button">로그인</button>
                            <button id="btnCancel" class="btn btn-outline-secondary" type="button">취소</button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        
    <c:url var="user_signin_js" value="/js/user_signin.js"/>
    <script src="${ user_signin_js }"></script>
    
</body>
</html>