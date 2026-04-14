<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Details bike ${bike.id}</title>
</head>
<body>
    <#import "/macros/nav.ftlh" as nav>
    <#import "/macros/title.ftlh" as title>
    <@nav.nav></@nav.nav>
    <@title.title title="Details of bike :  ${bike.id}"></@title.title>
    <div>
        <p>Brand = ${bike.brand}</p>
        <p>Model = ${bike.model}</p>
        <p>Power = ${bike.power}</p>
    </div>

</body>
</html>