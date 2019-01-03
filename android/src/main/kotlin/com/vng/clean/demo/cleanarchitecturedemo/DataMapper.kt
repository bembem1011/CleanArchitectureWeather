package com.vng.clean.demo.cleanarchitecturedemo

interface DataMapper<in Input, out Output> {
    fun transform(input: Input): Output
}