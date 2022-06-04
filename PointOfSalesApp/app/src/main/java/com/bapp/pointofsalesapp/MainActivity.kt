package com.bapp.pointofsalesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bapp.pointofsalesapp.feature_item.presentation.add_edit_item.AddEditItemScreen
import com.bapp.pointofsalesapp.ui.theme.PointOfSalesAppTheme
import com.bapp.pointofsalesapp.feature_item.presentation.item_list.ItemListScreen
import com.bapp.pointofsalesapp.feature_item.domain.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PointOfSalesAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.ITEM_LIST
                ) {
                    composable(Routes.ITEM_LIST) {
                        ItemListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                    composable(
                        route = Routes.ADD_EDIT_ITEM + "?itemID={itemID}",
                        arguments = listOf(
                            navArgument(name = "itemID") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditItemScreen(
                            onPopBackStack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

