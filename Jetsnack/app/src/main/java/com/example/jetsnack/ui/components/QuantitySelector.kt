/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetsnack.ui.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ChainStyle
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.preferredWidthIn
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.example.jetsnack.R
import com.example.jetsnack.ui.theme.JetsnackTheme

@Composable
fun QuantitySelector(
    count: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (qty, minus, quantity, plus) = createRefs()
        createHorizontalChain(qty, minus, quantity, plus, chainStyle = ChainStyle.Packed)
        ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
            Text(
                text = stringResource(R.string.quantity),
                style = MaterialTheme.typography.subtitle1,
                color = JetsnackTheme.colors.textSecondary,
                modifier = Modifier.constrainAs(qty) {
                    start.linkTo(parent.start)
                    linkTo(top = parent.top, bottom = parent.bottom)
                }
            )
        }
        JetsnackGradientTintedIconButton(
            asset = Icons.Outlined.RemoveCircleOutline,
            onClick = decreaseItemCount,
            modifier = Modifier.constrainAs(minus) {
                centerVerticallyTo(quantity)
                linkTo(top = parent.top, bottom = parent.bottom)
            }
        )
        ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.high) {
            Text(
                text = "$count",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 18.sp,
                color = JetsnackTheme.colors.textPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.preferredWidthIn(min = 24.dp).constrainAs(quantity) {
                    baseline.linkTo(qty.baseline)
                }
            )
        }
        JetsnackGradientTintedIconButton(
            asset = Icons.Outlined.AddCircleOutline,
            onClick = increaseItemCount,
            modifier = Modifier.constrainAs(plus) {
                end.linkTo(parent.end)
                centerVerticallyTo(quantity)
                linkTo(top = parent.top, bottom = parent.bottom)
            }
        )
    }
}

@Preview
@Composable
fun QuantitySelectorPreview() {
    JetsnackTheme {
        JetsnackSurface {
            QuantitySelector(1, {}, {})
        }
    }
}
