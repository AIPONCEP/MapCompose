package com.example.mapcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mapcompose.ui.theme.MapComposeTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * MapCompose:MainActivity
 * @author: Ana Isabel Ponce Pérez
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyGoogleMaps()
                }
            }
        }
    }
}

/**
 * fun MyGoobleMaps
 * integrar Google Maps enla aplicación
 */
@Composable
fun MyGoogleMaps() {
    // Define la ubicación del marcador en el mapa
    val marker = LatLng(28.270833,-16.63916)
    // Define y recuerda el estado de la posición de la cámara del mapa
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 10f) // Configura la posición inicial de la cámara
    }
    // Define y recuerda el estado de las propiedades del mapa (tipo de mapa, etc.)
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.HYBRID ))
    }
    // Define y recuerda el estado de la configuración de la interfaz de usuario del mapa (controles de zoom, etc.)
    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
    }
    // Crea un componente GoogleMap con los parámetros configurados
    GoogleMap(
        modifier = Modifier.fillMaxSize(), // Utiliza el tamaño disponible
        cameraPositionState = cameraPositionState,  // Usa el estado de la posición de la cámara
        properties = properties, // Usa el estado de las propiedades del mapa
        uiSettings = uiSettings // Usa el estado de la configuración de la interfaz de usuario del mapa
        )
}