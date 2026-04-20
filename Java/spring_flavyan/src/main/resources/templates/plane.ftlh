<#import "macros/layout.ftlh" as layout>

<@layout.myLayout title="Plane">
    <h1>Planes</h1>
    <table style="border-collapse: separate; border-spacing: 10px; border: 2px solid black;">
        <thead>
        <tr>
            <td>Plane Imma</td>
            <td>Plane Type </td>
            <td>Capacity </td>
            <td>Action </td>
        </tr>
        </thead>
        <tbody>
        <#list planes as plane>
            <tr id="plane-view-${plane.id?c}">
                <td>${plane.imma!"Unknown plane"}</td>
                <td>
                    <#if plane.planeType??>
                        ${plane.planeType.name} | ${plane.planeType.constructorName}
                    <#else>
                        Unknown plane type
                    </#if>
                </td>
                <td>
                    <#if plane.planeType?? && plane.planeType.nbPlaces??>
                        ${plane.planeType.nbPlaces}
                    <#else>
                        Unknown capacity
                    </#if>
                </td>
                <td>
                    <form action="/plane/${plane.id}/delete" method="POST" style="display:inline">
                        <button type="submit">Delete</button>
                    </form>
                    <button type="button" onclick="startEdit('plane', ${plane.id?c})">Edit</button>
                </td>
            </tr>
            <tr id="plane-edit-${plane.id?c}" style="display:none">
                <td>
                    <form action="/plane/${plane.id?c}" method="POST" style="display:flex; gap:8px; align-items:center">
                        <input type="text" name="imma" value="${plane.imma!""}">
                        <select name="planeTypeId" id="planeTypeId">
                            <#list planeTypes as planeType>
                                <option value="${planeType.id}">${planeType.name} | ${planeType.constructorName}</option>
                            </#list>
                        </select>
                        <button type="submit">Save</button>
                        <button type="button" onclick="cancelEdit('plane', ${plane.id?c})">Cancel</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <h2>Add a plane type : </h2>
    <form action="/planeType" method="POST">
        <label for="name">
            Plane type Name :
            <input type="text" id="name" name="name" placeholder="REQUIRED" required>
        </label>
        <label for="constructorName">
            Constructor name :
            <input type="text" id="constructorName" name="constructorName" placeholder="REQUIRED" required>
        </label>
        <label for="enginePower">
            Engine power :
            <input type="number" id="enginePower" name="enginePower" placeholder="REQUIRED" required>
        </label>
        <label for="nbPlaces">
            Number of place  :
            <input type="number" id="nbPlaces" name="nbPlaces" placeholder="REQUIRED" required>
        </label>
        <button type="submit">ADD</button>
    </form>
    <h2>Add a plane : </h2>
    <form action="/plane" method="POST">
        <label for="imma">
            Plane Immatriculation :
            <input type="text" id="imma" name="imma" placeholder="REQUIRED" required>
        </label>
        <label for="planeType">
            plane type name :
            <select name="planeType.id" id="planeType">
                <#list planeTypes as planeType>
                    <option value="${planeType.id}">${planeType.name}</option>
                </#list>
            </select>
        </label>
        <button type="submit">ADD </button>
    </form>

    <h2>Plane type</h2>
    <#list planeTypes as planeType>
        <div id="planeType-view-${planeType.id?c}">
            <p>${planeType.name} | ${planeType.constructorName}</p>
            <span>${planeType.nbPlaces} place and ${planeType.enginePower} hp</span>
            <form action="/planeType/${planeType.id}/delete" method="POST">
                <button type="submit">DELETE</button>
            </form>
            <button type="button" onclick="startEdit('planeType', ${planeType.id?c})">Edit</button>
            <hr>
        </div>
        <div id="planeType-edit-${planeType.id?c}" style="display:none">
            <form action="/planeType/${planeType.id?c}" method="POST" style="display:flex; gap:8px; align-items:center">
                <labell for="name">
                    plane type name :
                    <input type="text" name="name" value="${planeType.name!""}">
                </labell>
                <label for="constructorName">
                    Constructor name :
                    <input type="text" name="constructorName" value="${planeType.constructorName!""}">
                </label>
                <label for="enginePower">
                    Engine power :
                    <input type="number" name="enginePower" value="${(planeType.enginePower!0)?c}">
                </label>
                <label for="nbPlace">
                    Number of place  :
                    <input type="number" name="nbPlace" value="${(planeType.nbPlaces!0)?c}">
                </label>
                <button type="submit">Save</button>
                <button type="button" onclick="cancelEdit('planeType', ${planeType.id?c})">Cancel</button>
            </form>
        </div>

    </#list>

</@layout.myLayout>

<script>
    function startEdit(type, id) {
        document.getElementById(type + '-view-' + id).style.display = 'none';
        document.getElementById(type + '-edit-' + id).style.display = '';
    }

    function cancelEdit(type, id) {
        document.getElementById(type + '-edit-' + id).style.display = 'none';
        document.getElementById(type + '-view-' + id).style.display = '';
    }
</script>