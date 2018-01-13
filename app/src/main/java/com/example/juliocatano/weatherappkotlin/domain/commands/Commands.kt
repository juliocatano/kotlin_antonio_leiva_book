package com.example.juliocatano.weatherappkotlin.domain.commands

/**
 * Created by juliocatano on 11/2/17.
 */
public interface Command<out T> {
    fun execute(): T
}