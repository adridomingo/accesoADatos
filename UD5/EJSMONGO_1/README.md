### EJS 1 MONGO - EJERCICIOS CURSOS

#### Nivel básico:
##### 1. Mostrar todos los cursos
        db.cursos.find()
##### 2. Contar cuántos cursos hay en total
        db.cursos.countDocuments()
##### 3. Buscar cursos de la categoría Programación
        db.cursos.countDocuments()
##### 4. Buscar cursos de nivel Básico
        db.cursos.countDocuments()
##### 5. Buscar cursos con precio menor a 70 euros
        db.cursos.find({"precio" : {$lt:70}});
##### 6. Buscar cursos activos
        db.cursos.find({"precio" : {$lt:70}});
##### 7. Buscar cursos del instructor Carlos Martínez
        db.cursos.find({"instructor" : "Carlos Martínez"})
##### 8. Buscar cursos que estén en español
        db.cursos.aggregate([{$match : {idiomas : "Español"}}])
##### 9. Proyección: Mostrar solo título y precio de todos los cursos.
        db.cursos.find({}, {titulo:1, precio:1, _id:0})
##### 10. Buscar cursos con duración mayor a 30 horas
        db.cursos.find({duracion_horas : {$gt:30}})

####   Nivel 2: DOCUMENTOS ANIDADOS - MÓDULOS Y LECCIONES
#####   11. Buscar cursos que tengan más de 2 módulos
        db.cursos.find({$expr : {$gt : [{$size : "$modulos"}, 2]}})
#####   12. Buscar cursos donde algún módulo dure más de 10 horas
        db.cursos.find("modulos.duracion_horas" : {$gt : 10})
#####   13. Buscar cursos que tengan lecciones de tipo proyecto
        db.cursos.find({"modulos.lecciones.tipo" : "proyecto"})
#####   14. Buscar cursos donde alguna lección dure más de 60 minutos
        db.cursos.find({"modulos.lecciones.duracion_minutos" : {$gt : 60}})
#####   15. Buscar cursos donde alguna lección haya sido completada por más de 200 personas
        db.cursos.find({"modulos.lecciones.completado_por" : {$gt : 200}})
#####   16. Proyección: Mostrar título del curso y títulos de módulos
        db.cursos.find({}, {titulo:1, "modulos.titulo":1, _id:0})
#####   17. Buscar cursos que tengan al menos una lección de tipo video
        db.cursos.find({"modulos.lecciones.tipo" : "video"})
#####   18. Buscar cursos donde el primer módulo se llame Fundamentos de Python
        db.cursos.find({"modulos.0.titulo" : "Fundamentos de Python"})
#####   19. Proyección: Mostrar curso y solo la primera lección de cada módulo
        db.cursos.aggregate([
        {
            $project: {
            _id: 0,
            titulo: 1,
            modulos: {
                $map: {
                input: "$modulos",
                as: "m",
                in: {
                    titulo: "$$m.titulo",
                    primera_leccion: { $arrayElemAt: ["$$m.lecciones.titulo", 0] }
                }
                }
            }
            }
        }
        ])
#####   20. Buscar cursos que NO tengan requisitos (array vacío)
        db.cursos.find({ requisitos: ["Ninguno"] })

####   NIVEL 3: ESTUDIANTES Y PROGRESO

#####   21. Buscar cursos donde estudie Ana López
        db.cursos.find({"estudiantes.nombre" : "Ana López"})
#####   22. Buscar cursos con al menos un estudiante que haya obtenido certificado
        db.cursos.find({"estudiantes.certificado_obtenido" : true})
#####   23. Buscar cursos donde algún estudiante tenga 100% de progreso
        db.cursos.find({"estudiantes.progreso_porcentaje" : 100})
#####   24. Buscar cursos con más de 200 estudiantes totales
        db.cursos.find({"total_estudiantes" : {$gt:200}})
#####   25. Buscar cursos donde algún estudiante se inscribió en 2024
        db.cursos.find({
            "estudiantes.fecha_inscripcion": {
                $gte: ISODate("2024-01-01T00:00:00Z"),
                $lt: ISODate("2025-01-01T00:00:00Z")
            }
        })
#####   26. Buscar cursos donde algún estudiante tenga menos del 50% de progreso
        db.cursos.find({"estudiantes.progreso_porcentaje" : {$lt : 50}})
#####   27. Proyección: Mostrar curso y solo nombres de estudiantes
        db.cursos.find({}, 
        {
            titulo:1,
            "estudiantes.nombre":1,
            _id:0
        })
#####   28. Buscar cursos donde Roberto Silva sea estudiante
        db.cursos.find({"estudiantes.nombre" : "Roberto Silva"})
#####   29. Contar cuántos estudiantes están inscritos en cada curso (proyección)
        db.cursos.find(
        {},
        {
            titulo: 1,
            num_inscritos: { $size: "$estudiantes" },
            _id: 0
        }
        )
#####   30. Buscar cursos con al menos 3 estudiantes inscritos
        db.cursos.find({
            $expr : {$gte : [{$size : "$estudiantes"}, 3]}
        })
#####   31. Buscar cursos con promedio de valoración mayor o igual a 4.5
        
#####   32. Buscar cursos con valoración perfecta (5.0)

#####   33. Buscar cursos donde Ana López haya dejado una valoración

#####   34. Buscar cursos con al menos una valoración de 5 estrellas

#####   35. Buscar cursos donde alguna valoración tenga más de 10 votos útiles

#####   36. Buscar cursos SIN valoraciones

#####   37. Proyección: Mostrar título y comentarios de valoraciones

#####   38. Buscar cursos con más de 2 valoraciones

#####   39. Buscar cursos donde alguna valoración sea menor a 4 estrellas

#####   40. Buscar cursos creados en 2024
