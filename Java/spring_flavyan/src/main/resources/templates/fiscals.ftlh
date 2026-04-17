<#import "macros/layout.ftlh" as layout>
<@layout.myLayout title="Fiscals">
    <h1 style="text-align: center">Fiscals / Pilots / Mechanics</h1>
    <div style="display:flex; flex-direction: row; justify-content: center; align-items: flex-start; gap: 40px">
        <table>
            <thead>
            <tr>
                <td><h2>Fiscal Name</h2></td>
                <td><h2>Actions</h2></td>
            </tr>
            </thead>
            <tbody>
                <#list fiscals as fiscal>
                    <tr id="fiscal-view-${fiscal.id?c}">
                        <td>${fiscal.name!"Unknown Fiscal"}</td>
                        <td>
                            <form action="/fiscal/${fiscal.id?c}/delete" method="POST" style="display:inline">
                                <button type="submit">Delete</button>
                            </form>
                            <button type="button" onclick="startEdit('fiscal', ${fiscal.id?c})">Edit</button>
                        </td>
                    </tr>
                    <tr id="fiscal-edit-${fiscal.id?c}" style="display:none">
                        <td>
                            <form action="/fiscal/${fiscal.id?c}" method="POST" style="display:flex; gap:8px; align-items:center">
                                <input type="text" name="name" value="${fiscal.name!""}">
                                <button type="submit">Save</button>
                                <button type="button" onclick="cancelEdit('fiscal', ${fiscal.id?c})">Cancel</button>
                            </form>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <table>
            <thead>
                <tr>
                    <td><h2>Pilot Name</h2></td>
                    <td><h2>Brevet number</h2></td>
                    <td><h2>Actions</h2></td>
                </tr>
            </thead>
            <tbody>
                <#list pilots as pilot>
                    <tr id="pilot-view-${pilot.id?c}">
                        <td>${pilot.name!"Unknown Pilot"}</td>
                        <td>${pilot.brevetNumber!"Unknown brevet number"}</td>
                        <td>
                            <form action="/pilot/${pilot.id?c}/delete" method="POST" style="display:inline">
                                <button type="submit">Delete</button>
                            </form>
                            <button type="button" onclick="startEdit('pilot', ${pilot.id?c})">Edit</button>
                        </td>
                    </tr>
                    <tr id="pilot-edit-${pilot.id?c}" style="display:none">
                        <td>
                            <form action="/pilot/${pilot.id?c}" method="POST" style="display:flex; gap:8px; align-items:center">
                                <input type="text" name="name" value="${pilot.name!""}">
                                <input type="text" name="brevetNumber" value="${pilot.brevetNumber!""}">
                                <button type="submit">Save</button>
                                <button type="button" onclick="cancelEdit('pilot', ${pilot.id?c})">Cancel</button>
                            </form>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <table>
            <thead>
                <tr>
                    <td><h2>Mechanic Name</h2></td>
                    <td><h2>Actions</h2></td>
                </tr>
            </thead>
            <tbody>
                <#list mechanics as mechanic>
                    <tr id="mechanic-view-${mechanic.id?c}">
                        <td>${mechanic.name!"Unknown mechanic"}</td>
                        <td>
                            <form action="/mechanic/${mechanic.id?c}/delete" method="POST" style="display:inline">
                                <button type="submit">Delete</button>
                            </form>
                            <button type="button" onclick="startEdit('mechanic', ${mechanic.id?c})">Edit</button>
                        </td>
                    </tr>
                    <tr id="mechanic-edit-${mechanic.id?c}" style="display:none">
                        <td>
                            <form action="/mechanic/${mechanic.id?c}" method="POST" style="display:flex; gap:8px; align-items:center">
                                <input type="text" name="name" value="${mechanic.name!""}">
                                <button type="submit">Save</button>
                                <button type="button" onclick="cancelEdit('mechanic', ${mechanic.id?c})">Cancel</button>
                            </form>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>

    <br>
    <br>
    <br>
    <label for="choiceCreate">
        Choose :
        <select name="choiceCreate" id="choiceCreate" onchange="toggleForm()">
            <option value="Pilot">Pilot</option>
            <option value="Mechanic">Mechanic</option>
            <option value="Fiscal">Fiscal</option>
        </select>
    </label>

    <div id="pilotForm">
        <h2>Add a pilot :</h2>
        <form action="/pilot" method="POST">
            <label for="pilotName">
                Pilot Name :
                <input type="text" id="pilotName" name="name" placeholder="REQUIRED" required value="${pilot.name!""}">
            </label>
            <#if .vars['org.springframework.validation.BindingResult.pilot']??>
                <#assign errors = .vars['org.springframework.validation.BindingResult.pilot']>
                <#if errors.hasFieldErrors('name')>
                    <div style="color:red;">
                        ${errors.getFieldError('name').defaultMessage}
                    </div>
                </#if>
            </#if>
            <label for="brevetNumber">
                Brevet number :
                <input type="text" id="brevetNumber" name="brevetNumber" value="${pilot.brevetNumber!""}">
            </label>
            <#if .vars['org.springframework.validation.BindingResult.pilot']??>
                <#assign errors = .vars['org.springframework.validation.BindingResult.pilot']>
                <#if errors.hasFieldErrors('brevetNumber')>
                    <div style="color:red;">
                        ${errors.getFieldError('brevetNumber').defaultMessage}
                    </div>
                </#if>
            </#if>
            <button type="submit">ADD</button>
        </form>
    </div>
    <div id="mechanicForm" style="display: none;">
        <h2>Add a mechanic :</h2>
        <form action="/mechanic" method="POST">
            <#if .vars['org.springframework.validation.BindingResult.mechanic']??>
                <#assign errors = .vars['org.springframework.validation.BindingResult.mechanic']>
                <#if errors.hasFieldErrors('name')>
                    <div style="color:red;">
                        ${errors.getFieldError('name').defaultMessage}
                    </div>
                </#if>
            </#if>
            <label for="mechanicName">
                Mechanic Name :
                <input type="text" id="mechanicName" name="name" placeholder="REQUIRED" required>
            </label>
            <button type="submit">ADD</button>
        </form>
    </div>
    <div id="fiscalForm" style="display: none;">
        <h2>Add a Fiscal :</h2>
        <form action="/fiscal" method="POST">
            <#if .vars['org.springframework.validation.BindingResult.fiscal']??>
                <#assign errors = .vars['org.springframework.validation.BindingResult.fiscal']>
                <#if errors.hasFieldErrors('name')>
                    <div style="color:red;">
                        ${errors.getFieldError('name').defaultMessage}
                    </div>
                </#if>
            </#if>
            <label for="fiscalName">
                Fiscal Name :
                <input type="text" id="fiscalName" name="name" placeholder="REQUIRED" required>
            </label>
            <button type="submit">ADD</button>
        </form>
    </div>
</@layout.myLayout>

<script>
    function toggleForm() {
        const choice = document.getElementById("choiceCreate").value;
        const pilotForm = document.getElementById("pilotForm");
        const mechanicForm = document.getElementById("mechanicForm");
        const fiscalForm = document.getElementById("fiscalForm");
        if (choice === "Pilot") {
            pilotForm.style.display = "block";
            mechanicForm.style.display = "none";
            fiscalForm.style.display = "none";
        } else if(choice === "Mechanic"){
            pilotForm.style.display = "none";
            mechanicForm.style.display = "block";
            fiscalForm.style.display = "none";
        }else{
            pilotForm.style.display = "none";
            mechanicForm.style.display = "none";
            fiscalForm.style.display = "block";
        }
    }

    function startEdit(type, id) {
        document.getElementById(type + '-view-' + id).style.display = 'none';
        document.getElementById(type + '-edit-' + id).style.display = '';
    }

    function cancelEdit(type, id) {
        document.getElementById(type + '-edit-' + id).style.display = 'none';
        document.getElementById(type + '-view-' + id).style.display = '';
    }
</script>
