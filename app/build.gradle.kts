plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.cloudstudio.reading"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cloudstudio.reading"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //轮播
    implementation ("io.github.youth5201314:banner:2.2.3")
    //下拉刷新库
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    //图片加载库
    //implementation("com.squareup.picasso:picasso:2.8")//2.71828
    implementation("com.github.bumptech.glide:glide:4.16.0")//4.16.0

    //网络
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    //圆形图像view
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("androidx.percentlayout:percentlayout:1.0.0")
}