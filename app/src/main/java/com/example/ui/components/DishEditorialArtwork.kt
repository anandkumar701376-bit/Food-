package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.data.model.DishArtworkStyle

/**
 * Editorial Culinary Artwork Surface.
 * Renders warm, magazine-styled plated Indian dish compositions (copper handis,
 * brass dosa thalis, terracotta biryani pots, tandoori skewers, ceramic curry bowls)
 * with directional sunlight, spice accents, and gentle steam curves.
 */
@Composable
fun DishEditorialArtwork(
    style: DishArtworkStyle,
    modifier: Modifier = Modifier
) {
    val palette = artworkPaletteFor(style)
    Box(
        modifier = modifier.background(
            Brush.linearGradient(
                colors = listOf(palette.bgStart, palette.bgMid, palette.bgEnd)
            )
        )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val cx = w * 0.54f
            val cy = h * 0.56f
            val minDim = minOf(w, h)

            // Warm directional sunlight halo
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        palette.glowColor.copy(alpha = 0.42f),
                        Color.Transparent
                    ),
                    center = Offset(w * 0.25f, h * 0.18f),
                    radius = minDim * 0.95f
                ),
                radius = minDim * 0.95f,
                center = Offset(w * 0.25f, h * 0.18f)
            )

            // Subtle table linen / wood grain contour lines
            drawLine(
                color = Color.White.copy(alpha = 0.08f),
                start = Offset(0f, h * 0.78f),
                end = Offset(w, h * 0.72f),
                strokeWidth = 2f
            )
            drawLine(
                color = Color.Black.copy(alpha = 0.10f),
                start = Offset(0f, h * 0.85f),
                end = Offset(w, h * 0.82f),
                strokeWidth = 3f
            )

            // Soft platter shadow
            drawOval(
                color = Color(0xFF1A0D07).copy(alpha = 0.34f),
                topLeft = Offset(cx - minDim * 0.40f, cy - minDim * 0.24f + minDim * 0.08f),
                size = Size(minDim * 0.80f, minDim * 0.58f)
            )

            when (style) {
                DishArtworkStyle.BUTTER_CHICKEN_HANDI -> drawButterChickenHandi(cx, cy, minDim)
                DishArtworkStyle.MASALA_DOSA_THALI -> drawMasalaDosaThali(cx, cy, minDim)
                DishArtworkStyle.HYDERABADI_BIRYANI_POT -> drawBiryaniPot(cx, cy, minDim)
                DishArtworkStyle.PANEER_TIKKA_PLATTER -> drawPaneerTikkaPlatter(cx, cy, minDim)
                DishArtworkStyle.CHOLE_BHATURE_FEAST -> drawCholeFeast(cx, cy, minDim)
                DishArtworkStyle.MALABAR_FISH_CURRY -> drawMalabarCurry(cx, cy, minDim)
                DishArtworkStyle.KESAR_RASMALAI_BOWL -> drawRasmalaiBowl(cx, cy, minDim)
                DishArtworkStyle.DAL_BAATI_CHURMA -> drawDalBaatiThali(cx, cy, minDim)
                DishArtworkStyle.MUMBAI_PAV_BHAJI -> drawPavBhajiTawa(cx, cy, minDim)
                DishArtworkStyle.PALAK_PANEER_BOWL -> drawPalakPaneerBowl(cx, cy, minDim)
            }

            // Rising aromatic steam wisps
            drawSteamWisps(cx, cy - minDim * 0.33f, minDim)
        }
    }
}

private data class DishPalette(
    val bgStart: Color,
    val bgMid: Color,
    val bgEnd: Color,
    val glowColor: Color
)

private fun artworkPaletteFor(style: DishArtworkStyle): DishPalette = when (style) {
    DishArtworkStyle.BUTTER_CHICKEN_HANDI -> DishPalette(
        Color(0xFF5A2515), Color(0xFF8C391E), Color(0xFF3A1A11), Color(0xFFFFB76B)
    )
    DishArtworkStyle.MASALA_DOSA_THALI -> DishPalette(
        Color(0xFF47331D), Color(0xFF7B5728), Color(0xFF2B2014), Color(0xFFFFD88A)
    )
    DishArtworkStyle.HYDERABADI_BIRYANI_POT -> DishPalette(
        Color(0xFF3C2014), Color(0xFF7C441D), Color(0xFF28150D), Color(0xFFF6C25B)
    )
    DishArtworkStyle.PANEER_TIKKA_PLATTER -> DishPalette(
        Color(0xFF4F2217), Color(0xFF9C4124), Color(0xFF311610), Color(0xFFFFAD73)
    )
    DishArtworkStyle.CHOLE_BHATURE_FEAST -> DishPalette(
        Color(0xFF3B2418), Color(0xFF6B3E26), Color(0xFF24150E), Color(0xFFE7B26F)
    )
    DishArtworkStyle.MALABAR_FISH_CURRY -> DishPalette(
        Color(0xFF243B2C), Color(0xFF46653D), Color(0xFF1A281E), Color(0xFFF5C97B)
    )
    DishArtworkStyle.KESAR_RASMALAI_BOWL -> DishPalette(
        Color(0xFF5D3A1A), Color(0xFFA66F29), Color(0xFF3B2310), Color(0xFFFFE6A3)
    )
    DishArtworkStyle.DAL_BAATI_CHURMA -> DishPalette(
        Color(0xFF5A311B), Color(0xFF8E5128), Color(0xFF351D11), Color(0xFFFFCA80)
    )
    DishArtworkStyle.MUMBAI_PAV_BHAJI -> DishPalette(
        Color(0xFF5B1E14), Color(0xFFA3391E), Color(0xFF36130D), Color(0xFFFFB25E)
    )
    DishArtworkStyle.PALAK_PANEER_BOWL -> DishPalette(
        Color(0xFF1E3926), Color(0xFF365E3D), Color(0xFF15261A), Color(0xFFDDE8A2)
    )
}

private fun DrawScope.drawButterChickenHandi(cx: Float, cy: Float, d: Float) {
    // Hammered copper bowl rim
    drawCircle(
        brush = Brush.linearGradient(listOf(Color(0xFFE7A26B), Color(0xFF9B4E26))),
        radius = d * 0.34f,
        center = Offset(cx, cy)
    )
    // Rich makhani gravy
    drawCircle(
        brush = Brush.radialGradient(
            listOf(Color(0xFFE6662B), Color(0xFFBF3B19)),
            center = Offset(cx - d * 0.05f, cy - d * 0.04f),
            radius = d * 0.32f
        ),
        radius = d * 0.29f,
        center = Offset(cx, cy)
    )
    // Charred chicken pieces
    val chunks = listOf(
        Offset(cx - d * 0.10f, cy - d * 0.04f),
        Offset(cx + d * 0.09f, cy - d * 0.07f),
        Offset(cx + d * 0.03f, cy + d * 0.09f),
        Offset(cx - d * 0.08f, cy + d * 0.10f)
    )
    chunks.forEach { pos ->
        drawOval(
            color = Color(0xFF9E2D12),
            topLeft = Offset(pos.x - d * 0.06f, pos.y - d * 0.04f),
            size = Size(d * 0.12f, d * 0.085f)
        )
    }
    // Cream swirl
    val swirl = Path().apply {
        moveTo(cx - d * 0.14f, cy - d * 0.02f)
        cubicTo(cx - d * 0.06f, cy - d * 0.16f, cx + d * 0.14f, cy - d * 0.10f, cx + d * 0.10f, cy + d * 0.06f)
        cubicTo(cx + d * 0.05f, cy + d * 0.16f, cx - d * 0.08f, cy + d * 0.08f, cx, cy)
    }
    drawPath(swirl, color = Color(0xFFFFF7EA), style = Stroke(width = d * 0.024f, cap = StrokeCap.Round))
    // Fresh cilantro leaves
    drawHerbLeaves(cx, cy, d, Color(0xFF4E8C4B))
}

private fun DrawScope.drawMasalaDosaThali(cx: Float, cy: Float, d: Float) {
    // Brass plate
    drawCircle(
        brush = Brush.linearGradient(listOf(Color(0xFFE8C468), Color(0xFF9C762B))),
        radius = d * 0.37f,
        center = Offset(cx, cy)
    )
    // Banana leaf liner
    drawCircle(color = Color(0xFF3C6E3B), radius = d * 0.33f, center = Offset(cx, cy))
    // Crispy golden rolled dosa cone
    val dosa = Path().apply {
        moveTo(cx - d * 0.30f, cy + d * 0.10f)
        lineTo(cx + d * 0.25f, cy - d * 0.16f)
        lineTo(cx + d * 0.28f, cy + d * 0.04f)
        lineTo(cx - d * 0.22f, cy + d * 0.22f)
        close()
    }
    drawPath(
        dosa,
        brush = Brush.linearGradient(listOf(Color(0xFFE49D3A), Color(0xFFAF5F18)))
    )
    // Chutney katoris (white coconut + red tomato)
    drawCircle(color = Color(0xFFE2BE68), radius = d * 0.095f, center = Offset(cx - d * 0.14f, cy - d * 0.15f))
    drawCircle(color = Color(0xFFFBF7EE), radius = d * 0.078f, center = Offset(cx - d * 0.14f, cy - d * 0.15f))
    drawCircle(color = Color(0xFFE2BE68), radius = d * 0.095f, center = Offset(cx + d * 0.08f, cy - d * 0.19f))
    drawCircle(color = Color(0xFFD64B27), radius = d * 0.078f, center = Offset(cx + d * 0.08f, cy - d * 0.19f))
}

private fun DrawScope.drawBiryaniPot(cx: Float, cy: Float, d: Float) {
    // Terracotta handi
    drawCircle(
        brush = Brush.linearGradient(listOf(Color(0xFFD46A41), Color(0xFF7D3219))),
        radius = d * 0.35f,
        center = Offset(cx, cy)
    )
    // Ivory & saffron layered basmati rice
    drawCircle(color = Color(0xFFFFF8E8), radius = d * 0.29f, center = Offset(cx, cy))
    drawCircle(
        brush = Brush.radialGradient(
            listOf(Color(0xFFF2AA29).copy(alpha = 0.85f), Color.Transparent),
            center = Offset(cx - d * 0.08f, cy - d * 0.04f),
            radius = d * 0.20f
        ),
        radius = d * 0.22f,
        center = Offset(cx - d * 0.06f, cy - d * 0.03f)
    )
    // Crispy birista arcs & saffron flecks
    for (i in 0..8) {
        val angle = i * 0.72f
        val rx = cx + kotlin.math.cos(angle) * d * 0.14f
        val ry = cy + kotlin.math.sin(angle) * d * 0.13f
        drawRoundRect(
            color = if (i % 2 == 0) Color(0xFF7A3815) else Color(0xFFE07C1B),
            topLeft = Offset(rx, ry),
            size = Size(d * 0.055f, d * 0.016f),
            cornerRadius = CornerRadius(6f, 6f)
        )
    }
    drawHerbLeaves(cx + d * 0.04f, cy + d * 0.02f, d, Color(0xFF3E7F45))
}

private fun DrawScope.drawPaneerTikkaPlatter(cx: Float, cy: Float, d: Float) {
    // Stoneware oval platter
    drawRoundRect(
        color = Color(0xFFF4EAE0),
        topLeft = Offset(cx - d * 0.38f, cy - d * 0.24f),
        size = Size(d * 0.76f, d * 0.48f),
        cornerRadius = CornerRadius(d * 0.24f, d * 0.24f)
    )
    // Two metallic skewer lines
    drawLine(Color(0xFF6B5A4E), Offset(cx - d * 0.30f, cy - d * 0.08f), Offset(cx + d * 0.30f, cy - d * 0.08f), 4f)
    drawLine(Color(0xFF6B5A4E), Offset(cx - d * 0.30f, cy + d * 0.09f), Offset(cx + d * 0.30f, cy + d * 0.09f), 4f)
    // Charred paneer tikka cubes & green/red bell peppers
    val xs = listOf(-0.18f, -0.02f, 0.14f)
    xs.forEach { ox ->
        drawRoundRect(
            color = Color(0xFFD95B26),
            topLeft = Offset(cx + d * ox - d * 0.05f, cy - d * 0.14f),
            size = Size(d * 0.11f, d * 0.11f),
            cornerRadius = CornerRadius(10f, 10f)
        )
        drawRoundRect(
            color = Color(0xFF478341),
            topLeft = Offset(cx + d * (ox + 0.07f), cy + d * 0.04f),
            size = Size(d * 0.06f, d * 0.10f),
            cornerRadius = CornerRadius(8f, 8f)
        )
        drawRoundRect(
            color = Color(0xFFC8491C),
            topLeft = Offset(cx + d * ox - d * 0.06f, cy + d * 0.03f),
            size = Size(d * 0.11f, d * 0.11f),
            cornerRadius = CornerRadius(10f, 10f)
        )
    }
    // Mint chutney cup
    drawCircle(color = Color(0xFF4A924C), radius = d * 0.075f, center = Offset(cx + d * 0.27f, cy + d * 0.18f))
}

private fun DrawScope.drawCholeFeast(cx: Float, cy: Float, d: Float) {
    drawCircle(color = Color(0xFFD8B06F), radius = d * 0.34f, center = Offset(cx, cy))
    drawCircle(color = Color(0xFF4C2716), radius = d * 0.29f, center = Offset(cx, cy))
    for (i in 0..11) {
        val a = i * 0.55f
        val r = if (i % 2 == 0) d * 0.13f else d * 0.07f
        drawCircle(
            color = Color(0xFF7B4423),
            radius = d * 0.034f,
            center = Offset(cx + kotlin.math.cos(a) * r, cy + kotlin.math.sin(a) * r)
        )
    }
    // Ginger juliennes & onion rings
    drawCircle(
        color = Color(0xFFF4D5DA),
        radius = d * 0.08f,
        center = Offset(cx + d * 0.08f, cy - d * 0.06f),
        style = Stroke(width = d * 0.015f)
    )
    drawHerbLeaves(cx - d * 0.05f, cy + d * 0.03f, d, Color(0xFF56944D))
}

private fun DrawScope.drawMalabarCurry(cx: Float, cy: Float, d: Float) {
    // Dark clay chatti
    drawCircle(color = Color(0xFF3E261B), radius = d * 0.35f, center = Offset(cx, cy))
    // Golden coconut curry
    drawCircle(
        brush = Brush.radialGradient(listOf(Color(0xFFF3AF42), Color(0xFFD06A22))),
        radius = d * 0.29f,
        center = Offset(cx, cy)
    )
    // Coconut milk ribbon
    drawCircle(
        color = Color(0xFFFFF8EC).copy(alpha = 0.78f),
        radius = d * 0.18f,
        center = Offset(cx, cy),
        style = Stroke(width = d * 0.025f)
    )
    drawHerbLeaves(cx, cy, d, Color(0xFF2E6D3B))
}

private fun DrawScope.drawRasmalaiBowl(cx: Float, cy: Float, d: Float) {
    drawCircle(color = Color(0xFFE7D6BD), radius = d * 0.34f, center = Offset(cx, cy))
    // Saffron rabri milk
    drawCircle(
        brush = Brush.radialGradient(listOf(Color(0xFFFFF3C7), Color(0xFFF2C86B))),
        radius = d * 0.29f,
        center = Offset(cx, cy)
    )
    // Plump white chhena discs
    drawOval(Color(0xFFFFFCF4), Offset(cx - d * 0.19f, cy - d * 0.10f), Size(d * 0.22f, d * 0.16f))
    drawOval(Color(0xFFFFF8E8), Offset(cx - d * 0.02f, cy - d * 0.02f), Size(d * 0.22f, d * 0.16f))
    // Pistachio slivers & saffron threads
    drawHerbLeaves(cx, cy, d * 0.85f, Color(0xFF6BA050))
    drawLine(Color(0xFFD9411E), Offset(cx - d * 0.05f, cy - d * 0.03f), Offset(cx + d * 0.03f, cy - d * 0.05f), 4f)
}

private fun DrawScope.drawDalBaatiThali(cx: Float, cy: Float, d: Float) {
    drawCircle(color = Color(0xFFDAA956), radius = d * 0.36f, center = Offset(cx, cy))
    drawCircle(color = Color(0xFFF4EAD8), radius = d * 0.32f, center = Offset(cx, cy))
    // Golden baked baatis
    drawCircle(Color(0xFFD9953C), d * 0.105f, Offset(cx - d * 0.11f, cy + d * 0.07f))
    drawCircle(Color(0xFFCC842C), d * 0.105f, Offset(cx + d * 0.10f, cy + d * 0.09f))
    // Dal bowl
    drawCircle(Color(0xFFCF6E24), d * 0.115f, Offset(cx, cy - d * 0.12f))
    drawHerbLeaves(cx, cy - d * 0.12f, d * 0.6f, Color(0xFF4D8A46))
}

private fun DrawScope.drawPavBhajiTawa(cx: Float, cy: Float, d: Float) {
    // Cast iron tawa
    drawCircle(color = Color(0xFF2B2320), radius = d * 0.36f, center = Offset(cx, cy))
    // Crimson spiced bhaji
    drawCircle(
        brush = Brush.radialGradient(listOf(Color(0xFFE05225), Color(0xFFA92B15))),
        radius = d * 0.26f,
        center = Offset(cx - d * 0.04f, cy + d * 0.02f)
    )
    // Melting butter pat
    drawRoundRect(
        color = Color(0xFFFFEA8A),
        topLeft = Offset(cx - d * 0.09f, cy - d * 0.04f),
        size = Size(d * 0.11f, d * 0.10f),
        cornerRadius = CornerRadius(8f, 8f)
    )
    // Toasted ladi pav roll on rim
    drawRoundRect(
        color = Color(0xFFE0A458),
        topLeft = Offset(cx + d * 0.10f, cy - d * 0.22f),
        size = Size(d * 0.18f, d * 0.15f),
        cornerRadius = CornerRadius(14f, 14f)
    )
    drawHerbLeaves(cx - d * 0.03f, cy + d * 0.06f, d * 0.85f, Color(0xFF5DA452))
}

private fun DrawScope.drawPalakPaneerBowl(cx: Float, cy: Float, d: Float) {
    drawCircle(color = Color(0xFFEBE0D0), radius = d * 0.34f, center = Offset(cx, cy))
    drawCircle(
        brush = Brush.radialGradient(listOf(Color(0xFF3E7A42), Color(0xFF224F29))),
        radius = d * 0.29f,
        center = Offset(cx, cy)
    )
    // White paneer cubes
    listOf(
        Offset(cx - d * 0.12f, cy - d * 0.07f),
        Offset(cx + d * 0.06f, cy - d * 0.09f),
        Offset(cx - d * 0.02f, cy + d * 0.05f),
        Offset(cx + d * 0.10f, cy + d * 0.07f)
    ).forEach { pos ->
        drawRoundRect(
            color = Color(0xFFFFFAF0),
            topLeft = pos,
            size = Size(d * 0.09f, d * 0.085f),
            cornerRadius = CornerRadius(8f, 8f)
        )
    }
    // Red chili tadka accent
    drawLine(Color(0xFFD63B1E), Offset(cx - d * 0.06f, cy + d * 0.01f), Offset(cx + d * 0.07f, cy - d * 0.02f), 5f)
}

private fun DrawScope.drawHerbLeaves(cx: Float, cy: Float, d: Float, color: Color) {
    drawOval(color, Offset(cx - d * 0.09f, cy - d * 0.07f), Size(d * 0.07f, d * 0.035f))
    drawOval(color, Offset(cx + d * 0.03f, cy + d * 0.02f), Size(d * 0.065f, d * 0.032f))
    drawOval(color.copy(alpha = 0.85f), Offset(cx - d * 0.02f, cy + d * 0.08f), Size(d * 0.06f, d * 0.03f))
}

private fun DrawScope.drawSteamWisps(cx: Float, topY: Float, d: Float) {
    val wispColor = Color(0xFFFFF8EE).copy(alpha = 0.28f)
    listOf(-0.10f, 0.04f, 0.16f).forEach { offsetX ->
        val p = Path().apply {
            moveTo(cx + d * offsetX, topY + d * 0.10f)
            cubicTo(
                cx + d * (offsetX - 0.04f), topY + d * 0.03f,
                cx + d * (offsetX + 0.05f), topY - d * 0.04f,
                cx + d * offsetX, topY - d * 0.11f
            )
        }
        drawPath(p, color = wispColor, style = Stroke(width = 3.5f, cap = StrokeCap.Round))
    }
}
