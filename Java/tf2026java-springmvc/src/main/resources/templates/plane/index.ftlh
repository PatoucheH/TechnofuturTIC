<#import '../tools.ftlh' as tool>

<@tool.layout title="Plane - list">
    <form action="/planes" method="get">
        <input type="text" name="imma" placeholder="imma">
        <input type="text" name="owner" placeholder="owner"/>
        <input type="submit" value="Search">
    </form>

    <table>
        <thead>
        <tr>
            <th>#ID</th>
            <th>Imma</th>
            <th>Owner</th>
            <th>Type</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list planes as plane>
            <tr>
                <td>${plane.id}</td>
                <td>${plane.imma}</td>
                <td>${plane.owner.name}</td>
                <td>${plane.planeType.name}</td>
                <td>
                    <a href="/plane/edit/${plane.id}">Edit</a>
                    <a href="/plane/delete/${plane.id}">Delete</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@tool.layout>