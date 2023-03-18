package com.sam.texttoimage.feature_home.data.model

import com.sam.texttoimage.feature_home.domain.model.Data
import com.sam.texttoimage.feature_home.domain.model.Images
import com.sam.texttoimage.feature_home.domain.model.Prompt

fun DataDto.toData(): Data {
    return Data(
        url = url
    )
}

fun ImagesDto.toImages(): Images {
    return Images(
        created = created,
        data = data
    )
}

fun PromptDto.toPrompt(): Prompt {
    return Prompt(
        n = n,
        prompt = prompt,
        size = size
    )
}

fun Prompt.toPromptDto(): PromptDto{
    return PromptDto(
        n = n,
        prompt = prompt,
        size = size
    )
}