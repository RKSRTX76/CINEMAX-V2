package com.rksrtx76.cinemax.presentation.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rksrtx76.CINEMAX.model.CastDetails
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.Screen

@Composable
fun CastSection(
    modifier : Modifier = Modifier,
    castList : List<CastDetails>,
    type : String,
    navController: NavController,
    title : String
){
    if(castList.isNotEmpty()){
        ShowCast(
            castList = castList.take(12),
            title = title,
            modifier = modifier,
            type = type,
            navController = navController
        )
    }else{

    }
}



@Composable
fun ShowCast(
    modifier : Modifier = Modifier,
    castList : List<CastDetails>,
    navController: NavController,
    type : String,
    title : String
){
    Column(
        modifier = modifier.padding(top = 16.dp)
    ) {
        if(castList.isNotEmpty()){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 22.dp),
                    fontWeight = FontWeight.Bold,
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = font,
                    fontSize = 20.sp
                )
                Text(
                    text = stringResource(R.string.see_all),
                    fontWeight = FontWeight.Bold,
                    fontFamily = font,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            navController.navigate("${Screen.CAST_LIST_SCREEN}?castId=${castList.firstOrNull()?.id}&type=$type")
                        }
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(castList.size) { idx ->
                    Box(
                        modifier = Modifier
                            .width(120.dp),
                    ){
                        CastMemberItem(
                            cast = castList[idx],
                        )
                    }
                }
            }
        }
    }
}