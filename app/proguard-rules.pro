# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn okio.**
-dontwarn com.google.errorprone.annotations.*
-dontwarn retrofit2.Platform$Java8

-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# Firebase settings start
# Add this global rule
-keepattributes Signature

# This rule will properly ProGuard all the model classes in
# the package com.yourcompany.models. Modify to fit the structure
# of your app.
-keepclassmembers class org.microdegree.com.app.exp.vo.** {
  *;
}

# Firebase settings end

###### Razor Pay configs Start ####
#-keepclassmembers class * {
#    @android.webkit.JavascriptInterface <methods>;
#}
#
#-keepattributes JavascriptInterface
#-keepattributes *Annotation*
#
#-dontwarn com.razorpay.**
#-keep class com.razorpay.** {*;}
#
#-optimizations !method/inlining/*
#
#-keepclasseswithmembers class * {
#  public void onPayment*(...);
#}
#
###### Razor Pay configs End ####


