package com.test.mornhouse.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number_facts")
data class NumberFactData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: String,
    val fact: String
)
