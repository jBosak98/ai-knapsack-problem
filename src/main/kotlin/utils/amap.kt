package utils

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun <A, B> Iterable<A>.amap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { a:A ->
        async {
            f(a)
        }
    }.awaitAll()
}
