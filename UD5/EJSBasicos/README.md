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
        db.blog_posts.aggregate([
                { $group : { _id:"$creador", numeroPost : {$count: {} } } }
        ])
##### Ordenar por numero de post
        db.blog_posts.aggregate([
                { $group : { _id:"$creador", numeroPost : {$count: {} } } },
                { $sort: {numeroPost: -1} }
        ])
####    19. "Desenrollar" comentarios (uno por documento)
        db.blog_posts.aggregate([
                {$unwind : "$comments"},     
        ])
##### Mostrar tambien los demas datos
        db.blog_posts.aggregate([
                {$unwind : "$comments"}, 
                {$project : {
                        post_creador : "$creador",
                        post_contenido : "$contenido",
                        comentario_creador : "$comments.creador",
                        comentario_texto : "$comments.comentario",
                        comentario_fecha : "$comments.fecha"
                        } }    
        ])
####    20. Encontrar los usuarios más activos comentando
        db.blog_posts.aggregate([
                {$unwind : "$comments"},
                {$group : {
                        _id : "$comments.creador",
                        numeroComentarios : {$count : {}}
                }},
                {$sort : {numeroComentarios : -1}}  
        ])
####    21. Posts con sus comentarios ordenados por likes (descendente)
        db.blog_posts.aggregate([
                {$unwind : "$comments"},
                {$sort : { "$comments.likes" : -1}}     
        ])
####    22. Total de likes en posts por cada tag
        db.blog_posts.aggregate([
                {$unwind : "$tags"},
                {$group : {
                        _id: "$tags",
                        total_likes: {$sum: "$likes"}
                }}  
        ])
##### Añadiendo el nº de posts con cada etiqueta
        db.blog_posts.aggregate([
                {$unwind : "$tags"},
                {$group : {
                        _id: "$tags",
                        total_likes: {$sum: "$likes"},
                        numeroPost: {$count : {}}
                }}  
        ])
####    23. Posts publicados por mes
        db.blog_posts.aggregate([
                {$project : {
                        mes: {$month: "$fecha"},
                        año: {$year: "$fecha"},
                }
                },
                {
                        $group: {
                                _id: {año: "$año", mes: "$mes"},
                                cantidad: {$count: {}}
                        }
                },
                {$sort: {"_id.año" : 1, "_id.mes": 1}}

        ])

####    24. Buscar posts con comentarios de un usuario específico y mostrar solo esos comentarios
        db.blog_posts.aggregate([
                {
                        $match:{"comments.creador":"Ana García"}
                },
                {
                        $project: {
                                creador : 1,
                                contenido: 1,
                                comments: {
                                        $filter: {
                                                input: "$comments",
                                                as : "coment",
                                                cond: {
                                                        $eq: ["$$comments.creador", "Ana García"]
                                                }
                                        }
                                }
                        }
                }
                 
        ])
        

####    25. Estadísticas completas de interacción
        db.blog_posts.aggregate([
                {$project : {
                        creador: 1,
                        likes: 1,
                        numeroComentarios: {$size: "$comments"},
                        likesEnComentarios: {$sum: "$comments.likes"}
                }},
                {
                        $addFields: {
                                total_interaciones: {
                                        $add: ["$likes", "$numeroComentarios", "$likesEnComentarios"]
                                }
                        }
                },
                {$sort : {total_interaciones : -1}}
        ])

### Ejercicio 4: Actualización de datos
####    26. Añadir un nuevo comentario a un post específico ej: al post 8 (❌)
        db.blog_posts.updateOne(
                {_id:8},
                {$push : {
                        id_comment : 1111,
                        creador: "Usuario",
                        comentario: "Definicion perfecta",
                        fecha: new Date(),
                        likes: 0
                }}
        )
####    27. Incrementa los likes del post 1
        db.blog_posts.updateOne(
                {_id:1},
                {$inc: {likes: 1}}
        )
####    28. Incrementar likes de un comentario específico ej: post 10, comentario 1
        db.blog_posts.updateOne(
                {_id:10, "comments.id_comment": 1},
                {$inc: {"comments.$.likes": 1}}
        )
####    29. Añadimos Japon, aventura ya esta
        db.blog_posts.updateOne(
                {_id:1},
                {$addToSet : {tags: "Japon"}}
        )
####    30. Eliminar un comentario específico (post 3, comentario 2)
        db.blog_posts.updateOne(
                {_id:3},
                {$pull: {comments: {id_comment: 2}}}
        )
