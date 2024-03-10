package com.example.homemedicalkit.dataBase.useCase

data class MedicineUseCases(
    val getMedicines: GetMedicineUseCase,
    val deleteMedicine: DeleteMedicineUseCase,
    val addMedicine: AddMedicineUseCase
)
