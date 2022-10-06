package com.example.multitablesinroom.entities.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.multitablesinroom.entities.School
import com.example.multitablesinroom.entities.Student

data class SchoolWithStudents(
    @Embedded val school : School,
    @Relation (
        parentColumn = "schoolName",
        entityColumn = "schoolName")
    val students : List<Student>
)