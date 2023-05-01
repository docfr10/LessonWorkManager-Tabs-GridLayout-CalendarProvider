package com.example.lesson_workmanager_tabs_gridlayout_calendarprovider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GridLayout() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(100) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Example grid layout")
            }
        })

        LazyHorizontalGrid(rows = GridCells.Fixed(3), content = {
            items(100) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Example grid layout 2")
            }
        })
    }
}
