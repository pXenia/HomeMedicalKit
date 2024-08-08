package com.example.homemedicalkit.feature_medicine.presentation.kits_screen.kit_dialog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.homemedicalkit.feature_medicine.domain.model.Kit
import com.example.homemedicalkit.feature_medicine.domain.useCase.kit.KitUseCases
import com.example.homemedicalkit.presentation.kitsScreen.kitDialog.AddEditKitEvent
import com.example.homemedicalkit.presentation.kitsScreen.kitDialog.AddEditKitViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AddEditKitViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @MockK
    private lateinit var kitUseCases: KitUseCases

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: AddEditKitViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        savedStateHandle = SavedStateHandle()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun KitIdLoadsKitData() = runTest {
        val kit = Kit(kitId = 1, kitName = "First Aid", kitColor = 123456)
        coEvery { kitUseCases.getKit(1) } returns kit
        savedStateHandle["kitId"] = 1

        viewModel = AddEditKitViewModel(kitUseCases, savedStateHandle)

        assertThat(viewModel.kitName.value).isEqualTo("First Aid")
        assertThat(viewModel.kitColor.value).isEqualTo(123456)
    }

    @Test
    fun EnteredNameUpdatesKitName() = runTest {
        viewModel = AddEditKitViewModel(kitUseCases, savedStateHandle)

        viewModel.onEvent(AddEditKitEvent.EnteredName("Emergency Kit"))

        assertThat(viewModel.kitName.value).isEqualTo("Emergency Kit")
    }

    @Test
    fun ChangeColorUpdatesKitColor() = runTest {
        viewModel = AddEditKitViewModel(kitUseCases, savedStateHandle)

        viewModel.onEvent(AddEditKitEvent.ChangeColor(654321))

        assertThat(viewModel.kitColor.value).isEqualTo(654321)
    }
}
