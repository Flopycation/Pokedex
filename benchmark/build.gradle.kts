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
  id(libs.plugins.android.test.get().pluginId)
  id(libs.plugins.kotlin.android.get().pluginId)
}

android {
  compileSdk = Configuration.compileSdk

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "11"
  }

  defaultConfig {
    minSdk = Configuration.minSdk
    targetSdk = Configuration.compileSdk
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    // This benchmark buildType is used for benchmarking, and should function like your
    // release build (for example, with minification on). It"s signed with a debug key
    // for easy local/CI testing.
    create("benchmark") {
      isDebuggable = true
      signingConfig = getByName("debug").signingConfig
      matchingFallbacks += listOf("release")
    }
  }

  targetProjectPath = ":app"
    namespace = "com.github.skydoves.benchmark"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
  implementation(libs.profileinstaller)
  implementation(libs.macrobenchmark)
  implementation(libs.uiautomator)
  implementation(libs.android.test.runner)
}

androidComponents {
  beforeVariants(selector().all()) {
    it.enabled = it.buildType == "benchmark"
  }
}
