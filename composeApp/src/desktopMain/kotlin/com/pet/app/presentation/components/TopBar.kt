package com.pet.app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pet.app.presentation.commonComponents.CommonButton
import com.pet.app.presentation.commonComponents.CommonButtonColors
import org.jetbrains.compose.resources.imageResource
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.logo
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.pet.app.presentation.theme.PetAppTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    currRoute: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                bitmap = imageResource(Res.drawable.logo),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(48.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(25.dp),
            ) {

                Text(
                    text = "Home",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {

                    }
                )

                Text(
                    "Category",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {

                    }

                )
                Text(
                    "About",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {

                    }
                )
                Text(
                    "Contact",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {

                    }
                )
            }
        }
        Row {
            CommonButton(color = CommonButtonColors.QUATERNARY, label = "Login", onClick = {})
            Spacer(modifier = Modifier.width(10.dp))
            CommonButton(
                color = CommonButtonColors.PRIMARY,
                label = "Join the community",
                onClick = {}
            )
        }
    }


}

@Preview
@Composable
fun TopBarPreview() {
    PetAppTheme {
        TopBar(
            currRoute = "Home"
        )
    }
}