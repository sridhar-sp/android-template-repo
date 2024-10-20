package com.droidstarter.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

private val defaultShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

interface AppShape {
    val circleShape: Shape
}

object DefaultShapes : AppShape {
    val shapes = defaultShapes
    override val circleShape: Shape get() = RoundedCornerShape(50)
}