package com.example.homemedicalkit.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.dataBase.MedicineDao
import com.example.homemedicalkit.dataBase.Medicine
import kotlinx.coroutines.launch

class MedicalKitViewModel(val dao: MedicineDao): ViewModel() {
    var newMedicineName = ""
    fun addDrug(){
        viewModelScope.launch {
            val drug = Medicine()
            drug.medicineName = newMedicineName
            dao.insert(drug)
        }
    }
}