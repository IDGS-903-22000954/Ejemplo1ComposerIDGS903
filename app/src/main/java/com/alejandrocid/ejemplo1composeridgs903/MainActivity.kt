package com.alejandrocid.ejemplo1composeridgs903

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val tarjetas: List<PersonajeTarjeta> = listOf(
    PersonajeTarjeta("ChiChi", "Esposa de Goku y madre de Gohan. Es la princesa del Monte Fry-pan siendo la hija de la fallecida reina de esta montaña y el Rey Gyuma, ella terminaría conociendo a Son Goku cuando era tan solo una niña para años más tarde durante su adolescencia ser su esposa y convertirse en la estricta pero amorosa madre de Gohan y Goten."),
    PersonajeTarjeta("Goku", "El protagonista de la serie, conocido por su gran poder y personalidad amigable."),
    PersonajeTarjeta("Launch", "Personaje que sufre cambios de personalidad al estornudar."),
    PersonajeTarjeta("Vegeta", "Príncipe de los Saiyans, inicialmente un villano, pero luego se une a los Z Fighters."),
    PersonajeTarjeta("Piccolo", "Es un namekiano que surgió tras ser creado en los últimos momentos de vida de su padre, siendo su actual reencarnación."),
    PersonajeTarjeta("Bulma", "Bulma es la protagonista femenina de la serie manga Dragon Ball y sus adaptaciones al anime Dragon Ball, Dragon Ball Z, Dragon Ball Super y Dragon Ball GT."),
    PersonajeTarjeta("Freezer", "Freezer es el tirano espacial y el principal antagonista de la saga de Freezer.")
)

data class PersonajeTarjeta(val title: String, val body: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Ejemplo1ComposerIDGS903Theme {
                Tarjeta(tarjetas)
            }
        }
    }

    @Composable
    fun Tarjeta(personajes: List<PersonajeTarjeta>){
        LazyColumn {
            items(personajes){ personaje ->
                MyPersonajes(personaje)
            }
        }
    }

    @Composable
    fun MyPersonajes(personaje: PersonajeTarjeta) {
        Card (
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){

        }
        Row (
            modifier = Modifier.padding(8.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ImagenHeroe(personaje.title)
            Personajes(personaje)
        }
    }


    @Composable
    fun Personaje(name: String, color: Color, style: TextStyle, lines:Int=Int.MAX_VALUE){
        Column {
            Text(text = name, color = color, style = style, maxLines = lines)
        }
    }

    @Composable
    fun Personajes(personaje: PersonajeTarjeta){
        var expanded by remember { mutableStateOf(false) }
        Column (
            modifier = Modifier.padding(start = 8.dp).clickable {
                expanded = !expanded
            }
        ){
            Personaje(personaje.title,
                MaterialTheme.colorScheme.primary,
                MaterialTheme.typography.headlineMedium)

            Personaje(personaje.body,
                MaterialTheme.colorScheme.onBackground,
                MaterialTheme.typography.bodyLarge,
                if (expanded) Int.MAX_VALUE else 1)
        }
    }

    @Composable
    fun ImagenHeroe(imageName: String){
        val context = LocalContext.current
        val ImageResId = remember (imageName) {
            context.resources.getIdentifier(imageName.lowercase(),"drawable", context.packageName)
        }
        Image(
            painter = painterResource(id = ImageResId),
            contentDescription = "Chi Chi",
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)

        )
    }

    @Preview
    @Composable
    fun GreetingPreview(){
        Tarjeta(tarjetas)
    }
}
