package com.example.module_3.Module_3_7

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.module_3.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TodoUiTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    // 3) Отображаются все 3 задачи из JSON
    @Test
    fun allThreeTodosFromJson_areDisplayed() {
        composeRule.onNodeWithText("Купить молоко").assertIsDisplayed()
        composeRule.onNodeWithText("Позвонить маме").assertIsDisplayed()
        composeRule.onNodeWithText("Сделать ДЗ по Android").assertIsDisplayed()
    }

    // 4) Чекбокс переключает статус
    @Test
    fun checkbox_togglesStatus() {
        val checkbox = composeRule.onNodeWithTag("checkbox_1")

        checkbox.assertIsOff()
        checkbox.performClick()
        checkbox.assertIsOn()
        checkbox.performClick()
        checkbox.assertIsOff()
    }

    // 5) Навигация List → Detail → List
    @Test
    fun navigation_listToDetailAndBack() {
        // List → Detail
        composeRule.onNodeWithText("Купить молоко").performClick()
        // Статус показывается только на детальном экране
        composeRule.onNodeWithText("Не выполнено").assertIsDisplayed()
        composeRule.onNodeWithText("Позвонить маме").assertDoesNotExist()

        // Detail → List
        composeRule.onNodeWithContentDescription("Назад").performClick()
        composeRule.onNodeWithText("Позвонить маме").assertIsDisplayed()
        composeRule.onNodeWithText("Не выполнено").assertDoesNotExist()
    }
}
