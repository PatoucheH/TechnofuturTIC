<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Bike</title>
</head>
<body>
    <#import "/macros/nav.ftlh" as nav>
    <#import "/macros/title.ftlh" as title>
    <@nav.nav></@nav.nav>
    <@title.title title="All bikes"></@title.title>
    <div>
        <p>Add a bike : </p>
        <form action="/bike" method="post">
            <label for="brand">Brand : </label>
            <input type="text" name="brand" id="brand">
            <label for="model">Model : </label>
            <input type="text" name="model" id="model">
            <label for="power">Power : </label>
            <input type="number" name="power" id="power">
            <button type="submit">Add bike</button>
        </form>
        <form action="/bike" method="get">
            <p>Filter Bikes</p>
            <label for="searchBrand"></label>
            <input type="text" name="searchBrand" value="${search!''}" >
            <button type="submit">Filter</button>
        </form>
    </div>
    <ul>
        <#list bikes as bike>
            <li><a href="/bike/${bike.id}">${bike.id}) ${bike.brand} - ${bike.model} with ${bike.power} hp</a></li>
        </#list>
    </ul>

</body>
</html>