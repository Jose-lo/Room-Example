package com.example.maroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userEntity")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,
    @ColumnInfo(name = "grade")
    val grade: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "phone")
    val phone: String = "",
    @ColumnInfo(name = "español")
    val español: String = "",
    @ColumnInfo(name = "matematicas")
    val matematicas: String = "",
    @ColumnInfo(name = "Historia")
    val Historia: String = "",
    @ColumnInfo(name = "geografia")
    val geografia: String = "",
    @ColumnInfo(name = "Ingles")
    val Ingles: String = "",
    @ColumnInfo(name = "promedio")
    val promedio: Double = 0.0
        )

@Entity(tableName = "userSecondEntity")
data class UserSecondEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,
    @ColumnInfo(name = "grade")
    val grade: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "phone")
    val phone: String = "",
    @ColumnInfo(name = "español")
    val español: String = "",
    @ColumnInfo(name = "matematicas")
    val matematicas: String = "",
    @ColumnInfo(name = "Historia")
    val Historia: String = "",
    @ColumnInfo(name = "geografia")
    val geografia: String = "",
    @ColumnInfo(name = "Ingles")
    val Ingles: String = "",
    @ColumnInfo(name = "promedio")
    val promedio: Double = 0.0
)