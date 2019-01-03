package com.vng.clean.demo.cleanarchitecturedemo.data.model.mapper

interface Mapper<in Input, out Output> {
    fun transform(input: Input): Output
}