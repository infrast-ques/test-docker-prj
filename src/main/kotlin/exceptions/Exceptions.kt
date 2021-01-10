package exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
object NotFoundException : RuntimeException()

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
object DataAlreadyExistException : RuntimeException()