package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.rksrtx76.cinemax.R
import com.rksrtx76.cinemax.presentation.viewmodel.SearchViewModel
import com.rksrtx76.cinemax.ui.theme.BigRadius
import com.rksrtx76.cinemax.ui.theme.font

@Composable
fun FocusedSearchBar(
    searchQuery : String,
    searchViewModel: SearchViewModel
){

    val isDarkTheme = isSystemInDarkTheme()

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .height(BigRadius.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        // custom searchbar
        SearchBar(
            searchQuery = searchQuery,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = if(isDarkTheme) MaterialTheme.colorScheme.onBackground else Color.Black.copy(alpha = 0.10f),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            },
            placeholderText = stringResource(R.string.movies_shows_and_more),
            onValueChange = { newQuery ->
                searchViewModel.onSearchQueryChanged(newQuery)
            },
            searchViewModel = searchViewModel,
            isDarkTheme = isDarkTheme,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(50.dp)
        )
    }
}



@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    isDarkTheme : Boolean,
    searchQuery : String,
    searchViewModel: SearchViewModel,
    leadingIcon: (@Composable () -> Unit)? = null,
    placeholderText : String,
    onValueChange : (String) -> Unit,
) {
    // custom search bar
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isFocused) {
        if(isFocused){
            focusRequester.requestFocus()
        }
    }

    BasicTextField(
        value = searchQuery,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = font,
            fontSize = 16.sp
        ),
        modifier = modifier
            .clip(RoundedCornerShape(percent = 45))
            .background(
                if(isDarkTheme) Color(0xFF5E5E5E) else Color.Black.copy(alpha = 0.10f),
            )
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->  
                isFocused = focusState.isFocused
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(leadingIcon != null){
                    leadingIcon()
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (searchQuery.isEmpty() && !isFocused) {
                        Text(
                            text = placeholderText,
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                                fontFamily = font,
                                fontSize = 18.sp
                            )
                        )
                    }
                    innerTextField()
                }
                if(searchQuery.isNotEmpty()){
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(28.dp)
                            .clickable {
                                searchViewModel.onSearchQueryChanged("")
                            }
                    )
                }
            }
        }
    )
}

