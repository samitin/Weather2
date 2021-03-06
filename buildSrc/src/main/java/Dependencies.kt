import org.gradle.api.JavaVersion
object Config {
    const val application_id = "ru.samitin.weather"
    const val compile_sdk = 32
    const val min_sdk = 24
    const val target_sdk = 32
    val java_version = JavaVersion.VERSION_1_8
}
object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}
object Modules {

    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val retrofit = ":retrofit"
    const val room = ":room"

}
object Versions {
    //Design
    const val appcompat = "1.4.0"
    const val material = "1.4.0"
    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.4.3"
    const val coroutinesAndroid = "1.4.3"
    //Retrofit
    const val retrofit = "2.6.0"
    const val converterGson = "2.6.0"
    const val interceptor = "3.12.1"
    const val adapterCoroutines = "0.9.2"
    //Koin
    const val koin_version= "3.1.2"

    //Coil
    const val coil = "1.2.0"
    const val coilSvg = "1.2.0"
    //Glide
    const val glide = "4.11.0"
    const val glide_compiler = "4.11.0"
    //GlideToVectorYou
    const val glide_to_vector_you = "v2.0.0"
    //Room
    const val roomKtx = "2.3.0"
    const val runtime = "2.3.0"
    const val roomCompiler = "2.3.0"
    const val roomFirebase = "20.1.0"
    //Test
    const val jUnit = "4.12"
    const val runner = "1.2.0"
    const val espressoCore = "3.2.0"
}
object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}
object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}
object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}
object Koin {
    const val koin_android = "io.insert-koin:koin-android:${Versions.koin_version}"
    const val koin_core = "io.insert-koin:koin-core:${Versions.koin_version}"
    const val koin_compat = "io.insert-koin:koin-android-compat:${Versions.koin_version}"
    const val koin_test = "io.insert-koin:koin-test:${Versions.koin_version}"
}
object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coil_svg = "io.coil-kt:coil-svg:${Versions.coilSvg}"
}
object Glide{
    const val glide ="com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler}"
}
object GlideToVectorYou{
    const val glide_to_vector_you = "com.github.corouteam:GlideToVectorYou:${Versions.glide_to_vector_you}"
}
object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
    const val room_firebase = "com.google.firebase:firebase-messaging:${Versions.roomFirebase}"
}
object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso =
        "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
