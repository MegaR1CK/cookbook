package com.hfad.cookbook.ui.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.hfad.cookbook.R
import com.hfad.cookbook.data.domain.RecipeCard
import com.hfad.cookbook.ui.theme.CookbookTheme

@Composable
fun RecipesScreenEntry(recipesViewModel: RecipesViewModel) {
    val recipeCards = recipesViewModel.recipeCards.collectAsLazyPagingItems()
    RecipesScreen(recipeCards = recipeCards)
}

@Composable
fun RecipesScreen(recipeCards: LazyPagingItems<RecipeCard>, ) {
    Box(Modifier.padding(8.dp)) {
        RecipesList(recipeCards)
    }
}

@Composable
fun RecipesList(recipeCards: LazyPagingItems<RecipeCard>) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, state = rememberLazyListState()) {
        items(recipeCards) {
            if (it != null) {
                RecipeCardComposable(recipeCard = it, modifier = Modifier.padding(bottom = 16.dp))
            }
        }
        recipeCards.apply {
            when {
                loadState.refresh is LoadState.Loading -> item { LoadingScreen(modifier = Modifier.fillParentMaxSize()) }
                loadState.refresh is LoadState.Error -> item {
                    val error = (loadState.refresh as LoadState.Error).error
                    ErrorScreen(
                        text = error.localizedMessage ?: stringResource(id = R.string.unknown_error),
                        modifier = Modifier.fillParentMaxSize(),
                        onRetryClick = { recipeCards.retry() }
                    )
                }
                loadState.append is LoadState.Loading -> item { LoadingItem() }
                loadState.append is LoadState.Error -> item { ErrorItem { recipeCards.retry() } }
            }
        }
    }
}

@Composable
fun RecipeCardComposable(recipeCard: RecipeCard, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberCoilPainter(
                request = recipeCard.imageUrl,
                fadeIn = true
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Surface(
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                val horizontalPadding = Modifier.padding(horizontal = 16.dp)
                Text(
                    text = recipeCard.title,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = horizontalPadding
                        .padding(top = 16.dp)
                        .weight(1f)
                )
                RecipeCardMetaData(
                    recipeCard = recipeCard,
                    modifier = horizontalPadding.padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
fun RecipeCardMetaData(recipeCard: RecipeCard, modifier: Modifier = Modifier) {
    val resources = LocalContext.current.resources
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Schedule, contentDescription = null, tint = MaterialTheme.colors.onPrimary)
        Text(
            text = resources.getString(R.string.cook_time, recipeCard.readyInMinutes),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f)
        )
        Icon(imageVector = Icons.Outlined.Grade, contentDescription = null, tint = MaterialTheme.colors.onPrimary)
        Text(
            text = recipeCard.rating.toString(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, text: String, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier.padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(150.dp),
            imageVector = Icons.Outlined.CloudOff,
            contentDescription = null,
            tint = Color.Gray)
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetryClick) {
            Text(text = stringResource(id = R.string.load_retry))
        }
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        strokeWidth = 3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .size(30.dp)
    )
}

@Composable
fun ErrorItem(onRetryClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.unknown_error),
            style = MaterialTheme.typography.subtitle2,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetryClick) {
            Text(text = stringResource(id = R.string.load_retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    CookbookTheme {
        RecipeCardComposable(RecipeCard(0, "Recipe", "", 5, 5.5))
    }
}

