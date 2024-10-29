package com.test.mornhouse.presentation.mapper

import com.test.mornhouse.domain.model.NumberFact
import com.test.mornhouse.presentation.model.NumberFactUI

fun NumberFactUI.toDomain(): NumberFact {
    return NumberFact(
        id = id,
        number = number,
        fact = fact
    )
}
fun NumberFact.toUi(): NumberFactUI {
    return NumberFactUI(
        id = id,
        number = number,
        fact = fact
    )
}