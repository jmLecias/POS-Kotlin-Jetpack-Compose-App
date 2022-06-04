package com.bapp.pointofsalesapp.feature_item.domain.use_case

import com.bapp.pointofsalesapp.feature_item.domain.model.Brand
import com.bapp.pointofsalesapp.feature_item.domain.model.InvalidUnitException
import com.bapp.pointofsalesapp.feature_item.domain.model.SubUnit
import com.bapp.pointofsalesapp.feature_item.domain.repository.AppRepository
import kotlin.jvm.Throws

class AddSubUnit(
    private val repository: AppRepository
) {
    @Throws(InvalidUnitException::class)
    suspend operator fun invoke(subUnit: SubUnit) {

        if(subUnit.subUnitAbbreviation.length > 3) {
            throw InvalidUnitException("${subUnit.subUnitName} abbreviation is too long")
        }
        return repository.insertSubUnit(subUnit)
    }
}