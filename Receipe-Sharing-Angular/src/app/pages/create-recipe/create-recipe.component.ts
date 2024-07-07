import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatPseudoCheckboxModule } from '@angular/material/core';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { RecipeService } from '../../service/recipe/recipe.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-create-recipe',
  standalone: true,
  imports: [
    MatRadioModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  templateUrl: './create-recipe.component.html',
  styleUrl: './create-recipe.component.scss',
})
export class CreateRecipeComponent {

  recipeItemForm = new FormGroup({
    title: new FormControl('', [Validators.required, Validators.email]),
    description: new FormControl('', [Validators.required]),
    foodType: new FormControl('', [Validators.required]),
    image: new FormControl('', [Validators.required]),
  });

  constructor(private recipeService:RecipeService,public dialog: MatDialog){}

  onSubmit() {
    this.createRecipe(this.recipeItemForm.value)
  }

  createRecipe(recipe: any): void {    
    this.recipeService.createRecipe(recipe)
      .subscribe(
        {next:(newRecipe: any) => {
          console.log('Recipe created:', newRecipe);
          this.recipeService.getRecipes();
          this.dialog.closeAll();
        },
        error:(error: any) => {
          console.error('Error creating recipe:', error);
        }}
      );
  }
}
