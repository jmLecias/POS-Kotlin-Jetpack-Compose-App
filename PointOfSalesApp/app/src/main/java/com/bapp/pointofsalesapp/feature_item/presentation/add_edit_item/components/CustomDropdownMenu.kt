package com.bapp.pointofsalesapp.feature_item.presentation.add_edit_item.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*


@ExperimentalAnimationApi
@Composable
fun CustomDropdownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    label: String,
    textFieldValue: String,
    height: Dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onTextFieldValueChange: (String) -> Unit,
    onDropdownClick: () -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {

    val scrollState = rememberScrollState()

    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = textFieldValue,
                onValueChange = {
                    onTextFieldValueChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusEvent {
                        if (it.isFocused) onDropdownClick()
                    }
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text(label) },
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onDropdownClick()
                        },
                        modifier = Modifier
                            .scale(
                                scaleX = 1f,
                                scaleY = if (expanded) -1f else 1f
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Expand dropdown"
                        )
                    }
                },
                singleLine = true
            )

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                Box(
                    modifier = Modifier
                        .heightIn(0.dp,height)
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(5.dp)
                        )
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(
                                state = scrollState
                            )
                    ) {
                        content()
                    }
                }
            }


//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = {
//                    onDismissRequest()
//                },
//                modifier = Modifier
//                    .height(height)
//                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
//            ) {
//                content()
//            }
        }
    }
}

//@Composable
//fun CategoryDropdownMenu(
//    viewModel: AddEditItemViewModel = hiltViewModel(),
//    hint: String,
//    categories: State<List<Category>>
//){
//    var expanded by remember { mutableStateOf(false) }
//
//    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero)}
//
//    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown
//
//
//    Column(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        TextField(
//            value = viewModel.itemCategory,
//            onValueChange = {
//                viewModel.onEvent(AddEditItemEvent.OnCategoryNameChange(it))
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .onGloballyPositioned { coordinates ->
//                    //This value is used to assign to the DropDown the same width
//                    textFieldSize = coordinates.size.toSize()
//                },
//            label = { Text(hint) },
//            trailingIcon = {
//                IconButton(
//                    onClick = {
//                        expanded = !expanded
//                    }
//                ) {
//                    Icon(icon, "category dropdown",)
//                }
//            },
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = {
//                expanded = false
//            },
//            modifier = Modifier
//                .wrapContentHeight()
//                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
//        ) {
//            categories.value.forEach { category ->
//                DropdownMenuItem(onClick = {
//                    viewModel.onEvent(AddEditItemEvent.OnCategoryNameChange(category.categoryName))
//                    expanded = false
//                }) {
//                    Text(text = category.categoryName)
//                }
//            }
//        }
//    }
//}