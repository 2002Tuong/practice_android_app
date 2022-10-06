package com.example.multitablesinroom

import androidx.room.*
import com.example.multitablesinroom.entities.*
import com.example.multitablesinroom.entities.relation.SchoolAndDirector
import com.example.multitablesinroom.entities.relation.SchoolWithStudents
import com.example.multitablesinroom.entities.relation.StudentWithSubjects
import com.example.multitablesinroom.entities.relation.SubjectWithStudents

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef )

    @Transaction
    @Query("select * from school where schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName : String) : List<SchoolAndDirector>

    @Transaction
    @Query("select * from school where schoolName = :schoolName ")
    suspend fun getSchoolWithStudent(schoolName : String) : SchoolWithStudents

    @Transaction
    @Query("select * from student where studentName = :studentName ")
    suspend fun getSubjectsOfStudent(studentName : String) : List<StudentWithSubjects>

    @Transaction
    @Query("select * from subject where subjectName = :subjectName ")
    suspend fun getStudentsOfSubject(subjectName : String) : List<SubjectWithStudents>

    @Query("select School.schoolName from director, School where director.directorName = 'Jack Goff'" +
            " and School.schoolName = director.schoolName")
    suspend fun temp() : List<String>
}