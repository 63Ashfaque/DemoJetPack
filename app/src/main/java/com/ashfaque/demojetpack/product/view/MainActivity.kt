package com.ashfaque.demojetpack.product.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashfaque.demojetpack.ui.theme.DemoJetPackTheme
import coil.compose.rememberImagePainter
import com.ashfaque.demojetpack.product.viewmodel.ProductViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoJetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    PreviewPostList()
                }
            }
        }
    }
}

@Composable
fun PostList(viewModel: ProductViewModel = ProductViewModel()) {
    val productsResponse by viewModel.productsResponse.observeAsState()


    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    Column {
        Button(onClick = { viewModel.getProducts() }) {
            Text(text = "Fetch Products")
        }

        LazyColumn {
            productsResponse?.products?.let {
                items(it.size) { item ->
                    MyCardView(it[item].images[0],it[item].title,it[item].price)
                }
            }
        }

    }
}



@Preview
@Composable
fun PreviewPostList() {
    PostList()
}

@Composable
fun MyCardView(
    imageUrl: String,
    heading: String,
    subheading: Int,
    onClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image View

            val imagePainter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    // You can customize image loading options here if needed
                }
            )



           Image(
               // painter = painterResource(id = imageResource),
               painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            // Texts
            Column {
                Text(
                    text = heading,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = "$ $subheading",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}



