package com.example.homemedicalkit.featureMedicine.domain.useCase.medicine

data class MedicineUseCases(
    val getMedicines: GetMedicinesUseCase,
    val deleteMedicine: DeleteMedicineUseCase,
    val addMedicine: AddMedicineUseCase,
    val getMedicine: GetMedicineUseCase,
    val getAllMedicines: GetAllMedicineUseCase
)
