<%@ page import="com.model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Item item = (Item) request.getAttribute("item"); %>
<head>
    <title><%=item.getFirstName() + " " + item.getLastName()%>
    </title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/item.css">
</head>
<body>
<div class="row">
    <div class="col-sm-3"></div>
    <div class="col-sm-6">
        <h2>
            Information about current Item:
            <button class="btn btn-success edit-button changed" style="display: none;"
                    onclick="submit($('#id').val());">Submit
            </button>
            <button class="btn btn-primary edit-button changed" style="display: none;" onclick="cancel();">Cancel
            </button>
            <button class="btn btn-default edit-button stored" onclick="makeEditable();">Edit</button>
        </h2>
        <div class="alert alert-success alert-dismissible" role="alert" hidden="hidden" id="success-alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>Success:</strong> item was updated successfully.
        </div>
        <div class="alert alert-danger alert-dismissible" role="alert" hidden="hidden" id="unsuccess-alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>Unsuccess:</strong> error during item update.
        </div>
        <div class="col-sm-3 nested-markup">
            <img class="img-rounded"
                 src="https://s3.amazonaws.com/images.seroundtable.com/google-traitor-cake-1361192312.jpg"
                 style="width: 90%;">
        </div>
        <div class="col-sm-9 nested-markup">
            <table class="table table-bordered table-hover">
                <tbody>
                <input type="text" id="id" value="<%=item.getId()%>" hidden="hidden"/>
                <tr>
                    <th width="40%"><label for="fn">First Name</label></th>
                    <td class="stored" id="fn-stored"><%=item.getFirstName()%>
                    </td>
                    <td class="changed" hidden="hidden"><input id="fn" type="text" value="<%=item.getFirstName()%>"/>
                    </td>
                </tr>
                <tr>
                    <th><label for="ln">Last Name</label></th>
                    <td class="stored" id="ln-stored"><%=item.getLastName()%>
                    </td>
                    <td class="changed" hidden="hidden"><input id="ln" type="text" value="<%=item.getLastName()%>"/>
                    </td>
                </tr>
                <tr>
                    <th><label for="dc">Description</label></th>
                    <td class="stored" id="dc-stored"><%=item.getDescription()%>
                    </td>
                    <td hidden="hidden" class="changed"><input id="dc" type="text" value="<%=item.getDescription()%>"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>


    </div>
    <div class="col-sm-4"></div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/edit.js"></script>
</html>
