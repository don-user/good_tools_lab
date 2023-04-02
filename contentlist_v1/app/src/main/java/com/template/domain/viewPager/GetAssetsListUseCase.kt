package com.template.domain.viewPager

class GetAssetsListUseCase (private val repository: ViewPagerRepository) {

    fun getAssetsList(): List<String>{
        return repository.getAssetsList()
    }
}