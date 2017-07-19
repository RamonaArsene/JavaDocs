<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Enjoy Zero To Hero</h2>

<div>
<form id="form3" name="export" method = "get">
    <table>
        <tr>
            <td colspan="2">
                <label for="user">Username:</label>
                <input type="text" id="user" name="user"/>
            </td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        <tr>
            <td>
                <input type="button" name="export" value="Export file" onclick="exportFile()">
            </td>
            <td>
                <select name = "fileType" id = "fileType">
                    <option value="pdf">PDF</option>
                    <option value="xlsx">XLSX</option>

                </select>
            </td>
        </tr>
    </table>
</form>

</div>

<script type="text/javascript">
    function exportFile() {
        var username = document.getElementById('user').value;
        var fileType = document.getElementById('fileType').value;


        if (username != null && username != "") {
            //  1: Use window.open to call ExportFileServlet using its mapping and the following request parameters: template, fileType and username
            // TIP: template = UsernameReport, fileType = pdf
            // TIP: Remember that the application context it's named servlet-app
            window.open("/servlet-app-day2/export?template=UsernameReport&fileType=" +fileType + "&username=" +username);
        } else {
            // TODO 1: Use window.open to call ExportFileServlet using its mapping and the following request parameters: template and fileType
            // TIP: Remember that the application context it's named servlet-app
            window.open("/servlet-app-day2/export?template=UsernameReport&fileType=" +fileType);
        }

    }
</script>

</body>
</html>
