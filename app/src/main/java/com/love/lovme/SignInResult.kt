package com.love.lovme



data class SignInResult(val data: UserData?, val errorMessage: String?) {

}

data class UserData(val userId: String, val email: String?, val ppurl: String?)