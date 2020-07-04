package com.darc.coursera.ktx

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment

import kotlin.Suppress;

fun <T : View> Activity.bind(@IdRes idRes: Int): T {
    @Suppress("UNCHECKED_CAST")
    return findViewById(idRes)
}

fun <T : View> Activity.bindLazy(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { findViewById<T>(idRes) }
}

fun <T : View> Fragment.bindLazy(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { view!!.findViewById<T>(idRes) }
}

fun <T : View> View.bindLazy(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { findViewById<T>(idRes) }
}

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)