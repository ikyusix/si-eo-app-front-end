package com.enigma.si_eo_app.model

import java.io.Serializable

class User (
    var ID:Int,
    var CreatedAt:String,
    var UpdatedAt:String,
    var DeletedAt:String,
    var Fullname:String,
    var Gender:String,
    var BirthDate:String,
    var Email:String,
    var Username:String,
    var Password:String,
    var UrlImage:String,
    var Status:String
): Serializable{
}

class SignIn (
    var Email:String,
    var Password:String
){
}

class ResponseSignIn (
    var Success: Boolean,
    var successMessage: String,
    var data: User
) {
}

class SignUp (
    var Fullname:String,
    var Email:String,
    var Username:String,
    var Password:String
) {
}

class ResponseSignUP (
    var message: String,
    var response: User,
    var status: Boolean
)