package com.example.homemedicalkit.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.dataBase.DrugDao
import com.example.homemedicalkit.dataBase.Drugs
import kotlinx.coroutines.launch

class MedicalKitViewModel(val dao: DrugDao): ViewModel() {
    var newDrugName = ""
    fun addDrug(){
        viewModelScope.launch {
            val drug = Drugs()
            drug.drugName = newDrugName
            dao.insert(drug)
        }
    }
}