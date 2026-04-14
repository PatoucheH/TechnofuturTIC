<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>
</head>
<body>
    <#import "/macros/nav.ftlh" as nav>
    <#import "/macros/title.ftlh" as title>
    <@nav.nav></@nav.nav>
    <@title.title title="Hello world"></@title.title>
    <form method="get" action="/home/hello">
        <input type="text" name="name" placeholder="Enter your name" required>
        <button type="submit">Say Hello</button>
    </form>
</body>
</html>