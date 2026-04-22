<#import '../tools.ftlh' as tools>

<@tools.layout title="Home index">
    <#if (flash_success)??>
        <div class="flash bg-success-50 border border-success-200 p-4 rounded-md">
            <p class="text-success-700 font-body-bold">${flash_success}</p>
        </div>
    </#if>
    <#if (flash_error)??>
        <div class="flash bg-error-50 border border-error-200 p-4 rounded-md">
            <p class="text-error-700 font-body-bold">${flash_error}</p>
        </div>
        <div class="flash bg-error-50 border border-error-200 p-4 rounded-md">
            <#list errors as error>
                <p class="text-error-700 font-body-bold">${error.field} ${error.defaultMessage}</p>
            </#list>
        </div>
    </#if>
    <form method="post" action="/" class="max-w-md space-y-4 p-4 border border-neutral-border rounded-lg shadow-sm">
        <div>
            <label for="name" class="block text-body-bold text-default-font mb-1">Name</label>
            <input type="text" name="lastname" id="name" class="w-full p-2 border border-neutral-border rounded-md focus:ring-2 focus:ring-brand-primary focus:outline-none" />
        </div>
        <div>
            <label for="firstname" class="block text-body-bold text-default-font mb-1">Firstname</label>
            <input type="text" name="firstname" id="firstname" class="w-full p-2 border border-neutral-border rounded-md focus:ring-2 focus:ring-brand-primary focus:outline-none" />
        </div>
        <button type="submit" class="bg-brand-primary text-white px-4 py-2 rounded-md hover:bg-brand-700 transition-colors duration-200 font-body-bold">Submit</button>
    </form>
</@tools.layout>