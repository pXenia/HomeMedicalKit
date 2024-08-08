package com.example.homemedicalkit.feature_medicine.domain.useCase.kit

import com.example.homemedicalkit.feature_medicine.domain.repository.KitRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DeleteKitUseCaseTest {

    @Mock
    private lateinit var repository: KitRepository

    private lateinit var deleteKitUseCase: DeleteKitUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        deleteKitUseCase = DeleteKitUseCase(repository)
    }

    @Test
    fun DeleteWithCorrectID() = runTest {
        val kitId = 123
        deleteKitUseCase(kitId)
        verify(repository).delete(kitId)
    }
}