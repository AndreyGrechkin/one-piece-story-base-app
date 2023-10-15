package com.defey.onepiecestorybase.presentation.screens.detail.subject

import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.domain.model.Subject
import com.defey.onepiecestorybase.presentation.screens.UiState

data class SubjectUiState(
    val subject: Subject? = null,
    val manga: Manga? = null
) : UiState
