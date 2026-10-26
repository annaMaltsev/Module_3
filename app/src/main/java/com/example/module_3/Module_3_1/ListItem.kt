package com.example.module_3.Module_3_1

import androidx.annotation.DrawableRes

data class ListItem(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int
)

val sampleItems = listOf(
    ListItem(1, "Камера", "Сделать фото", android.R.drawable.ic_menu_camera),
    ListItem(2, "Галерея", "Просмотр изображений", android.R.drawable.ic_menu_gallery),
    ListItem(3, "Звонок", "Позвонить контакту", android.R.drawable.ic_menu_call),
    ListItem(4, "Почта", "Написать письмо", android.R.drawable.ic_dialog_email),
    ListItem(5, "Карта", "Моё местоположение", android.R.drawable.ic_menu_mylocation),
    ListItem(6, "Поиск", "Найти что-нибудь", android.R.drawable.ic_menu_search),
    ListItem(7, "Настройки", "Параметры приложения", android.R.drawable.ic_menu_manage),
    ListItem(8, "Календарь", "События и даты", android.R.drawable.ic_menu_my_calendar),
    ListItem(9, "Поделиться", "Отправить другу", android.R.drawable.ic_menu_share),
    ListItem(10, "Информация", "О приложении", android.R.drawable.ic_menu_info_details)
)