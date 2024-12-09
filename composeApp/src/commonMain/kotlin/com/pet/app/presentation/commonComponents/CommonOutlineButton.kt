package com.pet.app.presentation.commonComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pet.app.PlatformName
import com.pet.app.getPlatform
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.ic_chat_dots
import petapp.composeapp.generated.resources.ic_tweeter


enum class CommonOutlineButtonColors {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    QUATERNARY
}

@Composable
fun CommonOutlineButton(
    modifier: Modifier = Modifier,
    color: CommonOutlineButtonColors,
    label: String,
    leadingIcon: DrawableResource? = null,
    trailingIcon: DrawableResource? = null,
    onClick: () -> Unit
) {
    val isDesktop = getPlatform().name == PlatformName.DESKTOP

    val finalColor = when (color) {
        CommonOutlineButtonColors.PRIMARY -> MaterialTheme.colorScheme.primary
        CommonOutlineButtonColors.SECONDARY -> MaterialTheme.colorScheme.secondary
        CommonOutlineButtonColors.TERTIARY -> MaterialTheme.colorScheme.tertiary
        CommonOutlineButtonColors.QUATERNARY -> MaterialTheme.colorScheme.primaryContainer
    }

    OutlinedButton (
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier.height(if(isDesktop) 38.dp else 48.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = finalColor,
            containerColor = Color.Transparent,
        ),
        border = BorderStroke(
            color = finalColor,
            width = 2.dp
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let {
                Icon(
                    imageVector = vectorResource(it),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text = label,
                fontSize = if(isDesktop) 14.sp else 16.sp
            )
            trailingIcon?.let {
                Spacer(Modifier.width(8.dp))
                Icon(
                    imageVector = vectorResource(it),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }

}


@Preview()
@Composable
private fun CommonOutlineButtonPreview() {
    PetAppTheme {
        Column {
            CommonOutlineButton (
                color = CommonOutlineButtonColors.PRIMARY,
                label = "Hello Button primary",
                onClick = {}
            )
            CommonOutlineButton(
                color = CommonOutlineButtonColors.SECONDARY,
                label = "Hello Button secondary",
                onClick = {}
            )
            CommonOutlineButton(
                color = CommonOutlineButtonColors.TERTIARY,
                label = "Hello Button tertiary",
                onClick = {}
            )
        }

    }
}


@Preview()
@Composable
private fun CommonOutlineButtonQuaternaryPreview() {
    PetAppTheme {
        Column {
            CommonOutlineButton(
                color = CommonOutlineButtonColors.QUATERNARY,
                label = "Hello Button quaternary",
                onClick = {}
            )
        }

    }
}

@Preview()
@Composable
private fun CommonOutlineButtonLeadingIconPreview() {
    PetAppTheme {
        Column {
            CommonOutlineButton(
                color = CommonOutlineButtonColors.PRIMARY,
                label = "Hello Button",
                onClick = {},
                leadingIcon = Res.drawable.ic_tweeter
            )
            CommonOutlineButton(
                color = CommonOutlineButtonColors.SECONDARY,
                label = "Hello Button",
                onClick = {},
                leadingIcon = Res.drawable.ic_tweeter
            )
            CommonOutlineButton(
                color = CommonOutlineButtonColors.TERTIARY,
                label = "Hello Button",
                onClick = {},
                leadingIcon = Res.drawable.ic_chat_dots
            )
        }

    }
}