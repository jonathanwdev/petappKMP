package com.pet.app.presentation.commonComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonTableRow(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(0.5f),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xFF667479)
            )
            Text(
                text = ": $value",
                modifier = Modifier.weight(0.5f),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xFF667479)
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFFEBEEEF)
        )
    }
}