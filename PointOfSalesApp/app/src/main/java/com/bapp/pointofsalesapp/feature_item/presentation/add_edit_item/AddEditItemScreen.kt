package com.bapp.pointofsalesapp.feature_item.presentation.add_edit_item

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bapp.pointofsalesapp.feature_item.domain.util.UiEvent
import com.bapp.pointofsalesapp.feature_item.presentation.add_edit_item.components.CustomDropdownMenu
import com.bapp.pointofsalesapp.feature_item.presentation.item_list.components.AnimationBox
import kotlinx.coroutines.flow.collect

@ExperimentalAnimationApi
@Composable
fun AddEditItemScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    var brandDropdownExpanded by remember { mutableStateOf(false) }

    var categoryDropdownExpanded by remember { mutableStateOf(false) }

    var unitDropdownExpanded by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    val scrollState = rememberScrollState()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()

                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditItemEvent.OnSaveItemClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check ,
                    contentDescription = "Save")
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(
                        state = scrollState
                    ),
            ) {
                Text(
                    text = state.headerText,
                    style = MaterialTheme.typography.h4,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(32.dp))

                TextField(
                    value = state.itemName,
                    onValueChange = {
                        viewModel.onEvent(AddEditItemEvent.OnItemNameChange(it))
                    },
                    label = {
                        Text(text = "Item name")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onGo = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Go,
                        capitalization = KeyboardCapitalization.Words
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomDropdownMenu(
                    expanded = brandDropdownExpanded,
                    label = "Brand" ,
                    textFieldValue = state.itemBrand,
                    height = 200.dp,
                    keyboardActions = KeyboardActions(
                        onGo = {
                            focusManager.moveFocus(FocusDirection.Down)
                            brandDropdownExpanded = false
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Go,
                        capitalization = KeyboardCapitalization.Characters
                    ),
                    onTextFieldValueChange = {
                        viewModel.onEvent(AddEditItemEvent.OnBrandNameChange(it))
                    },
                    onDropdownClick = {
                        brandDropdownExpanded = !brandDropdownExpanded
                    },
                    onDismissRequest = {
                        brandDropdownExpanded = false
                    }
                ) {
                    state.brands.forEach { brand ->
                        DropdownMenuItem(
                            onClick = {
                                brandDropdownExpanded = false
                                viewModel.onEvent(AddEditItemEvent.OnBrandNameChange(brand.brandName))
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ) {
                            Text(text = brand.brandName)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                CustomDropdownMenu(
                    expanded = categoryDropdownExpanded,
                    label = "Category" ,
                    textFieldValue = state.itemCategory,
                    height = 200.dp,
                    keyboardActions = KeyboardActions(
                        onGo = {
                            focusManager.moveFocus(FocusDirection.Down)
                            categoryDropdownExpanded = false
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Go,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    onTextFieldValueChange = {
                        viewModel.onEvent(AddEditItemEvent.OnCategoryNameChange(it))
                    },
                    onDropdownClick = {
                        categoryDropdownExpanded = !categoryDropdownExpanded
                    },
                    onDismissRequest = {
                        categoryDropdownExpanded = false
                    }
                ) {
                    state.categories.forEach { category ->
                        DropdownMenuItem(
                            onClick = {
                                categoryDropdownExpanded = false
                                viewModel.onEvent(AddEditItemEvent.OnCategoryNameChange(category.categoryName))
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ) {
                            Text(text = category.categoryName)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = state.itemCost,
                        onValueChange = {
                            viewModel.onEvent(AddEditItemEvent.OnItemCostChange(it))
                        },
                        label = {
                            Text(text = "Cost per")
                        },
                        modifier = Modifier
                            .weight(0.6f),
                        singleLine = true,
                        keyboardActions = KeyboardActions(
                            onGo = {
                                focusManager.moveFocus(FocusDirection.Right)
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Go
                        )
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    CustomDropdownMenu(
                        expanded = unitDropdownExpanded,
                        label = "Unit" ,
                        textFieldValue = state.itemUnit,
                        height = 100.dp,
                        keyboardActions = KeyboardActions(
                            onGo = {
                                focusManager.moveFocus(FocusDirection.Right)
                                unitDropdownExpanded = false
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Go,
                            capitalization = KeyboardCapitalization.Words
                        ),
                        onTextFieldValueChange = {
                            viewModel.onEvent(AddEditItemEvent.OnUnitNameChange(it))
                        },
                        onDropdownClick = {
                            unitDropdownExpanded = !unitDropdownExpanded
                        },
                        onDismissRequest = {
                            unitDropdownExpanded = false
                        },
                        modifier = Modifier.weight(0.6f)
                    ) {
                        state.units.forEach { unit ->
                            DropdownMenuItem(
                                onClick = {
                                    unitDropdownExpanded = false
                                    viewModel.onEvent(AddEditItemEvent.OnUnitNameChange(unit.unitName))
                                    viewModel.onEvent(AddEditItemEvent.OnUnitAbbreviationChange(unit.unitAbbreviation))
                                    viewModel.onEvent(AddEditItemEvent.OnUnitDropdownClick(unit))
                                }
                            ) {
                                Text(text = unit.unitName)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    TextField(
                        value = state.itemUnitAbbreviation,
                        onValueChange = {
                            viewModel.onEvent(AddEditItemEvent.OnUnitAbbreviationChange(it))
                        },
                        label = {
                            Text(text = "Abv", overflow = TextOverflow.Ellipsis)
                        },
                        modifier = Modifier.weight(0.3f),
                        singleLine = true,
                        keyboardActions = KeyboardActions(
                            onGo = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Go,
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Prices", fontSize = 20.sp)

                    IconButton(
                        onClick = {
                            viewModel.onEvent(AddEditItemEvent.OnUndoAddPriceClick)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Undo,
                            contentDescription = "Undo price add")
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                
                
                // Prices section

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    for(_priceState in viewModel.itemPricesListState) {

                        val priceState: State<PriceState> = _priceState

                        AnimationBox(
                            enter = expandVertically(),
                            exit = shrinkHorizontally()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TextField(
                                    value = priceState.value.itemPrice,
                                    onValueChange = {
                                        _priceState.value = priceState.value.copy(
                                            itemPrice = it
                                        )
                                    },
                                    label = {
                                        Text(text = "Per")
                                    },
                                    modifier = Modifier.weight(0.23f),
                                    singleLine = true,
                                    keyboardActions = KeyboardActions(
                                        onGo = {
                                            focusManager.moveFocus(FocusDirection.Right)
                                        }
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Go
                                    )
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                CustomDropdownMenu(
                                    expanded = priceState.value.isDropdownExpanded,
                                    label = "Unit" ,
                                    textFieldValue = priceState.value.unitName,
                                    height = 100.dp,
                                    keyboardActions = KeyboardActions(
                                        onGo = {
                                            focusManager.moveFocus(FocusDirection.Right)
                                            _priceState.value = priceState.value.copy(
                                                isDropdownExpanded = false
                                            )
                                        }
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Go,
                                        capitalization = KeyboardCapitalization.Words
                                    ),
                                    onTextFieldValueChange = {
                                        _priceState.value = priceState.value.copy(
                                            unitName = it
                                        )
                                    },
                                    onDropdownClick = {
                                        _priceState.value = priceState.value.copy(
                                            isDropdownExpanded = !_priceState.value.isDropdownExpanded
                                        )
                                    },
                                    onDismissRequest = {
                                        _priceState.value = priceState.value.copy(
                                            isDropdownExpanded = false
                                        )
                                    },
                                    modifier = Modifier.weight(0.3f)
                                ) {
                                    state.units.forEach { unit ->
                                        DropdownMenuItem(
                                            onClick = {
                                                _priceState.value = priceState.value.copy(
                                                    isDropdownExpanded = false,
                                                    unitName = unit.unitName,
                                                    unitAbbreviation = unit.unitAbbreviation
                                                )
                                                focusManager.moveFocus(FocusDirection.Left)
                                            }
                                        ) {
                                            Text(text = unit.unitName)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.width(4.dp))

                                TextField(
                                    value = priceState.value.unitAbbreviation,
                                    onValueChange = {
                                        _priceState.value = priceState.value.copy(
                                            unitAbbreviation = it
                                        )
                                    },
                                    label = {
                                        Text(text = "Ab")
                                    },
                                    modifier = Modifier.weight(0.18f),
                                    singleLine = true,
                                    keyboardActions = KeyboardActions(
                                        onGo = {
                                            focusManager.moveFocus(FocusDirection.Right)
                                        }
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Go,
                                    ),
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                TextField(
                                    value = priceState.value.priceRatio,
                                    onValueChange = {
                                        _priceState.value = priceState.value.copy(
                                            priceRatio = it
                                        )
                                    },
                                    label = {
                                        Text(text = "%")
                                    },
                                    modifier = Modifier.weight(0.15f),
                                    singleLine = true,
                                    keyboardActions = KeyboardActions(
                                        onGo = {
                                            focusManager.moveFocus(FocusDirection.Down)
                                        }
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Go,
                                    ),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(AddEditItemEvent.OnAddPriceClick)
                        },
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Add another price",
                        fontSize = 15.sp,
                        style = TextStyle(textDecoration = TextDecoration.Underline),
                        color = MaterialTheme.colors.primary,
                    )

                }
                for(price in viewModel.itemPricesListState){
                    Row{
                        Text(
                            price.toString()
                        )
                    }

                }




                Spacer(modifier = Modifier.height(200.dp))
            }
        }
    }
}