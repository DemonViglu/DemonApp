package com.demonviglu.demonapp.util.data

import java.io.Serializable

class NoteConfig(data :MutableList<NoteData> = mutableListOf()){
    public var Notes = data
}

public class NoteData(data : String = "Default", state : NoteState = NoteState.NotBegin){
    public var Date : String = data
    public var NoteState : NoteState = state
    public var Content : String = ""

    public fun ToMyString():String{
        return "Date: $Date\nContent: $Content\nNoteState: $NoteState"
    }
}

public enum class NoteState{
    NotBegin,
    InProcess,
    End
}