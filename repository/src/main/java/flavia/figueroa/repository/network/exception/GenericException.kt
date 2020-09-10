package flavia.figueroa.repository.network.exception

import java.lang.Exception

class GenericException(var msg: String, var code: Int, var detail: String) : Exception(detail)