package com.example.homemedicalkit.featureMedicine.presentation.medicineCard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.homemedicalkit.featureMedicine.domain.model.Medicine
import com.example.homemedicalkit.featureMedicine.domain.useCase.kit.KitUseCases
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.MedicineUseCases
import com.example.homemedicalkit.presentation.medicineCard.AddEditMedicineEvent
import com.example.homemedicalkit.presentation.medicineCard.AddEditMedicineViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class AddEditMedicineViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @MockK
    private lateinit var medicineUseCases: MedicineUseCases

    @MockK
    private lateinit var kitUseCases: KitUseCases

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: AddEditMedicineViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        savedStateHandle = SavedStateHandle()
        coEvery { kitUseCases.getKits() } returns flowOf(emptyList())

        viewModel = AddEditMedicineViewModel(medicineUseCases, kitUseCases, savedStateHandle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun MedicineIdLoadsMedicineData() = runTest {
        val medicine = Medicine(
            medicineId = 1,
            medicineName = "Aspirin",
            medicineDate = 123456789L,
            medicineDescription = "Pain reliever",
            medicineKit = 1,
            medicineNumberFew = false,
            medicineImage = "image_uri"
        )
        coEvery { medicineUseCases.getMedicine(1) } returns medicine
        savedStateHandle["medicineId"] = 1

        viewModel = AddEditMedicineViewModel(medicineUseCases, kitUseCases, savedStateHandle)

        assertThat(viewModel.medicineName.value.text).isEqualTo("Aspirin")
        assertThat(viewModel.medicineDate.value).isEqualTo(123456789L)
        assertThat(viewModel.medicineDescription.value.text).isEqualTo("Pain reliever")
        assertThat(viewModel.medicineKit.value).isEqualTo(1)
        assertThat(viewModel.medicineFew.value).isEqualTo(false)
        assertThat(viewModel.medicineImage.value.imageUri).isEqualTo("image_uri")
    }

    @Test
    fun EnteredNameUpdatesMedicineName() = runTest {
        viewModel.onEvent(AddEditMedicineEvent.EnteredName("Paracetamol"))

        assertThat(viewModel.medicineName.value.text).isEqualTo("Paracetamol")
    }

    @Test
    fun EnteredDateUpdatesMedicineDate() = runTest {
        viewModel.onEvent(AddEditMedicineEvent.EnteredDate(987654321L))

        assertThat(viewModel.medicineDate.value).isEqualTo(987654321L)
    }

    @Test
    fun EnteredDescriptionUpdatesMedicineDescription() = runTest {
        viewModel.onEvent(AddEditMedicineEvent.EnteredDescription("New description"))

        assertThat(viewModel.medicineDescription.value.text).isEqualTo("New description")
    }

    @Test
    fun EnteredMedicineFewTogglesMedicineFew() = runTest {
        val initialState = viewModel.medicineFew.value

        viewModel.onEvent(AddEditMedicineEvent.EnteredMedicineFew(true))

        assertThat(viewModel.medicineFew.value).isEqualTo(!initialState)
    }

}