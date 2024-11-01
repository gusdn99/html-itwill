<%@page import="com.itwill.lab04.model.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <%-- jstl 사용하려면 적어야 함. --%> <%-- .core, .fmt를 많이 사용함 --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSTL</title>
</head>
<body>
    <%@ include file="header.jspf" %>
    
    <main>
        <h1>JSTL(JSP Standard Tag Library)</h1>
        <%-- JSTL 라이브러리 사용하기
        1. pom.xml 파일에 의존성(dependancy)을 추가.
            o. jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0
            o. org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1
        2. JSTL을 사용하는 JSP 파일에서 <%@ taglib prefix="" uri="" %> 지시문을 설정.
         --%>
         
         <%
         String[] sns = {"인*", "싸이월드", "얼굴책", "X"};
         pageContext.setAttribute("sns", sns);
         %>
         <h2>스크립트릿, 식 사용한 리스트</h2>
         <ul>
         <% for (String s : sns) { %>
            <li><%= s %></li>
         <% } %>
         </ul>
         
         <h2>JSTL, EL을 사용한 리스트</h2>
         <ul>
            <c:forEach items="${ sns }" var="s">
            <%--
            3번째 줄에서 prefix를 "c"로 정했기 때문에 "<c:" 이렇게 함. 
            setAttribute의 이름("sns")을 items에 EL로 적음. var는 변수 이름.
            --%>
                <li>${ s }</li> <%-- EL에 이미 선언된 변수 이름을 적음. --%>
            </c:forEach>
         </ul>
         
         <%
         ArrayList<Contact> data = new ArrayList<>();
         for (int i = 1; i <= 5; i++) {
        	  data.add(new Contact(i, "name_" + i, "phone_" + i, "email_" + i));
         }
         pageContext.setAttribute("contactList", data);
         %>
         
         <h2>scriptlet, expression 사용한 테이블</h2>
         <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thead>
            <tbody>
            <% for (Contact c : data) { %>
                <tr>
                    <td><%= c.getId() %></td>
                    <td><%= c.getName() %></td>
                    <td><%= c.getPhone() %></td>
                    <td><%= c.getEmail() %></td>
                </tr>
            <% } %>
            </tbody>
        </table>
         
         <h2>JSTL, EL 사용한 테이블</h2>
         <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ contactList }" var="c">
                    <tr>
                        <td>${ c.id }</td> <%-- EL은 프로퍼티 이름으로 getter 메서드를 찾음. --%>
                        <td>${ c.name }</td>
                        <td>${ c.phone }</td>
                        <td>${ c.email }</td>
                    </tr>
                </c:forEach>
            </tbody>
         </table>
               
         <h2>URL 태그</h2>
         <a href="result.jsp?username=gu&est&color=crimson">클릭1</a> <%-- 안녕하세요, gu! --%>
         
         <%-- 질의 문자열의 요청 파라미터 값에 특수 기호가 포함될 때(gu%est& --%>
         <c:url value="result.jsp" var="url">
            <c:param name="username" value="gu&est" /> <%-- 끝나는 컨텐트 안 적어도 됨. --%>
            <c:param name="color"  value="crimson" />
         </c:url>
         <a href="${ url }">클릭2</a> <%-- 안녕하세요, gu&est! --%>
        <%-- http://localhost:8080/lab04/result.jsp?username=gu%26est&color=crimson
        숫자 26은 "&"의 UTF 코드
         --%> 
          
    </main>
</body>
</html>