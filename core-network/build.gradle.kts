/*
 * Designed and developed by 2022 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.skydoves.pokedex.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id(libs.plugins.android.library.get().pluginId)
  id(libs.plugins.kotlin.android.get().pluginId)
  id(libs.plugins.kotlin.kapt.get().pluginId)
  id(libs.plugins.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
  compileSdk = Configuration.compileSdk

  defaultConfig {
    minSdk = Configuration.minSdk
    targetSdk = Configuration.targetSdk
  }
    namespace = "com.skydoves.pokedex.core.network"
}

dependencies {
  implementation(project(":core-model"))
  testImplementation(project(":core-test"))

  // coroutines
  implementation(libs.coroutines)
  testImplementation(libs.coroutines)
  testImplementation(libs.coroutines.test)

  // network
  implementation(libs.sandwich)
  implementation(libs.retrofit)
  implementation(libs.retrofit.moshi)
  implementation(libs.okhttp.interceptor)
  testImplementation(libs.okhttp.mockserver)
  testImplementation(libs.androidx.arch.core)

  // json parsing
  implementation(libs.moshi)
  ksp(libs.moshi.codegen)

  // di
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}
