package com.example.homemedicalkit.feature_medicine.domain.useCase.medicine

data class MedicineUseCases(
    val getMedicines: GetMedicinesUseCase,
    val deleteMedicine: DeleteMedicineUseCase,
    val addMedicine: AddMedicineUseCase,
    val getMedicine: GetMedicineUseCase,
    val getAllMedicines: GetAllMedicineUseCase
)
