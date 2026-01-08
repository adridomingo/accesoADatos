# EJERCICIOS BASICOS MONGODB

        db.blog_posts.insertMany(<json>)

### EJ 1 - Consultas nivel básico:
####    1: Mostrar todos los posts
        db.blog_posts.find()
####    2: Formatear a legible
        db.blog_posts.find().pretty()
####    3: Contar
        db.blog_posts.countDocuments()
####    4: Mostrar por creador
        db.blog_posts.find({"creador" : "Ana García"})
####    5: Filtrar por más de 100 likes
        db.blog_posts.find({"likes" : {$gt : 100 }})
####    6: Filtrar por tag programación
        db.blog_posts.find({"tags" : "programacion"})
####    7: Mostrar solo 2 campos
        db.blog_posts.find({}, {creador: 1, contenido: 1})
### EJ 2 – Consultas intermedias.
####    8: Buscar posts que tengan al menos 1 comentario
        db.blog_posts.find({"comments.0" : {$exists: true}})
####    9: Buscar posts SIN comentarios
        db.blog_posts.find({"comments.0" : {$exists: false}})
        db.blog_posts.find({"comments" : {$size: 0}})
####    10: Buscar posts donde "Pedro Ruiz" haya comentado
        db.blog_posts.find({"comments.creador" : "Pedro Ruiz"})
####    11: Buscar posts con más de 3 comentarios
        db.blog_posts.find({$expr : {$gt : [{$size: "$comments"}, 3]}})
####    12: Buscar posts donde algún comentario tenga más de 5 likes
        db.blog_posts.find({ "comments.likes" : { $gt : 5 }})
####    13: Proyección: Mostrar solo creador del post y comentarios
        db.blog_posts.find({}, {creador : 1, comments: 1, _id:0})
####    14: Proyección: Mostrar post y solo el creador de cada comentario
        db.blog_posts.find({}, {creador : 1, contenido:1, "comments.creador":1, _id:0})
### EJ 3 – Consultas avanzadas, agregación
####    15. Contar el número total de comentarios en todos los posts
        db.blog_posts.aggregate([
                {$project: {numeroComentarios : {$size : "$comments"}}},
                {$group : {_id : null, numeroComentarios : {$sum: "$numeroComentarios"}}}
        ])
####    16. Encontrar el post con más comentarios
        db.blog_posts.aggregate([
                {$project: {numeroComentarios : {$size : "$comments"}}},
                {$sort : {numeroComentarios : -1}},
                {$limit : 1}
        ])
#####   Mostrando mas cosas
        db.blog_posts.aggregate([
                {$project: {creador:1, contenido:1, numeroComentarios : {$size : "$comments"}}},
                {$sort : {numeroComentarios : -1}},
                {$limit : 1}
        ])
####    17. Mostrar el promedio de likes por post
        db.blog_posts.aggregate([
                {$group : {_id : null, promedioLikes : {$avg : "$likes"} } }
        ])
####    18. Listar creadores y cuántos posts han publicado
####    19. "Desenrollar" comentarios (uno por documento)
####    20. Encontrar los usuarios más activos comentando
####    21. Posts con sus comentarios ordenados por likes (descendente)
####    22. Total de likes en posts por cada tag
####    23. Posts publicados por mes
####    24. Buscar posts con comentarios de un usuario específico y mostrar solo esos comentarios
####    25. Estadísticas completas de interacción