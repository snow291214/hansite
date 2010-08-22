<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div>
    <p>For User</p>
    <a href="customers.htm">Customers</a><br />
    <a href="priceTypeEditor.htm">Add or edit type of price-list</a><br/>
    <a href="priceEditor.htm">Change rates wizard</a><br/>
    <a href="logout.htm">Logout</a>
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <p>For Administrator</p>
    </sec:authorize>
</div>