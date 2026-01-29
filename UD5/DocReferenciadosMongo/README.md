# EJERCICIOS REFERENCIADOS

### EJERCICIOS

####    1. Ver todos los libros
        db.libros.find()
####    2. Buscar un autor específico ej: id 1
        db.autores.findOne({_id: 1})
####    3. Buscar los libros de un autor específico ej: autor con id 1
        db.libros.aggregate([
        {
            $lookup: {
            from: "autores",
            localField: "autor_id",
            foreignField: "_id",
            as: "info_autor"
            }
        }
        ])
####    4. Busca todos los libros pero debes mostrar toda la información del autor
        db.libros.aggregate([
        {
            $lookup: {
            from: "autores",
            localField: "autor_id",
            foreignField: "_id",
            as: "info_autor"
            }
        }
        ])
####    5. Busca todos los libros pero debes mostrar toda la información del autor, esta vez sin array
        db.libros.aggregate([
        {
            $lookup: {
            from: "autores",
            localField: "autor_id",
            foreignField: "_id",
            as: "info_autor"
            }
        },
        {$unwind : "$info_autor"}
        ])
####    6. Saca toda la información de los libros, con sus autores y sus editoriales. Sin Array
        db.libros.aggregate([
        {
            $lookup: {
                from: "autores",
                localField: "autor_id",
                foreignField: "_id",
                as: "autor"
            }
        },
        {
            $lookup: {
                from: "editoriales",
                localField: "editorial_id",
                foreignField: "_id",
                as: "editorial"
            }
        },
        {$unwind : "$autor"},
        {$unwind : "$editorial"}
        ])
####    7. Busca la información relativa a los préstamos existentes, pero debes mostrar la información de los libros, autores y usuarios.
        db.prestamos.aggregate([
            {
                $lookup : {
                    from : "libros",
                    localField : "libro_id",
                    foreignField : "_id",
                    as: "libro"
                }
            },
            {$unwind : "$libro"},
            {
                $lookup : {
                    from : "autores",
                    localField : "libro.autor_id",
                    foreignField : "_id",
                    as: "autor"
                }
            },
            {$unwind : "$autor"},
            {
                $lookup : {
                    from : "usuarios",
                    localField : "usuario_id",
                    foreignField : "_id",
                    as: "usuario"
                }
            },
            {$unwind : "$usuario"}
        ])
#### 7.1 
        db.prestamos.aggregate([
            {
                $lookup : {
                    from : "libros",
                    localField : "libro_id",
                    foreignField : "_id",
                    as: "libro"
                }
            },
            {$unwind : "$libro"},
            {
                $lookup : {
                    from : "autores",
                    localField : "libro.autor_id",
                    foreignField : "_id",
                    as: "autor"
                }
            },
            {$unwind : "$autor"},
            {
                $lookup : {
                    from : "usuarios",
                    localField : "usuario_id",
                    foreignField : "_id",
                    as: "usuario"
                }
            },
            {$unwind : "$usuario"},
            {
                $project : {
                    libro.titulo: 1,
                    autor.nombre: 1,
                    usuario.nombre: 1,
                    fecha_prestamo: 1,
                    devuelto: 1,
                    _id:0 // Para que no aque el _1
                }
            }
        ])

####    8. Encuentra los libros de autores con premio Nobel
        db.autores.aggregate([
            {
                $match: {premios: "Nobel"}
            },
            {
                $lookup : {
                    from : "libros",
                    localField : "_id",
                    foreignField : "autor_id",
                    as: "libros"
                }
            },
            {$unwind: "$libros"}
        ])
####    8.1
        db.autores.aggregate([
            {
                $match: {premios: "Nobel"}
            },
            {
                $lookup : {
                    from : "libros",
                    localField : "_id",
                    foreignField : "autor_id",
                    as: "libros"
                }
            },
            {$unwind: "$libros"},
            {
                $project: {
                    "nombre":1,
                    "libros.titulo":1,
                    "libros.año_publicacion":1,
                    _id:0
                }
            }
        ])
####    9. Muestra el total de libros por editorial
        db.libros.aggregate([
            {
                $group: {
                    _id: "$editorial_id",
                    total_libros: {$sum: 1}
                }
            },
            {
                $lookup: {
                    from : "editoriales",
                    localField : "_id",
                    foreignField : "_id",
                    as: "editorial"
                }
            },
            {$unwind: "$editorial"},
            // Mostrar solo ciertos datos,
            {
                $project: {
                    "editorial.nombre": 1,
                    "total_libros":1,
                    _id:0
                }
            }
        ])
####    10. Encuentra los préstamos no devueltos muestra la información completa.
        db.prestamos.aggregate([
            {
                $match: {devuelto: false}
            },
            {
                $lookup: {
                    from: "libros",
                    localField: "libro_id",
                    foreignField: "_id",
                    as: "libro"
                }
            },
            {$unwind: "$libro"},
            {
                $lookup: {
                    from: "autores",
                    localField: "libro.autor_id",
                    foreignField: "_id",
                    as: "autor"
                }
            },
            {$unwind: "$autor"},
            {
                $lookup: {
                    from: "usuarios",
                    localField: "usuario_id",
                    foreignField: "_id",
                    as: "usuario"
                }
            },
            {$unwind: "$usuario"},
            {
                $project: {
                    "libro.titulo":1,
                    "autor.nombre":1,
                    "usuario.nombre":1,
                    "fecha_prestamo":1,
                    "fecha_devolucion":1,
                    _id:0
                }
            }
        ])
####    11. Encuentra el autor con más libros publicados
        db.libros.aggregate([
            {
                $group: {
                    _id: "$autor_id",
                    cantidad_libros: {$sum:1}
                }
            },
            {
                $lookup: {
                    from: "autores",
                    localField: "_id",
                    foreignField: "_id",
                    as: "autor"
                }
            },
            {$unwind: "$autor"},
            {
                $project: {
                    "autor.nombre":1,
                    "cantidad_libros":1,
                    _id:0
                }
            }
        ])
####    12. Encuentra los usuarios Premium con sus préstamos activos.
        db.usuarios.aggregate([
            {
                $match: {tipo: "premium"}
            },
            {
                $lookup: {
                    from: "prestamos",
                    localField: "_id",
                    foreignField: "usuario_id",
                    as: "prestamo"
                }
            },
            {$unwind: "$prestamo"},
            {
                $match: {"prestamo.devuelto": false}
            },
            {
                $lookup: {
                    from: "libros",
                    localField: "prestamo.libro_id",
                    foreignField: "_id",
                    as: "libro"
                }
            },
            {$unwind: "$libro"}
        ])
####    13. Muestra los libros publicados antes de 1970 con su autor y editorial
        db.libros.aggregate([
            {
                $match: {año_publicacion: {$lt:1970}}
            },
            {   
                $lookup: {
                    from: "autores",
                    localField: "autor_id",
                    foreignField: "_id",
                    as: "autor"
                }
            },
            {$unwind: "$autor"},
            {   
                $lookup: {
                    from: "editoriales",
                    localField: "editorial_id",
                    foreignField: "_id",
                    as: "editorial"
                }
            },
            {$unwind: "$editorial"}
        ])
####    14. Encuentra el promedio de páginas por género
        db.libros.aggregate([
            {
                $group: {
                    _id: "$genero",
                    promedio_paginas: {$avg: "$paginas"},
                    cantidad_libros: {$sum: 1}
                }
            }
        ])
####    15. Muestra los autores argentinos con sus libros y total de páginas
        db.autores.aggregate([
            {
                $match: {nacionalidad: "Argentino"}
            },
            {
                $lookup: {
                    from: "libros",
                    localField: "_id",
                    foreignField: "autor_id",
                    as: "libro"
                }
            },
            {$unwind: "$libro"},
            {
                $project: {
                    nombre:1,
                    cantidad_libros: {$size: "$libro"},
                    total_paginas: {$sum: "$libro.paginas"}
                }
            }
        ])
####    16. Encuentra los usuarios con más de 1 préstamo tanto los activos o los históricos
        db.prestamos.aggregate([
            {
                $group: {
                    _id: "$usuario_id",
                    total_prestamos: {$sum:1},
                    prestamos_activos: {
                        $sum: {$cond: [{$eq: ["$devuelto", false]}, 1,0]}
                    }
                }
            },
            {
                $match: {total_prestamos: {$gt: 1}}
            },
            {
                $lookup: {
                    from: "usuarios",
                    localField: "_id",
                    foreignField: "_id",
                    as: "usuario"
                }
            },
            {$unwind: "$usuario"},
            {
                $project: {
                    "usuario.nombre":1,
                    "usuario.email":1,
                    "total_prestamos":1,
                    "prestamos_activos":1,
                    "_id":0
                }
            },
            {
                $sort: {total_prestamos:-1}
            }
        ])
####    17. Muestra la editorial con el libro más largo y su autor
        db.libros.aggregate([
            {
                $sort:{paginas: -1}
            },
            {
                $limit: 1
            },
            {
                $lookup: {
                    from: "autores",
                    localField: "autor_id",
                    foreignField: "_id",
                    as: "autor"
                }
            },
            {
                $unwind: "$autor"
            },
            {
                $lookup: {
                    from: "editoriales",
                    localField: "editorial_id",
                    foreignField: "_id",
                    as: "editorial"
                }
            },
            {
                $unwind: "$editorial"
            },
            {
                $project: {
                    "titulo":1,
                    "paginas":1,
                    "autor.nombre":1,
                    "autor.nacionalidad":1,
                    "editorial.nombre":1,
                    "editorial.pais":1,
                    "_id":0,
                }
            },
        ])
####    18. Encuentra los libros que nunca han sido prestados.
        db.libros.aggregate([
            {
                $lookup: {
                    from: "prestamos",
                    localField: "_id",
                    foreignField: "libro_id",
                    as: "prestamo"
                }
            },
            {
                $match: {
                    prestamo:{$size:0}
                }
            },
            {
                $lookup: {
                    from: "autores",
                    localField: "autor_id",
                    foreignField: "_id",
                    as: "autor"
                }
            },
            {
                $unwind: "$autor"
            },
            {
                $project: {
                    "titulo":1,
                    "autor.nombre":1,
                    "año_publicacion":1,
                    "_id":0
                }
            }
        ])