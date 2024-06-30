package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){
    var inputValue by remember{mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    val conversionFactor = remember{ mutableStateOf(1.0) }
    val oconversionFactor = remember{ mutableStateOf(1.0) }

    fun convertUnits()
    {
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = (inputValueDouble*conversionFactor.value*100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //here all the UI element will be stacked below each other




        Text("Unit Converter")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = inputValue,
            onValueChange = {
                            inputValue = it
                convertUnits()
            },
            label = {Text("Enter Value")})


        Spacer(modifier = Modifier.height(16.dp))


        Row {

            Box {
                Button(onClick = { iExpand = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand = false }) {
                    DropdownMenuItem(text = { Text("Centimeter") },
                        onClick = {
                            inputUnit = "Centimeter"
                            iExpand = false
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Inch") },
                        onClick = {
                            inputUnit = "Inch"
                            iExpand = false
                            conversionFactor.value = 0.0254
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Meter") },
                        onClick = {inputUnit = "Meter"
                            iExpand = false
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Feet") },
                        onClick = {
                            inputUnit = "Feet"
                            iExpand = false
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })

                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpand = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {
                    DropdownMenuItem(text = { Text("Centimeter") },
                        onClick = {
                            outputUnit = "Centimeter"
                            oExpand = false
                            oconversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Inch") },
                        onClick = {
                            outputUnit = "Inch"
                            oExpand = false
                            oconversionFactor.value = 0.0254
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Meter") },
                        onClick = {
                            outputUnit = "Meter"
                            oExpand = false
                            oconversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Feet") },
                        onClick = {
                            outputUnit = "Feet"
                            oExpand = false
                            oconversionFactor.value = 0.3048
                            convertUnits()
                        })

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result : $outputValue")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}