<#import "nav.ftlh" as nav>
<#macro myLayout title="Aéroport V">
    <!DOCTYPE html>
    <html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
    </head>
    <body>
        <@nav.navBar></@nav.navBar>
        <#nested>
    </body>
    </html>
</#macro>
