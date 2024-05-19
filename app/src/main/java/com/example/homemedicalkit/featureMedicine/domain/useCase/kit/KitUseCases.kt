package com.example.homemedicalkit.featureMedicine.domain.useCase.kit

data class KitUseCases(
    val getKits: GetKitsUseCase,
    val deleteKit: DeleteKitUseCase,
    val addKit: AddKitUseCase,
    val getKit: GetKitUseCase,
)