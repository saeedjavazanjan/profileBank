object Room {
    const val version = "2.4.3"

    const val runtime ="androidx.room:room-runtime:$version"
    const val compiler="androidx.room:room-compiler:$version"

    // optional - coroutine support for Room
   const val roomKtx ="androidx.room:room-ktx:$version"
    const val roomCoroutine="androidx.room:room-coroutines:2.1.0-alpha04"
}