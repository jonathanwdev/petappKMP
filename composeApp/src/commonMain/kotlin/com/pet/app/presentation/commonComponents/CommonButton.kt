package com.pet.app.presentation.commonComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pet.app.Platform
import com.pet.app.PlatformName
import com.pet.app.getPlatform
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.ic_chat_dots
import petapp.composeapp.generated.resources.ic_tweeter


enum class CommonButtonColors {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    QUATERNARY
}

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    color: CommonButtonColors,
    label: String,
    leadingIcon: DrawableResource? = null,
    trailingIcon: DrawableResource? = null,
    onClick: () -> Unit
) {
    val isDesktop = getPlatform().name == PlatformName.DESKTOP

    val finalColor = when (color) {
        CommonButtonColors.PRIMARY -> MaterialTheme.colorScheme.primary
        CommonButtonColors.SECONDARY -> MaterialTheme.colorScheme.secondary
        CommonButtonColors.TERTIARY -> MaterialTheme.colorScheme.tertiary
        CommonButtonColors.QUATERNARY -> MaterialTheme.colorScheme.primaryContainer
    }

    val textColor = when (color) {
        CommonButtonColors.QUATERNARY -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.primaryContainer
    }

    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier.height(if(isDesktop) 38.dp else 48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = finalColor
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
private fun CommonButtonPreview() {
    PetAppTheme {
        Column {
            CommonButton (
                color = CommonButtonColors.PRIMARY,
                label = "Hello Button primary",
                onClick = {}
            )
            CommonButton(
                color = CommonButtonColors.SECONDARY,
                label = "Hello Button secondary",
                onClick = {}
            )
            CommonButton(
                color = CommonButtonColors.TERTIARY,
                label = "Hello Button tertiary",
                onClick = {}
            )
        }

    }
}


@Preview()
@Composable
private fun CommonButtonQuaternaryPreview() {
    PetAppTheme {
        Column {
            CommonButton(
                color = CommonButtonColors.QUATERNARY,
                label = "Hello Button quaternary",
                onClick = {}
            )
        }

    }
}

@Preview()
@Composable
private fun CommonButtonLeadingIconPreview() {
    PetAppTheme {
        Column {
            CommonButton(
                color = CommonButtonColors.PRIMARY,
                label = "Hello Button",
                onClick = {},
                leadingIcon = Res.drawable.ic_tweeter
            )
            CommonButton(
                color = CommonButtonColors.SECONDARY,
                label = "Hello Button",
                onClick = {},
                leadingIcon = Res.drawable.ic_tweeter
            )
            CommonButton(
                color = CommonButtonColors.TERTIARY,
                label = "Hello Button",
                onClick = {},
                leadingIcon = Res.drawable.ic_chat_dots
            )
        }

    }
}
