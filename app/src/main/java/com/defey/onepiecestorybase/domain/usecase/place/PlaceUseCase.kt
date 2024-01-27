package com.defey.onepiecestorybase.domain.usecase.place

import javax.inject.Inject

data class PlaceUseCase @Inject constructor(
    val syncPlaceUseCase: SynchronizePlaceUseCase,
    val syncBandUseCase: SynchronizeBandUseCase,
    val syncBandDescriptionUseCase: SynchronizeBandDescriptionUseCase,
    val syncBandPersonageUseCase: SynchronizeBandPersonageUseCase,
    val syncBondUseCase: SynchronizeBondUseCase,
    val syncFruitUseCase: SynchronizeFruitUseCase,
    val syncSubjectUseCase: SynchronizeSubjectUseCase,
    val syncMangaUseCase: SynchronizeMangaUseCase,
    val syncPersonageUseCase: SynchronizePersonageUseCase,
    val syncPersonageDescriptionUseCase: SynchronizePersonageDescriptionUseCase,
    val syncRewardUseCase: SynchronizeRewardUseCase,
    val syncSkillUseCase: SynchronizeSkillUseCase,
    val syncWeaponUseCase: SynchronizeWeaponUseCase,
    val syncPlaceDescriptionUseCase: SynchronizePlaceDescriptionUseCase,
    val syncPlaceItemUseCase: SynchronizePlaceItemUseCase,
    val syncPlaceItemTransitUseCase: SynchronizePlaceItemTransitUseCase,
    val syncShipUseCase: SynchronizeShipUseCase,
    val syncIslandTransitUseCase: SynchronizeIslandTransitUseCase
)