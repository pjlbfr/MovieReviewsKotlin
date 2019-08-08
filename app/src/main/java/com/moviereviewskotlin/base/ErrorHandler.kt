package com.moviereviewskotlin.base

import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

class ServerError : Exception {

    private var errorMessage: String? = null
    var code = -1

    constructor(throwable: Throwable) {
        throwable.printStackTrace()
        if (throwable is HttpException) {
            try {
                val responseData = throwable.response().errorBody()!!.string()
                var data: JSONObject? = null
                try {
                    data = JSONObject(responseData)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                parseErrorBody(data)

                if (errorMessage == null) {
                    errorMessage = throwable.code().toString() + " " + throwable.message()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        if (errorMessage == null) {
            errorMessage = throwable.message
        }
    }

    constructor(jsonObject: JsonObject) : super() {
        try {
            var data: JSONObject? = null
            try {
                data = JSONObject(jsonObject.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            parseErrorBody(data)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Throws(JSONException::class)
    fun parseErrorBody(data: JSONObject?) {
        if (data != null) {
            val iterator = data.keys()
            while (iterator.hasNext()) {
                val key = iterator.next()
                when (key) {
                    "code" -> code = data.getInt(key)
                    "errorMessage" -> errorMessage = data.getString(key)
                    "errors" -> {
                        val errors = data.get("errors")
                        if (errors is JSONArray) {
                            val error = errors.getJSONObject(0)
                            parseError(error)
                        } else if (errors is JSONObject) {
                            parseError(errors)
                        } else if (errors is String) {
                            errorMessage = errors
                        }
                    }
                    else -> {
                    }
                }
            }
        }
    }

    @Throws(JSONException::class)
    private fun parseError(error: JSONObject) {
        code = error.optInt("code")
        if (code == 31) {
            errorMessage = "You are logged in on a different device"
            return
        }
        errorMessage = error.optString("title")
        if (error.has("detail")) {
            parseDetail(error.get("detail"))
        }
        val validationErrors = error.optJSONObject("validation_errors")
        val validationErrorsIterator = validationErrors!!.keys()
        try {
            errorMessage = validationErrors.getString(validationErrorsIterator.next())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Throws(JSONException::class)
    private fun parseDetail(detail: Any) {
        if (detail is JSONObject) {
            val iterator = detail.keys()
            val `object` = detail.get(iterator.next())
            if (`object` is String) {
                errorMessage = `object`
            } else if (`object` is JSONArray) {
                if (`object`.length() > 0 && `object`.get(0) is String) {
                    errorMessage = `object`.getString(0)
                }
            }
        } else {
            errorMessage = detail as String
        }
    }

    fun getErrorMessage(): String? {
        return if (errorMessage == null || errorMessage == "") {
            "error in ServerError"
        } else {
            errorMessage
        }
    }

    fun setErrorMessage(message: String) {
        this.errorMessage = message
    }
}