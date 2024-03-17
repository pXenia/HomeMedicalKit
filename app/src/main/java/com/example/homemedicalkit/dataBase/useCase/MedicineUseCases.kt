package com.example.homemedicalkit.dataBase.useCase

data class MedicineUseCases(
    val getMedicines: GetMedicinesUseCase,
    val deleteMedicine: DeleteMedicineUseCase,
    val addMedicine: AddMedicineUseCase,
    val getMedicine: GetMedicineUseCase,
)
