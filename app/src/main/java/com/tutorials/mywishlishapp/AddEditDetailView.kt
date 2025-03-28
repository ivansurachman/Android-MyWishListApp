package com.tutorials.mywishlishapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState


import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tutorials.mywishlishapp.data.Wish
import kotlinx.coroutines.launch

/**
 * A Composable function that displays a view for adding or editing a wish.
 *
 * This function provides a UI for users to either create a new wish or modify an existing one.
 * It includes text fields for the wish's title and description, as well as a button to save the changes.
 *
 * @param modifier Modifier for styling and layout customization.
 * @param id The ID of the wish to edit. If 0L, a new wish will be created.
 * @param viewModel The [WishViewModel] instance for managing wish data.
 * @param navController The [NavController] for navigating back after saving.
 *
 * @see WishViewModel
 * @see Wish
 * @see AppBarView
 * @see WishTextField
 *
 * Functionality:
 * - **Edit Mode (id != 0L):**
 *   - Retrieves the wish data based on the provided `id` using `viewModel.getWishById(id)`.
 *   - Populates the text fields with the existing wish's title and description.
 *   - Sets the app bar title to "Update Wish".
 *   - Upon clicking the save button, updates the wish via `viewModel.updateWish()`.
 */
@Composable
fun AddEditDetailView(
    modifier: Modifier = Modifier,
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
){
    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    if (id != 0L){
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    } else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBarView(
                title =
                    if(id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(id = R.string.add_wish),
                onBackNavClicked = {
                    navController.navigateUp()
                }
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column (
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Title", value = viewModel.wishTitleState, onValueChanged = {
                viewModel.onWishTitleChange(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Description", value = viewModel.wishDescriptionState, onValueChanged = {
                viewModel.onWishDescriptionChange(it)
            })
            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()){
                    if (id != 0L){
                        //  UpdateWish
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                    } else{
                        // Add Wish
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Wish Added"
                    }

                } else{
                    // Enter fields for wish to be added
                    snackMessage.value = "Enter fields to add wish"
                }
                scope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            }) {
                Text(
                    text =
                        if(id != 0L) stringResource(id = R.string.update_wish)
                        else stringResource(id = R.string.add_wish),
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun WishTextField(
  label: String,
  value: String,
  onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            textColor = Color.Black,
            // using custom Color
            backgroundColor = colorResource(id = R.color.white),
            focusedBorderColor = colorResource(id = R.color.black),

            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black
        )
    )
}

@Preview (showBackground = true)
@Composable
fun AddEditDetailViewPreview(){
    WishTextField(label = "Title", value = "text", onValueChanged = {})
}