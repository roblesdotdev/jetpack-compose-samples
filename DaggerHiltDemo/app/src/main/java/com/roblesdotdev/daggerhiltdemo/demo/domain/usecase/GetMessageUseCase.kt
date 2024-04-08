package com.roblesdotdev.daggerhiltdemo.demo.domain.usecase

class GetMessageUseCase() {
    operator fun invoke(): String {
        return "Default message"
    }
}