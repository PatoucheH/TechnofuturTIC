<#macro addressForm errors="" address="">
    <label for="country">
        <input type="text" name="address.country" id="country" placeholder="Country or Region"
               value="<#if address?has_content>${address.country!""}</#if>">
    </label>
    <label for="city">
        <input type="text" name="address.city" id="city" placeholder="City"
               value="<#if address?has_content>${address.city!""}</#if>">
    </label>
    <label for="state">
        <input type="text" name="address.state" id="state" placeholder="State"
               value="<#if address?has_content>${address.state!""}</#if>">
    </label>
    <label for="zip">
        <input type="text" name="address.zip" id="zip" placeholder="ZIP Code"
               value="<#if address?has_content>${address.zip!""}</#if>">
    </label>
    <#if errors?is_hash && errors.hasFieldErrors('address.zip')>
        <div style="color:red;">${errors.getFieldError('address.zip').defaultMessage}</div>
    </#if>
    <label for="street">
        <input type="text" name="address.street" id="street" placeholder="Street"
               value="<#if address?has_content>${address.street!""}</#if>">
    </label>
</#macro>
