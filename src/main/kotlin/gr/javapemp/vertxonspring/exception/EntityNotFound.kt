package gr.javapemp.vertxonspring.exception

import io.netty.handler.codec.http.HttpResponseStatus


class EntityNotFound : RuntimeException {

    constructor() : super(HttpResponseStatus.NOT_FOUND.code().toString())

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    fun getHttpError(): HttpResponseStatus {
        return HttpResponseStatus.NOT_FOUND
    }

}
