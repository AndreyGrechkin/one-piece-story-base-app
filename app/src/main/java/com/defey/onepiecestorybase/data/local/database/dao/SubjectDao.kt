package com.defey.onepiecestorybase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.defey.onepiecestorybase.data.local.model.SubjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubject(subjects: List<SubjectEntity>)

    @Query("SELECT * FROM subject")
    fun getAllSubject(): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM subject WHERE placeId=:placeId")
    fun getSubjectInPlace(placeId: Int): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM subject WHERE Id=:subjectId")
    fun getSubject(subjectId: Int): Flow<SubjectEntity?>

    @Query("UPDATE subject SET isNewSubject = 0 WHERE id=:subjectId")
    suspend fun sendReadSubject(subjectId: Int)
}