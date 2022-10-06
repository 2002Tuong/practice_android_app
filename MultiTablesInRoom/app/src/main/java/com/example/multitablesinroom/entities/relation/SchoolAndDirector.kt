package com.example.multitablesinroom.entities.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.multitablesinroom.entities.Director
import com.example.multitablesinroom.entities.School

data class SchoolAndDirector (
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)