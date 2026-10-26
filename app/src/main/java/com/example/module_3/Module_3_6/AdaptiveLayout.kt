package com.example.module_3.Module_3_6

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.module_3.Module_3_1.ItemCard
import com.example.module_3.Module_3_1.ListItem
import com.example.module_3.Module_3_1.sampleItems
import com.example.module_3.ui.theme.Module_3Theme

@Composable
fun AdaptiveLayout(
    widthSizeClass: WindowWidthSizeClass,
    itemsList: List<ListItem> = sampleItems
) {
    // Храним id, а не сам ListItem: id переживает поворот экрана через rememberSaveable
    var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
    val selectedItem = itemsList.find { it.id == selectedId }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                OneColumnLayout(
                    items = itemsList,
                    selectedItem = selectedItem,
                    onItemSelected = { selectedId = it.id },
                    onBack = { selectedId = null },
                    modifier = modifier.fillMaxSize()
                )
            }
            WindowWidthSizeClass.Medium,
            WindowWidthSizeClass.Expanded -> {
                TwoColumnLayout(
                    items = itemsList,
                    selectedItem = selectedItem,
                    onItemSelected = { selectedId = it.id },
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }
}

// Телефон: показываем либо список, либо подробности
@Composable
fun OneColumnLayout(
    items: List<ListItem>,
    selectedItem: ListItem?,
    onItemSelected: (ListItem) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (selectedItem == null) {
        ItemsColumn(
            items = items,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
    } else {
        // Системная кнопка «Назад» тоже возвращает к списку
        BackHandler(onBack = onBack)
        ItemDetail(
            item = selectedItem,
            onBack = onBack,
            modifier = modifier
        )
    }
}

// Планшет: список слева, подробности справа
@Composable
fun TwoColumnLayout(
    items: List<ListItem>,
    selectedItem: ListItem?,
    onItemSelected: (ListItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ItemsColumn(
            items = items,
            onItemSelected = onItemSelected,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        VerticalDivider()
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            if (selectedItem == null) {
                Text(
                    text = "Выберите элемент",
                    style = MaterialTheme.typography.titleLarge
                )
            } else {
                ItemDetail(item = selectedItem)
            }
        }
    }
}

@Composable
fun ItemsColumn(
    items: List<ListItem>,
    onItemSelected: (ListItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items, key = { it.id }) { item ->
            ItemCard(
                item = item,
                modifier = Modifier.clickable { onItemSelected(item) }
            )
        }
    }
}

// onBack = null — кнопка «Назад» не нужна (в двух колонках список и так виден)
@Composable
fun ItemDetail(
    item: ListItem,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(item.imageRes),
            contentDescription = item.title,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = item.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.description, style = MaterialTheme.typography.bodyLarge)
        if (onBack != null) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onBack) {
                Text("‹ Назад")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun CompactPreview() {
    Module_3Theme {
        AdaptiveLayout(widthSizeClass = WindowWidthSizeClass.Compact)
    }
}

@Preview(showBackground = true, widthDp = 900, heightDp = 600)
@Composable
fun ExpandedPreview() {
    Module_3Theme {
        AdaptiveLayout(widthSizeClass = WindowWidthSizeClass.Expanded)
    }
}
