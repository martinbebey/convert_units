package com.developer.convertunits

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developer.convertunits.ui.theme.ConvertUnitsTheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertUnitsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun UnitConverter(){

    var enteredValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("null")}
    var selectedStartUnit by remember { mutableStateOf("meters")}
    var selectedEndUnit by remember { mutableStateOf("m")}
    var inputUnitListExpanded by remember { mutableStateOf(false) }
    var outputUnitListExpanded by remember { mutableStateOf(false) }
    var conversionRate by remember { mutableDoubleStateOf(0.01) }
    var outputConversionRate by remember { mutableDoubleStateOf(0.01) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 30.sp,
        color = Color.Red
    )

    fun convert(){
        outputValue = ((((enteredValue.toDoubleOrNull()?: 0.0) * conversionRate * 100.0) / outputConversionRate).roundToInt() / 100.0).toString()
    }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",
            style = customTextStyle
        )

        OutlinedTextField(
            value = enteredValue,
            label = { Text(text = "Enter a value")},
            onValueChange = {
                enteredValue = it
                convert()
            }
        )


        Spacer(modifier = Modifier.height(16.dp))

        Row {
            val context = LocalContext.current

            Box {
                //input unit button
                Button(onClick = {
                    Toast.makeText(context, "dropdown", Toast.LENGTH_LONG).show()
                    inputUnitListExpanded = true
                }) {
                    Text(text = selectedStartUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "arrow down")
                }

                DropdownMenu(expanded = inputUnitListExpanded, onDismissRequest = {inputUnitListExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "centimeters")},
                        onClick = {
                            selectedStartUnit = "cm"
                            inputUnitListExpanded = false
                            conversionRate = 0.01
                            convert()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "meters")},
                        onClick = {
                            selectedStartUnit = "m"
                            inputUnitListExpanded = false
                            conversionRate = 1.0
                            convert()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text(text = "feet")},
                        onClick = {
                            selectedStartUnit = "ft"
                            inputUnitListExpanded = false
                            conversionRate = 0.3048
                            convert()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text(text = "millimeters")},
                        onClick = {
                            selectedStartUnit = "mm"
                            inputUnitListExpanded = false
                            conversionRate = 0.001
                            convert()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                //output unit button
                Button(onClick = {
                    Toast.makeText(context, "dropdown", Toast.LENGTH_LONG).show()
                    outputUnitListExpanded = true
                }) {
                    Text(text = selectedEndUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "arrow down")
                }

                DropdownMenu(expanded = outputUnitListExpanded, onDismissRequest = {outputUnitListExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text(text = "centimeters")},
                        onClick = {
                            selectedEndUnit = "cm"
                            outputUnitListExpanded = false
                            outputConversionRate = 0.01
                            convert()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "meters")},
                        onClick = {
                            selectedEndUnit = "m"
                            outputConversionRate = 1.0
                            outputUnitListExpanded = false
                            convert()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text(text = "feet")},
                        onClick = {
                            selectedEndUnit = "ft"
                            outputUnitListExpanded = false
                            outputConversionRate = 0.3048
                            convert()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text(text = "millimeters")},
                        onClick = {
                            selectedEndUnit = "mm"
                            outputUnitListExpanded = false
                            outputConversionRate = 0.001
                            convert()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row{
            Text(
                text = "Result: $outputValue$selectedEndUnit",
                style = MaterialTheme.typography.headlineMedium
                )
        }
    }
}
@Preview (showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}