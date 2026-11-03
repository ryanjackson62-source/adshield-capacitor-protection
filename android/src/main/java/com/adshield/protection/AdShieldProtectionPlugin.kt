package com.adshield.protection

import android.app.Activity
import android.content.Intent
import android.net.VpnService
import com.getcapacitor.ActivityCallback
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin

@CapacitorPlugin(name = "AdShieldProtection")
class AdShieldProtectionPlugin : Plugin() {

    @PluginMethod
    fun getStatus(call: PluginCall) {
        val result = JSObject()
        result.put("platform", "android")
        result.put("engineAvailable", false)
        result.put("state", "unavailable")
        result.put("blockedCount", 0)
        result.put("upstream", "not_connected")
        call.resolve(result)
    }

    @PluginMethod
    fun requestPermission(call: PluginCall) {
        val permissionIntent = VpnService.prepare(context)
        if (permissionIntent == null) {
            val result = JSObject()
            result.put("granted", true)
            call.resolve(result)
            return
        }
        startActivityForResult(call, permissionIntent, "vpnPermissionResult")
    }

    @ActivityCallback
    private fun vpnPermissionResult(call: PluginCall, result: ActivityResult) {
        val response = JSObject()
        response.put("granted", result.resultCode == Activity.RESULT_OK)
        call.resolve(response)
    }

    @PluginMethod
    fun start(call: PluginCall) {
        // Intentionally fail closed until the packet engine, signed feed verifier,
        // and foreground VPN service have passed physical-device acceptance tests.
        call.reject(
            "The AdShield DNS engine is not installed yet. Protection has not started.",
            "ENGINE_NOT_READY"
        )
    }

    @PluginMethod
    fun stop(call: PluginCall) {
        val result = JSObject()
        result.put("state", "stopped")
        call.resolve(result)
    }
}
