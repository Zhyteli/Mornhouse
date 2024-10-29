package com.test.mornhouse.data.mapper

import com.test.mornhouse.data.model.NumberFactData
import com.test.mornhouse.domain.model.NumberFact

fun NumberFactData.toDomain(): NumberFact {
    return NumberFact(
        id = id,
        number = number,
        fact = fact
    )
}
fun NumberFact.toData(): NumberFactData {
    return NumberFactData(
        id = id,
        number = number,
        fact = fact
    )
}