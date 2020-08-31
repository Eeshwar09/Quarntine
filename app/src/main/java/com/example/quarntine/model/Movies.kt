package com.example.quarntine.model

import java.io.Serializable

data class Movies (
    var name:String ?= null,
    var bio:String ?= null,
    var image:String ?= null
):Serializable