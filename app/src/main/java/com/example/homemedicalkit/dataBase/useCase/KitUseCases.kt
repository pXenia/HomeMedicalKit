package com.example.homemedicalkit.dataBase.useCase

data class KitUseCases(
    val getKits: GetKitsUseCase,
    val deleteKit: DeleteKitUseCase,
    val addKit: AddKitUseCase,
    val getKit: GetKitUseCase
)