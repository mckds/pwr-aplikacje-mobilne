import { NgModule } from '@angular/core';

import {
  MatButtonModule,
  MatMenuModule,
  MatToolbarModule,
  MatIconModule,
  MatCardModule,
  MatInputModule,
  MatSelectModule,
  MatButtonToggleModule, MatDatepickerModule, MatFormFieldModule, MatNativeDateModule,
} from '@angular/material';
import { FormsModule } from '@angular/forms';
import {MatListModule} from '@angular/material/list';

@NgModule({
  imports: [
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatButtonToggleModule,
    FormsModule,
    MatSelectModule,
    MatListModule,
  ],
  exports: [
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatInputModule,
    MatButtonToggleModule,
    FormsModule,
    MatSelectModule,
    MatListModule,
  ],
  declarations: []
})
export class MaterialModule {}
