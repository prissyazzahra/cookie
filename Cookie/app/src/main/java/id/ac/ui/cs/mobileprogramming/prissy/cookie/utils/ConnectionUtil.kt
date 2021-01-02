package id.ac.ui.cs.mobileprogramming.prissy.cookie.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.*

class ConnectionUtil {
    fun isConnected(context: Context): Boolean {
        val connectivityManager: ConnectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        var netInternet = false
        var transportCellular = false
        var transportWifi = false
        var transportEthernet = false
        var transportVpn = false

        connectivityManager.allNetworks.forEach { network ->
            network?.let {
                connectivityManager.getNetworkCapabilities(it)
                    ?.let { networkCapabilities ->
                        netInternet =
                            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        transportCellular =
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        transportWifi =
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        transportEthernet =
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                        transportVpn =
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                    }
            }
        }

        return netInternet ||
                transportWifi || transportCellular ||
                transportEthernet || transportVpn
    }
}