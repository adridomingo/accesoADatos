###    Consultas Básicas
####    1.- Ver todos los cursos disponibles
        db.cursos.find()

####    2.- Buscar un estudiante específico por su _id (ejemplo: id 1)
        db.estudiantes.find({_id:1})

####    3.- Buscar todos los cursos de la categoría Programación
        db.cursos.find({categoria:"Programación"})

####    4.- Buscar estudiantes de nivel principiante
        db.estudiantes.find({nivel:"principiante"})

####    5.- Mostrar cursos con precio menor a 60€
        db.cursos.find({precio : {$lt:60}})

####    6.- Encontrar cursos con calificación mayor o igual a 4.5
        db.cursos.find({calificacion : {$gte: 4.5}})

####    7.- Buscar estudiantes de Madrid
        db.estudiantes.find({ciudad:"Madrid"})

####    8.- Mostrar solo el título y precio de todos los cursos
        db.cursos.find({}, {_id:0, titulo:1, precio:1})

###    CONSULTAS INTERMEDIAS (Con $lookup básico)
####    9.- . Mostrar todos los cursos con la información completa de su instructor
        db.cursos.aggregate([
            {
                $lookup: {
                    from: "instructores",
                    localField: "instructor_id",
                    foreignField: "_id",
                    as: "instructor"
                }
            }
        ])

####    10.- Mostrar las matrículas con información del curso (sin array)
        db.cursos.aggregate([
            {
                $lookup: {
                    from: "matriculas",
                    localField: "_id",
                    foreignField: "curso_id",
                    as: "matricula"
                }
            },
            {$unwind: "$matricula"}
        ])

####    11.- Mostrar información completa de matrículas: estudiante, curso e instructor
        db.matriculas.aggregate([
            {
                $lookup: {
                    from: "estudiantes",
                    localField: "estudiante_id",
                    foreignField: "_id",
                    as: "estudiante"
                }
            },
            {$unwind: "$estudiante"},
            {
                $lookup: {
                    from: "cursos",
                    localField: "curso_id",
                    foreignField: "_id",
                    as: "curso"
                }
            },
            {$unwind: "$curso"},
            {
                $lookup: {
                    from: "instructores",
                    localField: "curso.instructor_id",
                    foreignField : "_id",
                    as: "instructor"
                }
            },
            {$unwind : "$instructor"}
        ])

####    12.- . Buscar cursos de instructores españoles
        db.cursos.aggregate([
            {
                $lookup: {
                    from: "instructores",
                    localField: "instructor_id",
                    foreignField : "_id",
                    as: "instructor"
                }
            },
            {$unwind : "$instructor"},
            {
                $match: {
                    "instructor.pais": "España"
                }
            }
        ])

###    CONSULTAS AVANZADAS (Con agregaciones y filtros complejos)
####    13.- Contar el total de cursos por categoría
        db.cursos.aggregate([
            {
                $group: {
                    _id: "$categoria",
                    contadas : { $sum : 1}
                }
            },
            {
                $project : {
                    _id:1,
                    contadas:1
                }
            }
        ])

####    14.- Calcular el precio promedio de cursos por nivel
        db.cursos.aggregate([
            {
                $group: {
                    _id: "$nivel",
                    precios: { $avg: "$precio" }
                }
            },
            {
                $project: {
                    _id:1,
                    precios:1
                }
            }
        ])

####    15.- Encontrar el instructor con más cursos publicados
        db.cursos.aggregate([
            {
                $group : {
                    _id:"$instructor_id",
                    cursos_contados: { $sum:1}
                }
            },
            {
                $sort: { cursos_contados : -1}
            },
            {
                $limit : 1
            },
            {
                $lookup: {
                    from: "instructores",
                    localField: "_id",
                    foreignField : "_id",
                    as: "instructor"
                }
            },
            {$unwind: "$instructor"},
            {
                $project : {
                    _id:0,
                    nombre:"$instructor.nombre",
                    cursos_contados:1
                }
            }
        ])

####    16.- Mostrar matrículas completadas con toda la información
        db.matriculas.aggregate([
            {
                $match: {
                    completado : true
                }
            },
            {
                $lookup: {
                    from: "estudiantes",
                    localField: "estudiante_id",
                    foreignField: "_id",
                    as: "estudiante"
                }
            },
            {$unwind: "$estudiante"},
            {
                $lookup: {
                    from: "cursos",
                    localField: "curso_id",
                    foreignField: "_id",
                    as: "curso"
                }
            },
            {$unwind: "$curso"},
            {
                $lookup: {
                    from: "instructores",
                    localField: "curso.instructor_id",
                    foreignField : "_id",
                    as: "instructor"
                }
            },
            {$unwind : "$instructor"},
        ])

####    17.- Encontrar estudiantes con más de una matrícula activa (no completada)
        db.matriculas.aggregate([
            {
                $match: {
                    completado : false
                }
            },
            {
                $group : {
                    _id: "$estudiante_id",
                    conteo: {$sum : 1}
                }
            },
            {
                $match : {
                    conteo : {$gt:1}
                }
            },
            {
                $lookup: {
                    from: "estudiantes",
                    localField: "_id",
                    foreignField: "_id",
                    as: "estudiante"
                }
            },
            {$unwind: "$estudiante"},
        ])

####    18.- Calcular el progreso promedio de los estudiantes por curso
        db.matriculas.aggregate([
            {
                $group: {
                    _id: "$curso_id",
                    promedio_progreso: {$avg: "$progreso"}
                }
            },
            {
                $lookup: {
                    from: "cursos",
                    localField: "_id",
                    foreignField: "_id",
                    as: "curso"
                }
            },
            {$unwind: "$curso"},
            {
                $project: {
                    _id:0,
                    "curso.titulo":1,
                    "promedio_progreso":1
                }
            }
        ])

####    19.- Mostrar cursos con duración mayor a 50 horas con su instructor
        db.cursos.aggregate([
            {
                $match: {
                    "duracion_horas" : {$gt: 50}
                }
            },
            {
                $lookup: {
                    from: "instructores",
                    localField: "instructor_id",
                    foreignField: "_id",
                    as: "instructor"
                }
            },
            {$unwind: "$instructor"},
            {
                $project: {
                    _id:0,
                    "titulo":1,
                    "instructor.nombre":1
                }
            }
        ])

####    20.- Encontrar cursos que nunca han tenido matrículas
        db.cursos.aggregate([
            {
                $lookup: {
                    from: "matriculas",
                    localField: "_id",
                    foreignField: "curso_id",
                    as: "matricula"
                }
            },
            {
                $match : {matricula : {$size : 0}}
            },
            {
                $project: {
                    _id:0,
                    "titulo":1,
                }
            }
        ])

####    21.- Mostrar estudiantes de nivel intermedio con sus cursos activos
        db.estudiantes.aggregate([
            {
                $match: { nivel: "intermedio" }
            },
            {
                $lookup: {
                    from: "matriculas",
                    localField: "_id",
                    foreignField: "estudiante_id",
                    as: "matriculas"
                }
            },
            {
                $unwind: "$matriculas"
            },
            {
                $match: { "matriculas.completado": false }
            },
            {
                $lookup: {
                    from: "cursos",
                    localField: "matriculas.curso_id",
                    foreignField: "_id",
                    as: "curso"
                }
            },
            { $unwind: "$curso" },
            {
                $project: {
                    "nombre": 1,
                    "ciudad": 1,
                    "curso.titulo": 1,
                    "matriculas.progreso": 1,
                    _id: 0
                }
            }
        ])

####    22.- Calcular el total de horas de cursos ofrecidos por cada instructor


####    23.- Encontrar estudiantes con todas sus matrículas completadas


####    24.- Mostrar el curso más caro de cada categoría con su instructor


####    25.- Calcular los ingresos totales potenciales por curso


###    CONSULTAS AVANZADAS (Agregaciones complejas)
####    26.- Ranking de estudiantes por total de cursos completados con calificación 5

####    27.- Encontrar instructores con calificación promedio mayor a 4.5 y más de 2 cursos

####    28.- Analizar la tasa de finalización por ciudad de estudiantes

####    29.- Mostrar cursos con más de 2 matrículas y progreso promedio superior al 70%

####    30.- Crear un informe completo de rendimiento de instructores
